package userInterface;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.net.URL;
import java.time.LocalDate;

import competitionManager.CompetitionManager;


public class Main extends Application {
	static CompetitionManager competitionManager = new CompetitionManager();
	static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Main.competitionManager.createCompetition("1", new URL("http://s.com"), LocalDate.of(2021,12,1), false);
			Main.competitionManager.createCompetition("2", new URL("http://a.com"), LocalDate.of(2021,12,3), true);
			Main.primaryStage = primaryStage;
			Parent root= FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Competition Manager");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
