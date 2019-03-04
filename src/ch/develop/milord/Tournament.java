package ch.develop.milord;

import java.time.LocalDate;

public class Tournament extends BaseEntity {

	private LocalDate date;

	private Person person;

	public Tournament(int id, LocalDate date, Person person) {
		setId(id);
		this.date = date;
		this.person = person;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
