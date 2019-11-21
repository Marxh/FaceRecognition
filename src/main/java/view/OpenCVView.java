package view;

import controller.OpenCVController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.opencv.core.Core;

/**
 * The class for open cv page.
 *
 * @author Group 6
 */
public class OpenCVView {

    public void start() {
        Stage primaryStage = new Stage();
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/openCV.fxml"));
            Pane root = (Pane) loader.load();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}