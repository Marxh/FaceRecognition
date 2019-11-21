package database;

import models.LogEntity;
import models.StudentEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Shanyue Wan
 * @version jdk 1.8
 * @date Created in 14/11/19 8:54 pm
 * @modified By
 */
public class DAO {

    /**
     * URL to connect with db
     */
    //static final String url = "jdbc:mysql://127.0.0.1:3306/Face?user=root/Group6&useSSL=false&serverTimezone=UTC&useOldAliasMetadataBehavior=true&allowPublicKeyRetrieval=true";
    static final String url_win = "jdbc:mysql://localhost:3306/facerecognition?user=root&useSSL=false&serverTimezone=UTC&useOldAliasMetadataBehavior=true&allowPublicKeyRetrieval=true";

    /**
     * connection
     */
    private static Connection conn;

    /**
     * prepared statement
     */
    private static PreparedStatement preparedStatement;

    /**
     * Connection with db
     */
    static {
        String username = "root";
        String password_win = "123456";
        //String password = "19970228";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //conn = DriverManager.getConnection(url, username, password);
            conn=DriverManager.getConnection(url_win,username,password_win);
            System.out.println(conn);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Insert log in to db
     *
     * @param logEntity log pojo instance
     */
    public static void insertLog(LogEntity logEntity) {
        String sql = "INSERT INTO Student_Log (Stu_ID, Visit_Time, Reason)" +
                "VALUES (?, ?, ?);";
        Timestamp timestamp = new Timestamp(logEntity.getVisitTime().getTime());
        try {
            preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, logEntity.getStudentID());
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.setString(3, logEntity.getReason());

            // Execute statement and return the number of rows affected
            int rowCount = preparedStatement.executeUpdate();
            System.out.println("Number of rows affected: " + rowCount);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Insert student into db
     *
     * @param studentEntity student pojo instance
     */
    public static void insertStudent(StudentEntity studentEntity) {
        String sql = "INSERT INTO Student_Info (Student_ID, Student_Name, Age, Program, Gender,"
                + "Country, Remark)" + "VALUES (?, ?, ?, ?, ?, ?, ?);";
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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Select student by id
     *
     * @param stuID
     * @return information of the student
     */
    public static StudentEntity selectStudent(String stuID) {
        String sql = "select * from `Student_Info` where Student_Info.Student_ID = ?";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, stuID);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            StudentEntity studentEntity = new StudentEntity(rs.getString(1), rs.getString(2),
                    rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7));
            return studentEntity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Select latest log of the student
     *
     * @param stuID id
     * @return latest log of the student
     */
    public static LogEntity selectLatestLog(String stuID) {
        String sql = "select * from `Student_Log` where Stu_ID = ? and `Student_Log`.`Visit_Time`=(select max(Visit_Time) FROM\n"
                + "`Student_Log` where Stu_ID = ?);";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, stuID);
            preparedStatement.setString(2, stuID);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            LogEntity logEntity = new LogEntity(rs.getString(1), rs.getDate(2), rs.getString(3));
            return logEntity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Select log information by id
     *
     * @param stuID id
     * @return log information of id
     */
    public static List<LogEntity> selectStuLog(String stuID) {
        String sql = "select * from `Student_Log` where Stu_ID = ?;";
        LogEntity logEntity;
        List<LogEntity> logs = new ArrayList<>();
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, stuID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                logEntity = new LogEntity(rs.getString(1), rs.getDate(2), rs.getString(3));
                logs.add(logEntity);
            }
            return logs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Select all the log information
     *
     * @return all the log information
     */
    public static List<LogEntity> selectAllLog() {
        String sql = "select * from `Student_Log`;";
        LogEntity logEntity;
        List<LogEntity> logs = new ArrayList<>();
        try {
            preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                logEntity = new LogEntity(rs.getString(1), rs.getDate(2), rs.getString(3));
                logs.add(logEntity);
            }
            return logs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Select all log information by gender
     *
     * @return All log information by gender
     */
    public static List<LogEntity> selectLogGender() {
        String sql = "select Student_Log.Stu_ID, Visit_Time, Reason, Gender "
                + "from Student_Log, Student_Info where Student_Info.Student_ID=Student_Log.Stu_ID order by Reason";
        LogEntity logEntity;
        List<LogEntity> logs = new ArrayList<>();
        try {
            preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                logEntity = new LogEntity(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getString(4));
                logs.add(logEntity);
            }
            return logs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Select announcement
     *
     * @return announcement information by random
     */
    public static List<String> selectAnnouncement() {
        String sql = "select announcement from Announcement";
        List<String> annList_Temp = new ArrayList<>();
        List<String> annList = new ArrayList<>();
        Random random = new Random();
        try {
            preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                annList_Temp.add(rs.getString(1));
            }
            while (annList.size() < 2) {
                int index = random.nextInt(annList_Temp.size());
                if (!annList.contains(annList_Temp.get(index))) {
                    annList.add(annList_Temp.get(index));
                    System.out.println(annList_Temp.get(index));
                }
            }
            return annList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Select warnings
     *
     * @return warning by random
     */
    public static List<String> selectWarnings() {
        String sql = "select warnings from Warnings";
        List<String> warList_Temp = new ArrayList<>();
        List<String> warList = new ArrayList<>();
        Random random = new Random();
        try {
            preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                warList_Temp.add(rs.getString(1));
            }
            while (warList.size() < 2) {
                int index = random.nextInt(warList_Temp.size());
                if (!warList.contains(warList_Temp.get(index))) {
                    warList.add(warList_Temp.get(index));
                    System.out.println(warList_Temp.get(index));
                }
            }
            return warList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        selectAnnouncement();
        selectWarnings();
    }
}