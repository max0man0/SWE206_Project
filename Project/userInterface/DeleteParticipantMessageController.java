package userInterface;

import java.io.IOException;

import competitionManager.Competition;
import competitionManager.Participant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class DeleteParticipantMessageController {

	Participant participant;
	
	Competition competition;
	
    @FXML
    void closeWindow(ActionEvent event) {
    	CommonMethods.closeWindow(event);
    }

    @FXML
    void deleteParticipant(ActionEvent event) throws IOException {
    	competition.deleteParticipant(participant);
    	CommonMethods.closeWindow(event);
    	
    	int i;
    	for (i = 0; i < Main.competitionManager.getCompetitions().size(); i++) {
    		if (Main.competitionManager.getCompetitions().get(i) == competition) {
    			break;
    		}
    	}
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
    	Parent root = loader.load();
    	MainMenuController controller = loader.getController();
    	controller.tpCompetitions.getSelectionModel().select(i);
    	Main.primaryStage.setScene(new Scene(root));
    }
}
