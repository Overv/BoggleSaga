package View;

import Model.DiceCoord;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/16/13
 * Time: 9:18 PM
 */
public class GameFrame extends JFrame {
    
    private int gridWidth;
    private int gridHeight;
    
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private DefaultListModel<String> wordListModel;
    private JList<String> wordList;

    private JButton[][] diceButtons; 
    private char[][] diceLetters; 

    private boolean draggingWord = false;
    private ArrayList<DiceCoord> diceDragged;
    private Color defaultTextColor;

    private OnWordListener onWordListener;

    public GameFrame(int gridWidth, int gridHeight) {
        // set the ammount of buttons and letters based on size
        diceButtons = new JButton[gridWidth][gridHeight];
        diceLetters = new char[gridWidth][gridHeight];
        
        // set gridWith & height
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        
        // Use native look and feel (if this fails, we have bigger problems)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        // Initialize window properties
        setSize(800, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Boggle Saga Inc.");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create layout
        createLayout();

        // Show window
        setVisible(true);
    }

    private void createLayout() {
        // Create label displaying time left
        JLabel timeCaptionLabel = new JLabel("Time left");
        timeCaptionLabel.setAlignmentX(CENTER_ALIGNMENT);
        timeCaptionLabel.setBorder(new EmptyBorder(10, 20, 0, 20));
        timeCaptionLabel.setFont(new Font(timeCaptionLabel.getFont().getFontName(), Font.PLAIN, 25));

        timeLabel = new JLabel();
        timeLabel.setAlignmentX(CENTER_ALIGNMENT);
        timeLabel.setBorder(new EmptyBorder(5, 20, 10, 20));
        timeLabel.setFont(new Font(timeLabel.getFont().getFontName(), Font.PLAIN, 60));

        // Create score label displaying current score
        JLabel scoreCaptionLabel = new JLabel("Score");
        scoreCaptionLabel.setAlignmentX(CENTER_ALIGNMENT);
        scoreCaptionLabel.setBorder(new EmptyBorder(10, 20, 0, 20));
        scoreCaptionLabel.setFont(new Font(timeCaptionLabel.getFont().getFontName(), Font.PLAIN, 25));

        scoreLabel = new JLabel();
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
        scoreLabel.setBorder(new EmptyBorder(5, 20, 10, 20));
        scoreLabel.setFont(new Font(scoreLabel.getFont().getFontName(), Font.PLAIN, 60));

        // Create container layout for info labels
        JPanel infoLabelContainer = new JPanel();
        infoLabelContainer.setLayout(new BoxLayout(infoLabelContainer, BoxLayout.PAGE_AXIS));

        infoLabelContainer.add(timeCaptionLabel);
        infoLabelContainer.add(timeLabel);
        infoLabelContainer.add(scoreCaptionLabel);
        infoLabelContainer.add(scoreLabel);

        add(infoLabelContainer, BorderLayout.LINE_START);

        // Create list containing guessed words
        JLabel wordCaptionLabel = new JLabel("Words found");
        wordCaptionLabel.setAlignmentX(CENTER_ALIGNMENT);
        wordCaptionLabel.setBorder(new EmptyBorder(0, 10, 10, 10));
        wordCaptionLabel.setFont(new Font(wordCaptionLabel.getFont().getFontName(), Font.PLAIN, 25));

        wordListModel = new DefaultListModel<String>();

        wordList = new JList<String>(wordListModel);
        wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        wordList.setLayoutOrientation(JList.VERTICAL);
        wordList.setVisibleRowCount(-1);
        wordList.setFont(new Font(wordList.getFont().getFontName(), Font.PLAIN, 20));

        JScrollPane wordListScroller = new JScrollPane(wordList);
        wordListScroller.setPreferredSize(new Dimension(160, 800));

        JPanel wordListContainer = new JPanel();
        wordListContainer.setLayout(new BoxLayout(wordListContainer, BoxLayout.PAGE_AXIS));
        wordListContainer.setBorder(new EmptyBorder(10, 20, 10, 10));

        wordListContainer.add(wordCaptionLabel);
        wordListContainer.add(wordListScroller);

        add(wordListContainer, BorderLayout.LINE_END);

        // Add grid with widthxheight dices
        GridLayout gridLayout = new GridLayout(gridWidth, gridHeight);
        gridLayout.setHgap(30);
        gridLayout.setVgap(30);

        JPanel diceGridContainer = new JPanel();
        diceGridContainer.setLayout(gridLayout);
        diceGridContainer.setBorder(new EmptyBorder(10, 0, 10, 0));

        // Create button for each dice
        for (int y = 0; y < gridWidth; y++) {
            for (int x = 0; x < gridHeight; x++) {
                final JButton button = diceButtons[x][y] = new JButton();
                button.setFont(new Font(button.getFont().getFontName(), Font.PLAIN, 40));
                button.addMouseListener(createDiceListener(button, x, y));
                diceGridContainer.add(button);

                // Get default button text color for restoring
                defaultTextColor = button.getForeground();
            }
        }

        add(diceGridContainer, BorderLayout.CENTER);
    }

    private MouseListener createDiceListener(final JButton button, final int x, final int y) {
        return new MouseListener() {
            // Unused
            public void mouseClicked(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                // Reset selection
                diceDragged = new ArrayList<DiceCoord>();
                draggingWord = true;

                diceDragged.add(new DiceCoord(x, y));
                button.setForeground(Color.GREEN);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                draggingWord = false;

                // Reset colors of dices
                for (DiceCoord coord : diceDragged) {
                    diceButtons[coord.x][coord.y].setForeground(defaultTextColor);
                }

                // Check if the selection followed the rules of the game
                if (!checkSelection(diceDragged)) {
                    return;
                }

                // If a listener has been registered, tell it about the selected potential word
                if (onWordListener != null) {
                    String word = wordFromDice(diceDragged);
                    onWordListener.onWord(word);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (draggingWord) {
                    diceDragged.add(new DiceCoord(x, y));
                    button.setForeground(Color.GREEN);
                }
            }
        };
    }

    private boolean checkSelection(ArrayList<DiceCoord> dice) {
        // Check if any dice were selected multiple times
        if (new HashSet<DiceCoord>(dice).size() != dice.size()) {
            return false;
        }

        // Check if subsequent dice are neighbours
        DiceCoord lastDie = dice.get(0);

        for (int i = 1; i < dice.size(); i++) {
            if (!isNeighbour(dice.get(i), lastDie)) {
                return false;
            }
            
            lastDie = dice.get(i);
        }

        return true;
    }

    private boolean isNeighbour(DiceCoord a, DiceCoord b) {
        double dist = Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
        return dist <= Math.sqrt(2.0);
    }

    private String wordFromDice(ArrayList<DiceCoord> dices) {
        String word = "";

        for (DiceCoord coord : dices) {
            word += diceLetters[coord.x][coord.y];
        }

        return word;
    }

    public void setTimeLeft(int seconds) {
        int minutes = seconds / 60;

        String secs = String.valueOf(seconds % 60);
        if (secs.length() < 2) secs = "0" + secs;

        timeLabel.setText(minutes + ":" + secs);
    }

    public void setScore(int score) {
        scoreLabel.setText(String.valueOf(score));
    }

    public void setDice(char[][] letters) {
        for (int x = 0; x < gridWidth; x++) {
            for (int y = 0; y < gridHeight; y++) {
                diceLetters[x][y] = letters[x][y];
                diceButtons[x][y].setText(Character.toString(letters[x][y]).toUpperCase());
            }
        }
    }

    public void setWordsFound(ArrayList<String> words) {
        wordListModel.clear();

        for (String word : words) {
            // Space for left padding improves layout
            wordListModel.addElement(" " + word.substring(0, 1).toUpperCase() + word.substring(1));
        }
    }

    public void setOnWordListener(OnWordListener listener) {
        onWordListener = listener;
    }

    public interface OnWordListener {
        public void onWord(String word);
    }
}
