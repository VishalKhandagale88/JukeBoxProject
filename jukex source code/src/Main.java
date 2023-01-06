import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        try{
            User user = new User();

            user.checkUser();
        }catch (InputMismatchException inputMismatchException){
            System.out.println(inputMismatchException);
        }

    }

}