package ch.develop.mibo.data;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Logger;

import ch.develop.mibo.utils.DataNotFoundException;

public class ParticipationDAO {

	private static final Logger logger = Logger.getLogger(ParticipationDAO.class.getName());
	private MinigolfClub club;

	public void loadParticipations(int year) throws DataNotFoundException {
		log("load results");
		club = MinigolfClub.getInstance();
		try {
			int k = year == 2018 ? 0 : 1;
			for (int i = 0; i < resultString[k].length; i++) {
				String[] tokens = resultString[k][i].split(";");
				int toId = Integer.parseInt(tokens[0]);
				Tournament tournament = club.findTournament(toId);
				Member member = findMember(tokens[1]);
				double score = Double.parseDouble(tokens[2]);
				Category category = getEnumFromString(tokens[3]);
				Participation p = new Participation(member, tournament, score, category);
				SortedSet<Participation> setT = club.getParticipationsPerTournament().get(toId);
				if (setT == null)
					setT = new TreeSet<Participation>();
				setT.add(p);
				club.getParticipationsPerTournament().put(toId, setT);
				SortedSet<Participation> setP = club.getParticipationsPerPerson().get(tokens[1]);
				if (setP == null)
					setP = new TreeSet<Participation>();
				setP.add(p);
				club.getParticipationsPerPerson().put(tokens[1], setP);
			}
		} catch (NumberFormatException e) {
			System.out.println("Result String ist korrupt");
		}
	}

	private Member findMember(String id) throws DataNotFoundException {
		return (Member) club.getPersonList().get(id);
	}

	private Category getEnumFromString(String string) {
		if (string != null) {
			return Enum.valueOf(Category.class, string.trim());
		}
		return null;
	}

	private void log(String stmt) {
		String text = stmt.toString();
		logger.info(text.substring(text.indexOf(":") + 1));
	}

	public String[][] resultString = { { "12234;elim2;20.0;ELITE_MALE", "12480;elim4;21.2;ELITE_MALE",
			"14571;elif1;22.3;ELITE_FEMALE", "12234;elif3;21.1;ELITE_FEMALE", "12480;elif5;25.3;ELITE_FEMALE",
			"12480;elif3;22.4;ELITE_FEMALE", "14571;elif6;25.2;ELITE_FEMALE", "14571;self1;25.1;SENIOR",
			"14817;self2;35.1;SENIOR", "14940;self3;35.2;SENIOR", "12603;self5;21.5;SENIOR", "12972;julf1;19.5;JUNIOR",
			"13095;julf2;22.5;JUNIOR", "14202;julf5;36.1;JUNIOR", "14448;elif1;26.2;ELITE_FEMALE",
			"14817;self5;26.3;SENIOR", "15924;elif3;26.4;ELITE_FEMALE", "12849;elif4;26.5;ELITE_FEMALE",
			"13341;elif5;25.1;ELITE_FEMALE", "13464;self1;24.2;SENIOR", "15063;julf1;24.1;JUNIOR",
			"15801;julf2;23.1;JUNIOR", "15924;julf3;22.1;JUNIOR", "16170;julf4;23.3;JUNIOR", "16293;julf5;26.1;JUNIOR",
			"12234;elif3;27.2;ELITE_FEMALE", "12726;elif4;21.3;ELITE_FEMALE", "12726;elif5;27.4;ELITE_FEMALE",
			"12234;elim3;27.5;ELITE_MALE", "12726;elim4;21.6;ELITE_MALE", "12234;self4;27.7;SENIOR",
			"12726;elim5;27.8;ELITE_MALE", "13095;self1;27.9;SENIOR", "13218;self2;21.0;SENIOR",
			"14202;self3;27.1;SENIOR", "14325;self4;27.2;SENIOR", "14817;self5;22.3;SENIOR", "14940;julf1;27.4;JUNIOR",
			"15186;julf2;27.5;JUNIOR", "12603;julf3;25.1;JUNIOR", "12726;julf4;25.2;JUNIOR", "12726;julf5;24.7;JUNIOR",
			"15186;elif2;19.2;ELITE_FEMALE", "15186;elif3;22.7;ELITE_FEMALE", "15555;elif4;23.7;ELITE_FEMALE",
			"16047;elif5;24.7;ELITE_FEMALE", "15186;elim2;25.7;ELITE_MALE", "15186;elim3;26.7;ELITE_MALE",
			"15555;elim4;23.7;ELITE_MALE", "16047;elim5;22.7;ELITE_MALE", "16047;self1;21.3;SENIOR",
			"12480;self2;22.8;SENIOR", "12234;self2;24.3;SENIOR", "12480;self3;28.3;SENIOR", "16170;julf1;28.4;JUNIOR",
			"16293;julf2;28.5;JUNIOR", "13095;julf3;28.6;JUNIOR", "15432;julf4;28.7;JUNIOR",
			"14079;elif2;29.1;ELITE_FEMALE", "14571;elif3;29.2;ELITE_FEMALE", "14817;elif4;29.3;ELITE_FEMALE",
			"14079;elim2;23.4;ELITE_MALE", "14571;elim3;25.5;ELITE_MALE", "14817;elim4;22.6;ELITE_MALE",
			"15555;self1;21.7;SENIOR", "12480;self2;19.8;SENIOR" },
			{ "16416;elim1;20;ELITE_MALE", "16416;elif2;23;ELITE_FEMALE", "16416;self4;45;SENIOR",
					"16416;elim1;26;ELITE_MALE", "16416;elif1;26;ELITE_FEMALE", "13587;self2;26;SENIOR",
					"13587;self4;28;SENIOR", "13587;self5;28;SENIOR", "13587;julf5;29;JUNIOR", "13710;self3;26;SENIOR",
					"13710;elim1;25;ELITE_MALE", "13710;elif1;29;ELITE_FEMALE", "13833;julf3;26;JUNIOR",
					"13833;self3;39;SENIOR", "13833;self4;30;SENIOR", "13833;elif1;24;ELITE_FEMALE",
					"13956;elim3;21;ELITE_MALE", "13956;elif4;23;ELITE_FEMALE", "13956;julf4;26;JUNIOR",
					"13956;elif1;23;ELITE_FEMALE", "13956;elim1;27;ELITE_MALE", "13956;self4;26;SENIOR",
					"16539;elif2;26;ELITE_FEMALE", "16539;elim2;26;ELITE_MALE", "16539;self5;23;SENIOR",
					"14694;elif2;24;ELITE_FEMALE", "14694;elim2;24;ELITE_MALE", "14694;julf2;26;JUNIOR",
					"14694;self4;26;SENIOR", "15309;elif2;26;ELITE_FEMALE", "15309;elim5;19;ELITE_MALE",
					"15309;elif5;29;ELITE_FEMALE" } };
}
