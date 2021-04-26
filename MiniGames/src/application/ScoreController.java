/* This controller will control all actions taken in the ScoreScreen.fxml scene
 * Worked on by Paula Sirisumpund, sol203
 */
package application;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ScoreController implements Initializable {
	@FXML
	private AnchorPane mainPane;
    @FXML
    private TableView<Player> SudokuScores;
    @FXML
    private TableColumn<Player, String> sudPlayerCol;
    @FXML
    private TableColumn<Player, Integer> sudTimeCol;
    @FXML
    private TableView<Player> TicTacToeScores;
    @FXML
    private TableColumn<Player, Integer> tttWinsCol;
    @FXML
    private TableColumn<Player, Integer> tttLossesCol;
    @FXML
    private TableColumn<Player, String> tttPlayerCol;
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
		//Clears the sudokuStats.txt file
		PrintWriter pw = new PrintWriter("sudokuStats.txt");
		pw.close();
		//Clears the tictactoeStats.txt file
		pw = new PrintWriter("tictactoeStats.txt");
		pw.close(); 
		
		//Reloads the tableviews
		mainPane = FXMLLoader.load(getClass().getResource("ScoreScreen.fxml")); //Goes to home pane
		Scene scene = new Scene(mainPane); //Pane you are going to show
		Node eventStage = (Node) event.getSource(); //Gets object where button was pressed
		Stage window = (Stage)eventStage.getScene().getWindow(); //Gets primaryStage Window
		window.setScene( scene );
		window.show();
		
		//Shows confirmation alert of stats being reset.
		Alert a = new Alert(AlertType.NONE);
		a.setAlertType(AlertType.CONFIRMATION);// set alert type
		a.setContentText("All user statistics have been reset!");
		a.show();// show the dialog	
	}//End method reset stats
	
	//Populates a list with sudoku player objects to be displayed on tableview
	private ArrayList<Player> parseSudPlayerList() throws IOException{
		File file = new File("sudokuStats.txt"); //Opens file to be read
		Scanner scan = new Scanner(file ); //Open file to be read
		String temp; //Temp string to hold and parse file lines
		ArrayList<Player> list = new ArrayList<Player>(); //List to hold parsed sudoku players
		
		while( scan.hasNextLine() ) { //Loops through file to populate arraylist
			temp = scan.nextLine(); //Obtains a line in the file
			String[] tempStrArr = temp.split( "\\\\" ); //parses and splits info
			Player newPlayer = new Player( tempStrArr[0],  Integer.parseInt(tempStrArr[1]) ); //player object to add to list
			list.add( newPlayer ); //adds new player to arraylist
		}
		
		scan.close(); //close file being read
		return list;
	}//End method parseSudPlayerList()
	
	//Populates a list with tictactoe player objects to be displayed on tableview
	private ArrayList<Player> parseTicTacToePlayerList() throws IOException{
		File file = new File("tictactoeStats.txt"); //Opens file to be read
		Scanner scan = new Scanner(file ); //Open file to be read
		String temp; //Temp string to hold and parse file lines
		ArrayList<Player> list = new ArrayList<Player>(); //List to hold parsed sudoku players
		
		while( scan.hasNextLine() ) { //Loops through file to populate arraylist
			temp = scan.nextLine(); //Obtains a line in the file
			String[] tempStrArr = temp.split( "\\\\" ); //parses and splits info
			Player newPlayer = new Player( tempStrArr[0],  Integer.parseInt(tempStrArr[1]), Integer.parseInt(tempStrArr[2])  ); //player object to add to list
			list.add( newPlayer ); //adds new player to arraylist
		}
		
		scan.close(); //close file being read
		return list;
	}//End method parseTicTacToePlayerList
	
	//Runs and populates the tableviews on scene startup
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { //Populates the tableviews of the leaderboard page
		//Tells the sudoku columns what they will be holding
		sudPlayerCol.setCellValueFactory( new PropertyValueFactory<Player, String>("Username") );
		sudTimeCol.setCellValueFactory( new PropertyValueFactory<Player, Integer>("sudTimeCompleted") );
		//Tells the tictactoe columns what they will be holding
		tttPlayerCol.setCellValueFactory( new PropertyValueFactory<Player, String>("Username") );
		tttWinsCol.setCellValueFactory( new PropertyValueFactory<Player, Integer>("tttWins") );
		tttLossesCol.setCellValueFactory( new PropertyValueFactory<Player, Integer>("tttLosses") );
		
		//Grabs the player object list that will be used to populate the tableview
		try {
			SudokuScores.getItems().setAll(parseSudPlayerList()); //Grabs the appropriate array list and populates the tableview 
			TicTacToeScores.getItems().setAll(parseTicTacToePlayerList());//Grabs the appropriate array list and populates the tableview 
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}//End method initialize
}
