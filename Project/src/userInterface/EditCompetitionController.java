package userInterface;

import java.net.URL;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class EditCompetitionController {

    @FXML
    private DatePicker dpEditCompetitionDueDate;

    @FXML
    private TextField tfEditCompetitionName;

    @FXML
    private TextField tfEditCompetitionWebsite;

    @FXML
    void closeWindow(ActionEvent event) {
    	CommonMethods.closeWindow(event);
    }

    @FXML
    void editCompetition(ActionEvent event) {

    }
    
    void prepareTextfields(String name, URL website, LocalDate dueDate) {
    	tfEditCompetitionName.setText(name);
    	tfEditCompetitionWebsite.setText(website.toString());
    	dpEditCompetitionDueDate.setValue(dueDate);
    }

}
