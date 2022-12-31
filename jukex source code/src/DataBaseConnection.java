import java.sql.*;


public class DataBaseConnection {

    static Connection connection=null; // 1.if this is non static
    Statement statement=null;
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        //Loading Drivers
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root","root"); //2.can't access here because non-static variables cannot access in static method
        return  connection;


    }







}
