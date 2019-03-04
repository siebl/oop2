package ch.develop.milord;

import java.util.function.Predicate;

public class ParticipationFilter implements Predicate<Participation> {

	Tournament tournament;
	
	Category category;
	
	Gender gender;
	
	@Override
	public boolean test(Participation participation) {
		boolean matches = true;
		if(tournament != null) {
			matches &= participation.getTournament().equals(tournament);
		}
		if(category != null) {
			matches &= participation.getCategory() == category;
		}
		if( gender != null ) {
			Gender sex = participation.getMember().getSex();
			if(gender != null) {
				matches &= gender == sex; 
			}
		}
		return matches;
	}

}
