package ch.develop.milord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import ch.develop.milord.exception.DataNotFoundException;

public class MinigolfClub {

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
	
	
	
}
