package ch.develop.milord;

import java.time.LocalDate;

public class Tournament extends BaseEntity {

	private LocalDate date;

	private Person organisator;

	public Tournament(int id, LocalDate date, Person organisator) {
		setId(id);
		this.date = date;
		this.organisator = organisator;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Person getOrganisator() {
		return organisator;
	}

	public void setOrganisator(Person person) {
		this.organisator = person;
	}

}
