package competitionManager;

import java.net.URL;
import java.util.Date;
import java.util.LinkedList;

public class CompetitionManager {
	
	LinkedList<Competition> competitions = new LinkedList<>();

	public CompetitionManager() {}
	
	public void createCompetition(String name, URL link, Date dueDate, boolean isTeam) {
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
}
