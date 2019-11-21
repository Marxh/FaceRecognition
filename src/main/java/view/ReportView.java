/**
 * @author ：Shanyue Wan
 * @date ：Created in 4/11/19 7:10 pm
 * @description：This is the entrance of the program
 * @modified By：
 * @version: jdk
 */
package view;


import controller.ReportController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Date;


public class ReportView {
    Stage primaryStage = new Stage();
    public void start(Date startDate, Date endDate) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/reportPage.fxml"));
            Pane root = (Pane) loader.load();
            // set a whitesmoke background
            root.setStyle("-fx-background-color: whitesmoke;");
            // create and style a scene
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());

            // create the stage with the given title and the previously created scene
//            primaryStage.setTitle("Wolf Disco!");
            primaryStage.setScene(scene);
            // show the GUI
            primaryStage.show();
            ReportController controller = loader.getController();
            controller.initialize(startDate, endDate);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
