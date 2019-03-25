package ch.develop.mibo.data;

public class Participation implements Comparable<Participation>{

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

	@Override
	public int compareTo(Participation o) {
		//same object is always equal
		if( this == o ) {
			return 0;
		}
		
		int resCompare = Double.compare(this.getResult(), o.getResult());
		if( resCompare != 0 ) {
			return resCompare;
		}
		
		// compare on person
		int memberCompare = this.getMember().compareTo( o.getMember() );
		if( memberCompare != 0 ) {
			return memberCompare;
		}
		
		//compare on tournament
		int tournamentCompare = this.getTournament().compareTo( o.getTournament() );
		if( tournamentCompare != 0 ) {
			return tournamentCompare;
		}
		
		return 0;
	}


}
