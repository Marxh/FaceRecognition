package view;

import controller.CapturePageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CapturePage {
    Stage primaryStage;
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setResizable(false);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/capturePage.fxml"));
            Pane root = (Pane) loader.load();
            root.setStyle("-fx-background-color: whitesmoke;");

            Scene scene = new Scene(root);
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
