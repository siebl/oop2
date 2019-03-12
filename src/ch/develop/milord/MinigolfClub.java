package ch.develop.milord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.stream.Collectors;

import ch.develop.milord.comparator.ResultMapComparator;
import ch.develop.milord.exception.DataNotFoundException;

public class MinigolfClub {
	
	public static final Double sfTooFewResultsValue = Double.MAX_VALUE;
	public static final int sfDefaultNumberOfGames = 3;

	private List<Person> personList;
	private List<Member> memberList;
	private List<Junior> juniorList;
	private List<Senior> seniorList;
	private List<Elite> eliteList;
	private List<Tournament> tournamentList;
	private List<Participation> participationList;
	private static MinigolfClub instance;

	private MinigolfClub() {
		personList = new ArrayList<>();
		memberList = new ArrayList<>();
		juniorList = new ArrayList<>();
		seniorList = new ArrayList<>();
		eliteList = new ArrayList<>();
		tournamentList = new ArrayList<>();
		participationList  = new ArrayList<>();
	}

	public static MinigolfClub getInstance() throws DataNotFoundException {
		if (instance == null) {
			instance = new MinigolfClub();
			DataFactory.getPersonDAO().loadPersons();
			DataFactory.getTournamentDAO().loadTournaments();
			DataFactory.getResultDAO().loadResults();
		}
		return instance;
	}

	public Person findPerson(String id) throws DataNotFoundException {
		try {
			return personList.stream().filter(p -> p.equals(id) ).findFirst().get();
		}
		catch(NoSuchElementException e) {
			throw new DataNotFoundException();
		}
	}
	
	
	public Tournament findTournament(int id) throws DataNotFoundException {
		try {
			return tournamentList.stream().filter( p -> p.equals(id) ).findFirst().get();
		}
		catch(NoSuchElementException e) {
			throw new DataNotFoundException();
		}
	}
	
	public List<Person> getPersonList() {
		return personList;
	}
	
	public List<Member> getMemberList() {
		return memberList;
	}

	public List<Junior> getJuniorList() {
		return juniorList;
	}

	public List<Senior> getSeniorList() {
		return seniorList;
	}

	public List<Elite> getEliteList() {
		return eliteList;
	}

	public List<Tournament> getTournamentList() {
		return tournamentList;
	}

	public List<Participation> getParticipationList() {
		return participationList;
	}
	
	public List<Participation> getParticipations(ParticipationFilter filter) {
		return participationList.stream().filter( filter ).collect(Collectors.toList());
	}
	
	/**
	 * Do not use this shitty method! Use {@link MinigolfClub#getParticipations(ParticipationFilter)} instead.
	 * @throws DataNotFoundException 
	 */
	@Deprecated
	public List<Participation> getTournamentScores(int i) throws DataNotFoundException {
		ParticipationFilter filter = new ParticipationFilter();
		filter.setTournamentId(i);
		List<Participation> result = getParticipations(filter);
		if( result.isEmpty() ) {
			throw new DataNotFoundException();
		}
		return result;
	}

	/**
	 * Do not use this shitty method! Use {@link MinigolfClub#getParticipations(ParticipationFilter)} instead.
	 * @throws DataNotFoundException 
	 */
	@Deprecated
	public List<Participation> getPersonScores(String string, int i) throws DataNotFoundException {
		ParticipationFilter filter = new ParticipationFilter();
		Person p = findPerson(string);
		if((p instanceof Member) == false ) {
			throw new DataNotFoundException();
		}
		filter.setMember((Member) p);
		filter.setDateFrom(LocalDate.of(i-1, 12, 31));
		filter.setDateTo(LocalDate.of(i+1, 1, 1));
		List<Participation> result = getParticipations(filter);
		return result;
	}

	/**
	 * Do not use this shitty method! Use {@link MinigolfClub#getParticipations(ParticipationFilter)} instead.
	 * @throws DataNotFoundException 
	 */
	@Deprecated
	public List<Participation> getParticipationList(int i) throws DataNotFoundException {
		ParticipationFilter filter = new ParticipationFilter();
		filter.setDateFrom(LocalDate.of(i-1, 12, 31));
		filter.setDateTo(LocalDate.of(i+1, 1, 1));
		List<Participation> result = getParticipations(filter);
		return result;
	}
	
	public Map<Member, List<Double>> getResults( List<Participation> participations ) {
		Map<Member, List<Double>> memberResults = new HashMap<>();
		
		participations.forEach( p -> {
			if( memberResults.containsKey(p.getMember()) == false ) {
				memberResults.put( p.getMember(), new ArrayList<>() );
			}
			memberResults.get(p.getMember()).add(p.getResult());
		} );
		
		return memberResults;
	}
	
	public Map<Member, List<Double>> getResults( ParticipationFilter filter ) {
		List<Participation> list = getParticipations( filter );
		return getResults(list);
	}
	
	public Double getScore( List<Double> results, int numberOfGames ) {
		if( results.size() < numberOfGames ) {
			return sfTooFewResultsValue;
		}
		if( numberOfGames == -1 ) {
			numberOfGames = results.size();
		}
		results.sort( new Comparator<Double>() {
			@Override
			public int compare(Double o1, Double o2) {
				return (-1 * o1.compareTo(o2) );
			}
		} );

		double score = 0d;
		for( int i = 0; i < numberOfGames; i++ ) {
			score += results.get(i);
		}
		return score/numberOfGames;
	}
	
	/**
	 * Returns a sorted map of participants with their score.
	 * @param filter How to filter the Tournaments
	 * @param numberOfGames How many games are counted for the total score
	 * @return TreeMap containing Member as a key and their score as a value. Map is sorted in the right order.
	 */
	public TreeMap<Member, Double> getScoreMap( ParticipationFilter filter, int numberOfGames ) {
		Map<Member, List<Double>> resultMap = getResults(filter);
		Map<Member, Double> scoreMap = new HashMap<>();
		ResultMapComparator comparator = new ResultMapComparator(scoreMap);
		TreeMap<Member, Double> sortedMap = new TreeMap<>(comparator);
		
		resultMap.forEach( (k, v) -> {
			scoreMap.put( k, getScore( v, numberOfGames ) );
		} );
		
		sortedMap.putAll(scoreMap);
		return sortedMap;
	}
	
	public Category getCategory(Member member) {
		Category cat = null;
		if( member instanceof Junior ) {
			cat = Category.JUNIOR;
		} else if( member instanceof Elite ) {
			cat = Category.ELITE;
		} else if( member instanceof Senior ) {
			cat = Category.SENIOR;
		}
		return cat;
	}
	
	public <T extends Member> double getScore( T member, LocalDate from, LocalDate to, int number ) {
		Category cat = getCategory(member);
		ParticipationFilter filter = new ParticipationFilter();
		filter.setCategory( cat );
		filter.setDateFrom( from );
		filter.setDateTo( to );
		Map<Member, Double> results = getScoreMap(filter, number);
		return results.get(member);
	}
	
	/**
	 * Use {@link MinigolfClub#getScore} instead.
	 */
	@Deprecated
	public <T extends Member> double getScore( T member, int year, int number ) {
		LocalDate from = LocalDate.of( year-1, 12, 31 );
		LocalDate to = LocalDate.of( year + 1, 1, 1 );
		return getScore( member, from, to, number );
	}
	
	private int getRanking(Member m, TreeMap<Member, Double> scoreMap) {
		int i = 0;
		Iterator<Entry<Member, Double>> iterator = scoreMap.entrySet().iterator();
		while( iterator.hasNext() ) {
			Entry<Member, Double> entry = iterator.next();
			if( entry.getKey().equals( m ) ) {
				return i; //could add logic to increase rank if multiple people reached the same score, too lazy
			}
			i++;
		}
		return -1;
	}

	public int getRanking(Member m, Tournament t, Category category) {
		ParticipationFilter filter = new ParticipationFilter();
		filter.setTournament( t );
		filter.setCategory(category);
		TreeMap<Member, Double> scoreMap = getScoreMap(filter, -1);
		return getRanking(m, scoreMap);
	}

	public int getRanking(Member m, Category c, int year) {
		LocalDate from = LocalDate.of(year-1, 12, 31);
		LocalDate to = LocalDate.of(year+1, 1, 1);
		ParticipationFilter filter = new ParticipationFilter();
		filter.setCategory(c);
		filter.setDateFrom(from);
		filter.setDateTo(to);
		TreeMap<Member, Double> scoreMap = getScoreMap(filter, sfDefaultNumberOfGames);
		return getRanking(m, scoreMap);
	}
	
}
