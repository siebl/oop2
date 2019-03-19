package ch.develop.mibo.utils;

import ch.develop.mibo.data.Category;
import ch.develop.mibo.data.Member;
import ch.develop.mibo.data.Tournament;

public class DataNotFoundException extends Exception {

	private static final long serialVersionUID = -4730050730606170888L;

	public DataNotFoundException() {
		super();
	}

	public DataNotFoundException(String type) {
		super(type);
	}

	public DataNotFoundException(Member member, Tournament tournament, Category c) {
		super("Ranking for member "+ member.getId() +  "at tournament" + tournament.getId() + " with category " + c.toString());
	}

	public DataNotFoundException(String string, int id) {
		super(string + ": " + id);
	}

	public DataNotFoundException(String string, String id) {
		super(string + ": " + id);
	}

}
