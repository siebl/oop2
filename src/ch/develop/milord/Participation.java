package ch.develop.milord;

public class Participation {
	
	private Member member;
	
	private Tournament tournament;
	
	private double result;

	private Category category;

	public Participation(Member member, Tournament tournament, int result, Category category) {
		this.member = member;
		this.tournament = tournament;
		this.result = result;
		this.category = category;
	}
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
