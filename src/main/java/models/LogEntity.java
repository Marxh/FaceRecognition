package models;

import java.sql.Date;

/**
 * @author ：Shanyue Wan
 * @date ：Created in 14/11/19 9:11 pm
 * @version: jdk 1.8
 */
public class LogEntity {

    /**
     * id
     */
    private String studentID;

    /**
     * visit time
     */
    private Date visitTime;

    /**
     * reason
     */
    private String reason;

    /**
     * gender
     */
    private String studentGender;

    /**
     * constructor
     */
    public LogEntity() {
    }

    /**
     * @param studentID id
     * @param visitTime visit time
     * @param reason reason
     */
    public LogEntity(String studentID, Date visitTime, String reason) {
        this.studentID = studentID;
        this.visitTime = visitTime;
        this.reason = reason;
    }

    /**
     * @param studentID id
     * @param visitTime visit time
     * @param reason reason
     * @param gender gender
     */
    public LogEntity(String studentID, Date visitTime, String reason, String gender) {
        this.studentID = studentID;
        this.visitTime = visitTime;
        this.reason = reason;
        this.studentGender = gender;
    }

    /**
     * @param studentID id
     * @param reason reason
     */
    public LogEntity(String studentID, String reason) {
        this.studentID = studentID;
        Date date = new Date(System.currentTimeMillis());
        this.visitTime = date;
        this.reason = reason;
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
     * @return visit time
     */
    public Date getVisitTime() {
        return visitTime;
    }

    /**
     * @return reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @param visitTime visit time
     */
    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    /**
     * @return gender
     */
    public String getStudentGender() {
        return studentGender;
    }

    /**
     * @param studentGender gender
     */
    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }
}