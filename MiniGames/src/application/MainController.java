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

public class MainController {

    @FXML
    private Button sudokuButton; //Button to go to the sudoku page

    @FXML
    private Button leaderboardButton; //Button to go to the leaderboard page

    @FXML
    private Button tictactoeButton; //Button to go to the tic tac toe page
    
    //Change scene to sudoku
  	public void switchSudoku(ActionEvent event) throws IOException {
  		AnchorPane sudokuView = (AnchorPane)FXMLLoader.load(getClass().getResource("Sudoku.fxml"));
		Scene sudokuScene = new Scene(sudokuView);
		sudokuScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(sudokuScene);
		window.show();
  	}
  	
  	public void switchTicTacToe(ActionEvent event) throws IOException {
  		AnchorPane TicTacToeView = (AnchorPane)FXMLLoader.load(getClass().getResource("TicTacToe.fxml"));
		Scene TicTacToeScene = new Scene(TicTacToeView);
		TicTacToeScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(TicTacToeScene);
		window.show();
  	}
  	
  	public void switchLeaderboard(ActionEvent event) throws IOException {
  		AnchorPane LeaderboardView = (AnchorPane)FXMLLoader.load(getClass().getResource("ScoreScreen.fxml"));
		Scene LeaderboardScene = new Scene(LeaderboardView);
		LeaderboardScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(LeaderboardScene);
		window.show();
  	}
    
}
