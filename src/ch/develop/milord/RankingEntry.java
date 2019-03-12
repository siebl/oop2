package ch.develop.milord;

public class RankingEntry<T extends Person> implements Comparable<RankingEntry<T>> {

	private T member;
	private double score;
	
	@Override
	public int compareTo(RankingEntry<T> o) {
		Double thisOne = Double.valueOf(score);
		Double otherOne = Double.valueOf( o.getScore() );
		return thisOne.compareTo(otherOne);
	}

	public T getMember() {
		return member;
	}

	public void setMember(T member) {
		this.member = member;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((member == null) ? 0 : member.hashCode());
		long temp;
		temp = Double.doubleToLongBits(score);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RankingEntry<?> other = (RankingEntry<?>) obj;
		if (member == null) {
			if (other.member != null)
				return false;
		} else if (!member.equals(other.member))
			return false;
		if (Double.doubleToLongBits(score) != Double.doubleToLongBits(other.score))
			return false;
		return true;
	}

}
