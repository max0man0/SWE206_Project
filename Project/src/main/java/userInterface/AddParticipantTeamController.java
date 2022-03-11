package userInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import competitionManager.Competition;
import competitionManager.TeamBasedCompetition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddParticipantTeamController {

	@FXML
    private Label lbWarning;
	
    @FXML
    private Button btAddParticipantTeam;

    @FXML
    private Label lbCompetitionName;

    @FXML
    private TextField tfAddStudentIDTeam;

    @FXML
    private TextField tfAddStudentMajorTeam;

    @FXML
    private TextField tfAddStudentNameTeam;

    @FXML
    private TextField tfAddStudentTeam;

    void setCompetitionName(String name) {
    	lbCompetitionName.setText(name);
    }
    
    @FXML
    void addParticipant(ActionEvent event) throws IOException {
    	try {
	    	String name = tfAddStudentNameTeam.getText();
	    	String id = tfAddStudentIDTeam.getText();
	    	String major = tfAddStudentMajorTeam.getText();
	    	String team = tfAddStudentTeam.getText();
	    	Integer.parseInt(id);
	    	LinkedList<Competition> competitions = Start.competitionManager.getCompetitions();
	    	TeamBasedCompetition competition;
	    	int i;
	    	for (i = 0; i < competitions.size(); i++) {
	    		if (competitions.get(i).getName().equals(lbCompetitionName.getText())) {
	    			competition = (TeamBasedCompetition) competitions.get(i);
	    			HashMap<String, String> hashmap = new HashMap<>();
	    			hashmap.put("Name", name);
	    			hashmap.put("Id", id);
	    			hashmap.put("Major", major);
	    			hashmap.put("TeamName", team);
	    			competition.addParticipant(hashmap);
	    			break;
	    		}
	    	}
	    	
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
	    	Parent root = loader.load();
	    	MainMenuController controller = loader.getController();
	    	controller.tpCompetitions.getSelectionModel().select(i);
	    	Start.primaryStage.setScene(new Scene(root));
	    	
	    	tfAddStudentIDTeam.setText("");
	    	tfAddStudentNameTeam.setText("");
	    	tfAddStudentMajorTeam.setText("");
	    	tfAddStudentTeam.setText("");
    	}
    	catch (NumberFormatException e) {
    		lbWarning.setText("The ID must only contain numbers");
    		lbWarning.setVisible(true);
    	}
    }

    @FXML
    void closeWindow(ActionEvent event) {
    	CommonMethods.closeWindow(event);
    }
}
