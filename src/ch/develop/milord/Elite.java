package ch.develop.milord;

import java.time.LocalDate;

public class Elite extends Member {

	public Elite(String lastName, String firstName, String id, int i, LocalDate birthDate, Gender gender) {
		super(lastName, firstName, id, i, birthDate);
		this.sex = gender;
	}

}
