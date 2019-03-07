package ch.develop.milord;

import java.time.LocalDate;
import java.util.function.Predicate;

public class ParticipationFilter implements Predicate<Participation> {

	private Tournament tournament;
	
	private Integer tournamentId;
	
	private Category category;
	
	private Gender gender;
	
	private Member member;
	
	private LocalDate dateFrom;
	
	private LocalDate dateTo;
	
	@Override
	public boolean test(Participation participation) {
		boolean matches = true;
		if(tournament != null) {
			matches &= participation.getTournament().equals(tournament);
		}
		if(tournamentId != null) {
			matches &= tournamentId.equals( participation.getTournament().getId() );
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
		if(member != null) {
			matches &= participation.getMember().equals(member);
		}
		if(dateFrom != null) {
			matches &= dateFrom.isBefore( participation.getTournament().getDate() );
		}
		if(dateTo != null) {
			matches &= dateTo.isAfter( participation.getTournament().getDate() );
		}
		return matches;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}

	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}

	public void setTournamentId(Integer tournamentId) {
		this.tournamentId = tournamentId;
	}

}
