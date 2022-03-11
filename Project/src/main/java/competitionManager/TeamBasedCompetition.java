package competitionManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class TeamBasedCompetition extends Competition {
	private LinkedList<Team> teams = new LinkedList<Team>();
	
	public LinkedList<Team> getTeams() {
		return teams;
	}

	public TeamBasedCompetition(String newName, URL newlink, LocalDate newDate) {
		super(newName, newlink, newDate);
	}
	
	@Override
	public void addParticipant(HashMap<String, String> args) {
		if (args.size() == 4) {
			for (int i = 0; i < teams.size(); i++) {
				if(teams.get(i).getName().equals(args.get("TeamName"))) {
					teams.get(i).addParticipant(args.get("Name"), args.get("Id"), args.get("Major"));
					return;
				}
			}
			teams.add(new Team(args.get("TeamName")));
			teams.get(teams.size()-1).addParticipant(args.get("Name"), args.get("Id"), args.get("Major"));
		}
		else {
			throw new IllegalArgumentException("There should only be \"Name\", \"Id\", \"Major\", and \"TeamName\" keys in the hashmap");
		}
	}
	
//	public void addParticipant(String[] args) {
//		for (int i = 0; i < teams.size(); i++) {
//			if(teams.get(i).getName().equals(args[3])) {
//				teams.get(i).addParticipant(args[0], args[1], args[2]);
//				return;
//			}
//		}
//		teams.add(new Team(args[3]));
//		teams.get(teams.size()-1).addParticipant(teamName, id, major);
//		
//	}

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
		LinkedList<Participant> participants = new LinkedList<Participant>();
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
