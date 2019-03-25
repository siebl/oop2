package ch.develop.mibo.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ch.develop.mibo.utils.DataNotFoundException;
import ch.develop.mibo.utils.RankingEntry;

public class MinigolfClub {

	// Map aus PersonId und Person
	private HashMap<String, Person> personList;

	// Map mit Kategorie und Liste von Members
	private HashMap<Category, ArrayList<? extends Member>> memberMap;

	// Map mit TournierID und sortierte Participations
	private HashMap<Integer, SortedSet<Participation>> participationsPerTournament;

	// Map mit PersonID und sortierte Participations
	private HashMap<String, SortedSet<Participation>> participationsPerPerson;

	// Map aus TournamentId und Tournament
	private HashMap<Integer, Tournament> tournamentList;

	private static MinigolfClub instance;

	private MinigolfClub() {
		personList = new HashMap<>();
		memberMap = new HashMap<Category, ArrayList<? extends Member>>();
		participationsPerTournament = new HashMap<Integer, SortedSet<Participation>>();
		participationsPerPerson = new HashMap<String, SortedSet<Participation>>();
		tournamentList = new HashMap<>();
	}

	public static MinigolfClub getInstance() throws DataNotFoundException {
		if (instance == null) {
			instance = new MinigolfClub();
			DataFactory.getPersonDAO().loadPersons();
			DataFactory.getTournamentDAO().loadTournaments();
			DataFactory.getParticipationDAO().loadParticipations(2018);
		}
		return instance;
	}

	/**
	 * @param personId: the person id
	 * @return person with this id
	 * @throws DataNotFoundException
	 */
	public Person findPerson(String personId) throws DataNotFoundException {
		Person result = personList.get(personId);
		if( result == null ) {
			throw new DataNotFoundException("Person", personId);
		}
		return result;
	}

	/**
	 * @param tournamentId: the tournament id
	 * @return tournament with this id
	 * @throws DataNotFoundException
	 */
	public Tournament findTournament(int tournamentId) throws DataNotFoundException {
		Tournament result = tournamentList.get( tournamentId );
		if( result == null ) {
			throw new DataNotFoundException("Tournament", tournamentId);
		}
		return result;
	}

	
	/**
	 * @param tournamentId: the tournament id
	 * @return sorted set of participations of this tournament
	 * @throws DataNotFoundException
	 */
	public SortedSet<Participation> getTournamentScores(int tournamentId) throws DataNotFoundException {
		SortedSet<Participation> result = participationsPerTournament.get(tournamentId);
		if( result == null ) {
			throw new DataNotFoundException("TournamentScores: " + tournamentId);
		}
		return result;
	}
	

	/**
	 * @param tournamentId: the tournament id
	 * @param category
	 * @return sorted set of participations of this tournament in this category
	 * @throws DataNotFoundException
	 */
	public SortedSet<Participation> getTournamentScores(int tournamentId, Category category) throws DataNotFoundException {
		SortedSet<Participation> participations = participationsPerTournament.get( tournamentId );
		if( participations == null ) {
			throw new DataNotFoundException("TournamentScores: " + tournamentId + " " +  category);
		}
		return participations;
	}
	
	
	/**
	 * @param personId : the person id
	 * @param year
	 * @return sorted set of participations of this person and this year
	 * @throws DataNotFoundException
	 */
	public SortedSet<Participation> getPersonScores(String personId, int year) throws DataNotFoundException {
		participationsPerPerson.clear();
		DataFactory.getParticipationDAO().loadParticipations(year);
		SortedSet<Participation> result1 = participationsPerPerson.get( personId );
		if( result1 == null ) {
			throw new DataNotFoundException();
		}
		Set<Participation> temp = result1.stream().filter( new Predicate<Participation>() {
			@Override
			public boolean test(Participation t) {
				return t.getTournament().getDate().getYear() == year;
			}
		}).collect(Collectors.toSet());
		if(temp.isEmpty()) {
			throw new DataNotFoundException();
		}
		
		SortedSet<Participation> result2 = new TreeSet<>(temp);
		if( result2.isEmpty() ) {
			throw new DataNotFoundException();
		}
		return result2;
	}


	/**
	 * @param member
	 * @param year
	 * @param q
	 * @return return average of the q best scores of the year
	 * @throws DataNotFoundException
	 */
	public <T extends Member> double getScore(T member, int year, int q) throws DataNotFoundException {
		participationsPerPerson.clear();
		DataFactory.getParticipationDAO().loadParticipations(year);
		Set<Participation> participationSet = participationsPerPerson.get(member.getId());
		
		if( participationSet == null ) {
			throw new DataNotFoundException();
		}
		
		double score = 200;
		if( participationSet.size() > q ) {
			score = 0;
			Iterator<Participation> iter = participationSet.iterator();
			for( int i = 0; i < q; i++ ) {
				score += iter.next().getResult();
			}
			score /= q;
		}
		
		return score;
	}
	
	private int getRanking( Member m, SortedSet<Participation> participationSet ) throws DataNotFoundException {
		int rank = 0;
		Iterator<Participation> iter = participationSet.iterator();
		while( iter.hasNext() ) {
			rank++;
			Participation current = iter.next();
			if( current.getMember().equals(m) ) {
				return rank;
			}
		}
		throw new DataNotFoundException();
	}

	/**
	 * @param member: the miniclub member (senior, junior, elite)
	 * @param tournament: the tournament
	 * @param category: the category of the member
	 * @return ranking of this member in this tournament and category
	 * @throws DataNotFoundException
	 */
	public <T extends Member> int getRanking(T member, Tournament tournament, Category category)
			throws DataNotFoundException {
		SortedSet<Participation> tournamentScores = getTournamentScores(tournament.getId(), category);
		SortedSet<Participation> filtered = new TreeSet<>(tournamentScores.stream().filter( new Predicate<Participation>() {
			@Override
			public boolean test(Participation t) {
				return t.getCategory() == category;
			}
		}).collect(Collectors.toSet()) );
		
		return getRanking(member, filtered);
	}

	/**
	 * @param member: the minigolf club member (senior, junior, ..)
	 * @param category: the category of the member
	 * @param year: the members ranking year
	 * @return
	 * @throws DataNotFoundException
	 */
	public <T extends Member> int getRanking(T member, Category category, int year) 
			throws DataNotFoundException {
		RankingEntry<T> sre = new RankingEntry<T>(getScore(member, year, 3), member);
		SortedSet<RankingEntry<T>> rankingList = new TreeSet<>();
		for (Member s : memberMap.get(category)) {
			rankingList.add(new RankingEntry<T>(getScore(s, year, 3), (T) s));
		}
		return rankingList.headSet(sre).size() + 1;
	}

	public HashMap<String, Person> getPersonList() {
		return personList;
	}

	@SuppressWarnings("unchecked")
	public <T extends Member> List<T> getMembers(Category c) {
		return (List<T>) memberMap.get(c);
	}

	public HashMap<Integer, Tournament> getTournamentList() {
		return tournamentList;
	}

	public Map<Integer, SortedSet<Participation>> getParticipationsPerTournament() {
		return participationsPerTournament;
	}

	public HashMap<String, SortedSet<Participation>> getParticipationsPerPerson() {
		return participationsPerPerson;
	}

	public Map<Category, ArrayList<? extends Member>> getMemberMap() {
		return memberMap;
	}
}
