package userInterface;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import competitionManager.Competition;
import competitionManager.Participant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CompetitionTabIndividualController {

	Competition competition;

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
		this.competition = competition;
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

	@FXML
	void editParticipant(ActionEvent event) throws IOException {
		int participantIndex = TV.getSelectionModel().getSelectedIndex();
		Participant participant = competition.getParticipants().get(participantIndex);
		Stage editParticipantWindow = new Stage();
		editParticipantWindow.initModality(Modality.APPLICATION_MODAL);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Edit_ParticipantIndividual.fxml"));
		Parent root = loader.load();
		EditParticipantIndividualController controller = loader.getController();
		controller.prepareTextfields(participant.getName(), participant.getId(), participant.getMajor(),
				participant.getRank(), competition.getName());
		Scene editParticipantScene = new Scene(root);
		editParticipantWindow.setScene(editParticipantScene);
		editParticipantWindow.setTitle("Edit Participant");
		editParticipantWindow.show();
	}

	@FXML
	void deleteParticipant(ActionEvent event) throws IOException {
		int participantIndex = TV.getSelectionModel().getSelectedIndex();
		Participant participant = competition.getParticipants().get(participantIndex);
		Stage deleteParticipantWindow = new Stage();
		deleteParticipantWindow.initModality(Modality.APPLICATION_MODAL);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Delete_Participant_Message.fxml"));
		Parent root = loader.load();
		DeleteParticipantMessageController controller = loader.getController();
		controller.competition = competition;
		controller.participant = participant;
		Scene deleteParticipantScene = new Scene(root);
		deleteParticipantWindow.setScene(deleteParticipantScene);
		deleteParticipantWindow.setTitle("Delete Confirmation Message");
		deleteParticipantWindow.show();
	}
	
	@FXML
	void browseCompetitionWebsite() throws IOException {
    	Stage competitionWebsiteWindow = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Website.fxml"));
		WebsiteController.competitionUrl = competition.getLink();
		Parent root = loader.load();
		Scene competitionWebsiteScene = new Scene(root);
		competitionWebsiteWindow.setScene(competitionWebsiteScene);
		competitionWebsiteWindow.setTitle(competition.getName() + "'s Website");
		competitionWebsiteWindow.show();
	}

	@FXML
	void prepareEmail(ActionEvent event) throws IOException {
		Desktop desktop = Desktop.getDesktop();
		int participantIndex = TV.getSelectionModel().getSelectedIndex();
		Participant participant = competition.getParticipants().get(participantIndex);
		String subject = "subject=Congratulation on achieving " + participant.getRank() + " place in " + competition.getName()+"&";
		String body = "body=Dear " + participant.getName() + ",\n"
				+ "\n"
				+ "Conguratulation on your achievement in "+competition.getName()+". This achievement is deeply appreciated by the unversity and we will announce it in the appropriate medias.\n"
				+ "\n"
				+ "In case you have Photos you want to share with the news post, reply to this email with the photos.\n"
				+ "\n"
				+ "Regards and Congrats,\n"
				+ "KFUPM News Team";
		String message = "mailto:s" + participant.getId() + "@kfupm.edu.sa?" 
					+ subject.replaceAll(" ", "%20")
					+ body.replaceAll(" ", "%20").replaceAll("\n", "%0A");
					
		URI uri = URI.create(message);
		desktop.mail(uri);
	}
}
