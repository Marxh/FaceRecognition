package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @Description this class is used to show about us page, it contains the
 * information of all authors
 */
public class AboutUsPage {

    /**
     * primary stage
     */
    Stage primaryStage;

    /**
     *
     * @param primaryStage primary stage
     */
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setResizable(false);

        try {
            //load aboutUsPage.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/aboutUsPage.fxml"));
            Pane root = (Pane) loader.load();

            Scene scene = new Scene(root);
            //set the title of the stage
            primaryStage.setTitle("Face Recognition");
            primaryStage.setScene(scene);

            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
