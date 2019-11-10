package opencv;

public class RecognizedFace {
    private String id;
    private String name;
    private int predictedlabel;
    private double confidence;
    private String filePath;
    private FaceEmotion emotion;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        if (id == 1) {
            this.id = "U201901";
        } else if (id == 3) {
            this.id = "U201902";
        } else if (id == 5) {
            this.id = "U201903";
        } else if (id == 7) {
            this.id = "U201904";
        } else if (id == 9) {
            this.id = "U201905";
        }
    }

    public void setPredictedlabel(int predictedlabel) {
        this.predictedlabel = predictedlabel;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public void setEmotion(FaceEmotion emotion) {
        this.emotion = emotion;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getPredictedlabel() {
        return predictedlabel;
    }

    public double getConfidence() {
        return confidence;
    }

    public FaceEmotion getEmotion() {
        return emotion;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
