/**
 * @author ：Shanyue Wan
 * @date ：Created in 4/11/19 7:10 pm
 * @description：This is the entrance of the program
 * @modified By：
 * @version: jdk
 */
package view;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            Pane root = (Pane) loader.load();
            // set a whitesmoke background
            root.setStyle("-fx-background-color: whitesmoke;");
            // create and style a scene
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());

            // create the stage with the given title and the previously created
            // scene
            primaryStage.setTitle("Wolf Disco!");
            primaryStage.setScene(scene);
            // show the GUI
            primaryStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
