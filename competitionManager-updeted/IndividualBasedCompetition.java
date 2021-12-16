
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class IndividualBasedCompetition extends Competition {
	
	private LinkedList<Participant> participants = new LinkedList<Participant>();
	
	public IndividualBasedCompetition(String newName, URL newlink, LocalDate newDate) {
		super(newName, newlink, newDate);
	}
	
	@Override
	public void addParticipant(HashMap<String, String> args) {
		if (args.size() == 3) {
			participants.add(new Participant(args.get("Name"), args.get("Id"), args.get("Major")));
		}
		else {
			throw new IllegalArgumentException("There should only be \"Name\", \"Id\", and \"Major\" keys in the hashmap");
		}
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
