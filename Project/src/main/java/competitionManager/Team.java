package competitionManager;

import java.util.LinkedList;

public class Team {

	private static int teamCount = 0;
	private String name;
	private int number;
	private LinkedList<Participant> participants = new LinkedList<Participant>();
	private int rank;
	
	
	public Team(String newName) {
		teamCount++;
		name = newName;
		number = teamCount;
	}


	public void addParticipant(String name,String id,String major) {
		participants.add(new Participant(name, id, major, this));
	}
	
	public void deleteParticipant(int index) {
		participants.remove(index);
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getNumber() {
		return number;
	}


	public LinkedList<Participant> getParticipants() {
		return participants;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
