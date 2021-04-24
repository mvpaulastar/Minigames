package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TicTacController {

    @FXML
    private Button homeButton;
    
    
    
    
    
    //switch to Home menu
    public void switchHome(ActionEvent event) throws IOException {
		AnchorPane mainView = (AnchorPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene mainScene = new Scene(mainView);
		mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(mainScene);
		window.show();
	}
}
