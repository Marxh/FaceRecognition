import models.FaceDetector;
import models.RecognizedFace;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class Transform {
    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // Read data from training set
        File training = new File("C:\\Users\\user\\Desktop\\train_images\\train_images\\");

        FilenameFilter imgFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                name = name.toLowerCase();
                return name.endsWith(".jpg");
            }
        };

        File[] imageFiles = training.listFiles(imgFilter);

        System.out.println("the number of training images: " + imageFiles.length);

        FaceDetector faceDetector = new FaceDetector();
        faceDetector.cascadeLoad("resources/haarcascades/haarcascade_frontalface_alt.xml");

        int absoluteFaceSize = 0;
        int count = 29;
        for (File image : imageFiles) {
            // Parse the training files
            Mat frame = Imgcodecs.imread(image.getAbsolutePath());

            /***Mat resizeImage = new Mat();
            Size size = new Size(250, 250);
            Imgproc.resize(frame, resizeImage, size);
            String[] arr = image.getName().split("/");
            String name = arr[arr.length-1];
            String filename = "resources/trainingset/newset/"+name;
            Imgcodecs.imwrite(filename,resizeImage);***/





            MatOfRect faces = new MatOfRect();

            Mat grayFrame = new Mat();

            // convert the frame in gray scale
            Imgproc.cvtColor(frame, grayFrame, Imgproc.COLOR_BGR2GRAY);
            // equalize the frame histogram to improve the result
            Imgproc.equalizeHist(grayFrame, grayFrame);

            // compute minimum face size (20% of the frame height, in our case)
            if (absoluteFaceSize == 0) {
                int height = grayFrame.rows();
                if (Math.round(height * 0.2f) > 0) {
                    absoluteFaceSize = Math.round(height * 0.2f);
                }
            }

            // detect faces
            faces = faceDetector.faceDetection(grayFrame, faces, absoluteFaceSize);

            // each rectangle in faces is a face: draw them!
            Rect[] facesArray = faces.toArray();
            for (int i = 0; i < facesArray.length; i++) {
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
                String[] arr = image.getName().split("/");
                String name = arr[arr.length-1];
                //String name = "7-shanyue_" + count + ".png";
                String filename = "C:\\Users\\user\\Desktop\\train_images\\newset\\"+name;
                Imgcodecs.imwrite(filename,resizeImage);
            }
            count++;
        }
    }
}
