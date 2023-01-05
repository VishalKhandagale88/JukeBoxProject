import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


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
        System.out.println(this.userid);
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
    String current_user = "";

    public void showPlaylist() {

        try {
            Scanner ssp = new Scanner(System.in);

            System.out.println("**************************************************");
            System.out.println("**************************************************");
            System.out.println("----------- User name is "+User.usernameDuringTheRun);
            System.out.println("**************************************************");
            System.out.println("**************************************************");
            System.out.println("1.check your playlist  2.To create your playlist    0.To Main Menu");
            int choice = ssp.nextInt();
            if (choice==1){
                getThePlaylistOfCurrentUser();
            }
            else if (choice==2){
                createPlaylist();
            }else if (choice==0){
                System.out.println("Returning to main menu....\n");
            }

        }
        catch (InputMismatchException inputMismatchException){
            System.out.println(inputMismatchException);
        }
    }

    public void createPlaylist(){
//        HashSet<Songs> songsHashSet = new HashSet<>();
          List<Songs> songsList = new ArrayList<>();

        int user_id = 0; // --> user id is here
        int songid_tolisten=0;
        try {
            connection=DataBaseConnection.getConnection();
            statement = connection.createStatement();
            Scanner sccp = new Scanner(System.in);
            System.out.println("\n> Creating your playlist...\n");
            System.out.println("Enter the playlist name");
            String playlist_name = sccp.next(); //---> playlist name is Here
            setPlaylistName(playlist_name);
            ResultSet rs1 = statement.executeQuery("select * from users where username='"+User.usernameDuringTheRun+"' ");
            while (rs1.next()){
                user_id = rs1.getInt(1);
            }
            int plid=0;
            Songs songs = new Songs();
            setUserid(user_id);
            System.out.println(" Your playlist ");
            System.out.println("Add Songs or podcast into the playlist");
            System.out.println("1.To add songs 2.To add podcast 0.To exit");
            byte choice = sccp.nextByte();
            if (choice==1){
                boolean yes_no = true;
                while(yes_no){
                    songs.displaySongs();
                    System.out.println("-Select song from above list-");
                    Byte song_id = sccp.nextByte(); //-->Song id is here
                    System.out.println(" To create the playlist  ");
                    System.out.println("Enter the your playlist number");
                    System.out.println("This will make sure you having different playlists if you want");
                    plid = sccp.nextInt();

                    // user id is the only thing programmer will take care of
                    statement.executeUpdate("insert into playlist(plid,playlistname,songid,userid) values('"+plid+"','"+playlist_name+"','"+song_id+"','"+user_id+"') ");
                    System.out.println(">*< Your playlist is updated >*<\n");
                    System.out.println("-Do you want to add more songs-");
                    System.out.println("y->for yes  , n->for no");
                    String adding_more = sccp.next();
                    if (adding_more.contains("n")){
                        yes_no=false;
                    }
                }
                getThePlaylistOfCurrentUser();

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
            connection=DataBaseConnection.getConnection();
            statement = connection.createStatement();
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
            System.out.println("Podcast updated in playlist with user name : "+current_user);
        }catch (SQLException sqlException){
            System.out.println(sqlException);
        }catch (InputMismatchException inputMismatchException){
            System.out.println(inputMismatchException);
        }catch (ClassNotFoundException classNotFoundException){
            System.out.println(classNotFoundException);
        }
    }

    public void getThePlaylistOfCurrentUser(){

        try {
            connection=DataBaseConnection.getConnection();
            statement = connection.createStatement();
            ResultSet rs1 = statement.executeQuery("select * from users where username='"+User.usernameDuringTheRun+"' ");
            int userId_0=0;
            while (rs1.next()){
                userId_0=rs1.getInt(1);
            }
            setUserid(userId_0);

            String str = "select * from playlist where userid="+getUserid()+" order by plid";
            ResultSet result = statement.executeQuery(str);
            System.out.format("%85s","---------------------------\n");
            System.out.format("%85s","****** Play List **********\n");
            System.out.format("%85s","---------------------------\n");
            System.out.println("--------------------------------------------------------------------------------------------------");
            System.out.format("%-10s %15s %23s %35s  ","Plid","playlistName","SongId","PodcastEpisodeId\n");
            System.out.println("--------------------------------------------------------------------------------------------------");
            while (result.next()){
                System.out.format("%-10s %10s %30s %32s  \n",result.getInt(2),result.getString(3),result.getInt(4),result.getInt(5));
            }
            System.out.println("--------------------------------------------------------------------------------------------------\n");
            usersSpecificPlayList();
            System.out.println();
        }catch (SQLException sqlException){
            System.out.println(sqlException);
        }catch (InputMismatchException inputMismatchException){
            System.out.println(inputMismatchException);
        }catch (ClassNotFoundException classNotFoundException){
            System.out.println(classNotFoundException);
        }
    }
    public  void usersSpecificPlayList(){
        ArrayList<Songs> songsArrayList = new ArrayList<>();
        Scanner scusp = new Scanner(System.in);
        try{
            connection=DataBaseConnection.getConnection();
            statement = connection.createStatement();
            System.out.println();
            ResultSet rs1 = statement.executeQuery("select * from users where username='"+User.usernameDuringTheRun+"' ");
            Scanner scsp = new Scanner(System.in);
            int i=0;
            ResultSet resultSet0 = statement.executeQuery("select count(distinct(plid)) from playlist where userid="+getUserid() +"  group by plid");
            while (resultSet0.next()){
                resultSet0.getInt(1);
                i++;
            }
            System.out.println("\n********** You have "+i+" Number of playlists  *************** ");
            if (i>0 && i<=i){
                System.out.println("which playlist you want to select ");
                // select count(distinct(plid)) from playlist where userid=1 group by plid;
                Byte selected_playlist = scsp.nextByte();
                ResultSet resultSet = statement.executeQuery("select * from playlist where plid="+selected_playlist+" and userid="+getUserid());
                System.out.println("--------------------------------------------------------------------------------------------------");
                System.out.format("%-10s %15s %23s %35s ","Playlist","playlistName","SongId","PodcastEpisodeId\n");
                System.out.println("--------------------------------------------------------------------------------------------------");
                while (resultSet.next()){
                    System.out.format("%-10s %10s %30s %32s \n",resultSet.getInt(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getInt(5));
                }
                System.out.println("--------------------------------------------------------------------------------------------------\n");
                ResultSet result1_0 = statement.executeQuery("select * from songs");
                while (result1_0.next()){
                    songsArrayList.add(new Songs(result1_0.getInt(1),result1_0.getString(2),result1_0.getString(3),result1_0.getString(4),result1_0.getString(5),result1_0.getString(6),result1_0.getString(7)));
                }
                Songs s = new Songs();
                System.out.println("Enter song name to be played");
                int song_number = scusp.nextInt();
                s.playSong(songsArrayList,song_number);
            }else{
                System.out.println("Returning to main....");
            }
        }catch (SQLException sqlException){
            System.out.println(sqlException);
        }catch (InputMismatchException inputMismatchException){
            System.out.println(inputMismatchException);
        }catch (ClassNotFoundException classNotFoundException){
            System.out.println(classNotFoundException);
        }
    }


}
