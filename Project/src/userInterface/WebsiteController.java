package userInterface;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class WebsiteController implements Initializable {

	static URL competitionUrl;
	
	private WebEngine engine;
	
    @FXML
    private WebView wvCompetitionWebsite;

    @FXML
    private Label lbUrl;
    
    @FXML
    void next(ActionEvent event) {
    	WebHistory history = engine.getHistory();
    	history.go(1);
    	lbUrl.setText(engine.getLocation());
    }

    @FXML
    void previous(ActionEvent event) {
    	WebHistory history = engine.getHistory();
    	history.go(-1);
    	lbUrl.setText(engine.getLocation());
    }

    @FXML
    void refresh(ActionEvent event) {
    	engine.reload();
    	lbUrl.setText(engine.getLocation());
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		engine = wvCompetitionWebsite.getEngine();
		engine.load(competitionUrl.toString());
		lbUrl.setText(engine.getLocation());
		engine.setOnStatusChanged(e -> {
			lbUrl.setText(engine.getLocation());
		});
	}

}
