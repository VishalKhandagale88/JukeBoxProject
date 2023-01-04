import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        User user = new User();

        user.checkUser();

        Songs s = new Songs();
//        s.playSong();
//        s.showAllGenre();
    }

}

// note handle exception at invalid input