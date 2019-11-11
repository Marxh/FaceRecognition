package opencv;

public class FaceEmotion {
    private String name;
    private String filepath;
    private String joyLikelihood;
    private String sorrowLikelihood;
    private String angerLikelihood;
    private String surpriseLikelihood;

    public FaceEmotion(){

    }

    public FaceEmotion(String name, String filepath){
        this.name = name;
        this.filepath = filepath;
    }

    public FaceEmotion(String name, String filepath, String joy, String sorrow, String anger, String surprise){
        this.name = name;
        this.filepath = filepath;
        this.joyLikelihood = joy;
        this.sorrowLikelihood = sorrow;
        this.angerLikelihood = anger;
        this.surpriseLikelihood = surprise;
    }

    public String getName() {
        return name;
    }

    public String getFilepath() {
        return filepath;
    }

    public String getJoyLikelihood() {
        return joyLikelihood;
    }

    public String getSorrowLikelihood() {
        return sorrowLikelihood;
    }

    public String getAngerLikelihood() {
        return angerLikelihood;
    }

    public String getSurpriseLikelihood() {
        return surpriseLikelihood;
    }

    public void setId(String name) {
        this.name = name;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public void setJoyLikelihood(String joyLikelihood) {
        this.joyLikelihood = joyLikelihood;
    }

    public void setSorrowLikelihood(String sorrowLikelihood) {
        this.sorrowLikelihood = sorrowLikelihood;
    }

    public void setAngerLikelihood(String angerLikelihood) {
        this.angerLikelihood = angerLikelihood;
    }

    public void setSurpriseLikelihood(String surpriseLikelihood) {
        this.surpriseLikelihood = surpriseLikelihood;
    }
}
