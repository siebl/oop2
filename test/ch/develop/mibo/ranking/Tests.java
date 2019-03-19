package ch.develop.mibo.ranking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ch.develop.mibo.data.Category;
import ch.develop.mibo.data.Junior;
import ch.develop.mibo.data.Member;
import ch.develop.mibo.data.MinigolfClub;
import ch.develop.mibo.data.Participation;
import ch.develop.mibo.data.Tournament;
import ch.develop.mibo.utils.DataNotFoundException;

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
		assertEquals(6, club.getMembers(Category.JUNIOR).size());
	}

	@Test
	void test03() {
		assertEquals(6, club.getMembers(Category.SENIOR).size());
	}

	@Test
	void test04() {
		assertEquals(6, club.getMembers(Category.ELITE_FEMALE).size());
	}

	@Test
	void test05() {
		assertEquals(36, club.getTournamentList().size());
	}

	@Test
	void test06() {
		assertEquals("self4", club.getMembers(Category.JUNIOR).get(0).getTrainer().getId());
	}

	@Test
	void test07() {
		assertEquals("self4", club.getMembers(Category.JUNIOR).get(1).getTrainer().getId());
	}

	@Test
	void test08() {
		assertEquals("self4", club.getMembers(Category.JUNIOR).get(3).getTrainer().getId());
	}

	@Test
	void test09() {
		assertEquals("pelf4", ((Junior) club.getMembers(Category.JUNIOR).get(2)).getSupervisor().getId());
	}

	@Test
	void test10() {
		assertEquals("pelf4", ((Junior) club.getMembers(Category.JUNIOR).get(4)).getSupervisor().getId());
	}

	@Test
	void test11() {
		assertEquals("pelf1", club.getMembers(Category.ELITE_MALE).get(2).getTrainer().getId());
	}

	@Test
	void test12() {
		assertEquals("pelf2", club.getMembers(Category.ELITE_FEMALE).get(1).getTrainer().getId());
	}

	@Test
	void test13() {
		assertEquals("pelf1", club.getMembers(Category.ELITE_MALE).get(4).getTrainer().getId());
	}

	@Test
	void test14() {
		assertEquals("pelf2", club.getMembers(Category.SENIOR).get(2).getTrainer().getId());
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
		assertEquals("Peter5", club.getMembers(Category.ELITE_MALE).get(4).getFirstName());
	}

	@Test
	void test19() {
		assertEquals(26, club.getParticipationsPerTournament().size());
	}

	@Test
	void test20() {
		assertEquals(22.3, club.getParticipationsPerTournament().get(14571).first().getResult(), 0.01);
	}

	@Test
	void test21() {
		assertEquals(20.0, club.getParticipationsPerTournament().get(12234).first().getResult(), 0.01);
	}

	@Test
	void test22() {
		assertEquals(19.8, club.getParticipationsPerTournament().get(12480).first().getResult(), 0.01);
	}

	@Test
	void test23() {
		assertEquals("self2", club.getParticipationsPerTournament().get(12480).first().getMember().getId());
	}

	@Test
	void test24() {
		assertEquals("self3", club.getParticipationsPerTournament().get(12480).last().getMember().getId());
	}

	@Test
	void test25() {
		assertEquals(6, club.getParticipationsPerTournament().get(12480).size());
	}

	@Test
	void test31() {
		assertEquals("pelf1", club.getTournamentList().get(12234).getReferee().getId());
	}

	@Test
	void test32() throws DataNotFoundException {
		assertEquals(20, club.getTournamentScores(12234).first().getResult());
	}

	@Test
	void test33() throws DataNotFoundException {
		assertEquals(27.7, club.getTournamentScores(12234).last().getResult());
	}

	@Test
	void test34() throws DataNotFoundException {
		Iterator<Participation> iter = club.getTournamentScores(12234).iterator();
		iter.next();
		assertEquals(21.1, iter.next().getResult());
	}

	@Test
	void test35() throws DataNotFoundException {
		assertEquals(25.1, club.getTournamentScores(13341).first().getResult());
	}

	@Test
	void test36() throws DataNotFoundException {
		assertEquals(27.1, club.getTournamentScores(14202).first().getResult());
	}

	@Test
	void test37() throws DataNotFoundException {
		assertEquals(26.2, club.getTournamentScores(14448).first().getResult());
	}

	@Test
	void test38() throws DataNotFoundException {
		Iterator<Participation> iter = club.getTournamentScores(14202).iterator();
		iter.next();
		assertEquals(36.1, iter.next().getResult());
	}

	@Test
	void test39() throws DataNotFoundException {
		assertEquals(28.6, club.getTournamentScores(13095).last().getResult());
	}

	@Test
	void test40() throws DataNotFoundException {
		assertEquals(4, club.getPersonScores("julf2", 2018).size());
	}

	@Test
	void test41() throws DataNotFoundException {
		assertThrows(DataNotFoundException.class, () -> club.getTournamentScores(13958));
	}

	@Test
	void test42() throws DataNotFoundException {
		assertEquals(4, club.getPersonScores("elim1", 2019).size());
	}

	@Test
	void test43() throws DataNotFoundException {
		assertEquals(6, club.getPersonScores("elif1", 2019).size());
	}


	@Test
	void test48() throws DataNotFoundException {
		assertThrows(DataNotFoundException.class, () -> club.findTournament(21015));
	}

	@Test
	void test49() throws DataNotFoundException {
		double r = club.getScore((Member) club.findPerson("self1"), 2018, 3);
		assertEquals(22.4, r);
	}

	@Test
	void test50() throws DataNotFoundException {
		double r = club.getScore((Member) club.findPerson("julf1"), 2018, 3);
		assertEquals(23.66, r, 0.1);
	}

	@Test
	void test51() throws DataNotFoundException {
		double r = club.getScore((Member) club.findPerson("self2"), 2018, 3);
		assertEquals(21.2, r, 0.1);
	}

	@Test
	void test52() throws DataNotFoundException {
		double r = club.getScore((Member) club.findPerson("elif2"), 2018, 3);
		assertEquals(22.07, r, 0.1);
	}

	@Test
	void test53() throws DataNotFoundException {
		Iterator<Participation> iter = club.getPersonScores("elif2", 2018).iterator();
		assertEquals(19.2, iter.next().getResult());
	}

	@Test
	void test54() throws DataNotFoundException {
		Iterator<Participation> iter = club.getPersonScores("elif2", 2018).iterator();
		iter.next();
		assertEquals(23.0, iter.next().getResult());
	}

	@Test
	void test55() throws DataNotFoundException {
		Iterator<Participation> iter = club.getPersonScores("elif2", 2018).iterator();
		iter.next();
		iter.next();
		assertEquals(24.0, iter.next().getResult());
	}

	@Test
	void test56() throws DataNotFoundException {
		Member m = club.getMembers(Category.ELITE_MALE).get(0);
		Tournament t = club.findTournament(16416);
		int r = club.getRanking(m, t, Category.ELITE_MALE);
		assertEquals(1, r);
	}

	@Test
	void test57() throws DataNotFoundException {
		Member m = club.getMembers(Category.ELITE_FEMALE).get(4);
		Tournament t = club.findTournament(12480);
		int r = club.getRanking(m, t, Category.ELITE_FEMALE);
		assertEquals(2, r);
	}

	@Test
	void test58() throws DataNotFoundException {
		Member m = club.getMembers(Category.SENIOR).get(1);
		Tournament t = club.findTournament(12234);
		int r = club.getRanking(m, t, Category.SENIOR);
		assertEquals(1, r);
	}

	@Test
	void test59() throws DataNotFoundException {
		double s = club.getScore(club.getMembers(Category.ELITE_MALE).get(1), 2018, 2);
		assertEquals(21.7, s, 0.1);
	}

	@Test
	void test60() throws DataNotFoundException {
		double s = club.getScore(club.getMembers(Category.ELITE_FEMALE).get(2), 2018, 2);
		assertEquals(21.75, s, 0.1);
	}

	@Test
	void test61() throws DataNotFoundException {
		double s = club.getScore(club.getMembers(Category.SENIOR).get(1), 2018, 2);
		assertEquals(20.4, s, 0.1);
	}

	@Test
	void test62() throws DataNotFoundException {
		Member m = club.getMembers(Category.ELITE_MALE).get(1);
		int r = club.getRanking(m, Category.ELITE_MALE, 2018);
		assertEquals(2, r);
	}

	@Test
	void test63() throws DataNotFoundException {
		Member m = club.getMembers(Category.JUNIOR).get(4);
		int r = club.getRanking(m, Category.JUNIOR, 2018);
		assertEquals(5, r);
	}

	@Test
	void test64() throws DataNotFoundException {
		Member m = club.getMembers(Category.SENIOR).get(3);
		int r = club.getRanking(m, Category.SENIOR, 2018);
		assertEquals(4, r);
	}
}
