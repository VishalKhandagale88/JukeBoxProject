import javax.sound.sampled.*;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;


public class Songs {

    private int songId;
    private String  durationOFSong;
    private String SongName;
    private String SongPath;
    private String songGenre;
    private String ArtistName;
    private String Album;

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getDurationOFSong() {
        return durationOFSong;
    }

    public void setDurationOFSong(String durationOFSong) {
        this.durationOFSong = durationOFSong;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public String getSongPath() {
        return SongPath;
    }

    public void setSongPath(String songPath) {
        SongPath = songPath;
    }

    public String getSongGenre() {
        return songGenre;
    }

    public void setSongGenre(String songGenre) {
        this.songGenre = songGenre;
    }

    public String getArtistName() {
        return ArtistName;
    }

    public void setArtistName(String artistName) {
        ArtistName = artistName;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public Songs(int songId, String durationOFSong, String songName, String songPath, String songGenre, String artistName, String album) {
        this.songId = songId;
        this.durationOFSong = durationOFSong;
        SongName = songName;
        SongPath = songPath;
        this.songGenre = songGenre;
        ArtistName = artistName;
        Album = album;
    }

    public Songs(){

    }

    @Override
    public String toString() {
        return "Songs{" +
                "songId=" + songId +
                ", durationOFSong='" + durationOFSong + '\'' +
                ", SongName='" + SongName + '\'' +
                ", SongPath='" + SongPath + '\'' +
                ", songGenre='" + songGenre + '\'' +
                ", ArtistName='" + ArtistName + '\'' +
                ", Album='" + Album + '\'' +
                '}';
    }

    Statement statement = null;
    Connection connection=null;


    public void  displaySongs() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        // this method will display ant db other than user database
        List<Songs> songsdispaly = new ArrayList<>();
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
            songsdispaly.add(new Songs(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7)));
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        System.out.println("- Do u want to listen songs -");
        System.out.println("y->for yes , n-> for no");
        Scanner scda = new Scanner(System.in);
        String listen_on_or_not = scda.next();
        if(listen_on_or_not.contains("y")){
            System.out.println("select song number to play");
            Scanner scds = new Scanner(System.in);
            int song_selected = scds.nextInt();
            playSong(songsdispaly,song_selected);
            System.out.println("Would you like to listen other songs!");
            char re_listen = scda.next().charAt(0);
            while(re_listen!='n'){
                System.out.println("Enter song number : ");
                int song_number_reselected = scda.nextInt();
                playSong(songsdispaly,song_number_reselected);
                System.out.println("Enter q to return to main dispalay");
                re_listen=scda.next().charAt(0);
                if (re_listen=='q'){
                    break;
                }
            }
        } else if (listen_on_or_not.contains("n")){
            System.out.println("Returning to Main menu....");
        }


    }

    public void  displyaArtist() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
//        ArrayList<Songs> artists = new ArrayList<>();
        List<Songs> songsdispaly = new ArrayList<>();
        connection = DataBaseConnection.getConnection();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from Songs");
        System.out.println("********* Artists names *********");
        System.out.println("----------------------------");
        System.out.format("%10s %15s","serial No","Artist\n");
        System.out.println("----------------------------");
        while (resultSet.next()){
            System.out.format("%5s %20s\n",resultSet.getInt(1),resultSet.getString(6));
            songsdispaly.add(new Songs(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7)));

        }
        System.out.println("----------------------------");
        System.out.println("select song number to play");
        Scanner scda = new Scanner(System.in);
        int song_selected = scda.nextInt();
        System.out.println("Sort according to artist name");
        playSong(songsdispaly,song_selected);
//        return artists;

    }

    public void  displaySongNames() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
//        ArrayList<Songs> songnames =  new ArrayList<>();
        List<Songs> songsdispaly = new ArrayList<>();
        connection = DataBaseConnection.getConnection();
        statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from songs");
        System.out.println("***********Song names **********");
        System.out.println("-----------------------------------");
        System.out.format("%10s %19s","Serial No","Song name\n");
        System.out.println("-----------------------------------");
        while (result.next()){
            System.out.format("%5s %27s\n",result.getInt(1),result.getString(3));
            songsdispaly.add(new Songs(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7)));
        }
        System.out.println("-----------------------------------");

        System.out.println("select song number to play");
        Scanner scdsa = new Scanner(System.in);
        int song_selected = scdsa.nextInt();
        playSong(songsdispaly,song_selected);
        //return songnames;
    }


    public void  showAlbumName() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
//        List<Songs> album = new ArrayList<>();
        List<Songs> songsdispaly = new ArrayList<>();
        connection = DataBaseConnection.getConnection();
        statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from songs");
        System.out.println("*********** Album names **********");
        System.out.println("-----------------------------------");
        System.out.format("%10s %15s","serial no","Album\n");
        System.out.println("-----------------------------------");
        while (result.next()){
            System.out.format("%5s %25s\n",result.getInt(1),result.getString(7));
            songsdispaly.add(new Songs(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7)));
        }
        System.out.println("-----------------------------------");
        System.out.println("select song number to play");
        Scanner scdsa = new Scanner(System.in);
        int songid = scdsa.nextInt();
        playSong(songsdispaly,songid);
        System.out.println("Would you like to listen other songs!");
        char re_listen = scdsa.next().charAt(0);
        while(re_listen!='n'){
            System.out.println("Enter song number : ");
            int song_number_reselected = scdsa.nextInt();
            playSong(songsdispaly,song_number_reselected);
            System.out.println("Enter q to return to main dispalay");
            re_listen=scdsa.next().charAt(0);
            if (re_listen=='q'){
                break;
            }
        }
       // return  album;
    }

    public void showAllGenre() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {

        List<Songs> songsdispaly = new ArrayList<>();
//        List<Songs> genres = new ArrayList<>();
        connection = DataBaseConnection.getConnection();
        statement = connection.createStatement();
        ResultSet result1 = statement.executeQuery("select distinct(songGenre) from songs");

        System.out.println("*********** Genre  **********");
        while (result1.next()){
            System.out.println(result1.getString(1));
        }
        ResultSet result1_0 = statement.executeQuery("select * from songs");
        while (result1_0.next()){
            songsdispaly.add(new Songs(result1_0.getInt(1),result1_0.getString(2),result1_0.getString(3),result1_0.getString(4),result1_0.getString(5),result1_0.getString(6),result1_0.getString(7)));
        }

        Scanner scag = new Scanner(System.in);
        System.out.println("Which Genre would you to listen!");
        System.out.println("Enter song id");

        String genre_selected = scag.nextLine();
        ResultSet result2 = statement.executeQuery("select * from songs where songGenre='"+genre_selected+"' ");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.format("%-10s %15s %23s %35s %30s %30s","serial no","Duration","Song name","Genre","Artist","Album\n");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
        while (result2.next()){
            System.out.format("%-10s %10s %30s %32s %30s %35s \n",result2.getInt(1),result2.getString(2),result2.getString(3),result2.getString(5),result2.getString(6),result2.getString(7));
        }
        System.out.println("Which song do you like to listen");
        int gener_song_selected = scag.nextInt();

        playSong(songsdispaly,gener_song_selected);

        //return genres;
    }

    public void playSong(List<Songs> songsList,int Songid) throws  IOException, UnsupportedAudioFileException, LineUnavailableException {
        String SongPath = "";
        /*
        ResultSet rs = statement.executeQuery("select songPath from songs where songId="+Songid);
        while(rs.next()){
            SongPath=rs.getString(1);
        }
        */
        ListIterator<Songs> songsListIterator = songsList.listIterator() ;
        while (songsListIterator.hasNext()){
            Songs s =  songsListIterator.next();
            if (s.getSongId()==Songid) {
                SongPath = s.getSongPath();
            }
        }

        Scanner scp = new Scanner(System.in);
        int i=-1;
        long time = 0l;
        AudioInputStream audioInputStream;
        audioInputStream = AudioSystem.getAudioInputStream(new File(SongPath).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        while (i!=0){
            System.out.println("Enter 1.play 2.pause 3.Reset 4. Resume 0 to stop");
            i= scp.nextInt();
            switch (i){
                case 1:{
                    clip.start();
                    time = clip.getMicrosecondLength();
                    break;
                }
                case 2:{
                    clip.stop();
                    time = clip.getMicrosecondPosition();
                    System.out.println(time);
                    break;
                }
                case 3: {
                    clip.setMicrosecondPosition(0);
                    clip.start();
                    break;
                }
                case 4:{
                    clip.setMicrosecondPosition(time);
                    clip.start();
                    break;
                }
                case 0:{
                    clip.close();
                    break;
                }
            }
        }


        //"D:\stack route\JAVA project\jukebox-project\songs wav file\classical\Raag.wav"
        //D:\stack route\JAVA project\jukebox-project\songs wav file\classical\Raag.wav
        //D:\stack route\JAVA project\jukebox-project\songs wav file\Indian cinema\Rasiya.wav"
        //D:\stack route\JAVA project\jukebox-project\songs wav file\Indian cinema\Rasiya.wav

    }

}

