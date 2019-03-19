package ch.develop.mibo.data;

import java.time.LocalDate;

public class Tournament {

	private int id;
	private LocalDate date;
	private Person referee;

	public Tournament(int id, LocalDate date, Person referee) {
		this.id = id;
		this.date = date;
		this.referee = referee;
	}

	public int getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}

	public Person getReferee() {
		return referee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tournament other = (Tournament) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
