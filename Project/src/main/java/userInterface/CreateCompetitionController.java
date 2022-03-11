package userInterface;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class CreateCompetitionController implements Initializable {

	@FXML
	private Label lbWarning;

	@FXML
	private ToggleGroup competitionType;

	@FXML
	private DatePicker dpCreateCompetitionDueDate;

	@FXML
	private RadioButton rbIndividualBased;

	@FXML
	private RadioButton rbTeamBased;

	@FXML
	private TextField tfCreateCompetitionName;

	@FXML
	private TextField tfCreateCompetitionWebsite;

	@FXML
	void closeWindow(ActionEvent event) {
		CommonMethods.closeWindow(event);
	}

	@FXML
	void createCompetition(ActionEvent event) throws IOException {
		String competitionName = tfCreateCompetitionName.getText();
		if (competitionName.equals("")) {
			lbWarning.setText("The competition name is not entered");
			lbWarning.setVisible(true);
			return;
		}
		URL CompetitionWebsite = null;
		try {
			CompetitionWebsite = new URL(tfCreateCompetitionWebsite.getText());
			LocalDate localDate = dpCreateCompetitionDueDate.getValue();
			if (localDate == null) {
				lbWarning.setText("The competition due date is not selected");
				lbWarning.setVisible(true);
				return;
			}
			if (rbIndividualBased.isSelected()) {
				Start.competitionManager.createCompetition(competitionName, CompetitionWebsite, localDate, false);
			} else if (rbTeamBased.isSelected()) {
				Start.competitionManager.createCompetition(competitionName, CompetitionWebsite, localDate, true);
			} else {
				lbWarning.setText("Competition type is not selected");
				lbWarning.setVisible(true);
				return;
			}
			CommonMethods.closeWindow(event);

			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
			Parent root = loader.load();
			MainMenuController controller = loader.getController();
			controller.tpCompetitions.getSelectionModel().select(Start.competitionManager.getCompetitions().size() - 1);
			Start.primaryStage.setScene(new Scene(root));
		} catch (MalformedURLException e) {
			lbWarning.setText("The website is not in the correct format");
			lbWarning.setVisible(true);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		dpCreateCompetitionDueDate.setDayCellFactory(CommonMethods.disablePreviousDates);

	}
}