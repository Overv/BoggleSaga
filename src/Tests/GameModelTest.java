package Tests;

import Model.Game;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/18/13
 * Time: 9:58 PM
 */
public class GameModelTest {
    @Test
    public void GameModelTester() {
        Game game = new Game(4, 4);
        game.start();
        System.out.print(game.toString());
    }
}
