package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @Description this class shows the filter page, the user can select the start
 * date and end date before generating report
 */
public class FilterPage extends Application {

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
            //load filterPage.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/filterPage.fxml"));
            Pane root = (Pane) loader.load();
            root.setStyle("-fx-background-color: whitesmoke;");

            Scene scene = new Scene(root);
            //set the title of the page
            primaryStage.setTitle("Filter");
            primaryStage.setScene(scene);

            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args args
     */
    public static void main(String[] args) {
        launch(args);
    }
}