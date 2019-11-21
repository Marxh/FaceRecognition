package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.FaceDetector;
import models.RecognizedFace;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import view.*;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Group 6
 * @version jdk 1.8
 * @date 2019-11-14
 * @description This is a class used to control the page which opencv capture
 * page
 */
public class OpenCVController {
    // FXML buttons

    /**
     *
     */
    @FXML
    public Button cameraButton;

    /**
     *
     */
    @FXML
    public Button captureButton;
    // the FXML area for showing the current frame

    /**
     *
     */
    @FXML
    private ImageView originalFrame;

    /**
     *
     */
    @FXML
    public Button homepage;

    /**
     *
     */
    @FXML
    public Button reportButton;

    /**
     *
     */
    @FXML
    public Hyperlink urlOpenCV;

    /**
     *
     */
    @FXML
    public Hyperlink urlCMU;

    /**
     *
     */
    @FXML
    public Hyperlink urlHelp;

    /**
     *
     */
    @FXML
    public HBox captureHBox;

    /**
     *
     */
    public ArrayList<RecognizedFace> results;

    /**
     *
     */
    public static double threshold;

    // video stream timer
    /**
     *
     */
    private ScheduledExecutorService timer;
    // VideoCapture object

    /**
     *
     */
    @FXML
    private VideoCapture capture;
    // a flag to change the button behavior

    /**
     *
     */
    private boolean cameraActive;

    /**
     *
     */
    private int absoluteFaceSize;

    /**
     *
     */
    private FaceDetector faceDetector = new FaceDetector();

    /**
     *
     */
    public int index = 0;

    /**
     *
     */
    public int ind = 0;

    // New user Name for a training data
    /**
     *
     */
    public String newname;

    /**
     *
     */
    public OpenCVController() {
        Context.controllers.put(this.getClass().getSimpleName(), this);
    }

    /**
     *
     * @param actionEvent
     */
    public void goLog(ActionEvent actionEvent) {
    }

    /**
     *
     * @param actionEvent
     */
    public void goReport(ActionEvent actionEvent) {
        FilterPage filterPage = new FilterPage();
        filterPage.start(new Stage());
    }

    /**
     *
     * @param actionEvent
     */
    public void goSetting(ActionEvent actionEvent) {
        SettingPage settingPage = new SettingPage();
        settingPage.start(new Stage());
    }

    /**
     *
     * @param actionEvent
     */
    public void doExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    /**
     *
     * @param actionEvent
     */
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

    /**
     *
     * @param actionEvent
     */
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

    /**
     *
     * @param actionEvent
     */
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

    /**
     *
     * @param actionEvent
     */
    @FXML
    public void goHome(ActionEvent actionEvent) {
//        HomePage home = new HomePage();
//        home.start(new Stage());

        Stage stage = (Stage) homepage.getScene().getWindow();
        stage.close();
    }

    /**
     *
     * @param actionEvent
     */
    @FXML
    public void goCapture(ActionEvent actionEvent) {
        this.results = capture();

        CapturePage capturePage = new CapturePage();
        capturePage.start(new Stage());
    }

    /**
     * Init the controller, at start time
     */
    public void init() {
        this.capture = new VideoCapture();
        this.absoluteFaceSize = 0;
        this.captureButton.setDisable(true);
        this.cameraButton.setDisable(true);

        faceDetector.trainModel();
    }

    /**
     * The action triggered by pushing the button on the GUI
     */
    @FXML
    protected void startCamera() {
        // set a fixed width for the frame
        // originalFrame.setFitWidth(600);
        // preserve image ratio
        originalFrame.setPreserveRatio(true);

        if (!this.cameraActive) {

            // start the video capture
            this.capture.open(0);

            // is the video stream available?
            if (this.capture.isOpened()) {
                this.cameraActive = true;

                // grab a frame every 33 ms (30 frames/sec)
                Runnable frameGrabber = new Runnable() {

                    @Override
                    public void run() {
                        Image imageToShow = grabFrame();
                        originalFrame.setImage(imageToShow);
                    }
                };

                this.timer = Executors.newSingleThreadScheduledExecutor();
                this.timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS);

                // update the button content
                this.cameraButton.setText("Stop Camera");
            } else {
                // log the error
                System.err.println("Failed to open the camera connection...");
            }
        } else {
            // the camera is not active at this point
            this.cameraActive = false;
            // update again the button content
            this.cameraButton.setText("Start Camera");

            // stop the timer
            try {
                this.timer.shutdown();
                this.timer.awaitTermination(33, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                // log the exception
                System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
            }

            // release the camera
            this.capture.release();
            // clean the frame
            this.originalFrame.setImage(null);

            // Clear the parameters for new user data collection
            index = 0;
            newname = "";
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<RecognizedFace> capture() {
        ArrayList<RecognizedFace> list = new ArrayList<>();
        Mat frame = new Mat();
        if (this.capture.isOpened()) {
            try {
                // read the current frame
                this.capture.read(frame);

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
        this.capture.release();
        return list;
    }

    /**
     *
     * @param frame
     * @return
     */
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

            facesToBeRec.add(resizeImage);
        }
        //store
        Imgcodecs.imwrite("resources/temp/temp.png", frame);

        ArrayList<RecognizedFace> returnedResults = new ArrayList<>();
        try {
            returnedResults = faceDetector.faceRecognitionAndStore(facesToBeRec);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < returnedResults.size(); i++) {
            RecognizedFace face = returnedResults.get(i);
            double confidence = face.getConfidence();

            if (confidence > this.threshold) {
                face.setName("Unknown");
            }
            System.out.println(threshold);
        }
        return returnedResults;
    }

    /**
     * Get a frame from the opened video stream (if any)
     *
     * @return the {@link Image} to show
     */
    private Image grabFrame() {
        // init everything
        Image imageToShow = null;
        Mat frame = new Mat();

        // check if the capture is open
        if (this.capture.isOpened()) {
            try {
                // read the current frame
                this.capture.read(frame);

                // if the frame is not empty, process it
                if (!frame.empty()) {
                    // face detection
                    this.detectAndDisplay(frame);

                    // convert the Mat object (OpenCV) to Image (JavaFX)
                    imageToShow = mat2Image(frame);
                }

            } catch (Exception e) {
                // log the (full) error
                System.err.println("ERROR: " + e);
            }
        }

        return imageToShow;
    }

    /**
     * Method for face detection and tracking
     *
     * @param frame it looks for faces in this frame
     */
    private void detectAndDisplay(Mat frame) {
        MatOfRect faces = new MatOfRect();
        Mat grayFrame = new Mat();

        // convert the frame in gray scale
        Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
        // equalize the frame histogram to improve the result
        Imgproc.equalizeHist(grayFrame, grayFrame);

        // compute minimum face size (20% of the frame height, in our case)
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
            facesToBeRec.add(resizeImage);
        }

        ArrayList<RecognizedFace> returnedResults = new ArrayList<>();
        try {
            returnedResults = faceDetector.faceRecognition(facesToBeRec);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < returnedResults.size(); i++) {
            RecognizedFace face = returnedResults.get(i);
            double confidence = face.getConfidence();

            if (confidence > threshold) {
                face.setName("Unknown");
            }
            String name = face.getName();
            String box_text = "PredictedName = " + name + " Confidence = " + confidence;
            double pos_x = Math.max(facesArray[i].tl().x - 10, 0);
            double pos_y = Math.max(facesArray[i].tl().y - 10, 0);
            // And now put it into the image:
            Imgproc.putText(frame, box_text, new Point(pos_x, pos_y), 1, 1.0, new Scalar(0, 255, 0, 2.0));

        }
    }

    /**
     * Method for loading a classifier trained set from disk
     *
     * @param classifierPath the path on disk where a classifier trained set is
     * located
     */
    public void checkboxSelection(String classifierPath) {
        // load the classifier(s)
        this.faceDetector.cascadeLoad(classifierPath);

        // now the video capture can start
        this.cameraButton.setDisable(false);
    }

    /**
     * Convert a Mat object (OpenCV) in the corresponding Image for JavaFX
     *
     * @param frame the {@link Mat} representing the current frame
     * @return the {@link Image} to show
     */
    private Image mat2Image(Mat frame) {
        // create a temporary buffer
        MatOfByte buffer = new MatOfByte();
        // encode the frame in the buffer, according to the PNG format
        Imgcodecs.imencode(".png", frame, buffer);
        // build and return an Image created from the image encoded in the
        // buffer
        return new Image(new ByteArrayInputStream(buffer.toArray()));
    }

    /**
     *
     * @param threshold
     */
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }
}