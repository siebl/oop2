package ch.develop.milord;

import java.time.LocalDate;

public class Elite extends Member {

	private Gender sex;

	public Elite(String lastName, String firstName, String id, int i, LocalDate birthDate, Gender gender) {
		super(lastName, firstName, id, i, birthDate);
		this.sex = gender;
	}

	public Gender getSex() {
		return sex;
	}

	public void setSex(Gender sex) {
		this.sex = sex;
	}

}
