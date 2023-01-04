
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    static String current_user ="";

    public void displayUsersDatabase() throws SQLException, ClassNotFoundException {
        connection=DataBaseConnection.getConnection();
        statement= connection.createStatement();
        System.out.println("****** user data base ********");
        ResultSet resultSet = statement.executeQuery("select * from users");
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3));
        }
    }

    public void checkUser() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        connection=DataBaseConnection.getConnection();
        statement = connection.createStatement();
        Scanner scc = new Scanner(System.in);
        System.out.println(">>>>>>>> -- welcome to jukeX -- <<<<<<<<");
        System.out.println("Are you existing user : yes/no");
        try {
             String  existing = scc.next();
            if (existing.contains("y") || existing.contains("Y")){// dont want to use OR statement
                System.out.println("Enter user name : ");
                String existing_username = scc.next();
                String valid_password = "";
                ResultSet resultSet1 = statement.executeQuery("select * from users where username='"+existing_username+"' ");
                while (resultSet1.next()){
                    valid_password = resultSet1.getString(2);
                }
                current_user=existing;
                System.out.println(current_user);
                System.out.println(getUsername());
                if (existing_username.equals(valid_password) ){
                    System.out.println("Enter the password : ");
                    String  password = scc.next();
                    ResultSet resultSet = statement.executeQuery("select * from users where username='"+existing_username+"' ");
                    while (resultSet.next()){
                        valid_password = resultSet.getString(3);
                        if (valid_password.equals(password) ){
                            System.out.println(">>>>>>>> -- welcome to jukeX -- <<<<<<<<");
                            displayChoise();
                        }
                        else {
                            System.out.println("Incorrect password");
                            System.out.println("Re-login");
                            System.out.println("In development will take to the beginning to Enter user name");
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
                        System.out.println("In development");
                    }
                    System.out.println();
                }
            } else if (existing.contains("n")){createNewUser();}
        }catch (InputMismatchException inputMismatchException){
            System.out.println("Input mismatch : Expected Integer type but Entered String type");
            checkUser();
        }

    }

    private void createNewUser() {
        try{
            connection=DataBaseConnection.getConnection();
            statement = connection.createStatement();
            System.out.println(">>>>>>>> -- welcome to jukeX -- <<<<<<<<"+"\n"+"-* You are creating new account *-"+"\n"+"Enter user name to create new account");

            Scanner sccn = new Scanner(System.in);
            String new_userName = sccn.next();
            System.out.println("Enter password");
            System.out.println("PassWord length must be greater than 6  and less than 12 characters");
            String new_userPassWord = sccn.next();
            if (new_userPassWord.length()>6 && new_userPassWord.length()<12){
                    statement.executeUpdate("insert into users (username,passwords)  values ('" + new_userName + "','" + new_userPassWord + "')");
                    System.out.println(" ~ User account created ~ " + "\n" + " -> enjoy listening only on jukeX <- \n");
                    displayChoise();
            }else{
                System.out.println("PassWord length must be greater than 6  and less than 12 characters");
            }

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

    public void displayChoise() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        System.out.println("Enter your choice :");
        System.out.println("    1.show all songs \n\t2.show by artist \n\t3.show by song name  \n\t4.show by album name   \n\t5.show all genre \n\t6.show playlist  \n\t7.show podcast  \n\t0.exit");
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

                }else if (choise == 7) {
                    podcast.displayPodcast();
                } else if (choise==6) {
                    playlist.showPlaylist();
                }
                System.out.println("Enter your choice :");
                System.out.println("1.show all songs \n2.show by artist \n3.show by song name  \n4.show by album name   \n5.show all genre \n6.show playlist  \n7.show podcast  \n0.exit");
                choise= scd.nextInt();
            }
        }catch (InputMismatchException inputMismatchException){
            System.out.println("Input mismatch : Expected Integer type but Entered String type");
            displayChoise();
        }

    }


 }