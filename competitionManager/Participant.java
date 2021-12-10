package competitionManager;

public class Participant {
	
	private String id;
	private String major;
	private String name;
	private Team team = null;
	private int rank = 0;
	
	
	public Participant(String newName, String newId, String newMajor, Team newTeam) {
		id = newId;
		major = newMajor;
		name = newName;
		team = newTeam;
	}
	
	public Participant(String newName, String newId, String newMajor) {
		id = newId;
		major = newMajor;
		name = newName;
	}
	
	public int getTeamNumber() {
		return team.getNumber();
	}
	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
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
		if (team == null) {
			return rank;
		}
		else {
			return team.getRank();
		}
	}


	public void setRank(int rank) {
		if (team == null) {
			this.rank = rank;
		}
		else {
			team.setRank(rank);
		}
	}

	public boolean equals(Participant p) {
		if (this.getId().equals(p.getId()))
			return true;
		else 
			return false;
	}
	
}
