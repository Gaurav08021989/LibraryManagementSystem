package library;

/*import library.User;*/
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Loads the main page
public class Main extends Application {

	// Start page loads here
	@Override
	public void start(Stage primaryStage) {

		try {

			/* BorderPane root = new BorderPane(); */
			Parent root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
			// Length and Width of screen is set.
			Scene scene = new Scene(root, 700, 500);
			// application.css file is loaded.
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			// Set the scene to the stage
			primaryStage.setScene(scene);
			// Primary Stage is shown
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Main program is launched
	public static void main(String[] args) {
		launch(args);
	}
}
