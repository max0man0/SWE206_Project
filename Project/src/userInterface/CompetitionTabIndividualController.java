package userInterface;

import competitionManager.Competition;
import competitionManager.Participant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CompetitionTabIndividualController {

    @FXML
    private TableView<Participant> TV;

    @FXML
    private Label lbCompetitionDueDate;

    @FXML
    private Label lbCompetitionName;

    @FXML
    private Label lbCompetitionWebsite;

    @FXML
    private TableColumn<Participant, String> tcId;

    @FXML
    private TableColumn<Participant, String> tcMajor;

    @FXML
    private TableColumn<Participant, String> tcName;

    @FXML
    private TableColumn<Participant, Integer> tcRank;
    
    void competitionTab(int index) {
    	Competition competition = Main.competitionManager.getCompetitions().get(index);
    	lbCompetitionName.setText(competition.getName());
    	lbCompetitionWebsite.setText(competition.getLink().toString());
    	lbCompetitionDueDate.setText(competition.getDueDate().toString());
    	
    	tcId.setCellValueFactory(new PropertyValueFactory<>("Id"));
    	tcMajor.setCellValueFactory(new PropertyValueFactory<>("Major"));
    	tcName.setCellValueFactory(new PropertyValueFactory<>("Name"));
    	tcRank.setCellValueFactory(new PropertyValueFactory<>("Rank"));
    	ObservableList<Participant> data = FXCollections.observableArrayList(competition.getParticipants());
    	TV.setItems(data);
    }
}
