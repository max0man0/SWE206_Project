package userInterface;

import java.io.IOException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {
	@FXML
    private Label lbTabCompetitionName;
	@FXML
	private ToggleGroup competitionType;
	@FXML
	private DatePicker dpCreateCompetitionDueDate;
	@FXML
	private TextField tfCreateCompetitionName;
	@FXML
	private TextField tfCreateCompetitionWebsite;
	@FXML
	private DatePicker dpEditCompetitionDueDate;
	@FXML
	private TextField tfEditCompetitionName;
	@FXML
	private TextField tfEditCompetitionWebsite;
	@FXML
	private TextField tfAddStudentIDTeam;
	@FXML
	private TextField tfAddStudentMajorTeam;
	@FXML
	private TextField tfAddStudentNameTeam;
	@FXML
	private TextField tfAddStudentTeam;
	@FXML
	private ComboBox<?> cbCompetitionIndividual;
	@FXML
	private TextField tfAddStudentIDIndividual;
	@FXML
	private TextField tfAddStudentMajorIndividual;
	@FXML
	private TextField tfAddStudentNameIndividual;
	@FXML
	private TextField tfEditStudentIDIndividual;
	@FXML
	private TextField tfEditStudentMajorIndividual;
	@FXML
	private TextField tfEditStudentNameIndividual;
	@FXML
	private TextField tfEditStudentRankIndividual;
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
	private TabPane tpCompetitions;
	@FXML
	private TableView<String> TV;

	@FXML
	void closeWindow(ActionEvent event) throws IOException {
		/*
		 * The only component that is not an instance of Node and is used to close the
		 * current window is the MenuItem "exit" in the main menu. So, terminating the
		 * program is an acceptable choice. Keep this in mind when using closeWindow()
		 * method.
		 */
		if (event.getSource() instanceof Node) {
			Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
			stage.close();
		}
		else {
			// TODO Ask if the user wants to save if they did not
			Platform.exit();
		}
	}

	@FXML
	void createCompetition(ActionEvent event) {
	}

	@FXML
	void editCompetition(ActionEvent event) {
	}

	@FXML
	void addParticipant(ActionEvent event) {
	}

	@FXML
	void editParticipant(ActionEvent event) {
	}

	@FXML
	void prepareEmail(ActionEvent event) {
	}

	@FXML
	void showAboutWindow(ActionEvent event) throws IOException {
		// TODO complete the CompetitionTab scene and try again
		VBox root = FXMLLoader.load(getClass().getResource("CompetitionTab.fxml"));
		Tab tab = new Tab("Competition1", root);
//		((Labeled)root.getChildren().get(0)).setText("Competetion1");
//		ObservableList<String> data = FXCollections.observableArrayList(
//				"momo","2019","swe","1"
//		);
//		((TableView<String>) root.getChildren().get(1)).setEditable(true);
//		((TableView<String>) root.getChildren().get(1)).setItems(data);
//		((TableView<String>) root.getChildren().get(1)).refresh();
		tpCompetitions.getTabs().add(tab);
	}

	@FXML
	void showAddParticipantWindow(ActionEvent event) throws IOException {
		// TODO Finish it after getting competitionManager from the other team
	}

	@FXML
	void showCreateCompetitionWindow(ActionEvent event) throws IOException {
		Stage createCompetitionWindow = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("Create_Competition.fxml"));
		Scene createCompetitionScene = new Scene(root);
		createCompetitionWindow.setScene(createCompetitionScene);
		createCompetitionWindow.setTitle("Create Competition");
		createCompetitionWindow.show();
	}

	@FXML
	void showEditCompetitionWindow(ActionEvent event) {
		// TODO Finish it after getting competitionManager from the other team
	}

	@FXML
	void undo(ActionEvent event) {
	}

	@FXML
	void writeExcel(ActionEvent event) {
	}

	@FXML
	void deleteParticipant(ActionEvent event) {
	}

	@FXML
	void deleteCompetition(ActionEvent event) {
	}
}