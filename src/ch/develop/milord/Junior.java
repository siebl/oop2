package ch.develop.milord;

import java.time.LocalDate;

public class Junior extends Member {
	
	private Person supervisor;
	
	public Junior(String lastName, String firstName, String id, int i, LocalDate dateOfBirth, Person supervisor) {
		super(lastName, firstName, id, i, dateOfBirth);
		this.supervisor = supervisor;
	}

	public Junior(String lastName, String firstName, String id, int i, LocalDate birthDate ) {
		super(lastName,firstName, id, i, birthDate );
	}

	public Person getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Person supervisor) {
		this.supervisor = supervisor;
	}

}
