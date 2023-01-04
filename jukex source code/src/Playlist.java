import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Playlist {
    private int PlaylistId;
    private  String playlistName;
    private  int userid;

    private int SongId;
    private int PodcastId;

    public int getPlaylistId() {
        return PlaylistId;
    }

    public void setPlaylistId(int playlistId) {
        PlaylistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getSongId() {
        return SongId;
    }

    public void setSongId(int songId) {
        SongId = songId;
    }

    public int getPodcastId() {
        return PodcastId;
    }

    public void setPodcastId(int podcastId) {
        PodcastId = podcastId;
    }

    public Playlist(int playlistId, String playlistName, int userid, int songId, int podcastId) {
        PlaylistId = playlistId;
        this.playlistName = playlistName;
        this.userid = userid;
        SongId = songId;
        PodcastId = podcastId;
    }

    public Playlist(){

    }
    @Override
    public String toString() {
        return "Playlist{" +
                "PlaylistId=" + PlaylistId +
                ", playlistName='" + playlistName + '\'' +
                ", userid=" + userid +
                ", SongId=" + SongId +
                ", PodcastId=" + PodcastId +
                '}';
    }

    Connection connection=null;
    Statement statement = null;

    public void showPlaylist(){
        User user = new User();
        System.out.println(user.current_user);
        try {
            connection=DataBaseConnection.getConnection();
            statement = connection.createStatement();
            String str = "select * from playlist";
            ResultSet result = statement.executeQuery(str);
            System.out.format("%85s","---------------------------\n");
            System.out.format("%85s","****** Play List **********\n");
            System.out.format("%85s","---------------------------\n");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.format("%-10s %15s %23s %35s %30s %30s ","PlaylistId","Plid","playlistName","SongId","PodcastEpisodeId","userid\n");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
            while (result.next()){
                System.out.format("%-10s %10s %30s %32s %30s %30s\n",result.getInt(1),result.getInt(2),result.getString(3),result.getInt(4),result.getInt(5),result.getInt(6));
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            System.out.println("1.To create your playlist    0.To Main Menu");
            Scanner ssp = new Scanner(System.in);
            int choice = ssp.nextInt();
            if (choice==1){
                createPlaylist();
            }else{
                System.out.println("Returning to main menu....\n");
            }

        }
        catch (SQLException sqlException){
            System.out.println(sqlException);
        } catch (ClassNotFoundException classNotFoundException){
            System.out.println(classNotFoundException);
        }catch (InputMismatchException inputMismatchException){
            System.out.println(inputMismatchException);
        }
    }

    public void createPlaylist(){

        List<Songs> songsList = new ArrayList<>();
        System.out.println("> Creating your playlist...");
        int user_id = 0; // --> user id is here
        try {
            connection=DataBaseConnection.getConnection();
            statement = connection.createStatement();
            Scanner sccp = new Scanner(System.in);
            System.out.println("Enter the playlist name");
            String playlist_name = sccp.next(); //--->Here is playlist name
            setPlaylistName(playlist_name);
            System.out.println("please enter user name to create playlist");
            String user_name = sccp.next();
            ResultSet rs1 = statement.executeQuery("select * from users where username='"+user_name+"' ");

            while (rs1.next()){
                user_id = rs1.getInt(1);
            }
            Songs songs = new Songs();
            setUserid(user_id);
            System.out.println("Add Songs or podcast into the playlist");
            System.out.println("1.To add songs 2.To add podcast 0.To exit");
            byte choice = sccp.nextByte();
            if (choice==1){
                boolean yes_no = true;
                while(yes_no){

                    songs.displaySongs();
                    System.out.println("-Select song from above list-");
                    Byte song_id = sccp.nextByte(); //-->Song id is here
                    ResultSet rs2 = statement.executeQuery("select * from songs where songid="+song_id);
                    while (rs2.next()){
                        rs2.getInt(1);
                        songsList.add(new Songs(rs2.getInt(1),rs2.getString(2),rs2.getString(3),rs2.getString(4),rs2.getString(5),rs2.getString(6), rs2.getNString(7)));
                    }
                    System.out.println("To create the playlist  ");
                    System.out.println("Enter the your playlist number");
                    System.out.println("This will make sure you having different playlists if you want");
                    int plid = sccp.nextInt();
                    // user id is the only thing programmer will take care of
                    int rs3 = statement.executeUpdate("insert into playlist(plid,playlistname,songid,userid) values('"+plid+"','"+playlist_name+"','"+song_id+"','"+user_id+"') ");
                    System.out.println(">*< Your playlist is updated >*<\n");
                    System.out.println("-Do you want to add more songs-");
                    System.out.println("y->for yes  , n->for no");
                    String adding_more = sccp.next();
                    if (adding_more.contains("n")){
                        yes_no=false;
                    }
                }
                System.out.println("Would yo like to listen songs from the play list");
                System.out.println("y->for yes   n->for exit");
                char listen = sccp.next().charAt(0);
                if (listen=='y'){
                    System.out.println("Enter the playlist number song number");
                    Byte selected_playlist_number = sccp.nextByte();
                    System.out.println("Select the song id");
                    Byte selected_song_number = sccp.nextByte();
                    ResultSet resultSet = statement.executeQuery("select * from playlist where Plid="+selected_playlist_number+"and songid="+selected_song_number);
                    int songid_tolisten=0;
                    while (resultSet.next()){
                        songid_tolisten = resultSet.getInt(4);
                    }

                    songs.playSong(songsList,songid_tolisten);
                }
            }
            else if (choice==2) {
                System.out.println(getPlaylistName());
                Podcast podcast = new Podcast();
                podcast.displayPodcast();
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException);
        } catch (ClassNotFoundException classNotFoundException){
            System.out.println(classNotFoundException);
        }catch (InputMismatchException inputMismatchException){
            System.out.println(inputMismatchException);
        }catch (UnsupportedAudioFileException unsupportedAudioFileException){
            System.out.println(unsupportedAudioFileException);
        }catch (LineUnavailableException lineUnavailableException){
            System.out.println(lineUnavailableException);
        }catch (IOException ioException){
            System.out.println(ioException);
        }

    }

    public void createPlaylistforPdcast(){
        System.out.println(getPlaylistName());
        System.out.println(getUserid());
        System.out.println(getPodcastId());
        try{
            Scanner sccpld =new Scanner(System.in);
            System.out.println("Adding podcast to playlist");
            System.out.println("Enter playlist name");
            System.out.println("-Select song from above list-");
            System.out.println("To create the playlist  ");
            System.out.println("Enter the your playlist number");
            System.out.println("This will make sure you having different playlists if you want");
            int plid = sccpld.nextInt();
            System.out.println("Enter the podcast episode number");
            int podcast_episode_number =  sccpld.nextInt();
            int rs3 = statement.executeUpdate("insert into playlist(plid,playlistname,podcastEpisodeId,userid) values('"+plid+"','"+getPlaylistName()+"','"+podcast_episode_number+"','"+getUserid()+"') ");
        }catch (SQLException sqlException){
            System.out.println(sqlException);
        }catch (InputMismatchException inputMismatchException){
            System.out.println(inputMismatchException);
        }
    }


}
