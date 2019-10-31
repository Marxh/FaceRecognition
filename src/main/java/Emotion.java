import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class Emotion {
    public static void main(String[] args) throws Exception {
        //String filePath1 = "resources/trainingset/combined/9-xinrui_2.png";
        //String filePath2 = "resources/trainingset/combined/9-xinrui_4.png";
        String filePath = "resources/temp/temp.png";
        String[] filepaths = {filePath};
        ArrayList<String> list = detectMultiFaces(filepaths);
        System.out.println(list.get(0));
    }

    public static ArrayList<String> detectMultiFaces(String[] filePath) throws Exception {
        ArrayList<String> result = new ArrayList<>();
        List<AnnotateImageRequest> requests = new ArrayList<>();
        for (String file: filePath){
            ByteString imgBytes = ByteString.readFrom(new FileInputStream(file));
            Image img = Image.newBuilder().setContent(imgBytes).build();
            Feature feat = Feature.newBuilder().setType(Feature.Type.FACE_DETECTION).build();
            AnnotateImageRequest request =
                    AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
            requests.add(request);
        }

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    //System.out.printf("Error: %s\n", res.getError().getMessage());
                    return null;
                }

                // For full list of available annotations, see http://g.co/cloud/vision/docs
                for (FaceAnnotation annotation : res.getFaceAnnotationsList()) {
                    result.add(" joy: " + annotation.getJoyLikelihood() + "anger: " + annotation.getAngerLikelihood() + " surprise: " + annotation.getSurpriseLikelihood() + "\n");
                }
            }
        }
        return result;
    }

    public static void detectFaces(String filePath) throws Exception {
        List<AnnotateImageRequest> requests = new ArrayList<>();

        ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

        Image img = Image.newBuilder().setContent(imgBytes).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.FACE_DETECTION).build();
        AnnotateImageRequest request =
                AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
        requests.add(request);

        try (ImageAnnotatorClient client = ImageAnnotatorClient.create()) {
            BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();

            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    System.out.printf("Error: %s\n", res.getError().getMessage());
                    return;
                }

                // For full list of available annotations, see http://g.co/cloud/vision/docs
                for (FaceAnnotation annotation : res.getFaceAnnotationsList()) {
                    System.out.printf(
                            "anger: %s\njoy: %s\nsurprise: %s\nposition: %s",
                            annotation.getAngerLikelihood(),
                            annotation.getJoyLikelihood(),
                            annotation.getSurpriseLikelihood(),
                            annotation.getBoundingPoly());
                }
            }
        }
    }
}
