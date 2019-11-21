package view;

import controller.SettingPageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @Desciption this class is used to show setting page, the user can set classifer and thresold
 */
public class SettingPage {

    /**
     * primary stage
     */
    Stage primaryStage;

    /**
     * @param primaryStage stage
     */
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setResizable(false);

        try {
            //load settingPage.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/settingPage.fxml"));
            Pane root = (Pane) loader.load();
            root.setStyle("-fx-background-color: whitesmoke;");

            Scene scene = new Scene(root);
            // set the title of the page
            primaryStage.setTitle("Setting");
            primaryStage.setScene(scene);

            primaryStage.show();

            // init the controller
            SettingPageController controller = loader.getController();
            controller.init();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}