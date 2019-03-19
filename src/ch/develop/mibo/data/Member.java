package ch.develop.mibo.data;

import java.time.LocalDate;

public abstract class Member extends Person {

	private LocalDate dateOfBirth;
	protected Person trainer;

	protected Member(String lastName, String firstName, String id, int pw,  LocalDate dateOfBirth) {
		super(lastName, firstName, id, pw);
		this.dateOfBirth = dateOfBirth;
	}

	protected Member(String lastName, String firstName, String id,int pw, LocalDate dateOfBirth, Person trainer) {
		this(lastName, firstName, id, pw, dateOfBirth);
		this.trainer = trainer;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public Person getTrainer() {
		return trainer;
	}

	public void setTrainer(Person trainer) {
		this.trainer = trainer;
	}
}
