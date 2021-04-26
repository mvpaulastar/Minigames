package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TicTacController implements Initializable {
	
	public static int currPlayer; // will store which player's turn it is
	public static int[][] board; // will store a 3D int array for pieces to be checked internally
	public static String player1; // will store player 1 username string
	public static String player2; // will store player 2 username string
	public static int[][] playerStats; // will store each player's stats
	
	@FXML
	private Label header;
	
	@FXML
	private Button piece1button;
	
	@FXML
	private Button piece2button;
	
	@FXML
	private Button piece3button;
	
	@FXML
	private Button piece4button;
	
	@FXML
	private Button piece5button;
	
	@FXML
	private Button piece6button;
	
	@FXML
	private Button piece7button;
	
	@FXML
	private Button piece8button;
	
	@FXML
	private Button piece9button;
	
	@FXML
	private Button playerLoginButton;
	
	@FXML
	private Button resetButton;
	
	@FXML
	private Button changeUsersButton;
	
	@FXML
	private Button homeButton;
	
	@FXML
	public TextField p1nameField;
	
	@FXML
	public TextField p2nameField;
	
	@FXML
	private Label tictactoeBackground;
	
	@FXML
	private Label messageDisplay;
	
	// This method will add a game piece to the board. It will then check if the game should end
	//		ActionEvent event: will be used to know which button was clicked that called this method
	@FXML
	void addPiece(ActionEvent event) { // adds a piece to the selected spot
		// variable declared
		Button btn; // will store the contents of the button that was pressed (so we know which one was pressed)
		int endOfGame; // will hold value if game is done or not
		int buttonRow; // will hold the row number of the button
		int buttonColumn; // will hold the column number of the button
		
		// start of method
		changeUsersButton.setDisable(true); // disables change user button so the user cannot change username in the middle of a game
		btn = (Button) event.getSource(); // get details of which button was clicked
		switch(currPlayer) { // checks which player is currently on
			case 1: // handles player 1
				addX(btn); // calls method to add an X to board
				break;
			case 2: // handles player 2
				addO(btn); // calls method to add an O to board
				break;
		}
		buttonColumn = Character.getNumericValue( btn.getId().charAt(5) ) - 1; // gets the button number that was clicked
		buttonRow = 0; // initializes row to 0
		while( buttonColumn > 2) // checks if column is greater than 3
		{
			buttonColumn -= 3; // decreases column # by 3
			buttonRow++; // increments row#
		}
		board[buttonRow][buttonColumn] = currPlayer; // adds the player's piece to the 3d array
		endOfGame = checkBoard(); // calls method to see if the game is over and saves return integer value into endOfGame variable
		switch( endOfGame ) { // checks the int value of endOfGame
			case 1: // Handles win-condition
				if( currPlayer == 1 ) { // checks if current player is player1
					messageDisplay.setText("The winner is " + player1);// displays player 1 as winner
					playerStats[0][0] += 1;
					playerStats[1][1] += 1;
				}
				else { // else current player is player 2
					messageDisplay.setText("The winner is " + player2);// displays player 2 as winner
					playerStats[0][1] += 1;
					playerStats[1][0] += 1;
				}
				if( updateUserFile(true) ) { // calls method to update users' stats to the text file and checks if successful
					changeUsersButton.setDisable(false); // since game ended, the user can now hit the change username button
				}
				break;
			case -1: // Handles tie-condition
				messageDisplay.setText("There was a tie!"); // displays tie message
				changeUsersButton.setDisable(false); // since game ended, the user can now hit the change username button
				// end game
				break;
			case 0: // Handles ongoing game
				if( currPlayer == 1) { // checks if current player is player 1
					currPlayer = 2; // passes turn to player 2
					messageDisplay.setText( player2 +"'s turn"); // displays its player 2's turn
				}
				else {
					currPlayer = 1; // passes turn to player 1
					messageDisplay.setText( player1 +"'s turn"); // display its player 1's turn
				}
				break;
		}
	}

	// This method will reset the game which clears the board of all pieces
	//		ActionEVent event: will not be used
	@FXML
	void resetGame(ActionEvent event) {
		clearBoard(); // calls method to clear the board and the 3D array
		startOfGame(); // calls method to enable game buttons and initialize first turn
		changeUsersButton.setDisable(false); // enables button to allow users to change username
	}
	
	// This method will login the user by adding their names into the stats text file as long as they enter both as alphabetical characters only
	//		ActionEvent event: will not be used
	
	@FXML
	void login(ActionEvent event) {
		// variable declared
		String regexPattern;
		
		// start of method
		regexPattern = "[a-zA-Z]+"; // will store regex patter where user can only use alphabetical characters
		player1 = p1nameField.getText(); // grabs player 1's name
		player2 = p2nameField.getText(); // grabs player 2's name
		if( player1.length() == 0 || player2.length() == 0 ) { // checks if either text field is blank
			messageDisplay.setText("Please fill out both player name fields"); // displays to the user that there is a blank text field
			return; // ends method
		}
		if( !( Pattern.matches( regexPattern,  player1 ) && Pattern.matches( regexPattern, player2 ) ) ) { // checks if either username does not match the regex pattern
			messageDisplay.setText("Player names must consist of only alphabetical characters"); // displays to the user that they can only use alphabetical characters
			return; // ends method
		}
		if( player1.equals(player2) ) {
			messageDisplay.setText("Enter different usernames for each player");
			return;
		}
		playerStats = new int[2][2]; // creates 3D array for user stats
		if( updateUserFile(false) ) { // calls method to add users to the text file and checks if successful
			startOfGame(); // calls method to enable game buttons and initialize first turn
			disableLogin( true, "" ); // calls method do enable login buttons
		}

	}

	// This method will let the players choose new usernames
	//		ActionEvent event: not used
	@FXML
	void changeUsers(ActionEvent event) {
		clearBoard(); // clears the board of any existing pieces (after a tie or a win)
		disableBoard( true ); // calls method that disables game piece buttons
		disableLogin( false, "Enter username"); // Calls method that allows users to login
	}
	
	// This method will update tictactoeStats.txt
	//		boolean endOfGame: will hold true if game has ended or false if starting
	// Returns true if successful, otherwise false for failure
	boolean updateUserFile( boolean endOfGame ) {
		// Variables declared
		File file; // will store file object for tictactoeStats.txt
		int usersFound;
		
		// Start of method
		file = new File("tictactoeStats.txt"); // links file object with the file itself
		if( !endOfGame ) {
			usersFound = findUsername(file);
			if( usersFound == -1 ) {
				return false;
			}
			if( usersFound == 0 ) {
				if( addUser( file, player1, 0, 0) ) {
					return addUser( file, player2, 0, 0);
				}
				return false;
			}
			if( usersFound == 1 ) {
				return addUser( file, player2, 0, 0 );
			}
			if( usersFound == 2 ) {
				return addUser( file, player1, 0, 0 );
			}
			return true;
		}
		try {
			createNewUserFile(file);
		}
		catch( IOException io ) {
			return false;
		}
		return true;
	}
	
	boolean createNewUserFile( File file ) throws IOException {
		// variable declared
		File tempFile; // will store temp file object for tictactoeStats.txt
		BufferedReader reader; // will store buffered reader object
		Pattern pattern;
		Matcher match;
		String lineContent;
		boolean repeat;
		
		// start of method
		messageDisplay.setText("");

		tempFile = new File("temp.txt");
		if( tempFile.exists() ) {
			if( !tempFile.delete() ) {
				messageDisplay.setText("Error: \"" + tempFile.getName() +"\" exists and was unable to be deleted");// displays error message to user if could not update stats to file
				return false;
			}
		}
		pattern = Pattern.compile("([a-zA-Z]+)\s([0-9]+)\s([0-9]+)");
		lineContent = "";
		repeat = true;
		try {
			reader = new BufferedReader( new FileReader(file) );
		}
		catch( IOException input ) {
			messageDisplay.setText("Error: could not load contents of \"" + file.getName() + "\"");// displays error message to user if could not update stats to file
			return false;
		}
		while( repeat ) {
			try {
				lineContent = reader.readLine();
			}
			catch( IOException input ) {
				reader.close();
				messageDisplay.setText("Error: could not load contents of \"" + file.getName() + "\"");// displays error message to user if could not update stats to file
				return false;
			}
			if( lineContent != null ) {
				match = pattern.matcher( lineContent );
				match.find();
				if( !addUser( tempFile, match.group(1), Integer.parseInt( match.group(2) ), Integer.parseInt( match.group(3) ) ) ) {
					reader.close();
					return false;
				}
			}
			else {
				repeat = false;
				reader.close();
			}
		}
		repeat = true;
		if( !file.delete() ) {
			messageDisplay.setText("Error: \"" + file.getName() +"\" exists and was unable to be deleted");// displays error message to user if could not update stats to file
			return false;
		}
		try {
			reader = new BufferedReader( new FileReader(tempFile) );
		}
		catch( IOException input ) {
			messageDisplay.setText("Error: could not load contents of \"" + tempFile.getName() + "\"");// displays error message to user if could not update stats to file
			return false;
		}
		while( repeat ) {
			try {
				lineContent = reader.readLine();
			}
			catch( IOException input ) {
				reader.close();
				messageDisplay.setText("Error: could not load contents of \"" + tempFile.getName() + "\"");// displays error message to user if could not update stats to file
				return false;
			}
			if( lineContent != null ) {
				match = pattern.matcher( lineContent );
				match.find();
				if( !( player1.equals( match.group(1) ) || player2.equals( match.group(1) ) ) ) { // checks if pattern group 0 (username) does not match either current one
					if( !addUser( file, match.group(1), Integer.parseInt( match.group(2) ), Integer.parseInt( match.group(3) ) ) ) {
						reader.close();
						return false;
					}
				}
			}
			else {
				reader.close();
				if( !( addUser( file, player1, playerStats[0][0], playerStats[0][1] ) && addUser( file, player2, playerStats[1][0], playerStats[1][1] ) ) ){
					return false;
				}				
				repeat = false;
			}
		}
		if( !tempFile.delete() ) {
			messageDisplay.setText("Error: \"" + tempFile.getName() +"\" exists and was unable to be deleted");// displays error message to user if could not update stats to file
			return false;
		}
		return true;
	}
	
	int findUsername(File file) {
		// variables declared

		Pattern pattern;
		Matcher match;
		String lineContent;
		BufferedReader reader; // will store buffered reader object
		int usersFound;
		
		// start of method
		usersFound = 0;
		if( !file.exists() ) {
			return usersFound;
		}
		try {
			reader = new BufferedReader( new FileReader(file) );
			pattern = Pattern.compile("([a-zA-Z]+)\s([0-9]+)\s([0-9]+)");
			lineContent = reader.readLine();
			while( lineContent != null ) {
				match = pattern.matcher( lineContent );
				match.find();
				if( player1.equals( match.group(1).toString() ) ) {
					usersFound += 1;
					playerStats[0][0] = Integer.parseInt(match.group(2) );
					playerStats[0][1] = Integer.parseInt(match.group(3) );
				}
				if( player2.equals( match.group(1) ) ) {
					usersFound += 2;
					playerStats[1][0] = Integer.parseInt(match.group(2) );
					playerStats[1][1] = Integer.parseInt(match.group(3) );
				}
				if( usersFound == 3 ) {
					lineContent = null;
					reader.close();
				}
				else {
					lineContent = reader.readLine();
				}
			}
			reader.close();
		}
		catch( IOException input ) {
			messageDisplay.setText("Error: could not load contents of \"tictactoeStats.txt\"");// displays error message to user if could not update stats to file
			return -1;
		}
		return usersFound;
	}
	
	boolean addUser( File file, String playerName , int wins, int losses ) {
		// variable declared
		FileWriter writer;

		// Start of method
		try {
			writer = new FileWriter(file, true);
			writer.write( playerName + " " + wins + " " + losses + "\n" );
			writer.close();
		}
		catch( IOException ouput) {
			messageDisplay.setText("Error: could not append to \"" + file.getName() + "\"");
			return false;
		}
		return true;
	}
	
	
	// This method enables or disables the game piece buttons
	//		boolean disable: will be used to disable game piece buttons if true or enable them if false
	void disableBoard( boolean disable ) {
		piece1button.setDisable(disable);
		piece2button.setDisable(disable);
		piece3button.setDisable(disable);
		piece4button.setDisable(disable);
		piece5button.setDisable(disable);
		piece6button.setDisable(disable);
		piece7button.setDisable(disable);
		piece8button.setDisable(disable);
		piece9button.setDisable(disable);
	}
	
	// This method will clear the board and the 3D array
	void clearBoard() {
		for(int i=0; i < 3; i++) { // iterates through each row
			for(int j=0; j < 3; j++) { // iterates through each column
				board[i][j] = 0; // sets integer at index to zero
			}
		}
		piece1button.setText(""); // clears game piece button
		piece2button.setText(""); // clears game piece button
		piece3button.setText(""); // clears game piece button
		piece4button.setText(""); // clears game piece button
		piece5button.setText(""); // clears game piece button
		piece6button.setText(""); // clears game piece button
		piece7button.setText(""); // clears game piece button
		piece8button.setText(""); // clears game piece button
		piece9button.setText(""); // clears game piece button
	}
	
	// This method will enable game buttons and initialize first turn
	void startOfGame() {
		disableBoard(false); // calls method to enable game piece buttons
		messageDisplay.setText( player1 + "'s turn"); // displays its player 1's turn
		currPlayer = 1; // sets turn to player 1
	}
	
	// This method will disable or enable the login process
	//		boolean disable: if true, the login process will disable and enable game piece buttons. If false, the opposite occurs
	//		String prompText: will store the prompt text string for username text fields
	void disableLogin( boolean disable, String promptText) {
		p1nameField.setPromptText(promptText); // sets username text field's prompt text to string argument
		p2nameField.setPromptText(promptText); // sets username text field's prompt text to string argument
		p1nameField.setDisable(disable); // disables or enables the username text field
		p2nameField.setDisable(disable); // disables or enables the username text field
		playerLoginButton.setDisable(disable); // disables or enables the login button
		resetButton.setDisable(!disable); // enables or disables the reset game button
		changeUsersButton.setDisable(!disable); // enables or disables change users button
		disableBoard(!disable); // enables or disables board
		if( disable ) { // checks if login is to be disables
			p1nameField.clear(); // clears username text field
			p2nameField.clear(); // clears username text field
		}
		else { // happens if login is to be enables
			messageDisplay.setText("Please enter each player's username to start"); // displays to the user that they need to login
		}
	}

	// This method will add X to the board
	//		Button btn: this is the button that will have the X added to it
	void addX(Button btn){
		btn.setDisable(true); // disables button so it cannot be re-clicked
		btn.setText("X"); // Adds 'X' to the button
		btn.setTextFill(Color.RED); // colors the 'X' red
	}
	
	// This method will add O to the board
	//		Button btn: this is the button that will have the O added to it
	void addO(Button btn) {
		btn.setDisable(true); // disables button so it cannot be re-clicked
		btn.setText("O"); // Adds 'O' to the button
		btn.setTextFill(Color.BLUE); // colors the 'O' blue
	}
	
	// This method checks the board for a win
	// Returns: -1 if tie, 0 if game is ongoing, 1 if there is a win
	int checkBoard() {
		if( foundWinner() ) { // method will be called to see if there is a winner
			disableBoard(true); // calls method to enable login buttons and disable game buttons
			return 1; // returns 1 if there is a winner
		}
		if( isATie() ) { // method will check for a tie
			return -1; // returns -1 if there is a tie
		}
		return 0; // returns 0 if the game is still going on
	}
	
	// This method will check if a win condition is met
	// Returns true if there is a winner or false if no winner
	boolean foundWinner() {
		for( int playerNum = 1; playerNum <= 2; playerNum++ ) { // will check each player's value in the board
			for(int i=0; i < 3; i++ ) { // iterates through each row
				if( board[i][0] == playerNum && board[i][1] == playerNum && board[i][2] == playerNum) { // checks if player won by row 
					return true;
				}
			}
			for(int j=0; j < 3; j++ ) {
				if( board[0][j] == playerNum && board[1][j] == playerNum && board[2][j] == playerNum) { // checks if player won by column 
					return true;
				}
			}
		}
		if( board[1][1] != 0 ) { // checks if there is a middle piece set
			// checks if player won by diagonal
			if( ( board[1][1] == board[0][0] && board[1][1] == board[2][2] ) || ( ( board[1][1] == board[0][2] && board[1][1] == board[2][0] ) ) ) {
				return true; // returns true if there is a diagonal match
			}
		}
		return false; // returns false due to no win-condition met
	}

	// This method will check if there is a tie by seeing if all pieces have been used
	// Returns true if all pieces are taken up or false if spots or available
	boolean isATie() {
		for(int i=0; i < 3; i++) { // iterates through each row
			for(int j=0; j < 3; j++) { // iterates through each column
				if( board[i][j] == 0 ) { //  checks if there is a zero (blank spot)
					return false; // returns false to indicate there is a blank spot
				}
			}
		}
		return true; // returns true to indicate there are no more blank spots
	}

    //switch to Home menu
    public void switchHome(ActionEvent event) throws IOException {
		AnchorPane mainView = (AnchorPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene mainScene = new Scene(mainView);
		mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		window.setScene(mainScene);
		window.show();
	}
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		board = new int[3][3]; // creates 3D array
		disableBoard(true); // calls method to enable login buttons and disable game buttons
		clearBoard(); // calls method to clear the board of all pieces
		disableLogin(false, "Enter username"); // calls method that enables the login process
		playerLoginButton.setText("Start Game"); 
		resetButton.setText("Reset");
		changeUsersButton.setText("Change Users");
		homeButton.setText("Home");
	}
	
	
}