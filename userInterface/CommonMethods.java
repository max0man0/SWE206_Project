package userInterface;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class CommonMethods {
	public static void closeWindow(ActionEvent event){
		Stage stage = (Stage) ((Node) (event.getSource())).getScene().getWindow();
		stage.close();
	}
	
    public static void writeExcel(ActionEvent event) {
		// TODO
    }
}
