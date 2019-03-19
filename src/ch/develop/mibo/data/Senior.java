package ch.develop.mibo.data;

import java.time.LocalDate;

public class Senior extends Member {

	public Senior(String lastName, String firstName, String id, int pw, LocalDate dateOfBirth) {
		super(lastName, firstName, id, pw, dateOfBirth);
	}
	
	public Senior(String lastName, String firstName, String id, int pw, LocalDate dateOfBirth, Person trainer) {
		super(lastName, firstName, id, pw, dateOfBirth, trainer);
	}
}
