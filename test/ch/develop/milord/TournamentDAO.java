package ch.develop.milord;

import java.time.LocalDate;
import java.util.logging.Logger;

import ch.develop.milord.exception.DataNotFoundException;

public class TournamentDAO {

	private static final Logger logger = Logger.getLogger(TournamentDAO.class.getName());

	public TournamentDAO() {

	}

	public void loadTournaments() throws DataNotFoundException {
		log("load tournaments");
		MinigolfClub dataManager = MinigolfClub.getInstance();
		try {
			for (int i = 0; i < tournamentString.length; i++) {
				String[] tokens = tournamentString[i].split(";");
				int id = Integer.parseInt(tokens[0]);
				Person person = findPerson(tokens[4]);
				dataManager.getTournamentList()
						.add(new Tournament(id, getDate(tokens[1], tokens[2], tokens[3]), person));
			}
		} catch (NumberFormatException e) {
			System.out.println("Tournament String ist korrupt");
		}
	}

	private LocalDate getDate(String d, String m, String y) {
		int day = Integer.parseInt(d);
		int month = Integer.parseInt(m);
		int year = Integer.parseInt(y);
		return LocalDate.of(year, month, day);
	}

	private Person findPerson(String id) throws DataNotFoundException {
		for (Person p : MinigolfClub.getInstance().getPersonList()) {
			if (p.getId().equals(id))
				return p;
		}
		return null;
	}

	private void log(String stmt) {
		String text = stmt.toString();
		logger.info(text.substring(text.indexOf(":") + 1));
	}

	private String[] tournamentString = { "12234;20;3;2018;pelf1", "12357;13;4;2018;pelf1", "12480;1;5;2018;pelf1",
			"12603;20;5;2018;pelf1", "12726;30;5;2018;pelf2", "12849;3;6;2018;pelf2", "12972;15;6;2018;pelf2",
			"13095;26;6;2018;pelf2", "13218;5;7;2018;pelf2", "13341;12;7;2018;pelf2", "13464;1;8;2018;pelf2",
			"13587;11;1;2019;pelf2", "13710;2;2;2019;pelf2", "13833;20;2;2019;pelf3", "13956;1;1;2019;pelf3",
			"14079;21;10;2018;pelf3", "14202;5;11;2018;pelf3", "14325;2;3;2018;pelf3", "14448;14;4;2018;pelf3",
			"14571;2;5;2018;pelf4", "14694;21;2;2019;pelf5", "14817;31;5;2018;pelf4", "14940;4;5;2018;pelf5",
			"15063;16;6;2018;pelf4", "15186;27;6;2018;pelf5", "15309;6;7;2019;pelf4", "15432;13;7;2018;pelf5",
			"15555;2;8;2018;pelf4", "15678;12;8;2018;pelf5", "15801;3;9;2018;pelf4", "15924;21;9;2018;pelf5",
			"16047;2;10;2018;pelf4", "16170;22;10;2018;pelf5", "16293;6;11;2018;pelf4", "16416;3;3;2019;pelf5",
			"16539;15;3;2019;pelf4" };
}
