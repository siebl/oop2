package ch.develop.mibo.data;

import java.time.LocalDate;

public class Junior extends Member {

	private Person supervisor;

	public Junior(String lastName, String firstName, String id, int pw, LocalDate dateOfBirth) {
		super(lastName, firstName, id, pw, dateOfBirth);
	}

	public Junior(String lastName, String firstName, String id, int pw, LocalDate dateOfBirth, 
			Person supervisor, Person trainer) {
		super(lastName, firstName, id, pw, dateOfBirth, trainer);
		this.supervisor = supervisor;
	}

	public Person getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Person supervisor) {
		this.supervisor = supervisor;
	}

}
