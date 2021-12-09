package competitionManager;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;

public class TeamBasedCompetition extends Competition {
	
	private LinkedList<Team> teams = new LinkedList<Team>();
	
	
	public TeamBasedCompetition(String newName, URL newlink, LocalDate newDate) {
		super(newName, newlink, newDate);
	}

	public void addParticipant(String name,String id,String major, String teamName) {
		for (int i = 0; i < teams.size(); i++) {
			if(teams.get(i).getName().equals(teamName)) {
				teams.get(i).addParticipant(name, id, major);
				return;
			}
		}
		teams.add(new Team(teamName));
		teams.get(teams.size()-1).addParticipant(teamName, id, major);
	}

	@Override
	public void deleteParticipant(Participant participant) {
		for (int i = 0; i < teams.size(); i++) {
			for (int j = 0; j < teams.get(i).getParticipants().size(); j++) {
				if (teams.get(i).getParticipants().get(j).equals(participant))
					teams.get(i).deleteParticipant(j);
				
			}
			
		}
	}

	@Override
	public int getParticipantCount() {
		int count = 0;
		for (int i = 0; i < teams.size(); i++) {
			count += teams.get(i).getParticipants().size();
		}
		return count;
	}

	@Override
	public LinkedList<Participant> getParticipants() {
		LinkedList<Participant> participants = new LinkedList<>();
		for (int i = 0; i < teams.size(); i++) {
			participants.addAll(teams.get(i).getParticipants()) ;
		}
		return participants;
	};
	
	@Override
	public String toString() {
		return super.toString() + " Team Based Competition";
	}
}
