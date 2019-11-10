package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import opencv.FaceDetector;
import opencv.RecognizedFace;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import view.*;

import java.awt.*;

import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.ArrayList;


public class HomePageController {
    @FXML
    public Button homepage;
    @FXML
    public Hyperlink urlOpenCV;
    @FXML
    public Hyperlink urlCMU;
    @FXML
    public Hyperlink urlHelp;
    @FXML
    public Button capture;
    @FXML
    public ImageView heinzLogo;
    @FXML
    public ImageView originalFrame;
    @FXML
    public HBox topMenu;
    @FXML
    public VBox leftPane;
    @FXML
    public HBox captureHBox;
    @FXML
    public Pane urlPane;
    @FXML
    public Button logButton;
    @FXML
    public Button reportButton;
    @FXML
    public Button settingButton;
    @FXML
    public Button exitButton;

    private VideoCapture videoCapture;

    private int absoluteFaceSize;

    private FaceDetector faceDetector = new FaceDetector();

    public void init() {
        this.videoCapture = new VideoCapture();
        this.absoluteFaceSize = 0;
        this.faceDetector.cascadeLoad("resources/haarcascades/haarcascade_frontalface_alt.xml");

        faceDetector.trainModel();
    }

    @FXML
    public void goHome(ActionEvent actionEvent) {
        HomePage home = new HomePage();
        home.start(new Stage());

        Stage stage = (Stage) homepage.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void openUrlOpenCV(ActionEvent actionEvent) {
        if (Desktop.isDesktopSupported()) {
            try {
                URI uri = URI.create("https://opencv.org/about/");
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    dp.browse(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void openUrlCMU(ActionEvent actionEvent) {
        if (Desktop.isDesktopSupported()) {
            try {
                URI uri = URI.create("https://www.cmu.edu");
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    dp.browse(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void openUrlHelp(ActionEvent actionEvent) {
        if (Desktop.isDesktopSupported()) {
            try {
                URI uri = URI.create("https://docs.opencv.org/4.1.2/");
                Desktop dp = Desktop.getDesktop();
                if (dp.isSupported(Desktop.Action.BROWSE)) {
                    dp.browse(uri);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void goLog(ActionEvent actionEvent) {
    }

    public void goReport(ActionEvent actionEvent) {
        FilterPage filterPage = new FilterPage();
        filterPage.start(new Stage());
    }

    public void goSetting(ActionEvent actionEvent) {
        SettingPage settingPage = new SettingPage();
        settingPage.start(new Stage());
    }

    public void doExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void goCapture(ActionEvent actionEvent) {
        ArrayList<RecognizedFace> results = capture();
        for (RecognizedFace face : results){
            System.out.println(face.getName());
            System.out.println(face.getConfidence());
        }


        CapturePage capturePage = new CapturePage();
        capturePage.start(new Stage());
    }

    public ArrayList<RecognizedFace> capture() {
        ArrayList<RecognizedFace> list = new ArrayList<>();
        Mat frame = new Mat();
        if (this.videoCapture.isOpened()) {
            try {
                // read the current frame
                this.videoCapture.read(frame);

                // if the frame is not empty, process it
                if (!frame.empty()) {
                    // face detection and recognition
                    list = this.detectAndStore(frame);

                }

            } catch (Exception e) {
                // log the (full) error
                System.err.println("ERROR: " + e);
            }
        }
        return list;
    }

    public ArrayList<RecognizedFace> detectAndStore(Mat frame) {
        MatOfRect faces = new MatOfRect();
        Mat grayFrame = new Mat();

        // convert the frame in gray scale
        Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
        // equalize the frame histogram to improve the result
        Imgproc.equalizeHist(grayFrame, grayFrame);

        // compute minimum face size
        if (this.absoluteFaceSize == 0) {
            int height = grayFrame.rows();
            if (Math.round(height * 0.2f) > 0) {
                this.absoluteFaceSize = Math.round(height * 0.2f);
            }
        }

        // detect faces
        faces = this.faceDetector.faceDetection(grayFrame, faces, this.absoluteFaceSize);

        // each rectangle in faces is a face: draw them!
        Rect[] facesArray = faces.toArray();
        ArrayList<Mat> facesToBeRec = new ArrayList<>();
        for (int i = 0; i < facesArray.length; i++) {
            Imgproc.rectangle(frame, facesArray[i].tl(), facesArray[i].br(), new Scalar(0, 255, 0), 3);

            // Crop the detected faces
            Rect rectCrop = new Rect(facesArray[i].tl(), facesArray[i].br());
            Mat croppedImage = new Mat(frame, rectCrop);
            // Change to gray scale
            Imgproc.cvtColor(croppedImage, croppedImage, Imgproc.COLOR_BGR2GRAY);
            // Equalize histogram
            Imgproc.equalizeHist(croppedImage, croppedImage);
            // Resize the image to a default size
            Mat resizeImage = new Mat();
            Size size = new Size(250, 250);
            Imgproc.resize(croppedImage, resizeImage, size);

            Imgcodecs.imwrite("resources/temp/temp_" + i + ".png", resizeImage);

            facesToBeRec.add(resizeImage);
        }
        //store
        Imgcodecs.imwrite("resources/temp/temp.png", frame);

        ArrayList<RecognizedFace> returnedResults = new ArrayList<>();
        try {
            returnedResults = faceDetector.faceRecognition(facesToBeRec);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < returnedResults.size(); i++) {
            RecognizedFace face = returnedResults.get(i);
            double confidence = face.getConfidence();

            if (confidence < 0.7) {
                face.setName("Unknown");
            }
        }
        return returnedResults;

    }

    private Image mat2Image(Mat frame) {
        // create a temporary buffer
        MatOfByte buffer = new MatOfByte();
        // encode the frame in the buffer, according to the PNG format
        Imgcodecs.imencode(".png", frame, buffer);
        // build and return an Image created from the image encoded in the
        // buffer
        return new Image(new ByteArrayInputStream(buffer.toArray()));
    }
}
