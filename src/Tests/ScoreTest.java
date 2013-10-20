package Tests;

import Model.Score;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/20/13
 * Time: 9:31 PM
 */
public class ScoreTest {
    
    @Test
    public void testScore() throws Exception {
        Score score = new Score();
        ArrayList<String> foundWords = new ArrayList<String>();
        assert score.getCurrentScore() == 0;
        foundWords.add("beep"); // 1 point
        score.updateScore(foundWords);
        assert score.getCurrentScore() == 1;
        foundWords.add("hands"); // 2 points
        score.updateScore(foundWords);
        assert score.getCurrentScore() == 3;
        foundWords.add("wallet"); // 3 points
        score.updateScore(foundWords);
        assert score.getCurrentScore() == 6;
        foundWords.add("leaflet"); // 5 points
        score.updateScore(foundWords);
        assert score.getCurrentScore() == 11;
        foundWords.add("fairytale"); // 11 points
        score.updateScore(foundWords);
        assert score.getCurrentScore() == 22;
    }
}
