import Controller.GameController;
import View.GameFrame;

import java.util.ArrayList;

public class BoggleSaga {
    public static void main(String[] args) {
        // Create components
        GameFrame view = new GameFrame();
        GameController controller = new GameController(view, 4, 4);

        // Start game
        controller.startGame();
    }
}
