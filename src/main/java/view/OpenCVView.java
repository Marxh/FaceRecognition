package view;

import controller.OpenCVController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.opencv.core.Core;

/**
 * The main class for a JavaFX application. It creates and handle the main
 * window with its resources (style, graphics, etc.).
 * 
 * This application handles a video stream and try to find any possible human
 * face in a frame. It can use the Haar or the LBP classifier.
 * 
 * @author Luigi De Russis / Igor @ HeroinSoul / Jim O'Connorhorrill @ cuerobotics
 * @version 1.3 (2016-08-04)
 * @since 1.0 (2014-01-10)
 * 
 */
public class OpenCVView {

	public void start() {
		Stage primaryStage = new Stage();
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/OpenCV.fxml"));
			BorderPane root = (BorderPane) loader.load();
			// set a whitesmoke background
			root.setStyle("-fx-background-color: whitesmoke;");
			// create and style a scene
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			// create the stage with the given title and the previously created
			// scene
			primaryStage.setTitle("Face Detection and Tracking");
			primaryStage.setScene(scene);
			// show the GUI
			primaryStage.show();
			
			// init the controller
			OpenCVController controller = loader.getController();
			controller.init();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
