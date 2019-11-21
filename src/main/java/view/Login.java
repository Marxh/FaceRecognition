package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * @Description this class show the login page, at this page user is required to
 * input username and password
 */
public class Login extends Application {

    /**
     * @param args args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * stage
     */
    Stage primaryStage;

    /**
     * @param primaryStage stage
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        try {
            //load login.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/login.fxml"));
            Pane root = (Pane) loader.load();
            //set the background color transparent
            root.setStyle("-fx-background-color: transparent;");

            Scene scene = new Scene(root);
            //set the title of the stage
            primaryStage.setTitle("Hi Alvis");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}