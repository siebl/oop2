package ch.develop.mibo.utils;

import ch.develop.mibo.data.Member;

public class RankingEntry<T extends Member> implements Comparable<RankingEntry<T>> {
	private double score;
	private T member;

	public RankingEntry(double s, T s2) {
		this.score = s;
		this.member = s2;
	}

	@Override
	public int compareTo(RankingEntry<T> p) {
		return score > p.score ? +1 : score < p.score ? -1 : 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((member == null) ? 0 : member.hashCode());
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
		RankingEntry<T> other = (RankingEntry<T>) obj;
		if (member == null) {
			if (other.member != null)
				return false;
		} else if (!member.equals(other.member))
			return false;
		return true;
	}

	public T getMember() {
		return member;
	}

	@Override
	public String toString() {
		return "RankingEntry: score=" + score + ", member=" + member;
	}
	

}