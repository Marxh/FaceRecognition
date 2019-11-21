package google;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import models.FaceEmotion;

/**
 * This is a class implement Google Vision API.
 *
 * @author Xu Zheng
 * @version jdk 1.8
 * @date Created in 15/11/19 3:15 pm
 * @description This is a class used to generate chart data from database.
 */
public class FaceEmotionUtils {

    /**
     *
     * @param args args
     */
    public static void main(String[] args) {
        String file = "resources/temp/test.png";
        try {
            FaceEmotion faceEmotion = detectEmotion(file);
            System.out.println(faceEmotion.getJoyLikelihood());
            System.out.println(faceEmotion.getSorrowLikelihood());
            System.out.println(faceEmotion.getSurpriseLikelihood());
            System.out.println(faceEmotion.getAngerLikelihood());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Used to analyze emotions of the faces
     *
     * @param faces detected faces
     * @return faces instance with emotions
     * @throws Exception file no found exception
     */
    public static ArrayList<FaceEmotion> detectEmotion(ArrayList<FaceEmotion> faces) throws Exception {

        ArrayList<AnnotateImageRequest> requests = new ArrayList<>();

        for (FaceEmotion face : faces) {
            ByteString byteString = ByteString.readFrom(new FileInputStream(face.getFilepath()));
            Image image = Image.newBuilder().setContent(byteString).build();
            Feature feature = Feature.newBuilder().setType(Feature.Type.FACE_DETECTION).build();
            AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feature).setImage(image).build();
            requests.add(request);
        }

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {

            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();
            for (int i = 0; i < responses.size(); i++) {
                AnnotateImageResponse res = responses.get(i);
                if (res.hasError()) {
                    faces.get(i).setJoyLikelihood(null);
                    faces.get(i).setSorrowLikelihood(null);
                    faces.get(i).setAngerLikelihood(null);
                    faces.get(i).setSurpriseLikelihood(null);
                } else {
                    for (FaceAnnotation annotation : res.getFaceAnnotationsList()) {
                        faces.get(i).setJoyLikelihood(String.valueOf(annotation.getJoyLikelihood()));
                        faces.get(i).setSorrowLikelihood(String.valueOf(annotation.getSorrowLikelihood()));
                        faces.get(i).setAngerLikelihood(String.valueOf(annotation.getAngerLikelihood()));
                        faces.get(i).setSurpriseLikelihood(String.valueOf(annotation.getSurpriseLikelihood()));
                    }
                }
            }
        }
        return faces;
    }

    /**
     * Used to detect emotions of the face
     *
     * @param filepath
     * @return FaceEmotion instance of the detected emotions
     * @throws Exception file no found exception
     */
    public static FaceEmotion detectEmotion(String filepath) throws Exception {
        FaceEmotion face = new FaceEmotion();
        List<AnnotateImageRequest> requests = new ArrayList<>();

        ByteString imgBytes = ByteString.readFrom(new FileInputStream(filepath));

        Image img = Image.newBuilder().setContent(imgBytes).build();
        Feature feature = Feature.newBuilder().setType(Feature.Type.FACE_DETECTION).build();
        AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feature).setImage(img).build();
        requests.add(request);

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {

            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    face.setJoyLikelihood(null);
                    face.setSorrowLikelihood(null);
                    face.setAngerLikelihood(null);
                    face.setSurpriseLikelihood(null);
                } else {
                    FaceAnnotation annotation = res.getFaceAnnotationsList().get(0);
                    face.setJoyLikelihood(String.valueOf(annotation.getJoyLikelihood()));
                    face.setSorrowLikelihood(String.valueOf(annotation.getSorrowLikelihood()));
                    face.setAngerLikelihood(String.valueOf(annotation.getAngerLikelihood()));
                    face.setSurpriseLikelihood(String.valueOf(annotation.getSurpriseLikelihood()));
                }
            }
        } catch (IndexOutOfBoundsException ex) {
            face.setJoyLikelihood("VERY_UNLIKELY");
            face.setAngerLikelihood("VERY_UNLIKELY");
            face.setSorrowLikelihood("VERY_UNLIKELY");
            face.setSurpriseLikelihood("VERY_UNLIKELY");
        }
        return face;
    }
}