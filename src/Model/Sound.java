package Model;

import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/24/13
 * Time: 10:40 PM
 */
public class Sound {
    
    public static String FOUND_WORD_SOUND = "src/Resources/Sounds/found_word.wav";
    public static String ACHIEVEMENT_SOUND = "src/Resources/Sounds/achievement.wav";
    public static String GAME_SOUND = "src/Resources/Sounds/game.wav";
    

    public static void playFoundWordSound() {
        playSound(FOUND_WORD_SOUND);
    }

    public static void playAchievementSound() {
        playSound(ACHIEVEMENT_SOUND);
    }

    public static void playGameSound() {
        playSound(GAME_SOUND);
    }
    
    private static void playSound(String audioFileIn) {
        String audioFile = audioFileIn;
        InputStream in = null;
        try {
            in = new FileInputStream(audioFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        AudioStream audioStream = null;
        try {
            audioStream = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        AudioPlayer.player.start(audioStream);
    }
}
