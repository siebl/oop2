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
	
	@Override
	public String toString() {
		return "Tournament [date=" + date + ", organisator=" + organisator + ", id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((organisator == null) ? 0 : organisator.hashCode());
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
		if (organisator == null) {
			if (other.organisator != null)
				return false;
		} else if (!organisator.equals(other.organisator))
			return false;
		return true;
	}

}
