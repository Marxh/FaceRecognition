package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingPageController {

    @FXML
    public CheckBox haar;

    @FXML
    public CheckBox lbp;

    @FXML
    public TextField threshold;
    @FXML
    public Button apply;
    @FXML
    public Button cancel;

    public boolean haarSelected = false;
    public boolean lbpSelected = false;

    public void init(){
        this.threshold.setText("3000");
    }

    @FXML
    protected void haarSelected(Event event) {
        if (this.haar.isSelected()){
            haarSelected = true;
            lbpSelected = false;
            lbp.setSelected(false);
        }
    }

    @FXML
    protected void lbpSelected(Event event) {
        if (this.lbp.isSelected()){
            lbpSelected = true;
            haarSelected = false;
            haar.setSelected(false);
        }
    }

    public void doApply(ActionEvent actionEvent) {
        OpenCVController controller = (OpenCVController) Context.controllers.get(OpenCVController.class.getSimpleName());
        if (haarSelected){
            controller.checkboxSelection("resources/haarcascades/haarcascade_frontalface_alt.xml");
            haarSelected = false;
        }else if(lbpSelected){
            controller.checkboxSelection("resources/lbpcascades/lbpcascade_frontalface.xml");
            lbpSelected = false;
        }

        controller.setThreshold(Double.parseDouble(this.threshold.getText()));

        Stage stage = (Stage) apply.getScene().getWindow();
        stage.close();
    }

    public void doCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) apply.getScene().getWindow();
        stage.close();
    }
}
