package View;

import Model.Achievement;
import Model.Game;
import Model.Highscore;
import Model.HighscoreEntry;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Overv
 * Date: 28-10-13
 * Time: 1:10
 * To change this template use File | Settings | File Templates.
 */
public class EndFrame extends JFrame {
    public EndFrame(Game game) {
        // Initialize window properties
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Boggle Saga Inc.");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create layout
        createLayout(game);

        // Show window
        setVisible(true);
    }

    private void createLayout(Game game) {
        // Add overview of performance at top
        JLabel overviewLabel = new JLabel("<html>Time's up! You found <b>" + game.getFoundWords().size() + " words</b> with a total value of <b>" + game.getCurrentScore() + " points</b>!</html>");
        overviewLabel.setAlignmentX(CENTER_ALIGNMENT);
        overviewLabel.setBorder(new EmptyBorder(30, 30, 30, 30));
        overviewLabel.setFont(new Font(overviewLabel.getFont().getFontName(), Font.PLAIN, 26));
        add(overviewLabel, BorderLayout.PAGE_START);

        // Create GridLayout to contain 3 lists
        GridLayout gridLayout = new GridLayout(1, 3);
        gridLayout.setHgap(10);

        JPanel gridContainer = new JPanel();
        gridContainer.setLayout(gridLayout);
        gridContainer.setBorder(new EmptyBorder(0, 0, 0, 0));

        // Add total words list
        JLabel wordCaptionLabel = new JLabel("All words");
        wordCaptionLabel.setAlignmentX(CENTER_ALIGNMENT);
        wordCaptionLabel.setBorder(new EmptyBorder(0, 10, 10, 10));
        wordCaptionLabel.setFont(new Font(wordCaptionLabel.getFont().getFontName(), Font.PLAIN, 25));

        DefaultListModel<String> wordListModel = new DefaultListModel<String>();

        for (String word : game.findAllWords()) {
            if (game.getFoundWords().contains(word)) {
                wordListModel.addElement(" " + word.toUpperCase());
            } else {
                wordListModel.addElement(" " + word);
            }
        }

        JList<String> wordList = new JList<String>(wordListModel);
        wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        wordList.setLayoutOrientation(JList.VERTICAL);
        wordList.setVisibleRowCount(-1);
        wordList.setFont(new Font(wordList.getFont().getFontName(), Font.PLAIN, 20));

        JScrollPane wordListScroller = new JScrollPane(wordList);
        wordListScroller.setPreferredSize(new Dimension(160, 800));

        JPanel wordListContainer = new JPanel();
        wordListContainer.setLayout(new BoxLayout(wordListContainer, BoxLayout.PAGE_AXIS));
        wordListContainer.setBorder(new EmptyBorder(10, 20, 20, 10));

        wordListContainer.add(wordCaptionLabel);
        wordListContainer.add(wordListScroller);

        gridContainer.add(wordListContainer, BorderLayout.CENTER);
        
        // Add highscores list
        JLabel highscoreCaptionLabel = new JLabel("Highscores");
        highscoreCaptionLabel.setAlignmentX(CENTER_ALIGNMENT);
        highscoreCaptionLabel.setBorder(new EmptyBorder(0, 10, 10, 10));
        highscoreCaptionLabel.setFont(new Font(highscoreCaptionLabel.getFont().getFontName(), Font.PLAIN, 25));

        DefaultListModel<String> highscoreListModel = new DefaultListModel<String>();

        for (HighscoreEntry hs : Highscore.getInstance().getHighscores()) {
            highscoreListModel.addElement(" " + hs.getName() + " - " + hs.getScore());
        }

        JList<String> highscoreList = new JList<String>(highscoreListModel);
        highscoreList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        highscoreList.setLayoutOrientation(JList.VERTICAL);
        highscoreList.setVisibleRowCount(-1);
        highscoreList.setFont(new Font(highscoreList.getFont().getFontName(), Font.PLAIN, 20));

        JScrollPane highscoreListScroller = new JScrollPane(highscoreList);
        highscoreListScroller.setPreferredSize(new Dimension(160, 800));

        JPanel highscoreListContainer = new JPanel();
        highscoreListContainer.setLayout(new BoxLayout(highscoreListContainer, BoxLayout.PAGE_AXIS));
        highscoreListContainer.setBorder(new EmptyBorder(10, 0, 20, 10));

        highscoreListContainer.add(highscoreCaptionLabel);
        highscoreListContainer.add(highscoreListScroller);

        gridContainer.add(highscoreListContainer, BorderLayout.CENTER);

        // Add achievements list
        JLabel achievementCaptionLabel = new JLabel("Achievements");
        achievementCaptionLabel.setAlignmentX(CENTER_ALIGNMENT);
        achievementCaptionLabel.setBorder(new EmptyBorder(0, 10, 10, 10));
        achievementCaptionLabel.setFont(new Font(achievementCaptionLabel.getFont().getFontName(), Font.PLAIN, 25));

        DefaultListModel<String> achievementListModel = new DefaultListModel<String>();

        for (Achievement a : game.getAchievements()) {
            achievementListModel.addElement(" " + a.getName());
        }

        JList<String> achievementList = new JList<String>(achievementListModel);
        achievementList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        achievementList.setLayoutOrientation(JList.VERTICAL);
        achievementList.setVisibleRowCount(-1);
        achievementList.setFont(new Font(achievementList.getFont().getFontName(), Font.PLAIN, 11));

        JScrollPane achievementListScroller = new JScrollPane(achievementList);
        achievementListScroller.setPreferredSize(new Dimension(160, 800));

        JPanel achievementListContainer = new JPanel();
        achievementListContainer.setLayout(new BoxLayout(achievementListContainer, BoxLayout.PAGE_AXIS));
        achievementListContainer.setBorder(new EmptyBorder(10, 0, 20, 20));

        achievementListContainer.add(achievementCaptionLabel);
        achievementListContainer.add(achievementListScroller);

        gridContainer.add(achievementListContainer, BorderLayout.CENTER);

        // Add lists to window
        add(gridContainer, BorderLayout.CENTER);
    }
}
