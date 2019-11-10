package database;

import org.opencv.core.Mat;

import com.sun.org.apache.xpath.internal.operations.Mod;
import netscape.security.UserTarget;

import java.lang.reflect.Type;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DAO {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    static final String url = "jdbc:mysql://127.0.0.1:3306/?user=root&useSSL=false&serverTimezone=UTC";
//    jdbc:mysql://127.0.0.1:3306/?user=root
    static final String url = "jdbc:mysql://127.0.0.1:3306/Student?user=root/Group6&useSSL=false&serverTimezone=UTC&useOldAliasMetadataBehavior=true";
    static final String url2 = "jdbc:mysql://localhost:3306/sys?user=root/&useSSL=false&serverTimezone=UTC";

    static {
        String username = "root";
        String password = "Pa22word";
        try{
            Class.forName(JDBC_DRIVER);
            Connection con = DriverManager.getConnection (url, username, password);
            System.out.println(con);
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public static void InsertValueForInfo(String ID, String FirstName, String LastName,
                                   int Age, String Program, String Gender, String Country,
                                   String Remark, Statement s) throws SQLException {
        s.execute("INSERT INTO Student_info VALUES ('"+ID+"', '"+FirstName+"', '"+LastName+"', "+Age+", '"+Program+"', '"+Gender+"', '"+Country+"','"+Remark+"')");
    }

    public static void DeleteValueByID(Statement s, String ID) throws SQLException {
        s.execute("delete from Student_info where Student_ID = '"+ID+"'");
    }

    public static void DeleteValueByAge(Statement s, int age) throws SQLException {
        s.execute("delete from Student_info where Age = '"+age+"'");
    }

    public static void DeleteValueByProgram(Statement s, String program) throws SQLException {
        s.execute("delete from Student_info where Program = '"+program+"'");
    }

    public static void DeleteValueByGender(Statement s, String gender) throws SQLException {
        s.execute("delete from Student_info where Gender = '"+gender+"'");
    }

    public static void DeleteValueByCountry(Statement s, String country) throws SQLException {
        s.execute("delete from Student_info where Country = '"+country +"'");
    }

    public static void ModifyAge(Statement s, int new_age, String ID) throws SQLException {
        s.execute("update Student_info set Age = "+new_age+" where Student_ID = '"+ID+"'");
    }

    public static void ModifyProgram(Statement s, String new_program, String ID) throws SQLException {
        s.execute("update Student_info set Program = '"+new_program+"' where Student_ID = '"+ID+"'");
    }

    public static void ModifyRemark(Statement s, String new_remark, String ID) throws SQLException {
        s.execute("update Student_info set Remark = '"+new_remark+"' where Student_ID = '"+ID+"'");
    }

    public static Map QueryInfo(Statement s, String ID) throws SQLException {
        ResultSet rs = s.executeQuery("select * from Student_info where Student_ID = '"+ID+"'");
        Map<String,Object> QueryMap = new HashMap<>();
        while(rs.next()){
            QueryMap.put("Student_ID",rs.getString("Student_ID"));
            QueryMap.put("First_NAme",rs.getString("First_NAme"));
            QueryMap.put("Last_Name",rs.getString("Last_Name"));
            QueryMap.put("Age",rs.getString("Age"));
            QueryMap.put("Program",rs.getString("Program"));
            QueryMap.put("Gender",rs.getString("Gender"));
            QueryMap.put("Country",rs.getString("Country"));
        }
        return QueryMap;
    }

    public static void InsertValueForLog(String ID, Timestamp time, String reason, String emotion, Statement s) throws SQLException {
        ResultSet rs = s.executeQuery("select count(*) Number from Student_Log where Stu_ID = '"+ID+"' group by Stu_ID");
        int Num = 1;
        if(rs.next())Num = rs.getInt("Number")+1;
        s.execute("INSERT INTO Student_Log VALUES ('"+ID+"', '"+time+"', "+Num+", '"+reason+"', '"+emotion+"')");
    }

    public static void DeleteLog(Statement s, String ID, Timestamp T ) throws SQLException {
        s.execute("delete from Student_Log where Stu_ID = '"+ID+"' and Visit_Time = '"+T+"'");
    }

    public static Map QUeryLog(Statement s, String ID, Timestamp time) throws SQLException {
        ResultSet rs = s.executeQuery("select * from Student_Log where Stu_ID = '"+ID+"' and Visit_Time = '"+time+"'");
        Map<String,Object> QueryMap = new HashMap<>();
        while(rs.next()){
            QueryMap.put("Stu_ID",rs.getString("Stu_ID"));
            QueryMap.put("Visit_Time",rs.getString("Visit_Time"));
            QueryMap.put("Visit_Num",rs.getString("Visit_Num"));
            QueryMap.put("Reason",rs.getString("Reason"));
            QueryMap.put("Emotion",rs.getString("Emotion"));
        }
        return QueryMap;
    }

    public static Timestamp ChangeTime(String TimeString) throws ParseException {
        Timestamp T = Timestamp.valueOf(TimeString);
        return T;
    }

}
