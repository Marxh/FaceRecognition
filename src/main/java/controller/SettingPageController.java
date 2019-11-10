package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingPageController {

    @FXML
    public TextField threshold;
    @FXML
    public Button apply;
    @FXML
    public Button cancel;

    public void doApply(ActionEvent actionEvent) {

    }

    public void doCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) apply.getScene().getWindow();
        stage.close();
    }
}
