package ch.develop.milord;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
		assertEquals(31, club.getPersonList().size());
	}

	@Test
	void test02() {
		assertEquals(6, club.getJuniorList().size());
	}

	@Test
	void test03() {
		assertEquals(6, club.getSeniorList().size());
	}

	@Test
	void test04() {
		assertEquals(12, club.getEliteList().size());
	}

	@Test
	void test05() {
		assertEquals(36, club.getTournamentList().size());
	}

	@Test
	void test06() {
		assertEquals("self4", club.getJuniorList().get(0).getTrainer().getId());
	}

	@Test
	void test07() {
		assertEquals("self4", club.getJuniorList().get(1).getTrainer().getId());
	}

	@Test
	void test08() {
		assertEquals("self4", club.getJuniorList().get(3).getTrainer().getId());
	}

	@Test
	void test09() {
		assertEquals("pelf4", club.getJuniorList().get(2).getSupervisor().getId());
	}

	@Test
	void test10() {
		assertEquals("pelf4", club.getJuniorList().get(4).getSupervisor().getId());
	}

	@Test
	void test11() {
		assertEquals("pelf1", club.getEliteList().get(2).getTrainer().getId());
	}

	@Test
	void test12() {
		assertEquals("pelf1", club.getEliteList().get(1).getTrainer().getId());
	}

	@Test
	void test13() {
		assertEquals("pelf1", club.getEliteList().get(4).getTrainer().getId());
	}

	@Test
	void test14() {
		assertEquals("pelf2", club.getSeniorList().get(2).getTrainer().getId());
	}

	@Test
	void test15() throws DataNotFoundException {
		assertEquals("PerName1", club.findPerson("pelf1").getLastName());
	}

	@Test
	void test16() throws DataNotFoundException {
		assertEquals("FirstName1", club.findPerson("pelf1").getFirstName());
	}

	@Test
	void test17() throws DataNotFoundException {
		assertEquals("PerName3", club.findPerson("pelf3").getLastName());
	}

	@Test
	void test18() {
		assertEquals("Peter5", club.getEliteList().get(4).getFirstName());
	}

	@Test
	void test19() {
		assertEquals(91, club.getParticipationList().size());
	}

	@Test
	void test20() {
		assertEquals(21.0, club.getParticipationList().get(2).getResult(), 0.01);

	}

	@Test
	void test21() {
		assertEquals(35.0, club.getParticipationList().get(13).getResult(), 0.01);
	}

	@Test
	void test22() {
		assertEquals(26.0, club.getParticipationList().get(37).getResult(), 0.01);
	}

	@Test
	void test23() {
		assertEquals(13956, club.getParticipationList().get(2).getTournament().getId());

	}

	@Test
	void test24() {
		assertEquals(15186, club.getParticipationList().get(53).getTournament().getId());
	}

	@Test
	void test25() {
		assertEquals("julf4", club.getParticipationList().get(34).getMember().getId());
	}

	@Test
	void test26() {
		assertEquals("self2", club.getParticipationList().get(12).getMember().getId());
	}
}
