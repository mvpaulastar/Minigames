/* This controller will control all actions taken in the ScoreScreen.fxml scene
 * Worked on by Paula Sirisumpund, sol203
 */
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ScoreController implements Initializable {
	@FXML
	private AnchorPane mainPane;
    @FXML
    private TableView<?> SudokuScores;
    @FXML
    private TableColumn<?, ?> sudPlayerCol;
    @FXML
    private TableColumn<?, ?> sudTimeCol;
    @FXML
    private TableView<?> TicTacToeScores;
    @FXML
    private TableColumn<?, ?> tttWinsCol;
    @FXML
    private TableColumn<?, ?> tttLossesCol;
    @FXML
    private TableColumn<?, ?> tttPlayerCol;
    @FXML
    private Button reset;
    @FXML
    private Button menu;
    
	@FXML //Returns user back to Main.fxml
	public void goMainMenu( ActionEvent event ) throws IOException {
		mainPane = FXMLLoader.load(getClass().getResource("Main.fxml")); //Goes to home pane
		Scene scene = new Scene(mainPane); //Pane you are going to show
		Node eventStage = (Node) event.getSource(); //Gets object where button was pressed
		Stage window = (Stage)eventStage.getScene().getWindow(); //Gets primaryStage Window
		window.setScene( scene );
		window.show();
	}//End method goHome
	
	@FXML //This will reset the statistics of users in both the sudoku and tictactoe leaderboards
	public void resetStats( ActionEvent event ) throws IOException {
		//Shows confirmation alert of stats being reset.
		Alert a = new Alert(AlertType.NONE);
		a.setAlertType(AlertType.CONFIRMATION);// set alert type
		a.setContentText("All user statistics have been reset!");
		a.show();// show the dialog	
	}//End method reset stats
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { //Populates the tableviews of the leaderboard page
		
	}//End method initialize
}
