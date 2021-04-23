package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TicTacController implements Initializable {
	
	public static int player;
	public static int[][] board;
	
	@FXML
	private Label header;
	
	@FXML
	private Button piece1;
	
	@FXML
	private Button piece2;
	
	@FXML
	private Button piece3;
	
	@FXML
	private Button piece4;
	
	@FXML
	private Button piece5;
	
	@FXML
	private Button piece6;
	
	@FXML
	private Button piece7;
	
	@FXML
	private Button piece8;
	
	@FXML
	private Button piece9;
	
	
	@FXML
	void addPiece(ActionEvent event) { // adds a piece to the selected spot
		switch(player) { // checks which player is currently on
			case 1:
				addX();
				break;
			case 2:
				addO();
				break;
		}
		if( winnerExists() )
		{
			// open outfile
			// write-in stats
			// close outfile
		}
		return;
	}



	void addX(){
		//adds x to the board
		
	}
	
	void addO() {
		//add an O to the board
	}
	
	boolean winnerExists() {
		// checks the board for a win
		return true;
	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		board = new int[3][3];
		player = 1;
	}
	
	
}