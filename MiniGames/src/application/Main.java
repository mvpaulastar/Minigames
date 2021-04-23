  
package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Minigames"); //Sets the title of the application
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Main.fxml")); //Obtains main menu screen
			Scene scene = new Scene(root,1280,720); //Size of the screen
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); 
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}//end method start
	
	public static void main(String[] args) {
		launch(args); //Launches app
	} //end main method
} //end class main