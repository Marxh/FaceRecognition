package view;

import controller.EmotionPageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EmotionPage {
    Stage primaryStage;

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setResizable(false);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/emotionPage.fxml"));
            Pane root = (Pane) loader.load();
            root.setStyle("-fx-background-color: whitesmoke;");

            Scene scene = new Scene(root);
            primaryStage.setTitle("Emotion Analysis");
            primaryStage.setScene(scene);

            primaryStage.show();

            EmotionPageController controller = loader.getController();
            controller.init();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
