package view;

import controller.ReportController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Date;

/**
 * @author Shanyue Wan
 * @version jdk 1.8
 * @date Created in 4/11/19 7:10 pm
 * @description This is the entrance of the program
 * @Description this class shows the ReportView, the ReportView contains charts of report
 */
public class ReportView {

    /**
     * stage
     */
    Stage primaryStage = new Stage();

    /**
     * @param startDate start date
     * @param endDate   end date
     */
    public void start(Date startDate, Date endDate) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/reportPage.fxml"));
            Pane root = (Pane) loader.load();
            // set a whitesmoke background
            root.setStyle("-fx-background-color: whitesmoke;");
            // create and style a scene
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());

            primaryStage.setScene(scene);
            // show the GUI
            primaryStage.show();
            ReportController controller = loader.getController();
            controller.initialize(startDate, endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}