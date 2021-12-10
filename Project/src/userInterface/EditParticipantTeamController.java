package userInterface;

import java.io.IOException;
import java.util.LinkedList;

import competitionManager.Competition;
import competitionManager.TeamBasedCompetition;
import competitionManager.Participant;
import competitionManager.Team;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditParticipantTeamController {

    @FXML
    private Button btEditParticipantTeam;

    @FXML
    private Label lbWarning;
    
    @FXML
    private Label lbCompetitionName;

    @FXML
    private TextField tfEditStudentIDTeam;

    @FXML
    private TextField tfEditStudentMajorTeam;

    @FXML
    private TextField tfEditStudentNameTeam;

    @FXML
    private TextField tfEditStudentRankTeam;

    @FXML
    private TextField tfEditStudentTeam;

    @FXML
    void closeWindow(ActionEvent event) {
    	CommonMethods.closeWindow(event);
    }

    @FXML
    void editParticipant(ActionEvent event) throws IOException {
    	try {
	    	String name = tfEditStudentNameTeam.getText();
	    	String id = tfEditStudentIDTeam.getText();
	    	String major = tfEditStudentMajorTeam.getText();
	    	String teamName = tfEditStudentTeam.getText();
	    	String rank = tfEditStudentRankTeam.getText();
	    	
	    	// Check if id is numeric
	    	Integer.parseInt(id);
	    	LinkedList<Competition> competitions = Main.competitionManager.getCompetitions();
	    	TeamBasedCompetition competition;
	    	int i;
	    	boolean competitionFound = false;
	    	for (i = 0; i < competitions.size(); i++) {
	    		if (competitions.get(i).getName().equals(lbCompetitionName.getText())) {
	    			competition = (TeamBasedCompetition) competitions.get(i);
	    			competitionFound = true;
	    			LinkedList<Participant> participants = competition.getParticipants();
	    			for (int j = 0; j < competition.getParticipantCount(); j++) {
	    				if (participants.get(j).getId().equals(tfEditStudentIDTeam.getPromptText())) {
	    					Participant participant = participants.get(j);
	    					LinkedList<Team> teams = competition.getTeams();
	    					boolean teamFound = false;
	    					Team team = null;
	    					for (int k = 0; k < teams.size() && !teamFound; k++) {
	    						if (teams.get(k).getName().equals(teamName)) {
	    							team = teams.get(k);
	    							teamFound = true;
	    						}
	    					}
	    					if (!teamFound) {
	    						team = new Team(teamName);
	    						competition.getTeams().add(team);
	    					}
	    					participant.setName(name);
	    					participant.setId(id);
	    					participant.setMajor(major);
	    					participant.setTeam(team);
	    					participant.setRank(Integer.parseInt(rank));
	    					break;
	    				}
	    			}
	    		}
	    		if (competitionFound) {
	    			break;
	    		}
	    	}
	    	CommonMethods.closeWindow(event);
	    	
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
	    	Parent root = loader.load();
	    	MainMenuController controller = loader.getController();
	    	controller.tpCompetitions.getSelectionModel().select(i);
	    	Main.primaryStage.setScene(new Scene(root));
    	}
    	catch (NumberFormatException e) {
    		lbWarning.setText("The ID and rank must only contain numbers");
    		lbWarning.setVisible(true);
    	}
    }

    void prepareTextfields(String name, String id, String major, String teamName, int rank, String competitionName) {
		tfEditStudentNameTeam.setText(name);
		tfEditStudentIDTeam.setText(id);
		tfEditStudentMajorTeam.setText(major);
		tfEditStudentRankTeam.setText(rank + "");
		tfEditStudentTeam.setText(teamName);

		tfEditStudentNameTeam.setPromptText(name);
		tfEditStudentIDTeam.setPromptText(id);
		tfEditStudentMajorTeam.setPromptText(major);
		tfEditStudentRankTeam.setPromptText(rank + "");
		tfEditStudentTeam.setPromptText(teamName);

		lbCompetitionName.setText(competitionName);
	}
}
