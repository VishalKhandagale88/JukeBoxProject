
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class User {
    Songs songs = new Songs();
    Podcast podcast = new Podcast();
    Statement statement = null;
    Connection connection = null;

    public void displayUsersDatabase() throws SQLException, ClassNotFoundException {
        connection=DataBaseConnection.getConnection();
        statement= connection.createStatement();
        System.out.println("****** user data base ********");
        ResultSet resultSet = statement.executeQuery("select * from users");
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3));
        }
    }

    public void checkUser() throws SQLException, ClassNotFoundException {
        connection=DataBaseConnection.getConnection();
        statement = connection.createStatement();
        Scanner scc = new Scanner(System.in);

        System.out.println("Are you existing user : yes/no");
        String existing = scc.next();
        if (existing.contains("y") || existing.contains("Y")){// dont want to use OR statement
            System.out.println("Enter user name : ");
            String existing_username = scc.next();
            String valid_password = "";
            String valid_user="";
            ResultSet resultSet1 = statement.executeQuery("select * from users where username='"+existing_username+"' ");
            while (resultSet1.next()){
                valid_user = resultSet1.getString(2);
            }
            if (existing_username.equals(valid_user)){

                System.out.println("Enter the password : ");
                String  password = scc.next();
                ResultSet resultSet = statement.executeQuery("select * from users where username='"+existing_username+"' ");
                while (resultSet.next()){
                    valid_password = resultSet.getString(3);
                    if (valid_password.equals(password) ){
                        System.out.println("------------Welcome jukeX---------------");
                        displayChoise();
                    }
                    else {System.out.println("Incorrect password");}
                }
            } else {
                System.out.println("User not found with user name :  "+existing_username);
            }
        } else{createNewUser();}
    }

    private void createNewUser() throws SQLException, ClassNotFoundException {
        connection=DataBaseConnection.getConnection();
        statement = connection.createStatement();
        System.out.println("Welcome to jukeX"+"\n"+"You are creating new account"+"\n"+"Enter user id to be created");
        Scanner sccn = new Scanner(System.in);
        String new_userName = sccn.next();
        System.out.println("Enter password");
        String new_userPassWord = sccn.next();
        statement.executeUpdate("insert into users (username,passwords)  values ('"+new_userName+"','"+new_userPassWord+"')");
        System.out.println("User account created"+"\n"+"enjoy listening only on jukeX");
    }

    public void displayChoise() throws SQLException, ClassNotFoundException {
        System.out.println("Enter your choice :");
        System.out.println("1.show all songs 2.show by artist 3.show by song name  4.show by album name   5.show all genre 6.show playlist  7.show podcast 0.exit");
        Scanner scd = new Scanner(System.in);
        int choise = scd.nextInt();

        while (choise!=0){
            if (choise==1){
                songs.displaySongs();
            } else if (choise==2) {
                songs.displyaArtist();
            } else if (choise==3) {
                songs.displaySongNames();
            } else if (choise==4) {
                songs.showAlbumName();
            } else if (choise==5) {
                songs.showAllGenre();
            }else if (choise==7){
                podcast.displayPodcast();
                System.out.println("Check respective episodes");
                System.out.println("Enter the episode number");
                int  podcastid= scd.nextInt();
                if(podcastid>0 && podcastid<4){
                    podcast.displayPodcastEpisode(podcastid);
                }
                else{
                    System.out.println("Invalid input ---");
                    System.out.println("Please enter the serial number in displayed range");
                }

            }
            System.out.println("Enter you choice");
            System.out.println("1.show all songs 2.show by artist 3.show by song name  4.show by album name   5.show all genre 6.show playlist  7.show podcast 0.exit");
            choise = scd.nextInt();
            if(choise<0 || choise>7){
                System.out.println("Not valid choice");
                System.out.println("Would you like to select again!"+"yes/no");
                char select = scd.next().charAt(0);
                if (select=='y'){
                    System.out.println("Enter you choice");
                    System.out.println("1.show all songs 2.show by artist 3.show by song name  4.show by album name   5.show all genre 6.show playlist  7.show podcast 0.exit");
                    choise = scd.nextInt();
                }
            }
        }

    }

 }