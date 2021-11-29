package userInterface;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class MainController {

	@FXML
	private ToggleGroup competitionType;

	@FXML
	private DatePicker dpDueDate;

	@FXML
	private TextField tfName;

	@FXML
	private TextField tfWebsite;
	
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
	void closeWindow(ActionEvent event) {
		Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		stage.close();
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
    void showAboutWindow(ActionEvent event) {

    }

    @FXML
    void showAddParticipantWindow(ActionEvent event) {

    }

    @FXML
    void showCreateCompetitionWindow(ActionEvent event) {

    }

    @FXML
    void showEditCompetitionWindow(ActionEvent event) {

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
