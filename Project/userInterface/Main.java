package userInterface;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;

import competitionManager.Competition;
import competitionManager.CompetitionManager;


public class Main extends Application {
	static CompetitionManager competitionManager = new CompetitionManager();
	static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Main.competitionManager.createCompetition("1", new URL("http://google.com"), LocalDate.of(2021,12,1), false);
			HashMap<String, String> hashmap = new HashMap<>();
			hashmap.put("Name", "name");
			hashmap.put("Id", "201911111");
			hashmap.put("Major", "ICS");
			Main.competitionManager.getCompetitions().get(0).addParticipant(hashmap);
			Main.competitionManager.createCompetition("2", new URL("http://a.com"), LocalDate.of(2021,12,3), true);
			hashmap.put("Name", "name");
			hashmap.put("Id", "201911111");
			hashmap.put("Major", "ICS");
			hashmap.put("TeamName", "team ics");
			Main.competitionManager.getCompetitions().get(1).addParticipant(hashmap);
			hashmap.put("Name", "name2");
			hashmap.put("Id", "201900000");
			hashmap.put("Major", "ICS");
			hashmap.put("TeamName", "team ics");
			Main.competitionManager.getCompetitions().get(1).addParticipant(hashmap);
			
			
			Main.primaryStage = primaryStage;
			Parent mainRoot= FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(mainRoot);
			
			primaryStage.setOnCloseRequest(e -> {
				e.consume();
				Stage SaveConfirmationMessageStage = new Stage();
		    	SaveConfirmationMessageStage.initModality(Modality.APPLICATION_MODAL);
				Parent saveRoot = null;
				try {
					saveRoot = FXMLLoader.load(getClass().getResource("SaveConfirmationMessage.fxml"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Scene SaveConfirmationMessageScene = new Scene(saveRoot);
				SaveConfirmationMessageStage.setScene(SaveConfirmationMessageScene);
				SaveConfirmationMessageStage.setTitle("Create Competition");
				SaveConfirmationMessageStage.show();
			});
			primaryStage.setScene(scene);
			primaryStage.setTitle("Competition Manager");
			primaryStage.show();
			
			boolean alertShown = false;
			for (int i = 0; i < competitionManager.getCompetitions().size() && !alertShown; i++) {
				Competition competition = competitionManager.getCompetitions().get(i);
				if (competition.getDueDate().compareTo(LocalDate.now()) <= 0) {
					for (int j = 0; j < competition.getParticipantCount() && !alertShown; j++) {
						if (competition.getParticipants().get(j).getRank() == 0) {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("Not All Winners Are Updated");
							alert.setHeaderText("Some participants' rank did not get updated");
							alert.initOwner(primaryStage);
							alert.show();
							alertShown = true;
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
