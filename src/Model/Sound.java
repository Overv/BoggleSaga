package Model;

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
    public static String NEW_ACHIEVEMENT_SOUND = "src/Resources/Sounds/new_achievement.wav";
    public static String HOTSTREAK_SOUND = "src/Resources/Sounds/hotstreak.wav";
    private static InputStream musicStream;

    public static void playFoundWordSound() {
        playSound(FOUND_WORD_SOUND);
    }

    public static void playAchievementSound() {
        playSound(ACHIEVEMENT_SOUND);
    }

    public static void playGameSound() {
        playSound(GAME_SOUND);
    }
    
    public static void playNewAchievementSound() {
        playSound(NEW_ACHIEVEMENT_SOUND);
    }
    
    public static void playHotstreakSound() {
        playSound(HOTSTREAK_SOUND);
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

        if (audioFileIn.equals(GAME_SOUND)) {
            musicStream = audioStream;
        }

        AudioPlayer.player.start(audioStream);
    }

    public static void stopMusic() {
        if (musicStream != null) {
            AudioPlayer.player.stop(musicStream);
        }
    }
}
