package view;

import controller.EmotionPageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @Description this class shows the emotion page, the user can view the emotion analysis
 */
public class EmotionPage {

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
            //load emotionpage.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/emotionPage.fxml"));
            Pane root = (Pane) loader.load();
            //set the background color of the stage
            root.setStyle("-fx-background-color: whitesmoke;");

            Scene scene = new Scene(root);
            //set the title of the stage
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