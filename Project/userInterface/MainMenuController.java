package userInterface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import competitionManager.Competition;
import competitionManager.IndividualBasedCompetition;
import competitionManager.TeamBasedCompetition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenuController implements Initializable {

    @FXML
    TabPane tpCompetitions;

    @FXML
    void closeWindow(ActionEvent event) throws IOException {
    	Stage SaveConfirmationMessageStage = new Stage();
    	SaveConfirmationMessageStage.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("SaveConfirmationMessage.fxml"));
		Scene SaveConfirmationMessageScene = new Scene(root);
		SaveConfirmationMessageStage.setScene(SaveConfirmationMessageScene);
		SaveConfirmationMessageStage.setTitle("Create Competition");
		SaveConfirmationMessageStage.show();
    }

    @FXML
    void showAboutWindow(ActionEvent event) throws IOException {
    	Stage helpStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("Help.fxml"));
		Scene helpScene = new Scene(root);
		helpStage.setScene(helpScene);
		helpStage.setTitle("Help");
		helpStage.show();
    }

    @FXML
    void showAddParticipantWindow(ActionEvent event) throws IOException {
    	int competitionIndex = tpCompetitions.getSelectionModel().getSelectedIndex();
    	Competition competition = Main.competitionManager.getCompetitions().get(competitionIndex);
    	if (competition instanceof TeamBasedCompetition) {
    		Stage teamWindow = new Stage();
    		teamWindow.initModality(Modality.APPLICATION_MODAL);
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("Add_ParticipantTeam.fxml"));
    		Parent root = loader.load();
    		AddParticipantTeamController controller = loader.getController();
    		controller.setCompetitionName(competition.getName());
    		Scene teamScene = new Scene(root);
    		teamWindow.setScene(teamScene);
    		teamWindow.setTitle("Add Participant");
    		teamWindow.show();
    	}
    	else {
    		Stage indWindow = new Stage();
    		indWindow.initModality(Modality.APPLICATION_MODAL);
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("Add_ParticipantIndividual.fxml"));
    		Parent root = loader.load();
    		AddParticipantIndividualController controller = loader.getController();
    		controller.setCompetitionName(competition.getName());
    		Scene indScene = new Scene(root);
    		indWindow.setScene(indScene);
    		indWindow.setTitle("Add Participant");
    		indWindow.show();
    	}
    }

    @FXML
    void showCreateCompetitionWindow(ActionEvent event) throws IOException {
    	Stage createCompetitionWindow = new Stage();
    	createCompetitionWindow.initModality(Modality.APPLICATION_MODAL);
		Parent root = FXMLLoader.load(getClass().getResource("Create_Competition.fxml"));
		Scene createCompetitionScene = new Scene(root);
		createCompetitionWindow.setScene(createCompetitionScene);
		createCompetitionWindow.setTitle("Create Competition");
		createCompetitionWindow.show();
    }

    @FXML
    void showEditCompetitionWindow(ActionEvent event) throws IOException {
    	int competitionIndex = tpCompetitions.getSelectionModel().getSelectedIndex();
    	Competition competition = Main.competitionManager.getCompetitions().get(competitionIndex);
    	Stage editCompetitionWindow = new Stage();
    	editCompetitionWindow.initModality(Modality.APPLICATION_MODAL);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Edit_Competition.fxml"));
		Parent root = loader.load();
		EditCompetitionController controller = loader.getController();
		controller.prepareTextfields(competition.getName(), competition.getLink(), competition.getDueDate());
		Scene editCompetitionScene = new Scene(root);
		editCompetitionWindow.setScene(editCompetitionScene);
		editCompetitionWindow.setTitle("Edit Competition");
		editCompetitionWindow.show();
    }
    
    @FXML
    void save(ActionEvent event) {
    	// TODO
    }
    
    void showCompetitionTabs() throws IOException {
    	for (int i = 0; i < Main.competitionManager.getCompetitions().size(); i++) {
    		if (Main.competitionManager.getCompetitions().get(i) instanceof IndividualBasedCompetition) {
	    		FXMLLoader CompetitionTabloader = new FXMLLoader(getClass().getResource("CompetitionTabIndividual.fxml"));
	        	Parent root = CompetitionTabloader.load();
	        	CompetitionTabIndividualController controller = CompetitionTabloader.getController();
	        	controller.competitionTab(i);
	        	Tab tab = new Tab(Main.competitionManager.getCompetitions().get(i).getName(), root);
	        	tab.setOnCloseRequest(e -> {
	        		e.consume();
	        		for (int j = 0; j < tpCompetitions.getTabs().size(); j++) {
	        			if (tpCompetitions.getTabs().get(j).equals(tab)) {
	        				try {
								deleteCompetition(j);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	        				break;
	        			}
	        		}
	        	});
	        	tpCompetitions.getTabs().add(tab);
    		}
    		else {
    			FXMLLoader CompetitionTabloader = new FXMLLoader(getClass().getResource("CompetitionTabTeam.fxml"));
	        	Parent root = CompetitionTabloader.load();
	        	CompetitionTabTeamController controller = CompetitionTabloader.getController();
	        	controller.competitionTab(i);
	        	Tab tab = new Tab(Main.competitionManager.getCompetitions().get(i).getName(), root);
	        	tab.setOnCloseRequest(e -> {
	        		e.consume();
	        		for (int j = 0; j < tpCompetitions.getTabs().size(); j++) {
	        			if (tpCompetitions.getTabs().get(j).equals(tab)) {
	        				try {
								deleteCompetition(j);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	        				break;
	        			}
	        		}
	        	});
	        	tpCompetitions.getTabs().add(tab);
    		}
    	}
    }

    void deleteCompetition(int competitionIndex) throws IOException {
		Stage deleteParticipantWindow = new Stage();
		deleteParticipantWindow.initModality(Modality.APPLICATION_MODAL);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Delete_Competition_Message.fxml"));
		Parent root = loader.load();
		DeleteCompetitionMessageController controller = loader.getController();
		controller.competition = Main.competitionManager.getCompetitions().get(competitionIndex);
		Scene deleteParticipantScene = new Scene(root);
		deleteParticipantWindow.setScene(deleteParticipantScene);
		deleteParticipantWindow.setTitle("Delete Confirmation Message");
		deleteParticipantWindow.show();
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			showCompetitionTabs();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}