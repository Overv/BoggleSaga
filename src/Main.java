import View.GameFrame;

public class Main {
    private static GameFrame view;

    public static void main(String[] args) {
        // Create components
        view = new GameFrame();

        // Temporary setup code
        view.setTimeLeft(0);

        view.setDices(new char[][] {
            {'R', 'H', 'R', 'E'},
            {'Y', 'P', 'C', 'S'},
            {'W', 'N', 'S', 'N'},
            {'T', 'E', 'G', 'O'}
        });

        view.setWordsGuessed(new String[] {"wench", "once"});
    }
}
