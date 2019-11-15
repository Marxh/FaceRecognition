package models;

import google.FaceEmotionUtils;
import models.FaceEmotion;
import models.RecognizedFace;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Size;
import org.opencv.face.FisherFaceRecognizer;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FaceDetector {

    private HashMap<Integer, String> names = new HashMap<>();
    private FisherFaceRecognizer faceRecognizer = FisherFaceRecognizer.create();
    private CascadeClassifier faceCascade = new CascadeClassifier();

    public void trainModel() {
        // Read data from training set
        File training = new File("resources/trainingset/grayscaletrain/");

        FilenameFilter imgFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                name = name.toLowerCase();
                return name.endsWith(".png");
            }
        };

        File[] imageFiles = training.listFiles(imgFilter);

        List<Mat> images = new ArrayList<Mat>();

        System.out.println("the number of training images: " + imageFiles.length);

        Mat labels = new Mat(imageFiles.length, 1, CvType.CV_32SC1);

        int counter = 0;

        for (File image : imageFiles) {
            // Parse the training files
            Mat img = Imgcodecs.imread(image.getAbsolutePath());
            // Change to Grayscale and equalize the histogram
            Imgproc.cvtColor(img, img, Imgproc.COLOR_BGR2GRAY);
            Imgproc.equalizeHist(img, img);
            // Extract label from file name
            int label = Integer.parseInt(image.getName().split("\\-")[0]);
            // Extract name from the file name and add it to names HashMap
            String labelAndName = image.getName().split("\\_")[0];
            String name = labelAndName.split("\\-")[1];
            names.put(label, name);
            // Add training set images to images Mat
            images.add(img);

            labels.put(counter, 0, label);
            counter++;
        }
        faceRecognizer.train(images, labels);
    }

    public MatOfRect faceDetection(Mat grayFrame, MatOfRect faces, int absoluteFaceSize){
        this.faceCascade.detectMultiScale(grayFrame, faces, 1.1, 2, 0 | Objdetect.CASCADE_SCALE_IMAGE,
                new Size(absoluteFaceSize, absoluteFaceSize), new Size());
        return faces;
    }

    public ArrayList<RecognizedFace> faceRecognition(ArrayList<Mat> faces) throws Exception {
        ArrayList<RecognizedFace> recognizedFaces = new ArrayList<>();

        for (int i = 0; i < faces.size(); i++) {
            RecognizedFace recognizedFace = new RecognizedFace();
            int[] predictedLabel = new int[1];
            double[] confidence = new double[1];
            faceRecognizer.predict(faces.get(i), predictedLabel, confidence);
            recognizedFace.setPredictedlabel(predictedLabel[0]);
            recognizedFace.setConfidence(confidence[0]);
            recognizedFace.setName(names.get(predictedLabel[0]));
            recognizedFace.setId(predictedLabel[0]);
            String filepath = "resources/temp/temp_" + i + ".png";
            recognizedFace.setFilePath(filepath);
            Imgcodecs.imwrite("resources/temp/temp_" + i + ".png", faces.get(i));
            recognizedFaces.add(recognizedFace);
        }

        return recognizedFaces;
    }

    public ArrayList<RecognizedFace> emotionAnalysis(ArrayList<RecognizedFace> faces) throws Exception{
        ArrayList<RecognizedFace> recognizedFaces = new ArrayList<>();
        ArrayList<FaceEmotion> emotions = new ArrayList<>();
        for (int i = 0; i < faces.size(); i++) {
            RecognizedFace recognizedFace = faces.get(i);
            emotions.add(new FaceEmotion(recognizedFace.getName(), "resources/temp/temp_" + i + ".png"));
        }

        emotions = FaceEmotionUtils.detectEmotion(emotions);

        for (int i = 0; i < recognizedFaces.size(); i++) {
            recognizedFaces.get(i).setEmotion(emotions.get(i));
        }
        return recognizedFaces;
    }

    public void cascadeLoad(String path){
        this.faceCascade.load(path);
    }
}
