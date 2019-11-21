package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Group 6
 * @version jdk 1.8
 * @date 2019-11-14
 * @description This is a class used to control the page which set the
 * properties of the system such as the confidence level
 */
public class SettingPageController {

    /**
     * check box for haar
     */
    @FXML
    public CheckBox haar;

    /**
     * check box for lbp
     */
    @FXML
    public CheckBox lbp;

    /**
     * threshold
     */
    @FXML
    public TextField threshold;

    /**
     * apply button
     */
    @FXML
    public Button apply;

    /**
     * cancel button
     */
    @FXML
    public Button cancel;

    /**
     * selected haar
     */
    public boolean haarSelected = false;

    /**
     * selected lbp
     */
    public boolean lbpSelected = false;

    /**
     * @description This is the initial method for the class. The method display
     * the default value of threshold
     */
    public void init() {
        this.threshold.setText("1200");
    }

    /**
     * @param event action event
     * @description This method controls the usage of the haar methods for
     * recognition
     */
    @FXML
    protected void haarSelected(Event event) {
        if (this.haar.isSelected()) {
            haarSelected = true;
            lbpSelected = false;
            lbp.setSelected(false);
        }
    }

    /**
     * @param event action event
     * @description This method controls the usage of the Lbp methods for
     * recognition
     */
    @FXML
    protected void lbpSelected(Event event) {
        if (this.lbp.isSelected()) {
            lbpSelected = true;
            haarSelected = false;
            haar.setSelected(false);
        }
    }

    /**
     * @param actionEvent action event
     * @description This method controls the capture face process and recognize
     * process through calling OpenCV
     */
    public void doApply(ActionEvent actionEvent) {
        OpenCVController controller = (OpenCVController) Context.controllers.get(OpenCVController.class.getSimpleName());
        if (haarSelected) {
            controller.captureButton.setDisable(false);
            controller.cameraButton.setDisable(false);
            controller.checkboxSelection("resources/haarcascades/haarcascade_frontalface_alt.xml");
            haarSelected = false;
        } else if (lbpSelected) {
            controller.captureButton.setDisable(false);
            controller.cameraButton.setDisable(false);
            controller.checkboxSelection("resources/lbpcascades/lbpcascade_frontalface.xml");
            lbpSelected = false;
        }

        controller.setThreshold(Double.parseDouble(this.threshold.getText()));
        OpenCVController.threshold = Double.parseDouble(this.threshold.getText());

        Stage stage = (Stage) apply.getScene().getWindow();
        stage.close();
    }

    /**
     * @param actionEvent action event
     * @description This method controls the process of jumping the pages into
     * the previous page and cancel the process of setting the values for
     * threshold
     */
    public void doCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) apply.getScene().getWindow();
        stage.close();
    }
}