import Controller.GameController;
import Model.Settings;
import View.GameFrame;
import View.SplashFrame;

import java.util.ArrayList;

public class BoggleSaga {
    public static void main(String[] args) {
        // Load settings
        Settings.loadSettings();

        // Show splash screen
        SplashFrame splashFrame = new SplashFrame();

        // Add callback to start main game
        splashFrame.setOnStartGameListener(new SplashFrame.OnStartGameListener() {
            @Override
            public void onStart() {
                // Create components
                GameController controller = new GameController();

                // Start game
                controller.startGame();
            }
        });
    }
}
