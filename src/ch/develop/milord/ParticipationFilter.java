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
			Member participant = participation.getMember();
			if(participant instanceof Elite) {
				Elite elite = (Elite)participant;
				matches &= elite.getSex() == gender;
			}
		}
		return matches;
	}

}
