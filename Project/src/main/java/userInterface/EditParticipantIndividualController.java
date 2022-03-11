
package userInterface;

import java.io.IOException;
import java.util.LinkedList;

import competitionManager.Competition;
import competitionManager.IndividualBasedCompetition;
import competitionManager.Participant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditParticipantIndividualController {

	@FXML
	private Button btEditParticipantIndividual;

	@FXML
	private Label lbWarning;

	@FXML
	private Label lbCompetitionNameIndividual;

	@FXML
	private TextField tfEditStudentIDIndividual;

	@FXML
	private TextField tfEditStudentMajorIndividual;

	@FXML
	private TextField tfEditStudentNameIndividual;

	@FXML
	private TextField tfEditStudentRankIndividual;

	@FXML
	void closeWindow(ActionEvent event) {
		CommonMethods.closeWindow(event);
	}

	@FXML
	void editParticipant(ActionEvent event) throws IOException {
		try {
	    	String name = tfEditStudentNameIndividual.getText();
	    	String id = tfEditStudentIDIndividual.getText();
	    	String major = tfEditStudentMajorIndividual.getText();
	    	String rank = tfEditStudentRankIndividual.getText();
	    	// Check if id is numeric
	    	Integer.parseInt(id);
	    	LinkedList<Competition> competitions = Start.competitionManager.getCompetitions();
	    	IndividualBasedCompetition competition;
	    	int i;
	    	boolean competitionFound = false;
	    	for (i = 0; i < competitions.size(); i++) {
	    		if (competitions.get(i).getName().equals(lbCompetitionNameIndividual.getText())) {
	    			competitionFound = true;
	    			competition = (IndividualBasedCompetition) competitions.get(i);
	    			LinkedList<Participant> participants = competition.getParticipants();
	    			for (int j = 0; j < competition.getParticipantCount(); j++) {
	    				if (participants.get(j).getId().equals(tfEditStudentIDIndividual.getPromptText())) {
	    					Participant participant = participants.get(j);
	    					participant.setName(name);
	    					participant.setId(id);
	    					participant.setMajor(major);
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
	    	Start.primaryStage.setScene(new Scene(root));
    	}
    	catch (NumberFormatException e) {
    		lbWarning.setText("The ID and rank must only contain numbers");
    		lbWarning.setVisible(true);
    	}
	}

	void prepareTextfields(String name, String id, String major, int rank, String competitionName) {
		tfEditStudentNameIndividual.setText(name);
		tfEditStudentIDIndividual.setText(id);
		tfEditStudentMajorIndividual.setText(major);
		tfEditStudentRankIndividual.setText(rank + "");

		tfEditStudentNameIndividual.setPromptText(name);
		tfEditStudentIDIndividual.setPromptText(id);
		tfEditStudentMajorIndividual.setPromptText(major);
		tfEditStudentRankIndividual.setPromptText(rank + "");

		lbCompetitionNameIndividual.setText(competitionName);
	}
}
