package competitionManager;

public class Participant {
	
	private String id;
	private String major;
	private String name;
	private int rank = 0;
	
	
	public Participant(String newId, String newMajor, String newName, int newRank) {
		id = newId;
		major = newMajor;
		name = newName;
		rank = newRank;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getMajor() {
		return major;
	}


	public void setMajor(String major) {
		this.major = major;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}

	public boolean equals(Participant p) {
		if (this.getId().equals(p.getId()))
			return true;
		else 
			return false;
	}
	
	
	
}
