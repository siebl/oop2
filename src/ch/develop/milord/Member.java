package ch.develop.milord;

import java.time.LocalDate;

public abstract class Member extends Person {

	protected LocalDate dateOfBirth;

	private Person trainer;
	
	public Member(String lastName, String firstName, String id, int i, LocalDate dateOfBirth) {
		super(lastName, firstName, id, i);
		this.dateOfBirth = dateOfBirth;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Person getTrainer() {
		return trainer;
	}

	public void setTrainer(Person trainer) {
		this.trainer = trainer;
	}

}
