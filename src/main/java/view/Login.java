package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Login extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/login.fxml"));
            Pane root = (Pane) loader.load();
            root.setStyle("-fx-background-color: transparent;");

            Scene scene = new Scene(root);
            primaryStage.setTitle("Hi Alvis");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
