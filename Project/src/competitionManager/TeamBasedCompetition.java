package competitionManager;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;

public class TeamBasedCompetition extends Competition {
	
	private LinkedList<Team> teams = new LinkedList<Team>();
	
	
	public TeamBasedCompetition(String newName, URL newlink, Date newDate) {
		super(newName, newlink, newDate);
	}

	public void addParticipant(String name,String id,String major,int rank, int teamNum) {
		teams.get(teamNum).addParticipant(name, id, major);
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
	
	
}
