
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class User {
    private int userid;
    private String username;
    private String password;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public  String getUsername() {
        return this.username; // vishal
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(int userid, String username, String password) {
        this.userid = userid;
        this.username = username;
        this.password = password;
    }

    public User(){

    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    Songs songs = new Songs();
    Podcast podcast = new Podcast();
    Statement statement = null;
    Connection connection = null;
    Playlist playlist =new Playlist();

    static String usernameDuringTheRun ="";

    public void displayUsersDatabase() throws SQLException, ClassNotFoundException {
        connection=DataBaseConnection.getConnection();
        statement= connection.createStatement();
        System.out.println("****** user data base ********");
        ResultSet resultSet = statement.executeQuery("select * from users");
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3));
        }
    }

    public void checkUser(){

        try {
            connection=DataBaseConnection.getConnection();
            statement = connection.createStatement();
            Scanner scc = new Scanner(System.in);
            System.out.println("\n>>>>>>>> -- welcome to jukeBoX -- <<<<<<<<");
            System.out.println("Are you existing user : yes/no");
             String  existing = scc.next();
            if (existing.contains("y") || existing.contains("Y")){// dont want to use OR statement
                System.out.println("Enter user name : ");
                String existing_username = scc.next();
                String valid_password = "";
                String valid_user=""; //stop after this
                ResultSet resultSet1 = statement.executeQuery("select * from users where username='"+existing_username+"' ");
                while (resultSet1.next()){
                    valid_user = resultSet1.getString(2);
                }
                usernameDuringTheRun = valid_user;
                if (existing_username.equals(valid_user) ){
                    System.out.println("Enter the password : ");
                    String  password = scc.next();
                    ResultSet resultSet = statement.executeQuery("select * from users where username='"+existing_username+"' ");
                    while (resultSet.next()){
                        valid_password = resultSet.getString(3);
                        if (valid_password.equals(password) ){
                            System.out.println("\n>>>>>>>> -- welcome to jukeBoX -- <<<<<<<<\n");
                            displayChoise();
                        }
                        else {
                            System.out.println("Incorrect password");
                            System.out.println("Re-login");
                            checkUser();
                        }
                    }
                } else {
                    System.out.println("User not found with user name :  "+existing_username);
                    System.out.println("-* Create a new account with username *- "+existing_username);
                    System.out.println("Are you willing to create new account : "+"Yes/No");
                    char yes_no = scc.next().charAt(0);
                    if (yes_no=='y'){
                        createNewUser();
                    }
                    else if (yes_no=='n'){
                        System.out.println("\n----Thank you for visiting jukeBoX----");
                        return;
                    }
                    System.out.println();
                }
            } else if (existing.contains("n")){createNewUser();}
        }catch (InputMismatchException inputMismatchException){
            System.out.println("Input mismatch : Expected Integer type but Entered String type");
            checkUser();
        }catch (SQLException sqlException){
            System.out.println(sqlException);
        }catch (ClassNotFoundException classNotFoundException){
            System.out.println(classNotFoundException);
        }

    }

    private void createNewUser() {
        try{
            connection=DataBaseConnection.getConnection();
            statement = connection.createStatement();
            System.out.println(">>>>>>>> -- welcome to jukeBox -- <<<<<<<<"+"\n"+"-* You are creating new account *-"+"\n");
                Scanner sccn = new Scanner(System.in);
                System.out.println("Enter user name to create new account");
                String new_userName = sccn.next();

                System.out.println("\nEnter password");
                System.out.println("PassWord length must be greater than 5  and less than 10 characters");
                String new_userPassWord = sccn.next();
                if (new_userPassWord.length() >5 && new_userPassWord.length() <=18) {
                    statement.executeUpdate("insert into users (username,passwords)  values ('" + new_userName + "','" + new_userPassWord + "')");
                    System.out.println(" ~ User account created ~ " + "\n" + " -> enjoy listening only on jukeBox <- \n");
                    usernameDuringTheRun = new_userName;
                    displayChoise();
                }else {
                    System.out.println("\nTry to set password again with required length of password");
                    System.out.println("Re-open the application");
                }


        }catch (SQLException sqlException){
            System.out.println(sqlException);
        }catch (ClassNotFoundException classNotFoundException){
            System.out.println(classNotFoundException);
        }
    }

    public void displayChoise(){
        System.out.println("Enter your choice :");
        System.out.println("1.show all songs \t\n2.show by artist \t\n3.show by song name  \t\n4.show by album name   \t\n5.show all genre \t\n6.show podcast  \t\n7.show playlist  \t\n0.exit");
        Scanner scd = new Scanner(System.in);
        try {
            int choise = scd.nextInt();
            while (choise != 0 && choise<=8) {
                if (choise == 1) {
                    songs.displaySongs();
                } else if (choise == 2) {
                    songs.displyaArtist();
                } else if (choise == 3) {
                    songs.displaySongNames();
                } else if (choise == 4) {
                    songs.showAlbumName();
                } else if (choise == 5) {
                    songs.showAllGenre();
//                String genre = scd.next();

                }else if (choise == 6) {
                    podcast.displayPodcast();
                } else if (choise==7) {
                    playlist.showPlaylist();
                }
                System.out.println("Enter your choice :");
                System.out.println("1.show all songs \n2.show by artist \n3.show by song name  \n4.show by album name   \n5.show all genre \n6.show podcast  \n7.show playlist  \n0.exit");
                choise= scd.nextInt();
            }
        }catch (InputMismatchException inputMismatchException){
            System.out.println("Input mismatch : Expected Integer type but Entered String type");
            displayChoise();
        }catch (SQLException sqlException){
            System.out.println(sqlException);
        }catch (ClassNotFoundException classNotFoundException){
            System.out.println(classNotFoundException);
        }catch (UnsupportedAudioFileException unsupportedAudioFileException){
            System.out.println(unsupportedAudioFileException);
        }catch (LineUnavailableException lineUnavailableException){
            System.out.println(lineUnavailableException);
        }catch (IOException ioException){
            System.out.println(ioException);
        }

    }


 }