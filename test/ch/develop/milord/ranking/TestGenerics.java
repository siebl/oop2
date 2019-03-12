package ch.develop.milord.ranking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ch.develop.milord.Category;
import ch.develop.milord.Elite;
import ch.develop.milord.Junior;
import ch.develop.milord.MinigolfClub;
import ch.develop.milord.Participation;
import ch.develop.milord.Senior;
import ch.develop.milord.Tournament;
import ch.develop.milord.exception.DataNotFoundException;

public class TestGenerics {

	private static MinigolfClub club;

	public static void testSetUp() throws DataNotFoundException {
		club = MinigolfClub.getInstance();
	}

	@BeforeAll
	private static void setUp() {
		try {
			testSetUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void test01() {
		assertEquals("pelf1", club.getTournamentList().get(1).getReferee().getId());
	}

	@Test
	void test02() throws DataNotFoundException {
		assertEquals(20, club.getTournamentScores(12234).get(0).getResult());
	}

	@Test
	void test03() throws DataNotFoundException {
		assertEquals(21, club.getTournamentScores(12234).get(1).getResult());
	}

	@Test
	void test04() throws DataNotFoundException {
		assertEquals(27, club.getTournamentScores(12234).get(2).getResult());
	}

	@Test
	void test05() throws DataNotFoundException {
		assertEquals(26, club.getTournamentScores(13341).get(0).getResult());
	}

	@Test
	void test06() throws DataNotFoundException {
		assertEquals(36, club.getTournamentScores(14202).get(0).getResult());
	}

	@Test
	void test07() throws DataNotFoundException {
		assertEquals(26, club.getTournamentScores(14448).get(0).getResult());
	}

	@Test
	void test08() throws DataNotFoundException {
		assertEquals(23, club.getTournamentScores(13956).get(1).getResult());
	}

	@Test
	void test09() throws DataNotFoundException {
		assertEquals(26, club.getTournamentScores(13956).get(2).getResult());
	}

	@Test
	void test10() throws DataNotFoundException {
		assertEquals(4, club.getPersonScores("julf1", 2018).size());
	}

	@Test
	void test11() throws DataNotFoundException {
		assertThrows(DataNotFoundException.class, () -> club.getTournamentScores(13958));
	}

	@Test
	void test12() throws DataNotFoundException {
		assertEquals(3, club.getPersonScores("elif1", 2019).size());
	}

	@Test
	void test13() throws DataNotFoundException {
		assertEquals(3, club.getPersonScores("elif1", 2019).size());
	}

	@Test
	void test15() throws DataNotFoundException {
		assertEquals(66, club.getParticipationList(2018).size());

	}

	@Test
	void test16() throws DataNotFoundException {
		assertEquals(25, club.getParticipationList(2019).size());
	}

	@Test
	void test17() throws DataNotFoundException {
		assertEquals(0, club.getParticipationList(2015).size());
	}

	@Test
	void test18() throws DataNotFoundException {
		assertThrows(DataNotFoundException.class, () -> club.findTournament(21015));
	}

	@Test
	void test19() throws DataNotFoundException {
		List<Participation> r = club.getPersonScores("self1", 2018);
		assertEquals(5, r.size());
	}

	@Test
	void test20() throws DataNotFoundException {
		List<Participation> r = club.getPersonScores("julf2", 2018);
		assertEquals(4, r.size());
	}

	@Test
	void test21() throws DataNotFoundException {
		List<Participation> r = club.getPersonScores("self1", 2018);
		assertEquals(5, r.size());
	}

	@Test
	void test22() throws DataNotFoundException {
		List<Participation> r = club.getPersonScores("self2", 2018);
		assertEquals(5, r.size());
	}

	@Test
	void test23() throws DataNotFoundException {
		List<Participation> r = club.getPersonScores("elif1", 2017);
		assertEquals(0, r.size());
	}

	@Test
	void test24() throws DataNotFoundException {
		assertThrows(DataNotFoundException.class, () -> club.getPersonScores("elif7", 2017));
	}

	@Test
	void test25() throws DataNotFoundException {
		assertThrows(DataNotFoundException.class, () -> club.getPersonScores("julf7", 2017));
	}

	@Test
	void test26() throws DataNotFoundException {
		Elite m = club.getEliteList().get(0);
		Tournament t = club.findTournament(16416);
		int r = club.getRanking(m, t, Category.ELITE);
		assertEquals(1, r);
	}

	@Test
	void test27() throws DataNotFoundException {
		Elite m = club.getEliteList().get(10); System.out.println(m);
		Tournament t = club.findTournament(12480);
		int r = club.getRanking(m, t, Category.ELITE);
		assertEquals(2, r);
	}

	@Test
	void test28() throws DataNotFoundException {
		Senior m = club.getSeniorList().get(1);
		Tournament t = club.findTournament(12234);
		int r = club.getRanking(m, t, Category.SENIOR);
		assertEquals(1, r);
	}

	@Test
	void test29() throws DataNotFoundException {
		double s = club.getScore(club.getEliteList().get(1), 2018, 2);
		assertEquals(21.5,s,0.1);
	}

	@Test
	void test30() throws DataNotFoundException {
		double s = club.getScore(club.getEliteList().get(8), 2018, 2);
		assertEquals(21.5,s,0.1);
	}

	@Test
	void test31() throws DataNotFoundException {
		double s = club.getScore(club.getSeniorList().get(1), 2018, 2);
		assertEquals(20.0,s,0.1);
	}
	
	@Test
	void test32() throws DataNotFoundException {
		Elite m = club.getEliteList().get(2);
		int r = club.getRanking(m, Category.ELITE, 2018);
		assertEquals(3, r);
	}

	@Test
	void test33() throws DataNotFoundException {
		Junior m = club.getJuniorList().get(4);
		int r = club.getRanking(m, Category.JUNIOR, 2018);
		assertEquals(5, r);
	}

	@Test
	void test34() throws DataNotFoundException {
		Senior m = club.getSeniorList().get(3);
		int r = club.getRanking(m, Category.SENIOR, 2019);
		assertEquals(1, r);
	}
}
