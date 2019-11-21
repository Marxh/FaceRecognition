package view;

import controller.CapturePageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @Description this class use to show the capture page, this page is the main
 * page containing images, information analysis and output
 */
public class CapturePage {

    /**
     * primary stage
     */
    Stage primaryStage;

    /**
     * @param primaryStage primary stage
     */
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setResizable(false);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/capturePage.fxml"));
            Pane root = (Pane) loader.load();
            root.setStyle("-fx-background-color: whitesmoke;");

            Scene scene = new Scene(root);
            //set the title of page
            primaryStage.setTitle("Face Recognition");
            primaryStage.setScene(scene);

            primaryStage.show();
            CapturePageController controller = loader.getController();
            controller.init();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}