package models;

/**
 * @author ：Shanyue Wan
 * @date ：Created in 14/11/19 9:11 pm
 * @version: jdk 1.8
 */
public class StudentEntity {

    /**
     * id
     */
    private String studentID;

    /**
     * name
     */
    private String studentName;

    /**
     * age
     */
    private int age;

    /**
     * program
     */
    private String program;

    /**
     * gender
     */
    private String gender;

    /**
     * country
     */
    private String country;

    /**
     * remark
     */
    private String remark;

    /**
     * @param studentID   id
     * @param studentName name
     * @param age         age
     * @param program     program
     * @param gender      gender
     * @param country     country
     * @param remark      remark
     */
    public StudentEntity(String studentID, String studentName, int age, String program, String gender, String country, String remark) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.age = age;
        this.program = program;
        this.gender = gender;
        this.country = country;
        this.remark = remark;
    }

    /**
     * @return id
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * @param studentID id
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * @return name
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * @param studentName name
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * @return program
     */
    public String getProgram() {
        return program;
    }

    /**
     * @param program program
     */
    public void setProgram(String program) {
        this.program = program;
    }

    /**
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}