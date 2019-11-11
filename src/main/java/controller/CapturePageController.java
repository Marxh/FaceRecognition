package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import jdk.internal.util.xml.impl.Input;
import opencv.RecognizedFace;
import view.EmotionPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

    public void init() throws FileNotFoundException {
        Context.controllers.put(this.getClass().getSimpleName(), this);
        OpenCVController controller = (OpenCVController) Context.controllers.get(OpenCVController.class.getSimpleName());
        InputStream inputStream = new FileInputStream(new File("resources/temp/temp.png"));
        Image image = new Image(inputStream);
        photoDetected.setImage(image);
        this.results = controller.results;
        ArrayList<String> names = new ArrayList<>();
        int known = 0, unknown = 0;
        for (RecognizedFace face : results) {
            String name = face.getName();
            names.add(name);
            if (name.equals("Unknown")) {
                unknown++;
            } else {
                known++;
            }
        }
        namesCombo.getItems().addAll(names);
        namesCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                try{
                    for (RecognizedFace face : results) {
                        if(face.getName().equals(newValue)){
                            InputStream inputStream = new FileInputStream(new File(face.getFilePath()));
                            Image image = new Image(inputStream);
                            croppedPhoto.setImage(image);
                        }
                    }
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        });

        totalDetected.setText(String.valueOf(results.size()));
        totalUnRecognized.setText(String.valueOf(unknown));
        totalRecognized.setText(String.valueOf(known));
    }

    @FXML
    public void comboAction() {
        namesCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> selected, String oldName, String newName) {
                System.out.println(newName);
            }
        });
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
        EmotionPage emotionPage = new EmotionPage();
        emotionPage.start(new Stage());
    }

    public ArrayList<RecognizedFace> getResults(){
        return results;
    }
}
