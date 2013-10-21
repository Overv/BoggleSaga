package Tests;

import Model.Highscore;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/21/13
 * Time: 10:48 PM
 */
public class HighscoreTest {
    @Test
    public void testFillHighscoreList() throws Exception {
        Highscore highscore = new Highscore();
        System.out.println(highscore.toString());
    }

    @Test
    public void testAddHighscore() throws Exception {
        Highscore highscore = new Highscore();
        highscore.addHighscore("Robert", 488);
    }

    @Test
    public void testSaveHighscores() throws Exception {
        Highscore highscore = new Highscore();
        highscore.saveHighscores();
        highscore.toString();
    }
    
    @Test
    public void testHighscoresToString() {
        Highscore highscore = new Highscore();
        highscore.toString();
    }
}
