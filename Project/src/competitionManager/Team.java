package competitionManager;
import java.util.LinkedList;

public class Team {

	private String name;
	private int number;
	private LinkedList<Participant> participants = new LinkedList<>();
	private int rank;
	
	
	public Team(String newName, int newNumber, int newRank, int maxParticipant) {
		name = newName;
		number = newNumber;
		rank = newRank;
	}


	public void addParticipant(String name,String id,String major) {
		participants.add(new Participant(id, major, name, rank));
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


	public void setNumber(int number) {
		this.number = number;
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
	
	
	
	
	
	
}
