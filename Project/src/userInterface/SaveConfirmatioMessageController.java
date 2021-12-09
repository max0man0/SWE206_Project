package userInterface;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SaveConfirmatioMessageController {

    @FXML
    void closeWindow(ActionEvent event) {
    	CommonMethods.closeWindow(event);
    }

    @FXML
    void save(ActionEvent event) {
    	// TODO
    }

    @FXML
    void terminate(ActionEvent event) {
    	Platform.exit();
    }

}
