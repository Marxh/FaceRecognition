package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import opencv.RecognizedFace;

import java.util.ArrayList;

public class CapturePageController {

    @FXML
    public Button save;
    @FXML
    public Label totalDetected;
    @FXML
    public Label totalRecognized;
    @FXML
    public Label totalUnRecognized;
    @FXML
    public ImageView photoDetected;
    @FXML
    public ImageView croppedPhoto;
    @FXML
    public ComboBox namesCombo;
    @FXML
    public TextField nameField;
    @FXML
    public TextField sexField;
    @FXML
    public TextField idField;
    @FXML
    public TextField programField;
    @FXML
    public TextField countryField;
    @FXML
    public TextField dobField;
    @FXML
    public TextField remarkField;
    @FXML
    public Button updateButton;
    @FXML
    public Button emotionButton;
    @FXML
    public Label returnedSimilarity;
    @FXML
    public Button exit;
    @FXML
    public TextField historyTimeField;
    @FXML
    public TextField historyReason;
    @FXML
    public TextField visitingReason;
    @FXML
    public Button enterButton;

    public ArrayList<RecognizedFace> results = new ArrayList<>();

    public void init() {
        OpenCVController controller = (OpenCVController) Context.controllers.get(OpenCVController.class.getSimpleName());
        this.results = controller.results;
        ArrayList<String> names = new ArrayList<>();
        int known = 0, unknown = 0;
        for (RecognizedFace face : results) {
            String name = face.getName();
            names.add(name);
            if (name.equals("Unknown")){
                unknown++;
            }else{
                known++;
            }
        }
        namesCombo.getItems().addAll(names);
        namesCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                System.out.println(oldValue);
                System.out.println(newValue);
            }
        });

        totalDetected.setText(String.valueOf(results.size()));
        totalUnRecognized.setText(String.valueOf(unknown));
        totalRecognized.setText(String.valueOf(known));
    }

    @FXML
    public void backToHome() {
        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void doSave() {

    }

    @FXML
    public void doUpdate() {

    }

    @FXML
    public void doEnter() {

    }

    public void doEmotionAnalysis() {

    }
}
