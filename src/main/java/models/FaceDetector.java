package models;

import google.FaceEmotionUtils;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Size;
import org.opencv.face.FisherFaceRecognizer;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Face Detection Class
 * @author Xu Zheng
 */
public class FaceDetector {

    /**
     * map of ids and names
     */
    private HashMap<Integer, String> names = new HashMap<>();

    /**
     * face recognizer
     */
    private FisherFaceRecognizer faceRecognizer = FisherFaceRecognizer.create();

    /**
     * cascade classifier
     */
    private CascadeClassifier faceCascade = new CascadeClassifier();

    /**
     * read model from xml
     */
    public void trainModel() {
        faceRecognizer.read("resources/model/model.xml");
    }

    /**
     *
     * @param grayFrame face frame
     * @param faces all the faces
     * @param absoluteFaceSize face mat size
     * @return face detected
     */
    public MatOfRect faceDetection(Mat grayFrame, MatOfRect faces, int absoluteFaceSize){
        this.faceCascade.detectMultiScale(grayFrame, faces, 1.1, 2, 0 | Objdetect.CASCADE_SCALE_IMAGE,
                new Size(absoluteFaceSize, absoluteFaceSize), new Size());
        return faces;
    }

    /**
     *
     * @param faces all faces in list
     * @return list of recognized faces
     */
    public ArrayList<RecognizedFace> faceRecognitionAndStore(ArrayList<Mat> faces){
        ArrayList<RecognizedFace> recognizedFaces = new ArrayList<>();

        for (int i = 0; i < faces.size(); i++) {
            RecognizedFace recognizedFace = new RecognizedFace();
            int[] predictedLabel = new int[1];
            double[] confidence = new double[1];
            faceRecognizer.predict(faces.get(i), predictedLabel, confidence);
            recognizedFace.setPredictedlabel(predictedLabel[0]);
            recognizedFace.setConfidence(confidence[0]);
            recognizedFace.setId(predictedLabel[0]);
            recognizedFace.setName(names.get(predictedLabel[0]));
            String filepath = "resources/temp/temp_" + i + ".png";
            recognizedFace.setFilePath(filepath);
            Imgcodecs.imwrite("resources/temp/temp_" + i + ".png", faces.get(i));
            recognizedFaces.add(recognizedFace);
        }
        return recognizedFaces;
    }

    /**
     *
     * @param faces all faces in list
     * @return list of recognized faces
     */
    public ArrayList<RecognizedFace> faceRecognition(ArrayList<Mat> faces){
        ArrayList<RecognizedFace> recognizedFaces = new ArrayList<>();

        for (int i = 0; i < faces.size(); i++) {
            RecognizedFace recognizedFace = new RecognizedFace();
            int[] predictedLabel = new int[1];
            double[] confidence = new double[1];
            faceRecognizer.predict(faces.get(i), predictedLabel, confidence);
            recognizedFace.setPredictedlabel(predictedLabel[0]);
            recognizedFace.setConfidence(confidence[0]);
            recognizedFace.setId(predictedLabel[0]);
            recognizedFace.setName(names.get(predictedLabel[0]));
            recognizedFaces.add(recognizedFace);
        }

        return recognizedFaces;
    }

    /**
     *
     * @param path cascade file path
     */
    public void cascadeLoad(String path){
        this.faceCascade.load(path);
    }
}