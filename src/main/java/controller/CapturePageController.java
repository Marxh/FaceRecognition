package controller;

import database.DAO;
import database.PieChartService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import models.LogEntity;
import models.StudentEntity;
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
import models.RecognizedFace;
import view.EmotionPage;
import view.ErrorView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CapturePageController {

    @FXML
    private Button save;
    @FXML
    private Label totalDetected;
    @FXML
    private Label totalRecognized;
    @FXML
    private Label totalUnRecognized;
    @FXML
    private ImageView photoDetected;
    @FXML
    private ImageView croppedPhoto;
    @FXML
    public ComboBox namesCombo;
    @FXML
    private TextField nameField;
    @FXML
    private TextField sexField;
    @FXML
    private TextField idField;
    @FXML
    private TextField programField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField remarkField;
    @FXML
    private Button updateButton;
    @FXML
    private Button emotionButton;
    @FXML
    private Label returnedSimilarity;
    @FXML
    private Button exit;
    @FXML
    private TextField historyTimeField;
    @FXML
    private TextField historyReason;
    @FXML
    private TextField visitingReason;
    @FXML
    private Button enterButton;
    @FXML
    private PieChart pieChart;

    private String currentFaceID = "Unknown";

    private ErrorView errorView = new ErrorView();

    private ArrayList<RecognizedFace> results = new ArrayList<>();

    private PieChartService pieChartService = new PieChartService();

    public void init() throws FileNotFoundException {
        updateButton.setDisable(true);
        emotionButton.setDisable(true);
        enterButton.setDisable(true);
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
                            StudentEntity stud = DAO.selectStudent(face.getId());
                            nameField.setText(stud.getStudentName());
                            sexField.setText(stud.getGender());
                            idField.setText(stud.getStudentID());
                            countryField.setText(stud.getCountry());
                            programField.setText(stud.getProgram());
                            ageField.setText(stud.getGender());
                            remarkField.setText(stud.getRemark());
                            currentFaceID = stud.getStudentID();
                            LogEntity log = DAO.selectLatestLog(face.getId());
                            historyTimeField.setText(log.getVisitTime().toString());
                            historyReason.setText(log.getReason());
                            returnedSimilarity.setText(String.format("%.2f", face.getConfidence()));
                            changePieChart();
                            break;
                        }
                    }
                    if(currentFaceID.equals("Unknown")) {
                        updateButton.setDisable(false);
                        enterButton.setDisable(true);
                        emotionButton.setDisable(true);
                    }else{
                        updateButton.setDisable(true);
                        enterButton.setDisable(false);
                        emotionButton.setDisable(false);
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
        if(nameField.getText().equals("") | sexField.getText().equals("") | idField.getText().equals("")|
                programField.getText().equals("") | countryField.getText().equals("")|
                countryField.getText().equals("") | ageField.getText().equals("")|
                remarkField.getText().equals("")){
            errorView.start("Input not complete.");
            return;
        }
        StudentEntity studentEntity = new StudentEntity(idField.getText(), nameField.getText(), Integer.parseInt(ageField.getText()),
                programField.getText(), sexField.getText(), countryField.getText(), remarkField.getText());
        DAO.insertStudent(studentEntity);
        currentFaceID = idField.getText();
        errorView.start("Insert Success.");
    }

    @FXML
    public void doEnter() {
        if(visitingReason.getText().equals("")){
            errorView.start("Input not complete.");
            return;
        }
        DAO.insertLog(new LogEntity(currentFaceID, visitingReason.getText()));
        errorView.start("Log Insert Success.");
    }

    @FXML
    public void exit(){
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    public void doEmotionAnalysis() {
        EmotionPage emotionPage = new EmotionPage();
        emotionPage.start(new Stage());
    }

    public ArrayList<RecognizedFace> getResults(){
        return results;
    }

    private void changePieChart(){
        ObservableList<PieChart.Data> dataSet = pieChartService.getPieChartData(currentFaceID);
        pieChart.setData(FXCollections.observableArrayList(dataSet));
    }
}
