package competitionManager;
import java.util.Date;
import java.util.LinkedList;

public class TeamBasedCompetition extends Competition {
	
	private LinkedList<Team> teams = new LinkedList<Team>();
	
	
	public TeamBasedCompetition(String newName, String newlink, Date newDate) {
		super(newName, newlink, newDate);
	}

	public void addParticipant(String name,String id,String major,int rank, int teamNum) {
		teams.get(teamNum).addParticipant(name, id, major);
	}

	@Override
	public void deleteParticipant(Participant participant) {
		teams.get(participant.get); //***************************
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
