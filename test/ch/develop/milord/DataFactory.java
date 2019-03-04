package ch.develop.milord;

public class DataFactory{
	
	public static PersonDAO getPersonDAO() {
		return new PersonDAO();
	}

	public static ParticipationDAO getResultDAO() {
		return new ParticipationDAO();
	}

	public static TournamentDAO getTournamentDAO() {
		return new TournamentDAO();
	}
}
