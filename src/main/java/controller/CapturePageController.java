package controller;

import database.DAO;
import database.ChartService;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Group 6
 * @version jdk 1.8
 * @date 2019-11-14
 * @description This is a class used to control the page which display the
 * specific information of the recognition results
 */
public class CapturePageController implements Initializable {

    /**
     * warnin1
     */
    @FXML
    public Label warning1;

    /**
     * warning2
     */
    @FXML
    public Label warning2;

    /**
     * announcement1
     */
    @FXML
    public Label anc1;

    /**
     * announcement12
     */
    @FXML
    public Label anc2;

    /**
     * save
     */
    @FXML
    private Button save;

    /**
     * total detected
     */
    @FXML
    private Label totalDetected;

    /**
     * total recognized
     */
    @FXML
    private Label totalRecognized;

    /**
     * total unrecognized
     */
    @FXML
    private Label totalUnRecognized;

    /**
     * photo detected
     */
    @FXML
    private ImageView photoDetected;

    /**
     * cropped photo
     */
    @FXML
    private ImageView croppedPhoto;

    /**
     * combo box
     */
    @FXML
    public ComboBox namesCombo;

    /**
     * name
     */
    @FXML
    private TextField nameField;

    /**
     * sex
     */
    @FXML
    private TextField sexField;

    /**
     * id
     */
    @FXML
    private TextField idField;

    /**
     * program
     */
    @FXML
    private TextField programField;

    /**
     * country
     */
    @FXML
    private TextField countryField;

    /**
     * age
     */
    @FXML
    private TextField ageField;

    /**
     * remark
     */
    @FXML
    private TextField remarkField;

    /**
     * update
     */
    @FXML
    private Button updateButton;

    /**
     * emotion
     */
    @FXML
    private Button emotionButton;

    /**
     * similarity
     */
    @FXML
    private Label returnedSimilarity;

    /**
     * exit
     */
    @FXML
    private Button exit;

    /**
     * history
     */
    @FXML
    private TextField historyTimeField;

    /**
     * history reason
     */
    @FXML
    private TextField historyReason;

    /**
     * visiting reason
     */
    @FXML
    private TextField visitingReason;

    /**
     * enter
     */
    @FXML
    private Button enterButton;

    /**
     * pie chart
     */
    @FXML
    private PieChart pieChart;

    /**
     * total number
     */
    @FXML
    private Label totalNumber;

    /**
     * id
     */
    private String currentFaceID = "Unknown";

    /**
     * error view
     */
    private ErrorView errorView = new ErrorView();

    /**
     * recognized face list
     */
    private ArrayList<RecognizedFace> results = new ArrayList<>();

    /**
     * chart service
     */
    private ChartService chartService = new ChartService();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Context.controllers.put(this.getClass().getSimpleName(), this);
        //get the names that recognized by openCV project
        OpenCVController controller = (OpenCVController) Context.controllers.get(OpenCVController.class.getSimpleName());
        InputStream inputStream = null;
        //get the images which belongs to the people that are captured
        try {
            inputStream = new FileInputStream(new File("resources/temp/temp.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(inputStream);
        photoDetected.setImage(image);
        this.results = controller.results;
        /*
         * calculate the total count of the faces that have been recognized and
         * not been recognized and store the results of the names from openCV to
         * name arrayList
         */
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
        // add the names to the ComboBox fields
        namesCombo.getItems().addAll(names);
        /*
         * @description This method is to control the changes of the pages after
         * the users finish the action of choosing the names in the ComboBox
         * @param ChangeListener change listener
         */
        namesCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue) {
                try {
                    for (RecognizedFace face : results) {
                        // show the person's image who was chosen by the user
                        if (face.getName().equals(newValue)) {
                            InputStream inputStream = new FileInputStream(new File(face.getFilePath()));
                            Image image = new Image(inputStream);
                            croppedPhoto.setImage(image);
                            //if the person chosen by the user is not recognized, call the errorView class to alert the user
                            if (newValue.equals("Unknown")) {
                                clearPage();
                                updateButton.setDisable(false);
                                emotionButton.setDisable(false);
                                enterButton.setDisable(true);
                                errorView.start("The person wasn't recognized! You can enter the person's data.");
                                break;
                            }
                            /*
                             * display the person's information including the
                             * person's name, gender, ID, country and program
                             * from the database also display the person's last
                             * visiting reason and last visiting time from the
                             * Student_Log table in database and show the
                             * person't confidence values if the person chosen
                             * by the user doesn't have records in the database,
                             * the fields would display nothing
                             */
                            updateButton.setDisable(true);
                            emotionButton.setDisable(false);
                            enterButton.setDisable(false);
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
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        //show the number of total recognized people and unrecognized people
        totalDetected.setText(String.valueOf(results.size()));
        totalUnRecognized.setText(String.valueOf(unknown));
        totalRecognized.setText(String.valueOf(known));
        //display the announcements and warnings
        anc1.setText("1." + DAO.selectAnnouncement());
        warning1.setText("1." + DAO.selectWarnings());
    }

    /**
     * combo action
     */
    @FXML
    public void comboAction() {
    }

    /**
     * @description This method is to control the page jump into home page
     */
    @FXML
    public void backToHome() {
        Stage stage = (Stage) save.getScene().getWindow();
        stage.close();
    }

    /**
     * save
     */
    @FXML
    public void doSave() {

    }

    /**
     * @description This method is to update the person's information if the
     * user enter some new data of the recognized person or insert the
     * unrecognized person's information if the user enter data
     */
    @FXML
    public void doUpdate() {
        if (nameField.getText().equals("") | sexField.getText().equals("") | idField.getText().equals("")
                | programField.getText().equals("") | countryField.getText().equals("")
                | countryField.getText().equals("") | ageField.getText().equals("")
                | remarkField.getText().equals("")) {
            errorView.start("Input not complete.");
            return;
        }
        StudentEntity studentEntity = new StudentEntity(idField.getText(), nameField.getText(), Integer.parseInt(ageField.getText()),
                programField.getText(), sexField.getText(), countryField.getText(), remarkField.getText());
        DAO.insertStudent(studentEntity);
        enterButton.setDisable(false);
        currentFaceID = idField.getText();
        errorView.start("Insert Success.");
    }

    /**
     * @description This method is to update the person's Log records including
     * the visiting reason
     */
    @FXML
    public void doEnter() {
        if (visitingReason.getText().equals("")) {
            errorView.start("Input not complete.");
            return;
        }
        DAO.insertLog(new LogEntity(currentFaceID, visitingReason.getText()));
        errorView.start("Log Insert Success.");
    }

    /**
     * @description This method is to control the page jump into home page
     */
    @FXML
    public void exit() {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    /**
     * @description This method is to jump into emotion analysis page
     */
    public void doEmotionAnalysis() {
        EmotionPage emotionPage = new EmotionPage();
        emotionPage.start(new Stage());
    }

    /**
     * @return @description This method is to get the recognition results
     */
    public ArrayList<RecognizedFace> getResults() {
        return results;
    }

    /**
     * @description This method is to draw the piechart of the person's
     * historical visiting reasons
     */
    private void changePieChart(){
        ObservableList<PieChart.Data> dataSet = chartService.getPieChartDataByStudentID(currentFaceID);
        AtomicReference<Double> totalNum = new AtomicReference<>(0.0);

        pieChart.setData(FXCollections.observableArrayList(dataSet));
        dataSet.forEach(data ->{
                    data.nameProperty().bind(
                            Bindings.concat(
                                    data.getName(), ": ", data.pieValueProperty().asString("%.0f")
                            )
                    );
                    totalNum.updateAndGet(v -> new Double((double) (v + data.pieValueProperty().doubleValue())));
                }
        );
        totalNumber.setText(totalNum.toString());
        pieChart.setLegendVisible(false);
    }

    /**
     * @description This method is to clear all the contents in the page
     */
    private void clearPage() {
        nameField.setText("");
        sexField.setText("");
        idField.setText("");
        countryField.setText("");
        programField.setText("");
        ageField.setText("");
        remarkField.setText("");
        emotionButton.setDisable(true);
        enterButton.setDisable(true);
        ObservableList<PieChart.Data> dataSet = FXCollections.observableArrayList();
        historyTimeField.setText("");
        totalNumber.setText("");
        historyReason.setText("");
        pieChart.setData(dataSet);
    }
}