/* Player object class created by Paula Sirisumpund 
 * Contains constructor, getter, and setter functions used to create the player object
 * 
 * */
package application;

public class Player {
	//Variable declarations
	private int tttWins; //Number of wins in tic tac toe
	private int tttLosses; //Number of losses in tic tac toe
	private int sudTimeCompleted; //Time to complete sudoku
	private String userName; //name of player
	
	/*CONSTRUCTOR SECTION - Contains all the constructor functions for the player object*/
	//Constructor for a sudoku player
	public Player( String name, int wins, int losses ) {
		tttWins = wins;
		tttLosses = losses;
		userName = name;
	}
	//Constructor for a tictactoe player
	public Player( String name, int timeToComplete ) {
		sudTimeCompleted = timeToComplete;
		userName = name;
	}
	
	/*GETTERS SECTION - Contains all the getter functions for the player object*/
	//Getter returns int tictactoe wins
	public int getTttWins( ) {
		return tttWins;
	}
	//Getter returns int tictactoe losses
	public int getTttLosses( ) {
		return tttLosses;
	}
	//Getter returns int sudTimeCompleted
	public int getSudTimeCompleted( ) {
		return sudTimeCompleted;
	}
	//Getter returns String userName
	public String getUsername( ) {
		return userName;
	}
	
	/*SETTERS SECTION - Contains all the setter functions for the player object*/
	//setter sets the player object's tttwins
	public void settttWins( int newWins ) {
		tttWins = newWins;
	}
	//setter sets the player object's tttLosses
	public void settttLosses( int newLosses ) {
		tttLosses = newLosses;
	}
	//setter sets the player object's sudTimeCompleted
	public void setSudTimeCompleted( int newTimeCompleted ) {
		sudTimeCompleted = newTimeCompleted;
	}
	public void setUsername( String newUsername ) {
		userName = newUsername;
	}
}//end class player
