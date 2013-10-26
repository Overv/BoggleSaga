import Controller.GameController;
import Model.Settings;
import View.GameFrame;

import java.util.ArrayList;

public class BoggleSaga {
    public static void main(String[] args) {
        // Load settings
        Settings.loadSettings();

        // Create components
        GameFrame view = new GameFrame();
        GameController controller = new GameController(view);

        // Start game
        controller.startGame();
    }
}
