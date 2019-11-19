import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
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
        File training = new File("resources/trainingset/combined/");

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
            // Extract name from the file name and add it to names HashMap
            String labelAndName = image.getName().split("\\_")[0];
            String name = labelAndName.split("\\-")[1];
            String filename = "resources/trainingset/new/"+name+counter+".png";
            Imgcodecs.imwrite(filename,img);
            counter++;
        }
    }
}
