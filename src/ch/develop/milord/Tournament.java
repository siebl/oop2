package ch.develop.milord;

import java.time.LocalDate;

public class Tournament extends BaseEntity {

	private LocalDate date;

	private Person referee;

	public Tournament(int id, LocalDate date, Person organisator) {
		setId(id);
		this.date = date;
		this.referee = organisator;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Person getReferee() {
		return referee;
	}

	public void setReferee(Person person) {
		this.referee = person;
	}
	
	@Override
	public String toString() {
		return "Tournament [date=" + date + ", organisator=" + referee + ", id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((referee == null) ? 0 : referee.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tournament other = (Tournament) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (referee == null) {
			if (other.referee != null)
				return false;
		} else if (!referee.equals(other.referee))
			return false;
		return true;
	}

}
