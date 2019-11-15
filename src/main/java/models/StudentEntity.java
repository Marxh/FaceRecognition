/**
 * @author ：Shanyue Wan
 * @date ：Created in 14/11/19 9:11 pm
 * @description：
 * @modified By：
 * @version: jdk
 */
package models;

public class StudentEntity {
    private String studentID;
    private String studentName;
    private int age;
    private String program;
    private String gender;
    private String country;
    private String remark;

    public StudentEntity(){}

    public StudentEntity(String studentID, String studentName, int age, String program, String gender, String country, String remark) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.age = age;
        this.program = program;
        this.gender = gender;
        this.country = country;
        this.remark = remark;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
