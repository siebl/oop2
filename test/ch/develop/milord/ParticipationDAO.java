package ch.develop.milord;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import ch.develop.milord.exception.DataNotFoundException;

public class ParticipationDAO {

	private static final Logger logger = Logger.getLogger(ParticipationDAO.class.getName());

	public void loadResults() throws DataNotFoundException {
		log("load results");
		MinigolfClub dataManager = MinigolfClub.getInstance();
		try {
			for (int i = 0; i < resultString.length; i++) {
				String[] tokens = resultString[i].split(";");
				int toId = Integer.parseInt(tokens[0]);
				Tournament tournament = dataManager.findTournament(toId);
				Member member = findMember(tokens[1]);
				int score = Integer.parseInt(tokens[2]);
				Category category = getEnumFromString(tokens[3]);
				dataManager.getParticipationList().add(new Participation(member, tournament, score, category));
			}
		} catch (NumberFormatException e) {
			System.out.println("Result String ist korrupt");
		}
	}

	private Member findMember(String id) throws DataNotFoundException {
		MinigolfClub dataManager = MinigolfClub.getInstance();
		List<Member> ms = dataManager.getMemberList();
		List<Member> ps = ms.stream().filter(e -> e.getId().equals(id)).collect(Collectors.toList());
		return ps.get(0);
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

	public String[] resultString = { "16416;elim1;20;ELITE", "12234;elim2;20;ELITE", "13956;elim3;21;ELITE",
			"12480;elim4;21;ELITE", "14571;elif1;22;ELITE", "16416;elif2;23;ELITE", "12234;elif3;21;ELITE",
			"13956;elif4;23;ELITE", "12480;elif5;25;ELITE", "12480;elif3;22;ELITE", "14571;elif6;25;ELITE",
			"14571;self1;25;SENIOR", "14817;self2;45;SENIOR", "14940;self3;35;SENIOR", "16416;self4;45;SENIOR",
			"12603;self5;21;SENIOR", "12972;julf1;19;JUNIOR", "13095;julf2;22;JUNIOR", "13833;julf3;26;JUNIOR",
			"13956;julf4;26;JUNIOR", "14202;julf5;36;JUNIOR", "14448;elif1;26;ELITE", "15309;elif2;26;ELITE",
			"15924;elif3;26;ELITE", "12849;elif4;26;ELITE", "13341;elif5;26;ELITE", "13464;self1;26;SENIOR",
			"13587;self2;26;SENIOR", "13710;self3;26;SENIOR", "13956;self4;26;SENIOR", "14817;self5;26;SENIOR",
			"15063;julf1;26;JUNIOR", "15801;julf2;26;JUNIOR", "15924;julf3;26;JUNIOR", "16170;julf4;26;JUNIOR",
			"16293;julf5;26;JUNIOR", "16416;elif1;26;ELITE", "16539;elif2;26;ELITE", "12234;elif3;27;ELITE",
			"12726;elif4;21;ELITE", "12726;elif5;27;ELITE", "16416;elim1;26;ELITE", "16539;elim2;26;ELITE",
			"12234;elim3;27;ELITE", "12726;elim4;21;ELITE", "12234;self4;27;SENIOR", "12726;elim5;27;ELITE",
			"13095;self1;27;SENIOR", "13218;self2;21;SENIOR", "14202;self3;27;SENIOR", "14325;self4;27;SENIOR",
			"14817;self5;22;SENIOR", "14940;julf1;27;JUNIOR", "15186;julf2;27;JUNIOR", "12603;julf3;27;JUNIOR",
			"12726;julf4;27;JUNIOR", "12726;julf5;27;JUNIOR", "13956;elif1;23;ELITE", "15186;elif2;19;ELITE",
			"15186;elif3;27;ELITE", "15555;elif4;27;ELITE", "16047;elif5;27;ELITE", "13956;elim1;27;ELITE",
			"15186;elim2;27;ELITE", "15186;elim3;27;ELITE", "15555;elim4;27;ELITE", "16047;elim5;27;ELITE",
			"16047;self1;21;SENIOR", "12480;self2;28;SENIOR", "12234;self2;24;SENIOR", "12480;self3;28;SENIOR",
			"13587;self4;28;SENIOR", "13587;self5;28;SENIOR", "16170;julf1;28;JUNIOR", "16293;julf2;28;JUNIOR",
			"13095;julf3;28;JUNIOR", "15432;julf4;28;JUNIOR", "13587;julf5;29;JUNIOR", "13710;elif1;29;ELITE",
			"14079;elif2;29;ELITE", "14571;elif3;29;ELITE", "14817;elif4;29;ELITE", "15309;elif5;29;ELITE",
			"13710;elim1;25;ELITE", "14079;elim2;23;ELITE", "14571;elim3;25;ELITE", "14817;elim4;22;ELITE",
			"15309;elim5;19;ELITE", "15555;self1;21;SENIOR", "12480;self2;19;SENIOR", "13833;self3;39;SENIOR" };
}
