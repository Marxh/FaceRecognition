package google;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;
import models.FaceEmotion;

public class FaceEmotionUtils {
    public static void main(String[] args) throws Exception{
        //test
        FaceEmotion face_1 = new FaceEmotion("Shanyue Wan", "resources/trainingset/grayscaletrain/1-xuzheng_8.png");
        FaceEmotion face_2 = new FaceEmotion("Xinrui Zheng", "resources/trainingset/grayscaletrain/1-xuzheng_12.png");
        ArrayList<FaceEmotion> faces = new ArrayList<>();
        faces.add(face_1);
        faces.add(face_2);
        ArrayList<FaceEmotion> results = detectEmotion(faces);
        for (FaceEmotion result : results){
            System.out.println(result.getName()+" " + result.getFilepath()+"\n" +
                    "Joy: "+result.getJoyLikelihood()+"\nSorrow: "+result.getSorrowLikelihood()+"\nAnger: "+result.getAngerLikelihood()+"\nSurprise: "+result.getSurpriseLikelihood()+"\n");
        }
    }

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
                } else{
                    FaceAnnotation annotation = res.getFaceAnnotationsList().get(0);
                    face.setJoyLikelihood(String.valueOf(annotation.getJoyLikelihood()));
                    face.setSorrowLikelihood(String.valueOf(annotation.getSorrowLikelihood()));
                    face.setAngerLikelihood(String.valueOf(annotation.getAngerLikelihood()));
                    face.setSurpriseLikelihood(String.valueOf(annotation.getSurpriseLikelihood()));
                }
            }
        }
        return face;
    }
}
