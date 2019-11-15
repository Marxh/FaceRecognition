/**
 * @author ：Shanyue Wan
 * @date ：Created in 14/11/19 8:54 pm
 * @description：
 * @modified By：
 * @version: jdk
 */
package database;

import models.LogEntity;
import models.StudentEntity;

import java.sql.*;

public class DAO {
    private static Connection conn;
    private static PreparedStatement preparedStatement;

    static {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Face","root","Pa22word");
        }catch(Exception e){ System.out.println(e);}
    }

    public static void insertLog(LogEntity logEntity) {
        String sql = "INSERT INTO Student_Log (Stu_ID, Visit_Time, Reason)" +
                "VALUES (?, ?, ?);";
        try {
            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, logEntity.getStudentID());
            preparedStatement.setDate(2, logEntity.getVisitTime());
            preparedStatement.setString(3, logEntity.getReason());

            // Execute statement and return the number of rows affected
            int rowCount = preparedStatement.executeUpdate();
            System.out.println("Number of rows affected: " + rowCount);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void insertStudent(StudentEntity studentEntity) {
        String sql = "INSERT INTO Student_Info (Student_ID, Student_Name, Age, Program, Gender," +
                "Country, Remark)" + "VALUES (?, ?, ?, ?, ?, ?, ?);";
        try {
            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, studentEntity.getStudentID());
            preparedStatement.setString(2, studentEntity.getStudentName());
            preparedStatement.setInt(3, studentEntity.getAge());
            preparedStatement.setString(4, studentEntity.getProgram());
            preparedStatement.setString(5, studentEntity.getGender());
            preparedStatement.setString(6, studentEntity.getCountry());
            preparedStatement.setString(7, studentEntity.getRemark());

            // Execute statement and return the number of rows affected
            int rowCount = preparedStatement.executeUpdate();
            System.out.println("Number of rows affected: " + rowCount);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static StudentEntity selectStudent(String stuID) {
        String sql = "select * from `Student_Info` where Student_Info.Student_ID = ?";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, stuID);
            ResultSet rs=preparedStatement.executeQuery();
            rs.next();
            StudentEntity studentEntity = new StudentEntity(rs.getString(1),rs.getString(2),
                    rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),
                    rs.getString(7));
            return studentEntity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LogEntity selectLatestLog(String stuID) {
        String sql = "select * from `Student_Log` where Stu_ID = ? and `Student_Log`.`Visit_Time`=(select max(Visit_Time) FROM\n" +
                "`Student_Log` where Stu_ID = ?);";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, stuID);
            preparedStatement.setString(2, stuID);
            ResultSet rs=preparedStatement.executeQuery();
            rs.next();
            LogEntity logEntity = new LogEntity(rs.getString(1),rs.getDate(2),rs.getString(3));
            return logEntity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static LogEntity selectStuLog(String stuID) {
        String sql = "select * from `Student_Log` where Stu_ID = ? and `Student_Log`.`Visit_Time`=(select max(Visit_Time) FROM\n" +
                "`Student_Log` where Stu_ID = ?);";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, stuID);
            preparedStatement.setString(2, stuID);
            ResultSet rs=preparedStatement.executeQuery();
            rs.next();
            LogEntity logEntity = new LogEntity(rs.getString(1),rs.getDate(2),rs.getString(3));
            return logEntity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
