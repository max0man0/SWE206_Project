package competitionManager;
import java.util.Date;
import java.util.LinkedList;

public abstract class Competition {
	
	private String name;
	private String link;
	private Date dueDate;
	
	public Competition(String newName, String newlink, Date newDate) {
		name = newName;
		link = newlink;
		dueDate = newDate;
	}
	
	abstract void deleteParticipant(Participant participant);

	abstract int getParticipantCount();

	abstract LinkedList<Participant> getParticipants();

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
}
