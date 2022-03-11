package userInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

import competitionManager.Competition;
import competitionManager.IndividualBasedCompetition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddParticipantIndividualController {

	@FXML
    private Label lbWarning;
	
    @FXML
    private Button btAddParticipantIndividul;

    @FXML
    private Label lbCompetitionName;

    @FXML
    private TextField tfAddStudentIDIndividual;

    @FXML
    private TextField tfAddStudentMajorIndividual;

    @FXML
    private TextField tfAddStudentNameIndividual;

    void setCompetitionName(String name) {
    	lbCompetitionName.setText(name);
    }
    
    @FXML
    void addParticipant(ActionEvent event) throws IOException {
    	try {
	    	String name = tfAddStudentNameIndividual.getText();
	    	String id = tfAddStudentIDIndividual.getText();
	    	String major = tfAddStudentMajorIndividual.getText();
	    	Integer.parseInt(id);
	    	LinkedList<Competition> competitions = Start.competitionManager.getCompetitions();
	    	IndividualBasedCompetition competition;
	    	int i;
	    	for (i = 0; i < competitions.size(); i++) {
	    		if (competitions.get(i).getName().equals(lbCompetitionName.getText())) {
	    			competition = (IndividualBasedCompetition) competitions.get(i);
	    			HashMap<String, String> hashmap = new HashMap<>();
	    			hashmap.put("Name", name);
	    			hashmap.put("Id", id);
	    			hashmap.put("Major", major);
	    			competition.addParticipant(hashmap);
	    			break;
	    		}
	    	}
	    	
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
	    	Parent root = loader.load();
	    	MainMenuController controller = loader.getController();
	    	controller.tpCompetitions.getSelectionModel().select(i);
	    	Start.primaryStage.setScene(new Scene(root));
	    	
	    	tfAddStudentIDIndividual.setText("");
	    	tfAddStudentNameIndividual.setText("");
	    	tfAddStudentMajorIndividual.setText("");
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
