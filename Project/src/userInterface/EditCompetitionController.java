package userInterface;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

import competitionManager.Competition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditCompetitionController {

	@FXML
	private Label lbWarning;
	
    @FXML
    private DatePicker dpEditCompetitionDueDate;

    @FXML
    private TextField tfEditCompetitionName;

    @FXML
    private TextField tfEditCompetitionWebsite;

    @FXML
    void closeWindow(ActionEvent event) {
    	CommonMethods.closeWindow(event);
    }

    @FXML
    void editCompetition(ActionEvent event) throws IOException {
    	String competitionName = tfEditCompetitionName.getText();
    	if (competitionName.equals("")) {
    		lbWarning.setText("The competition name is not entered");
    		lbWarning.setVisible(true);
    		return;
    	}
    	URL CompetitionWebsite = null;
		try {
			CompetitionWebsite = new URL(tfEditCompetitionWebsite.getText());
			LocalDate localDate = dpEditCompetitionDueDate.getValue();
	    	CommonMethods.closeWindow(event);
	    	int i;
	    	for (i = 0; i < Main.competitionManager.getCompetitions().size(); i++) {
	    		if (Main.competitionManager.getCompetitions().get(i).getName().equals(tfEditCompetitionName.getPromptText())) {
	    			Competition competition = Main.competitionManager.getCompetitions().get(i);
	    			competition.setName(competitionName);
	    			competition.setLink(CompetitionWebsite);
	    			competition.setDueDate(localDate);
	    			break;
	    		}
	    	}
	    	
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
	    	Parent root = loader.load();
	    	MainMenuController controller = loader.getController();
	    	controller.tpCompetitions.getSelectionModel().select(i);
	    	Main.primaryStage.setScene(new Scene(root));
		}
		catch (MalformedURLException e) {
			lbWarning.setText("The website is not in the correct format");
			lbWarning.setVisible(true);
		}
    }
    
    void prepareTextfields(String name, URL website, LocalDate dueDate) {
    	tfEditCompetitionName.setText(name);
    	tfEditCompetitionWebsite.setText(website.toString());
    	dpEditCompetitionDueDate.setValue(dueDate);
    	tfEditCompetitionName.setPromptText(name);
    	tfEditCompetitionWebsite.setPromptText(website.toString());
    	dpEditCompetitionDueDate.setPromptText(dueDate.toString());
    }

}
