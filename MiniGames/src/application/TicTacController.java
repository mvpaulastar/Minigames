package application;

public class TicTacController {
	package application;

	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import javafx.fxml.FXML;
	import javafx.scene.control.Button;
	import javafx.scene.control.Label;

	public class TicTacController {
		
		public static int player;
		
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
			
		}



		void addX(){
			//do something
		}
		
		void addO() {
			//do something
		}
		
		
		
		
		
		
	}}
