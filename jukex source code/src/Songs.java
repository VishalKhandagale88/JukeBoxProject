import javax.sound.sampled.*;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class Songs {

    private int songId;
    private String  durationOFSong;
    private String SongName;
    private String SongPath;
    private String songGenre;
    private String ArtistName;
    private String Album;
    private int plid;

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
        this.SongName = songName;
        SongPath = songPath;
        this.songGenre = songGenre;
        ArtistName = artistName;
        Album = album;
    }


    Songs(int songId,String songPath){
        this.songId=songId;
        SongPath=songPath;
    }

    public Songs(){
    }
    private int userid_in_songs;
    public Songs(String songPath,int userid_in_songs,int songId){
        this.songId=songId;
        this.SongPath = songPath;
        this.userid_in_songs = userid_in_songs;
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


    public void  displaySongs()  {
        // this method will display ant db other than user database
        try {
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
            int i =0;
            while (result.next()){
                System.out.format("%-10s %10s %30s %32s %30s %35s \n",result.getInt(1),result.getString(2),result.getString(3),result.getString(5),result.getString(6),result.getString(7));
                songsdispaly.add(new Songs(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),result.getString(5),result.getString(6),result.getString(7)));
                i++;
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            System.out.println("- Do u want to listen songs -");
            System.out.println("y->for yes , n-> for no");
            Scanner scda = new Scanner(System.in);
            String listen_on_or_not = scda.next();
            if(listen_on_or_not.contains("y")) {
                System.out.println("select song number to play");
                Scanner scds = new Scanner(System.in);
                int song_selected = scds.nextInt();
                if (song_selected>0 && song_selected <= i){
                    playSong(songsdispaly, song_selected);
                System.out.println("Would you like to listen other songs!");
                System.out.println("n -> for No");
                char re_listen = scda.next().charAt(0);
                while (re_listen != 'n') {
                    System.out.println("Enter song number : ");
                    int song_number_reselected = scda.nextInt();
                    playSong(songsdispaly, song_number_reselected);
                    System.out.println("Enter q to return to main display or any key listen again");
                    re_listen = scda.next().charAt(0);
                    if (re_listen == 'q') {
                        break;
                    }
                }
            }else {
                    System.out.println("input Out of range\n");
                }
            } else if (listen_on_or_not.contains("n")){

            }

        }catch (InputMismatchException inputMismatchException){
            System.out.println("Please enter the song id in the given range");
        }catch (SQLException sqlException){
            System.out.println(sqlException);
        }catch (ClassNotFoundException classNotFoundException){
            System.out.println(classNotFoundException);
        }


    }

    public void  displyaArtist(){
//        ArrayList<Songs> artists = new ArrayList<>();
        try{
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
            int proper_out=0;
            System.out.println("Enter the artist name");
            Scanner scda = new Scanner(System.in);
            String artist_name_selected = scda.nextLine();
            boolean valid_artistname = false;
            String numbers = "0123456789~!@#$%^&*()_+<>?:{}[];'.,/";
            ArrayList<Character> numberslist = new ArrayList<>();
            for (int i=0;i<numbers.length();i++){
                    numberslist.add(numbers.charAt(i));
            }
            for (int i=0;i<artist_name_selected.length();i++){
                if (numberslist.contains(artist_name_selected.charAt(i))) {
                    valid_artistname = false;
                    proper_out=1;
                }else {
                    valid_artistname=true;
                }
            }
            ArrayList<Integer> songspresent = new ArrayList<>();

            if (valid_artistname==true) {
                Iterator<Songs> checkArtists = songsdispaly.listIterator();
                while (checkArtists.hasNext()){
                    Songs s =  checkArtists.next();
                    if (s.getArtistName().equals(artist_name_selected)) {
                        valid_artistname=true;
                        songspresent.add(s.getSongId());
                        break;
                    }else {
                        valid_artistname=false;
                    }
                }

            }
            if (valid_artistname==true){
                ResultSet result2 = statement.executeQuery("select * from songs where artistname='" + artist_name_selected + "' ");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.format("%-10s %15s %23s %35s %30s %30s", "serial no", "Duration", "Song name", "Genre", "Artist", "Album\n");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                while (result2.next()) {
                    System.out.format("%-10s %10s %30s %32s %30s %35s \n", result2.getInt(1), result2.getString(2), result2.getString(3), result2.getString(5), result2.getString(6), result2.getString(7));
                }
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Enter the song id to listen");
                int song_id_selected = scda.nextInt();
                for (Integer i : songspresent){
                    if (songspresent.contains(song_id_selected)){
                        playSong(songsdispaly, song_id_selected);
                    }else {
                        System.out.println("Songs is not present with given song id \n");
                    }
                }

            }else if (valid_artistname==false){
                System.out.println("--Invalid input-- \n");
            }
            }catch (InputMismatchException inputMismatchException){
                System.out.println(inputMismatchException);
            }catch (SQLException sqlException){
                System.out.println(sqlException);
            }catch (ClassNotFoundException classNotFoundException){
                System.out.println(classNotFoundException);
            }



//        return artists;

    }

    public void  displaySongNames() {
//        ArrayList<Songs> songnames =  new ArrayList<>();
        try {
            Scanner scdsn = new Scanner(System.in);
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
            int proper_out=0;
            System.out.println("Enter the song  name");
            String song_name_selected = scdsn.nextLine();
            boolean valid_song_name = false;
            String numbers = "0123456789~!@#$%^&*()_+<>?:{}[];'.,/";
            ArrayList<Character> numberslist = new ArrayList<>();
            for (int i=0;i<numbers.length();i++){
                numberslist.add(numbers.charAt(i));
            }
            for (int i=0;i<song_name_selected.length();i++){
                if (numberslist.contains(song_name_selected.charAt(i))) {
                    valid_song_name = false;
//                    proper_out=1;
                }else {
                    valid_song_name=true;
                }
            }
            ArrayList<Integer> songspresentname = new ArrayList<>();
            if (valid_song_name==true) {
                Iterator<Songs> checkArtists = songsdispaly.listIterator();
                while (checkArtists.hasNext()){
                    Songs s =  checkArtists.next();
                    if (s.getSongName().equals(song_name_selected)) {
                        valid_song_name=true;
                        songspresentname.add(s.getSongId());
                        break;
                    }else{
                        valid_song_name=false;


                    }
                }
            }
            if (valid_song_name==true) {
                ResultSet result2 = statement.executeQuery("select * from songs where songname='"+song_name_selected+"' ");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.format("%-10s %15s %23s %35s %30s %30s","serial no","Duration","Song name","Genre","Artist","Album\n");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                while (result2.next()){
                    System.out.format("%-10s %10s %30s %32s %30s %35s \n",result2.getInt(1),result2.getString(2),result2.getString(3),result2.getString(5),result2.getString(6),result2.getString(7));
                }
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Enter the song id to listen");
                int song_id_selected = scdsn.nextInt();
                for (Integer i : songspresentname){
                    if (songspresentname.contains(song_id_selected)){
                        playSong(songsdispaly, song_id_selected);
                    }else {
                        System.out.println("Songs is not present in list\n");
                    }
                }

            }if (valid_song_name==false){
                System.out.println("--Invalid input-- \n");
            }

        }catch (InputMismatchException inputMismatchException){
            System.out.println("Input Mismatch : Integer expected entered String");
        }catch (SQLException sqlException){
            System.out.println(sqlException);
        }catch (ClassNotFoundException classNotFoundException){
            System.out.println(classNotFoundException);
        }
        //return songnames;
    }


    public void  showAlbumName() {
//        List<Songs> album = new ArrayList<>();
        try {
            Scanner scsa =  new Scanner(System.in);
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
            int proper_out=0;
            System.out.println("Enter the album  name");
            String album_selected = scsa.nextLine();
            boolean valid_album_name = false;
            String numbers = "0123456789~!@#$%^&*()_+<>?:{}[];'.,/";
            ArrayList<Character> numberslist = new ArrayList<>();
            for (int i=0;i<numbers.length();i++){
                numberslist.add(numbers.charAt(i));
            }
            for (int i=0;i<album_selected.length();i++){
                if (numberslist.contains(album_selected.charAt(i))) {
                    valid_album_name = false;
                    proper_out=1;

                }else {
                    valid_album_name=true;
                }
            }
            ArrayList<Integer> songspresentname = new ArrayList<>();
            if (valid_album_name==true) {
                Iterator<Songs> checkArtists = songsdispaly.listIterator();
                while (checkArtists.hasNext()){
                    Songs s =  checkArtists.next();
                    if (s.getAlbum().equals(album_selected)) {
                        valid_album_name=true;
                        songspresentname.add(s.getSongId());
                        break;
                    }else {
                        valid_album_name=false;
                        proper_out=2;
                    }
                }
            } if (valid_album_name==true) {
                ResultSet result2 = statement.executeQuery("select * from songs where album='"+album_selected+"' ");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.format("%-10s %15s %23s %35s %30s %30s","serial no","Duration","Song name","Genre","Artist","Album\n");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                while (result2.next()){
                    System.out.format("%-10s %10s %30s %32s %30s %35s \n",result2.getInt(1),result2.getString(2),result2.getString(3),result2.getString(5),result2.getString(6),result2.getString(7));
                }
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Which song do you like to listen");
                int album_song_selected = scsa.nextInt();
                for (Integer i : songspresentname){
                    if (songspresentname.contains(album_song_selected)){
                        playSong(songsdispaly, album_song_selected);
                    }else {
                        System.out.println("Songs is not present\n");
                    }
                }
            }
            if (valid_album_name==false){
                System.out.println("--Invalid input-- \n");
            }
        }catch (InputMismatchException inputMismatchException){
            System.out.println("Input Mismatch : Integer expected entered String");
        }catch (SQLException sqlException){
            System.out.println(sqlException);
        }catch (ClassNotFoundException classNotFoundException){
            System.out.println(classNotFoundException);
        }
        /*System.out.println("Would you like to listen other songs!");
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
        }*/

    }

    public void showAllGenre() {
        try{
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
            String genre_selected = scag.nextLine();
//            int proper_out=0;
            boolean valid_genre_name = false;
            String numbers = "0123456789~!@#$%^&*()_+<>?:{}[];'.,/";
            ArrayList<Character> numberslist = new ArrayList<>();
            for (int i=0;i<numbers.length();i++){
                numberslist.add(numbers.charAt(i));
            }
            for (int i=0;i<genre_selected.length();i++){
                if (numberslist.contains(genre_selected.charAt(i))) {
                    valid_genre_name = false;
                    //proper_out=1;
                }else {
                    valid_genre_name=true;
                }
            }

            //
            ArrayList<String > songgenre = new ArrayList<>();
            Iterator<Songs> songsgenrecheck = songsdispaly.listIterator();
            while (songsgenrecheck.hasNext()){
                Songs s = songsgenrecheck.next();
                if (s.getSongGenre().equals(genre_selected)){
                    songgenre.add(s.getSongGenre());
                }
            }
            ArrayList<Integer> songspresentname = new ArrayList<>();

            if (valid_genre_name==true) {
                Iterator<Songs> checkArtists = songsdispaly.listIterator();
                while (checkArtists.hasNext()){
                    Songs s =  checkArtists.next();
                    if (songgenre.contains(genre_selected)){
                        if (s.getSongGenre().equals(genre_selected)) {
                            valid_genre_name=true;
                            songspresentname.add(s.getSongId());
                        }
                    }else if(!s.getSongGenre().equals(genre_selected)) {
                        valid_genre_name=false;
                        //proper_out=2;
                    }

                }
            }if (valid_genre_name==true) {

                ResultSet result2 = statement.executeQuery("select * from songs where songGenre='"+genre_selected+"' ");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.format("%-10s %15s %23s %35s %30s %30s","serial no","Duration","Song name","Genre","Artist","Album\n");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                while (result2.next()){
                    System.out.format("%-10s %10s %30s %32s %30s %35s \n",result2.getInt(1),result2.getString(2),result2.getString(3),result2.getString(5),result2.getString(6),result2.getString(7));
                }
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Which song do you like to listen");
                System.out.println("Enter the song id");
                int gener_song_selected = scag.nextInt();
                for (Integer i : songspresentname){
                    if (songspresentname.contains(gener_song_selected)){
                        playSong(songsdispaly, gener_song_selected);
                    }else {
                        System.out.println("Songs is not present\n");
                    }
                }
            }if (valid_genre_name==false){
                System.out.println("--Invalid input-- \n");
            }
        }catch (InputMismatchException inputMismatchException){
            System.out.println(inputMismatchException);
        }catch (SQLException sqlException){
            System.out.println(sqlException);
        }catch (ClassNotFoundException classNotFoundException){
            System.out.println(classNotFoundException);
        }

    }

    public void playSong(List<Songs> songsList,int Songid) {
        try{
            String SongPath = "";
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
            long tot=0l;
            long micro=0l;

            while (i!=0){
                System.out.println("Enter 1.play 2.pause 3.Reset 4. Resume 0 to stop");
                i= scp.nextInt();
                switch (i){
                    case 1:{
                        clip.start();
                        time = clip.getMicrosecondLength();
                        tot= clip.getMicrosecondLength()/1000000;
                        System.out.println("Total duration: "+tot+" seconds");
                        micro=clip.getMicrosecondPosition()/1000000;

                        System.out.println("played in seconds:"+micro);
                        System.out.println("remaining time for this song:"+(tot-micro)+" seconds\n");

                        break;
                    }
                    case 2:{
                        clip.stop();
                        time = clip.getMicrosecondPosition();
                        tot= clip.getMicrosecondLength()/1000000;
                        System.out.println("Total duration: "+tot+" seconds");
                        micro=clip.getMicrosecondPosition()/1000000;

                        System.out.println("played in seconds:"+micro);
                        System.out.println("remaining time for this song:"+(tot-micro)+" seconds\n");
                        break;
                    }
                    case 3: {
                        clip.setMicrosecondPosition(0);
                        clip.start();
                        tot= clip.getMicrosecondLength()/1000000;
                        System.out.println("Total duration: "+tot+" seconds");
                        micro=clip.getMicrosecondPosition()/1000000;

                        System.out.println("played in seconds:"+micro);
                        System.out.println("remaining time for this song:"+(tot-micro)+" seconds\n");
                        break;
                    }
                    case 4:{
                        clip.setMicrosecondPosition(time);
                        clip.start();
                        tot= clip.getMicrosecondLength()/1000000;
                        System.out.println("Total duration: "+tot+" seconds");
                        micro=clip.getMicrosecondPosition()/1000000;

                        System.out.println("played in seconds:"+micro);
                        System.out.println("remaining time for this song:"+(tot-micro)+" seconds\n");
                        break;
                    }
                    case 0:{
                        clip.close();
                        break;
                    }
                }
            }

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

}

