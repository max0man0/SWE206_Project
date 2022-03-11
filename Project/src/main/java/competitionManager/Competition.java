package competitionManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class Competition {
	
	private String name;
	private URL link;
	private LocalDate dueDate;
	
	public Competition(String newName, URL newlink, LocalDate newDate) {
		name = newName;
		link = newlink;
		dueDate = newDate;
	}
	
	public abstract void deleteParticipant(Participant participant);

	public abstract int getParticipantCount();

	public abstract LinkedList<Participant> getParticipants();

	public abstract void addParticipant(HashMap<String, String> args);
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public URL getLink() {
		return link;
	}

	public void setLink(URL link) {
		this.link = link;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	public boolean equals(Competition competition) {
		if (this.name.equals(competition.getName())) 
			return true;
		else 
			return false;
	}
	
	@Override
	public String toString() {
		return name + " " + link.toString() + " " + dueDate.toString();
	}
	
}
