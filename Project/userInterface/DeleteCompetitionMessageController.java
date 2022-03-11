package userInterface;

import java.io.IOException;

import competitionManager.Competition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class DeleteCompetitionMessageController {

	Competition competition;
	
    @FXML
    void closeWindow(ActionEvent event) {
    	CommonMethods.closeWindow(event);
    }

    @FXML
    void deleteCompetition(ActionEvent event) throws IOException {
    	Main.competitionManager.deleteCompetition(competition);
    	CommonMethods.closeWindow(event);
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
    	Parent root = loader.load();
    	MainMenuController controller = loader.getController();
    	controller.tpCompetitions.getSelectionModel().select(Main.competitionManager.getCompetitions().size()-1);
    	Main.primaryStage.setScene(new Scene(root));
    }

}
