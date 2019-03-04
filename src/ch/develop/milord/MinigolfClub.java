package ch.develop.milord;

import java.util.ArrayList;
import java.util.List;

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

	public static MinigolfClub getInstance() {
		if (instance == null) {
			instance = new MinigolfClub();
			DataFactory.getPersonDAO().loadPersons();
			DataFactory.getTournamentDAO().loadTournaments();
			DataFactory.getResultDAO().loadResults();
		}
		return instance;
	}

	public Person findPerson(String id) {
		for (Person p : personList) {
			if (p.getId().equals(id))
				return p;
		}
		return null;
	}
	
	
	public Tournament findTournament(int id) {
		for (Tournament t : tournamentList) {
			if (t.getId() == id)
				return t;
		}
		return null;
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

}
