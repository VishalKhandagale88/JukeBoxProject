import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Songs {

    Statement statement = null;
    Connection connection=null;

    public void displaySongs() throws SQLException, ClassNotFoundException {
        // this method will display ant db other than user database
        connection=DataBaseConnection.getConnection();
        statement = connection.createStatement();
        String str = "select * from songs";
        ResultSet result = statement.executeQuery(str);

        System.out.format("%85s","---------------------------\n");
        System.out.format("%85s","****** Songs List **********\n");
        System.out.format("%85s","---------------------------\n");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("%-10s %15s %23s %35s %30s %30s","serial no","Duration","Song name","Genre","Artist","Album\n");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
        while (result.next()){
            System.out.format("%-10s %10s %30s %32s %30s %35s \n",result.getInt(1),result.getString(2),result.getString(3),result.getString(5),result.getString(6),result.getString(7));
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void displyaArtist() throws SQLException, ClassNotFoundException {
        connection = DataBaseConnection.getConnection();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select songid,ArtistName  from Songs");
        System.out.println("********* Artists names *********");
        System.out.println("----------------------------");
        System.out.format("%10s %15s","serial No","Artist\n");
        System.out.println("----------------------------");
        while (resultSet.next()){
            System.out.format("%5s %20s\n",resultSet.getInt(1),resultSet.getString(2));
        }
        System.out.println("----------------------------");

    }

    public void displaySongNames() throws SQLException, ClassNotFoundException {
        connection = DataBaseConnection.getConnection();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select songid,songname from songs");
        System.out.println("***********Song names **********");
        System.out.println("-----------------------------------");
        System.out.format("%10s %19s","Serial No","Song name\n");
        System.out.println("-----------------------------------");
        while (resultSet.next()){
            System.out.format("%5s %27s\n",resultSet.getInt(1),resultSet.getString(2));
        }
        System.out.println("-----------------------------------");

    }


    public void showAlbumName() throws SQLException, ClassNotFoundException {
        connection = DataBaseConnection.getConnection();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select songid,album from songs");
        System.out.println("*********** Album names **********");
        System.out.println("-----------------------------------");
        System.out.format("%10s %15s","serial no","Album\n");
        System.out.println("-----------------------------------");
        while (resultSet.next()){
            System.out.format("%5s %25s\n",resultSet.getInt(1),resultSet.getString(2));
        }
        System.out.println("-----------------------------------");
    }

    public void showAllGenre() throws SQLException, ClassNotFoundException {
        connection = DataBaseConnection.getConnection();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select distinct(songGenre) from songs");
        System.out.println("*********** Genre  **********");

        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
    }

}
