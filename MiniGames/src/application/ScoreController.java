package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ScoreController {
	@FXML
	private AnchorPane mainPane;
    @FXML
    private TableView<?> SudokuScores;
    @FXML
    private TableView<?> TicTacToeScores;
    @FXML
    private Button menu;
    @FXML
    private Button reset;
    
	@FXML
	public void goMainMenu( ActionEvent event ) throws IOException {//Returns user back to Main.fxml
		mainPane = FXMLLoader.load(getClass().getResource("Main.fxml")); //Goes to home pane
		Scene scene = new Scene(mainPane); //Pane you are going to show
		Node eventStage = (Node) event.getSource(); //Gets object where button was pressed
		Stage window = (Stage)eventStage.getScene().getWindow(); //Gets primaryStage Window
		window.setScene( scene );
		window.show();
	}//End method goHome

}
