import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Podcast {

    private static int podcastId;
    private String podcastName;
    private String podcastPath;

    public int getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(int podcastId) {
        this.podcastId = podcastId;
    }

    public String getPodcastName() {
        return podcastName;
    }

    public void setPodcastName(String podcastName) {
        this.podcastName = podcastName;
    }

    public String getPodcastPath() {
        return podcastPath;
    }

    public void setPodcastPath(String podcastPath) {
        this.podcastPath = podcastPath;
    }

    public Podcast(int podcastId, String podcastName, String podcastPath) {
        this.podcastId = podcastId;
        this.podcastName = podcastName;
        this.podcastPath = podcastPath;
    }

    public Podcast(){

    }

    @Override
    public String toString() {
        return "Podcast{" +
                "podcastId=" + podcastId +
                ", podcastName='" + podcastName + '\'' +
                ", podcastPath='" + podcastPath + '\'' +
                '}';
    }

    Connection connection = null;
    Statement statement = null;

    Songs songs = new Songs();

    public void displayPodcast() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {

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
        ResultSet resultSet1 = statement.executeQuery("select count(podcastName) from podcast");
        int count_of_podcasts = 0;

        while (resultSet1.next()){
            count_of_podcasts=resultSet1.getInt(1);
        }
        Scanner scp = new Scanner(System.in);

        try {
                Podcast podcast = new Podcast();
                System.out.println("enter serial number of podcast");
                int selected_episode_number = scp.nextInt();
                if (selected_episode_number >= 1 && selected_episode_number <= count_of_podcasts) {
                    podcast.displayPodcastEpisode(selected_episode_number);
                    setPodcastId(selected_episode_number);

                } else {
                    while (selected_episode_number > count_of_podcasts + 1) {
                        System.out.println("Invalid input");
                        System.out.println("please enter valid serial number of podcast in given range");
                        int reselected = scp.nextInt();
                        if (reselected <= count_of_podcasts) {
                            podcast.displayPodcastEpisode(reselected);
                            break;
                        }
                    }
                }
        }
        catch (InputMismatchException ime){
            System.out.println("Input miss match : integer expected but String value entered\n");
        }catch (FileNotFoundException fileNotFoundException){
            System.out.println(fileNotFoundException);
        }

    }

    public void displayPodcastEpisode(int podcastid) throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        connection = DataBaseConnection.getConnection();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select PodcastEpisodeId,durationofpodcastepisode,episodenumber,podcastepisodename,PathOfEpisode from podcastEpisodes where podcastid="+podcastid);
        System.out.format("%30s","*********** Podcast Episodes  **********\n");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("%10s %12s %19s %20s %30s","Podcast Episode Id","Duration","Episode Number","Episode name","Episode path\n");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        while (resultSet.next()){
            System.out.format("%10s %20s %14s %26s %48s\n",resultSet.getInt(1),resultSet.getString(2),resultSet.getFloat(3),resultSet.getString(4),resultSet.getString(5));
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\n--------click on above link to listen the podcast-----------\n");
        System.out.println("\nFor checking podcast again enter 1 or 2 to add in play list 0  Return to main menu");
        Scanner scpe = new Scanner(System.in);

        try {
            Byte chek_podcast = scpe.nextByte();
            if (chek_podcast == 1) {
                Podcast p = new Podcast();
                p.displayPodcast();
            } else if (chek_podcast==0){
                System.out.println("Returning to main.....");
            }else if (chek_podcast==2){
                System.out.println("Add to play list");
                Playlist playlist = new Playlist();
                playlist.createPlaylistforPdcast();
            }
        }catch (InputMismatchException inputMismatchException){
            System.out.println("Input mismatch");
        }

    }

}
