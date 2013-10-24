package Tests;

import Model.Sound;
import javafx.application.Application;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/24/13
 * Time: 10:50 PM
 */
public class SoundTester {
    @Test
    public void playSound() {
        Sound.playAchievementSound();
        Sound.playFoundWordSound();
        Sound.playGameSound();
    }
}
