package ch.develop.mibo.data;

public class Participation {

	private Member member;
	private Tournament tournament;
	private double result;
	private Category category;

	public Participation(Member member, Tournament tournament, double result) {
		this.member = member;
		this.tournament = tournament;
		this.result = result;
	}

	public Participation(Member member, Tournament tournament, double result, Category category) {
		this(member, tournament, result);
		this.category = category;
	}

	public Member getMember() {
		return member;
	}

	public Category getCategory() {
		return category;
	}

	public double getResult() {
		return result;
	}

	public Tournament getTournament() {
		return tournament;
	}

	@Override
	public String toString() {
		return member.lastName + member.firstName + ": " + result + ". ";
	}


}
