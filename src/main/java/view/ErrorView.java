/**
 * @author ：Shanyue Wan
 * @date ：Created in 13/11/19 12:40 am
 * @description：
 * @modified By：
 * @version: jdk
 */
package view;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class ErrorView {
    /**
     * pop out message to notice
     * @param message
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
            Scene sc = new Scene(pane, 200, 200);
            // set the scene
            primaryStage.setScene(sc);
            primaryStage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
