import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Podcast {
    Connection connection = null;
    Statement statement = null;
    public void displayPodcast() throws SQLException, ClassNotFoundException {
        connection = DataBaseConnection.getConnection();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from podcast;");
        System.out.println("*********** Podcast's  **********");
        System.out.println("--------------------------------");
        System.out.format("%10s %19s","Serial number","Podcast Name\n");
        System.out.println("--------------------------------");
        while (resultSet.next()){
            System.out.format("%5s %25s\n",resultSet.getInt(1),resultSet.getString(2));
        }
        System.out.println("--------------------------------");
    }

    public void displayPodcastEpisode(int podcastid) throws SQLException, ClassNotFoundException {
        connection = DataBaseConnection.getConnection();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select durationofpodcastepisode,episodenumber,podcastepisodename from podcastEpisodes where podcastid="+podcastid);
        System.out.format("%30s","*********** Podcast Episodes  **********\n");
        System.out.println("--------------------------------------------------------");
        System.out.format("%12s %19s %22s","Duration","Episode Number","Episode name\n");
        System.out.println("--------------------------------------------------------");
        while (resultSet.next()){
            System.out.format("%9s %14s %30s \n",resultSet.getString(1),resultSet.getFloat(2),resultSet.getString(3));
        }
        System.out.println("--------------------------------------------------------");
    }

}
