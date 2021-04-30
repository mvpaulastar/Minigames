package application;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SudokuController {
	
	public static String userName; //lets user add in name
	
	public static long start; //to start timer
	
	private static DecimalFormat df2 = new DecimalFormat("#.##"); //sets timer to two decimal places.
    
	@FXML
    private Button checkButton; //used to check if sudoku is correct

    @FXML
    private TextField nameField; //used to add name
    
    @FXML
    private Button clearButton; //used to clear sudoku

    @FXML
    private Button submitButton; //used to submit name for leaderboard
    
    @FXML
    private Button homeButton; //used to let user go to main menu 

    @FXML
    private Label resultLabel; //used to display messages
    
    @FXML
    private Label timer; //used to time progress

    //fields to enter numbers for sudoku
    @FXML
    private TextField f0;

    @FXML
    private TextField f3;

    @FXML
    private TextField b0;

    @FXML
    private TextField f4;

    @FXML
    private TextField f5;

    @FXML
    private TextField b2;

    @FXML
    private TextField f6;

    @FXML
    private TextField b3;

    @FXML
    private TextField f7;

    @FXML
    private TextField b4;

    @FXML
    private TextField f8;

    @FXML
    private TextField b5;

    @FXML
    private TextField b6;

    @FXML
    private TextField b7;

    @FXML
    private TextField b8;

    @FXML
    private TextField g0;

    @FXML
    private TextField g1;

    @FXML
    private TextField g2;

    @FXML
    private TextField g3;

    @FXML
    private TextField c0;

    @FXML
    private TextField g5;

    @FXML
    private TextField c2;

    @FXML
    private TextField g6;

    @FXML
    private TextField c3;

    @FXML
    private TextField g8;

    @FXML
    private TextField c5;

    @FXML
    private TextField c6;

    @FXML
    private TextField c7;

    @FXML
    private TextField h0;

    @FXML
    private TextField h1;

    @FXML
    private TextField h2;

    @FXML
    private TextField d0;

    @FXML
    private TextField d1;

    @FXML
    private TextField h5;

    @FXML
    private TextField d2;

    @FXML
    private TextField h6;

    @FXML
    private TextField d3;

    @FXML
    private TextField d4;

    @FXML
    private TextField h8;

    @FXML
    private TextField d7;

    @FXML
    private TextField i1;

    @FXML
    private TextField i3;

    @FXML
    private TextField e0;

    @FXML
    private TextField i4;

    @FXML
    private TextField e2;

    @FXML
    private TextField i6;

    @FXML
    private TextField e3;

    @FXML
    private TextField i7;

    @FXML
    private TextField e4;

    @FXML
    private TextField a1;

    @FXML
    private TextField e5;

    @FXML
    private TextField a2;

    @FXML
    private TextField e6;

    @FXML
    private TextField e8;

    @FXML
    private TextField a6;

    @FXML
    private TextField a7;

    @FXML
    private TextField a8;
    
    @FXML
	void login(ActionEvent event) {
		// variable declared
		
		// start of method
		userName = nameField.getText(); // grabs player's name
		if( userName.length() == 0 ) { // checks if either text field is blank
			resultLabel.setText("Please fill out player name"); // displays to the user that there is a blank text field
			return; // ends method
		} else {
			start = System.currentTimeMillis();//starts timer for sudoku
			for (int i = 0; i <5; i++) {
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {
						e.printStackTrace();
				}
			}
			resultLabel.setText("Start!"); // Start prompt
			nameField.clear();
		}
		
	}
    
    //checks if the game is correct
    public void checkResult(ActionEvent event) {
    	//array of correct answers
    	int[] solution = new int[] {5, 9, 4, 6, 1, 6, 7, 9, 4, 1, 2, 5, 8, 8, 4, 2, 6, 3, 7, 9, 6, 3, 8, 2, 1, 1, 5, 6, 7, 
    			3, 8, 4, 7, 1, 9, 5, 6, 2, 3, 3, 7, 6, 4, 9, 5, 2, 4, 8, 1, 2, 9, 7, 9, 7, 3, 1, 4};
    	//int array of user answers
    	int[] userAnswers = new int[57];
    	
    	//String array for inputs
    	String[] inputString = new String[57];
    	
    	//gets inputs from user and inputs them into the string
    	inputString[0] = a1.getText();
    	inputString[1] = a2.getText();
    	inputString[2] = a6.getText();
    	inputString[3] = a7.getText();
    	inputString[4] = a8.getText();
    	inputString[5] = b0.getText();
    	inputString[6] = b2.getText();
    	inputString[7] = b3.getText();
    	inputString[8] = b4.getText();
    	inputString[9] = b5.getText();
    	inputString[10] = b6.getText();
    	inputString[11] = b7.getText();
    	inputString[12] = b8.getText();
    	inputString[13] = c0.getText();
    	inputString[14] = c2.getText();
    	inputString[15] = c3.getText();
    	inputString[16] = c5.getText();
        inputString[17] = c6.getText();
        inputString[18] = c7.getText();
        inputString[19] = d0.getText();
        inputString[20] = d1.getText();
        inputString[21] = d2.getText();
        inputString[22] = d3.getText();
        inputString[23] = d4.getText();
        inputString[24] = d7.getText();
        inputString[25] = e0.getText();
        inputString[26] = e2.getText();
        inputString[27] = e3.getText();
        inputString[28] = e4.getText();
        inputString[29] = e5.getText();
        inputString[30] = e6.getText();
        inputString[31] = e8.getText();
        inputString[32] = f0.getText();
        inputString[33] = f3.getText();
        inputString[34] = f4.getText();
        inputString[35] = f5.getText();
        inputString[36] = f6.getText();
        inputString[37] = f7.getText();
        inputString[38] = f8.getText();
        inputString[39] = g0.getText();
        inputString[40] = g1.getText();
        inputString[41] = g2.getText();
        inputString[42] = g3.getText();
        inputString[43] = g5.getText();
        inputString[44] = g6.getText();
        inputString[45] = g8.getText();
        inputString[46] = h0.getText();
        inputString[47] = h1.getText();
        inputString[48] = h2.getText();
        inputString[49] = h5.getText();
        inputString[50] = h6.getText();
        inputString[51] = h8.getText();
        inputString[52] = i1.getText();
        inputString[53] = i3.getText();
        inputString[54] = i4.getText();
        inputString[55] = i6.getText();
        inputString[56] = i7.getText();
        
        
        try {
        	//convert string input array into int array
        	for(int i = 0; i < inputString.length; i++) {
        		userAnswers[i] = Integer.parseInt(inputString[i]);
        	}
        	
        	//if solved the puzzle
        	if(Arrays.equals(userAnswers, solution) == true) {
        		resultLabel.setText("Congratulations!\nYou have solved the puzzle!");
        		//stops timer
        		long end = System.currentTimeMillis();
        		float sec = (end - start) / 1000F;//milliseconds to seconds
        		float minutes = sec/60F;//seconds to minutes
        		timer.setText(df2.format(minutes) + " minutes");
        	}
        	//if didn't solve the puzzle
        	else {
        		resultLabel.setText("Sorry!\nThere are some errors!\nPlease Try Again!");
        	}
        	
        } catch (NumberFormatException e) {
        	//if there are any blanks
        	resultLabel.setText("Sorry!\nThere are some blanks!\nPlease Try Again!");
        }
    }
    
    //Clears all fields
    public void clearFields(ActionEvent event) {
    	f0.clear();
    	f3.clear();
        b0.clear();
        f4.clear();
        f5.clear();
        b2.clear();
        f6.clear();
        b3.clear();
        f7.clear();
        b4.clear();
        f8.clear();
        b5.clear();
        b6.clear();
        b7.clear();
        b8.clear();
        g0.clear();
        g1.clear();
        g2.clear();
        g3.clear();
        c0.clear();
        g5.clear();
        c2.clear();
        g6.clear();
        c3.clear();
        g8.clear();
        c5.clear();
        c6.clear();
        c7.clear();
        h0.clear();
        h1.clear();
        h2.clear();
        d0.clear();
        d1.clear();
        h5.clear();
        d2.clear();
        h6.clear();
        d3.clear();
        d4.clear();
        h8.clear();
        d7.clear();
        i1.clear();
        i3.clear();
        e0.clear();
        i4.clear();
        e2.clear();
        i6.clear();
        e3.clear();
        i7.clear();
        e4.clear();
        a1.clear();
        e5.clear();
        a2.clear();
        e6.clear();
        e8.clear();
        a6.clear();
        a7.clear();
        a8.clear();
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
    
}
