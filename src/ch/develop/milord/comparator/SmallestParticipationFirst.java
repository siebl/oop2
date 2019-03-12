package ch.develop.milord.comparator;

import java.util.Comparator;

import ch.develop.milord.Participation;

public class SmallestParticipationFirst implements Comparator<Participation> {

	@Override
	public int compare(Participation o1, Participation o2) {
		Double r1 = Double.valueOf( o1.getResult() );
		Double r2 = Double.valueOf( o2.getResult() );
		return r1.compareTo( r2 );
	}
	
}
