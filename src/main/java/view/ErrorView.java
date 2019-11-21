package view;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * @author Shanyue Wan
 * @version jdk 1.8
 * @date Created in 13/11/19 12:40 am
 */
public class ErrorView {

    /**
     * pop out message to notice
     *
     * @param message message
     */
    public void start(String message) {
        Stage primaryStage = new Stage();
        try {
            primaryStage.setTitle("Message");
            // create a label
            Label label = new Label(message);
            // create a Stack pane
            StackPane pane = new StackPane();
            // add password field
            pane.getChildren().add(label);
            // create a scene
            Scene sc = new Scene(pane, 300, 300);
            // set the scene
            primaryStage.setScene(sc);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}