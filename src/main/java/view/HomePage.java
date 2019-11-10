package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HomePage extends Application {
    Stage primaryStage;

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setResizable(false);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("/legacy/homePage.fxml"));
            BorderPane root = (BorderPane) loader.load();
            root.setStyle("-fx-background-color: whitesmoke;");

            Scene scene = new Scene(root);
            primaryStage.setTitle("Face Recognition");
            primaryStage.setScene(scene);

            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
