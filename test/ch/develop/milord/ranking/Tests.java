package ch.develop.milord.ranking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ch.develop.milord.MinigolfClub;
import ch.develop.milord.Participation;
import ch.develop.milord.exception.DataNotFoundException;

public class Tests {

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
}
