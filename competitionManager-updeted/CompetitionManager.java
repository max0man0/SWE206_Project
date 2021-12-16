

import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;

public class CompetitionManager {
	
	LinkedList<Competition> competitions = new LinkedList<Competition>();

	

	public CompetitionManager() {}
	
	public void createCompetition(String name, URL link, LocalDate dueDate, boolean isTeam) {
		if (isTeam) {
			competitions.add(new TeamBasedCompetition(name, link, dueDate));
		}else {
			competitions.add(new IndividualBasedCompetition(name, link, dueDate));
		}
	}
	
	
	public void deleteCompetition(Competition competition) {
		for (int i = 0; i < competitions.size(); i++) {
			if (competitions.get(i).equals(competition))
				competitions.remove(i);
			
		}
	}
	
	public void deleteCompetition(int index) {
		competitions.remove(index);
	}
	
	
	public LinkedList<Competition> getCompetitions() {
		return competitions;
	}
}
