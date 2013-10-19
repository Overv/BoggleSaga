package View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/16/13
 * Time: 9:18 PM
 */
public class GameFrame extends JFrame {
    private JLabel timeLabel;
    private JList<String> wordList;
    private JButton[] diceButton = new JButton[16];

    public GameFrame() {
        // Use native look and feel (if this fails, we have bigger problems)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        // Initialize window properties
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Boggle Saga Inc.");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create layout
        createLayout();

        // Show window
        setVisible(true);
    }

    public void createLayout() {
        // Create label displaying time left
        JLabel timeCaptionLabel = new JLabel("Time left:");
        timeCaptionLabel.setAlignmentX(CENTER_ALIGNMENT);
        timeCaptionLabel.setBorder(new EmptyBorder(10, 20, 0, 20));
        timeCaptionLabel.setFont(new Font(timeCaptionLabel.getFont().getFontName(), Font.PLAIN, 15));

        timeLabel = new JLabel("0:00");
        timeLabel.setAlignmentX(CENTER_ALIGNMENT);
        timeLabel.setBorder(new EmptyBorder(5, 20, 10, 20));
        timeLabel.setFont(new Font(timeLabel.getFont().getFontName(), Font.PLAIN, 60));

        JPanel timeLabelContainer = new JPanel();
        timeLabelContainer.setLayout(new BoxLayout(timeLabelContainer, BoxLayout.PAGE_AXIS));

        timeLabelContainer.add(timeCaptionLabel);
        timeLabelContainer.add(timeLabel);

        add(timeLabelContainer, BorderLayout.LINE_START);

        // Create list containing guessed words
        JLabel wordCaptionLabel = new JLabel("Words found:");
        wordCaptionLabel.setAlignmentX(CENTER_ALIGNMENT);
        wordCaptionLabel.setBorder(new EmptyBorder(0, 10, 10, 10));
        wordCaptionLabel.setFont(new Font(wordCaptionLabel.getFont().getFontName(), Font.PLAIN, 15));

        wordList = new JList<String>();
        wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        wordList.setLayoutOrientation(JList.VERTICAL);
        wordList.setVisibleRowCount(-1);

        JScrollPane wordListScroller = new JScrollPane(wordList);
        wordListScroller.setPreferredSize(new Dimension(160, 800));

        JPanel wordListContainer = new JPanel();
        wordListContainer.setLayout(new BoxLayout(wordListContainer, BoxLayout.PAGE_AXIS));
        wordListContainer.setBorder(new EmptyBorder(10, 10, 10, 10));

        wordListContainer.add(wordCaptionLabel);
        wordListContainer.add(wordListScroller);

        add(wordListContainer, BorderLayout.LINE_END);

        // Add grid with 4x4 dices
        JPanel diceGridContainer = new JPanel();
        diceGridContainer.setLayout(new GridLayout(4, 4));
        diceGridContainer.setBorder(new EmptyBorder(10, 0, 10, 0));

        for (int i = 0; i < 16; i++) {
            diceButton[i] = new JButton(Character.toString((char) (i + 'A')));
            diceButton[i].setFont(new Font(diceButton[i].getFont().getFontName(), Font.PLAIN, 60));
            diceGridContainer.add(diceButton[i]);
        }

        add(diceGridContainer, BorderLayout.CENTER);
    }
}