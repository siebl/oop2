package ch.develop.milord;

import java.time.LocalDate;
import java.util.logging.Logger;

import ch.develop.milord.exception.DataNotFoundException;

public class PersonDAO {

	private static final Logger logger = Logger.getLogger(PersonDAO.class.getName());

	public void loadPersons() throws DataNotFoundException {
		log("load persons");
		MinigolfClub dataManager = MinigolfClub.getInstance();
		loadPersons(dataManager);
		addTrainersAndCustodians(dataManager);
	}

	private void loadPersons(MinigolfClub dataManager) {
		for (int i = 0; i <= 6; i++) {
			Person person = new Person("PerName" + i, "FirstName" + i, "pelf" + i, 17 );
			dataManager.getPersonList().add(person);
		}
		for (int i = 1; i <= 6; i++) {
			Elite elite = new Elite("EliName" + i, "Peter" + i,"elim" + i,  17, LocalDate.of(1980, i, i), Gender.MALE);
			dataManager.getEliteList().add(elite);
			dataManager.getPersonList().add(elite);
			dataManager.getMemberList().add(elite);
		}
		for (int i = 1; i <= 6; i++) {
			Elite elite = new Elite("EliName" + i, "Anna" + i,"elif" + i,  17, LocalDate.of(1980, i, i), Gender.FEMALE);
			dataManager.getEliteList().add(elite);
			dataManager.getPersonList().add(elite);
			dataManager.getMemberList().add(elite);
		}
		for (int i = 1; i <= 6; i++) {
			Senior senior = new Senior("SenLastName" + i, "FirstName" + i, "self" + i, 17,  LocalDate.of(1970, i, i));
			dataManager.getSeniorList().add(senior);
			dataManager.getPersonList().add(senior);
			dataManager.getMemberList().add(senior);
		}
		for (int i = 1; i <= 6; i++) {
			Junior junior = new Junior("JunLastName" + i, "FirstName" + i, "julf" + i, 17,  LocalDate.of(2008, i, i));
			dataManager.getJuniorList().add(junior);
			dataManager.getMemberList().add(junior);
			dataManager.getPersonList().add(junior);
		}
	}

	private void addTrainersAndCustodians(MinigolfClub dataManager) throws DataNotFoundException {
		for (Member elite : dataManager.getMemberList()) {
			elite.setTrainer(dataManager.findPerson("pelf1"));
		}
		for (Member senior : dataManager.getSeniorList()) {
			senior.setTrainer(dataManager.findPerson("pelf2"));
		}
		for (Junior junior : dataManager.getJuniorList()) {
			junior.setTrainer(dataManager.getSeniorList().get(3));
			junior.setSupervisor(dataManager.findPerson("pelf4"));
		}
	}

	private void log(String stmt) {
		String text = stmt.toString();
		logger.info(text.substring(text.indexOf(":") + 1));
	}

}
