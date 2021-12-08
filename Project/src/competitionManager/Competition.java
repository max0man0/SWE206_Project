package competitionManager;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;

public abstract class Competition {
	
	private String name;
	private URL link;
	private Date dueDate;
	
	public Competition(String newName, URL newlink, Date newDate) {
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

	public URL getLink() {
		return link;
	}

	public void setLink(URL link) {
		this.link = link;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public boolean equals(Competition competition) {
		if (this.name.equals(competition.getName())) 
			return true;
		else 
			return false;
	}
	
}
