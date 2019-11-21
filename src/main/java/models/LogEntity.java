/**
 * @author ：Shanyue Wan
 * @date ：Created in 14/11/19 9:11 pm
 * @description：
 * @modified By：
 * @version: jdk
 */
package models;

import java.sql.Timestamp;
import java.sql.Date;

public class LogEntity {
    private String studentID;
    private Date visitTime;
    private String reason;
    private String studentGender;

    public LogEntity(){}

    public LogEntity(String studentID, Date visitTime, String reason) {
        this.studentID = studentID;
        this.visitTime = visitTime;
        this.reason = reason;
    }

    public LogEntity(String studentID, Date visitTime, String reason, String gender) {
        this.studentID = studentID;
        this.visitTime = visitTime;
        this.reason = reason;
        this.studentGender = gender;
    }

    public LogEntity(String studentID, String reason) {
        this.studentID = studentID;
        Date date = new Date(System.currentTimeMillis());
        this.visitTime = date;
        this.reason = reason;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }
}
