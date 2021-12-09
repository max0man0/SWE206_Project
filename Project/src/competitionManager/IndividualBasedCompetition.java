package competitionManager;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedList;

public class IndividualBasedCompetition extends Competition {
	
	private LinkedList<Participant> participants = new LinkedList<>();
	
	public IndividualBasedCompetition(String newName, URL newlink, LocalDate newDate) {
		super(newName, newlink, newDate);
	}
	
	
	public void addParticipant(String name,String id,String major) {
		participants.add(new Participant(id, major, name));
	}


	@Override
	public void deleteParticipant(Participant participant) {
		for (int i = 0; i < participants.size(); i++) {
			if (participants.get(i).equals(participant)) {
				participants.remove(i);
			}
		}
	}


	@Override
	public int getParticipantCount() {
		return participants.size();
	}


	@Override
	public LinkedList<Participant> getParticipants() {
		return participants;
	};
	
	@Override
	public String toString() {
		return super.toString() + " Individual Based Competition";
	}
}
