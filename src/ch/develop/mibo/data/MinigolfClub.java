package ch.develop.mibo.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

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
		// ToDo
		throw new DataNotFoundException("Person", personId);
	}

	/**
	 * @param tournamentId: the tournament id
	 * @return tournament with this id
	 * @throws DataNotFoundException
	 */
	public Tournament findTournament(int tournamentId) throws DataNotFoundException {
		// ToDo
		throw new DataNotFoundException("Tournament", tournamentId);
	}

	
	/**
	 * @param tournamentId: the tournament id
	 * @return sorted set of participations of this tournament
	 * @throws DataNotFoundException
	 */
	public SortedSet<Participation> getTournamentScores(int tournamentId) throws DataNotFoundException {
		// ToDo
		throw new DataNotFoundException("TournamentScores: " + tournamentId);
	}
	

	/**
	 * @param tournamentId: the tournament id
	 * @param category
	 * @return sorted set of participations of this tournament in this category
	 * @throws DataNotFoundException
	 */
	public SortedSet<Participation> getTournamentScores(int tournamentId, Category category) throws DataNotFoundException {
		// ToDo
		throw new DataNotFoundException("TournamentScores: " + tournamentId + " " +  category);
	}
	
	
	/**
	 * @param personId : the person id
	 * @param year
	 * @return sorted set of participations of this person and this year
	 * @throws DataNotFoundException
	 */
	public SortedSet<Participation> getPersonScores(String personId, int year) throws DataNotFoundException {
		// ToDo
		return null;
	}


	/**
	 * @param member
	 * @param year
	 * @param q
	 * @return return average of the q best scores of the year
	 * @throws DataNotFoundException
	 */
	public <T extends Member> double getScore(T member, int year, int q) throws DataNotFoundException {
		// ToDo
		return 200;
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
		// ToDo
		throw new DataNotFoundException(member, tournament, category);
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
