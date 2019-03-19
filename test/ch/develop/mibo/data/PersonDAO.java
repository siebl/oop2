package ch.develop.mibo.data;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Logger;

import ch.develop.mibo.utils.DataNotFoundException;

public class PersonDAO {

	private static final Logger logger = Logger.getLogger(PersonDAO.class.getName());

	public void loadPersons() throws DataNotFoundException {
		log("load persons");
		MinigolfClub club = MinigolfClub.getInstance();
		loadPersons(club);
		addTrainersAndCustodians(club);
	}

	private void loadPersons(MinigolfClub club) {
		for (int i = 0; i <= 6; i++) {
			Person person = new Person("PerName" + i, "FirstName" + i, "pelf" + i, 17 );
			club.getPersonList().put("pelf" + i,person);
		}
		
		ArrayList<Elite> eliteMaleList = new ArrayList<>();
		for (int i = 1; i <= 6; i++) {
			Elite elite = new Elite("EliName" + i, "Peter" + i,"elim" + i,  17, LocalDate.of(1980, i, i), Gender.MALE);
			club.getPersonList().put("elim" + i,elite);
			eliteMaleList.add(elite);
		}
		club.getMemberMap().put(Category.ELITE_MALE, eliteMaleList);

		ArrayList<Elite> eliteFemaleList = new ArrayList<>();
		for (int i = 1; i <= 6; i++) {
			Elite elite = new Elite("EliName" + i, "Anna" + i,"elif" + i,  17, LocalDate.of(1980, i, i), Gender.FEMALE);
			club.getPersonList().put("elif" + i,elite);
			eliteFemaleList.add(elite);
		}
		club.getMemberMap().put(Category.ELITE_FEMALE, eliteFemaleList);
		
		ArrayList<Senior> seniorList = new ArrayList<>();
		for (int i = 1; i <= 6; i++) {
			Senior senior = new Senior("SenLastName" + i, "FirstName" + i, "self" + i, 17,  LocalDate.of(1970, i, i));
			club.getPersonList().put("self" + i,senior);
			seniorList.add(senior);
		}
		club.getMemberMap().put(Category.SENIOR, seniorList);

		ArrayList<Junior> juniorList = new ArrayList<>();
		for (int i = 1; i <= 6; i++) {
			Junior junior = new Junior("JunLastName" + i, "FirstName" + i, "julf" + i, 17,  LocalDate.of(2008, i, i));
			club.getPersonList().put("julf" + i, junior);
			juniorList.add(junior);
		}
		club.getMemberMap().put(Category.JUNIOR, juniorList);

	}

	private void addTrainersAndCustodians(MinigolfClub club) throws DataNotFoundException {
		for (Member elite : club.getMemberMap().get(Category.ELITE_MALE)) {
			elite.setTrainer(club.findPerson("pelf1"));
		}
		for (Member elite : club.getMemberMap().get(Category.ELITE_FEMALE)) {
			elite.setTrainer(club.findPerson("pelf2"));
		}
		for (Member senior : club.getMemberMap().get(Category.SENIOR)) {
			senior.setTrainer(club.findPerson("pelf2"));
		}
		for (Member junior : club.getMemberMap().get(Category.JUNIOR)) {
			junior.setTrainer(club.findPerson("self4"));
			((Junior)junior).setSupervisor(club.findPerson("pelf4"));
		}
	}

	private void log(String stmt) {
		String text = stmt.toString();
		logger.info(text.substring(text.indexOf(":") + 1));
	}

}
