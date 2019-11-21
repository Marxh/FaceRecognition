package models;

/**
 * model class for FaceEmotion
 *
 * @author Xu Zheng
 */
public class FaceEmotion {

    /**
     * name
     */
    private String name;

    /**
     * file path
     */
    private String filepath;

    /**
     * joy likelihood
     */
    private String joyLikelihood;

    /**
     * sorrow likelihood
     */
    private String sorrowLikelihood;

    /**
     * anger likelihood
     */
    private String angerLikelihood;

    /**
     * surprise likelihood
     */
    private String surpriseLikelihood;

    /**
     * construction
     */
    public FaceEmotion() {

    }

    /**
     * constructor
     *
     * @param name     name
     * @param filepath file path
     */
    public FaceEmotion(String name, String filepath) {
        this.name = name;
        this.filepath = filepath;
    }

    /**
     * constructor
     *
     * @param name     name
     * @param filepath filepath
     * @param joy      joy
     * @param sorrow   sorrow
     * @param anger    anger
     * @param surprise surprise
     */
    public FaceEmotion(String name, String filepath, String joy, String sorrow, String anger, String surprise) {
        this.name = name;
        this.filepath = filepath;
        this.joyLikelihood = joy;
        this.sorrowLikelihood = sorrow;
        this.angerLikelihood = anger;
        this.surpriseLikelihood = surprise;
    }

    /**
     * getter for name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * getter for filepath
     *
     * @return file path
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * getter for joy
     *
     * @return joy likelihood
     */
    public String getJoyLikelihood() {
        return joyLikelihood;
    }

    /**
     * getter for sorror
     *
     * @return sorrow likelihood
     */
    public String getSorrowLikelihood() {
        return sorrowLikelihood;
    }

    /**
     * getter for anger
     *
     * @return anger likelihood
     */
    public String getAngerLikelihood() {
        return angerLikelihood;
    }

    /**
     * getter for surprise
     *
     * @return surprise likelihood
     */
    public String getSurpriseLikelihood() {
        return surpriseLikelihood;
    }

    /**
     * setter for name
     *
     * @param name name
     */
    public void setId(String name) {
        this.name = name;
    }

    /**
     * setter for filepath
     *
     * @param filepath file path
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * setter for joyLikelihood
     *
     * @param joyLikelihood joyLikelihood
     */
    public void setJoyLikelihood(String joyLikelihood) {
        this.joyLikelihood = joyLikelihood;
    }

    /**
     * setter for sorrowLikelihood
     *
     * @param sorrowLikelihood sorrowLikelihood
     */
    public void setSorrowLikelihood(String sorrowLikelihood) {
        this.sorrowLikelihood = sorrowLikelihood;
    }

    /**
     * setter for angerLikelihood
     *
     * @param angerLikelihood angerLikelihood
     */
    public void setAngerLikelihood(String angerLikelihood) {
        this.angerLikelihood = angerLikelihood;
    }

    /**
     * setter for surpriseLikelihood
     *
     * @param surpriseLikelihood surpriseLikelihood
     */
    public void setSurpriseLikelihood(String surpriseLikelihood) {
        this.surpriseLikelihood = surpriseLikelihood;
    }
}
