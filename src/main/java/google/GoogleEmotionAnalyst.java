 /**
 * @author ：Shanyue Wan
 * @date ：Created in 29/10/19 8:50 pm
 * @description：
 * @modified By：
 * @version: jdk
 */

 package google;

import com.google.cloud.vision.v1.*;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.protobuf.ByteString;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


class GoogleEmotionAnalyst {
    public static void main(String[] args) throws Exception {
        String filePath = "img/barack-obama-12782369-1-402.jpg";
        GoogleEmotionAnalyst googleEmotionAnalyst = new GoogleEmotionAnalyst();
        googleEmotionAnalyst.detectFaces(filePath);
    }

    public void detectFaces(String filePath) throws Exception {
        List<AnnotateImageRequest> requests = new ArrayList<>();

        ByteString imgBytes = ByteString.readFrom(new FileInputStream(filePath));

        Image img = Image.newBuilder().setContent(imgBytes).build();
        Feature feat = Feature.newBuilder().setType(Type.FACE_DETECTION).build();
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
