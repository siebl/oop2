package ch.develop.mibo.data;

public class DataFactory{
	
	public static PersonDAO getPersonDAO() {
		return new PersonDAO();
	}

	public static ParticipationDAO getParticipationDAO() {
		return new ParticipationDAO();
	}

	public static TournamentDAO getTournamentDAO() {
		return new TournamentDAO();
	}
}
