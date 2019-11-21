package models;

/**
 * model class for recognized face
 *
 * @author Xu Zheng
 */
public class RecognizedFace {

    /**
     * id
     */
    private String id;

    /**
     * name
     */
    private String name;

    /**
     * label
     */
    private int predictedlabel;

    /**
     * confidence
     */
    private double confidence;

    /**
     * filepath
     */
    private String filePath;

    /**
     * emotion
     */
    private FaceEmotion emotion;

    /**
     * @param name name
     */
    public void setName(String name) {
        if (!(name == null)) {
            this.name = name;
        } else {
            if (this.id.equals("U201901")) {
                this.name = "xuzheng";
            } else if (this.id.equals("U201902")) {
                this.name = "yufan";
            } else if (this.id.equals("U201903")) {
                this.name = "xinrui";
            } else if (this.id.equals("U201904")) {
                this.name = "shanyue";
            } else if (this.id.equals("U201905")) {
                this.name = "jianping";
            }
        }
    }

    /**
     * @param id id
     */
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

    /**
     * @param predictedlabel predictedlabel
     */
    public void setPredictedlabel(int predictedlabel) {
        this.predictedlabel = predictedlabel;
    }

    /**
     * @param confidence confidence
     */
    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    /**
     * @param emotion emotion
     */
    public void setEmotion(FaceEmotion emotion) {
        this.emotion = emotion;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @return label
     */
    public int getPredictedlabel() {
        return predictedlabel;
    }

    /**
     * @return confidence
     */
    public double getConfidence() {
        return confidence;
    }

    /**
     * @return emotion
     */
    public FaceEmotion getEmotion() {
        return emotion;
    }

    /**
     * @return filepath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath filepath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}