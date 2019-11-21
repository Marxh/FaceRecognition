package controller;

import google.FaceEmotionUtils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.FaceEmotion;
import models.RecognizedFace;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class EmotionPageController {

    public ImageView croppedFace;

    public Label name;
    public ImageView expression;
    public Button exit;

    public ArrayList<RecognizedFace> results;
    public FaceEmotion faceEmotion;

    private int joy = 1;
    private int sorrow = 1;
    private int anger = 1;
    private int surprise = 1;

    public void init() {
        CapturePageController controller = (CapturePageController) Context.controllers.get(CapturePageController.class.getSimpleName());
        String chosenOne = String.valueOf(controller.namesCombo.getValue());
        // 根据comboBox的名字来进行设定
        name.setText(chosenOne);
        // croppedFace也是根据opencv和comboBox返回的值来进行确定。
        this.results = controller.getResults();
        try {
            for (RecognizedFace face : results) {
                if (face.getName().equals(chosenOne)) {
                    croppedFace.setImage(new Image(new FileInputStream(new File(face.getFilePath()))));
                    faceEmotion = FaceEmotionUtils.detectEmotion(face.getFilePath());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 把可能性分级，very unlikely = 1, ...... very likeyly = 5
        // 根据google emotion analysis返回的值进行调整，默认为1
        setJoy(faceEmotion.getJoyLikelihood());
        setSorrow(faceEmotion.getAngerLikelihood());
        setAnger(faceEmotion.getAngerLikelihood());
        setSurprise(faceEmotion.getSurpriseLikelihood());


        NumberAxis YAxis = new NumberAxis(0, 5, 1);
        CategoryAxis XAxis = new CategoryAxis();

        BarChart emotionChart = new BarChart(XAxis, YAxis);

        YAxis.setLabel("Value");
        XAxis.setLabel("Emotion");


        emotionChart.setLayoutX(215);
        emotionChart.setLayoutY(117);
        emotionChart.setPrefWidth(681);
        emotionChart.setPrefHeight(415);
        YAxis.setMinorTickLength(0);
        XAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList(
                "Joy",
                "Sorrow",
                "Anger",
                "Surprise"
        )));

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.getData().addAll(new XYChart.Data<String, Number>("Joy", joy));
        series.getData().addAll(new XYChart.Data<String, Number>("Sorrow", sorrow));
        series.getData().addAll(new XYChart.Data<String, Number>("Anger", anger));
        series.getData().addAll(new XYChart.Data<String, Number>("Surprise", surprise));
        series.setName("Emotion Value");

        emotionChart.setCategoryGap(70);

        emotionChart.getData().addAll(series);

        Pane root = (Pane) name.getScene().getRoot();
        root.getChildren().add(emotionChart);
    }

    public void goExit() {
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }

    public void setJoy(String joy) {
        if (joy.equals("VERY_UNLIKELY")) {
            this.joy = 1;
        } else if (joy.equals("UNLIKELY")) {
            this.joy = 2;
        } else if (joy.equals("POSSIBLE")) {
            this.joy = 3;
        } else if (joy.equals("LIKELY")) {
            this.joy = 4;
        } else if (joy.equals("VERY_LIKELY")) {
            this.joy = 5;
        }
    }

    public void setSorrow(String sorrow) {
        if (sorrow.equals("VERY_UNLIKELY")) {
            this.sorrow = 1;
        } else if (sorrow.equals("UNLIKELY")) {
            this.sorrow = 2;
        } else if (sorrow.equals("POSSIBLE")) {
            this.sorrow = 3;
        } else if (sorrow.equals("LIKELY")) {
            this.sorrow = 4;
        } else if (sorrow.equals("VERY_LIKELY")) {
            this.sorrow = 5;
        }
    }

    public void setAnger(String anger) {
        if (anger.equals("VERY_UNLIKELY")) {
            this.anger = 1;
        } else if (anger.equals("UNLIKELY")) {
            this.anger = 2;
        } else if (anger.equals("POSSIBLE")) {
            this.anger = 3;
        } else if (anger.equals("LIKELY")) {
            this.anger = 4;
        } else if (anger.equals("VERY_LIKELY")) {
            this.anger = 5;
        }
    }

    public void setSurprise(String surprise) {
        if (surprise.equals("VERY_UNLIKELY")) {
            this.surprise = 1;
        } else if (surprise.equals("UNLIKELY")) {
            this.surprise = 2;
        } else if (surprise.equals("POSSIBLE")) {
            this.surprise = 3;
        } else if (surprise.equals("LIKELY")) {
            this.surprise = 4;
        } else if (surprise.equals("VERY_LIKELY")) {
            this.surprise = 5;
        }
    }
}
