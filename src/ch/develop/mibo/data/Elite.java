package ch.develop.mibo.data;

import java.time.LocalDate;

public class Elite extends Member {

	private Gender sex;

	public Elite(String lastName, String firstName, String id, int pw, LocalDate dateOfBirth, Gender s) {
		super(lastName, firstName, id, pw, dateOfBirth);
		this.sex = s;
	}

	public Elite(String lastName, String firstName, Gender s, int pw, String id, LocalDate dateOfBirth, Person trainer) {
		super(lastName, firstName, id, pw, dateOfBirth, trainer);
		this.sex = s;
	}

	public Gender getSex() {
		return sex;
	}
}
