package View;

import Model.DiceCoord;
import Model.Achievements.Achievement;
import Model.Achievements.AchievementListener;
import Model.Time.TimeListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TimerTask;
import java.util.Timer;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/16/13
 * Time: 9:18 PM
 */
public class GameFrame extends JFrame implements AchievementListener {
    
    private int gridWidth;
    private int gridHeight;
    
    private JLabel timeLabel;
    private JLabel scoreLabel;
    private JLabel wordPlaceholder;
    private DefaultListModel<String> wordListModel;
    private JList<String> wordList;

    private JButton[][] diceButtons; 
    private char[][] diceLetters; 

    private boolean draggingWord = false;
    private ArrayList<DiceCoord> diceDragged;
    private Color defaultTextColor;

    private OnWordListener onWordListener;
    
    private JLabel hotstreakLabel;
    private Timer hotstreakTimer;
    
    private JLabel achievementLabel;
    private JLabel achievementIconLabel;
    private ArrayList<Achievement> achievementsLeft;
    private Timer achievementTimer;

    public GameFrame(int gridWidth, int gridHeight) {        
        // initialize achievementsLeft
        achievementsLeft = new ArrayList<Achievement>();
        hotstreakTimer = new Timer();
        achievementTimer = new Timer();
        
        // set the ammount of buttons and letters based on size
        diceButtons = new JButton[gridWidth][gridHeight];
        diceLetters = new char[gridWidth][gridHeight];
        
        // set gridWith & height
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;

        // Initialize window properties
        setResizable(false);
        setTitle("Boggle Saga Inc.");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create layout
        createLayout();

        // Show window
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createLayout() {
        setPreferredSize(new Dimension(900, 500));

        // Create label displaying time left
        JLabel timeCaptionLabel = new JLabel("Time left");
        timeCaptionLabel.setAlignmentX(CENTER_ALIGNMENT);
        timeCaptionLabel.setBorder(new EmptyBorder(20, 20, 0, 20));
        timeCaptionLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
        timeCaptionLabel.setForeground(Color.decode("#D72828"));

        timeLabel = new JLabel();
        timeLabel.setAlignmentX(CENTER_ALIGNMENT);
        timeLabel.setBorder(new EmptyBorder(5, 20, 10, 20));
        timeLabel.setFont(new Font("Arial", Font.BOLD, 60));
        timeLabel.setForeground(Color.decode("#D72828"));

        // Create score label displaying current score
        JLabel scoreCaptionLabel = new JLabel("Score");
        scoreCaptionLabel.setAlignmentX(CENTER_ALIGNMENT);
        scoreCaptionLabel.setBorder(new EmptyBorder(10, 20, 0, 20));
        scoreCaptionLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
        scoreCaptionLabel.setForeground(Color.decode("#56A739"));

        scoreLabel = new JLabel();
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);
        scoreLabel.setBorder(new EmptyBorder(5, 20, 10, 20));
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 60));
        scoreLabel.setForeground(Color.decode("#56A739"));

        // Create container layout for info labels
        JPanel infoLabelContainer = new JPanel();
        infoLabelContainer.setLayout(new BoxLayout(infoLabelContainer, BoxLayout.PAGE_AXIS));
        infoLabelContainer.setBackground(Color.decode("#111111"));
        
        hotstreakLabel = new JLabel();
        hotstreakLabel.setAlignmentX(CENTER_ALIGNMENT);
        hotstreakLabel.setBorder(new EmptyBorder(5, 20, 10, 20));
        hotstreakLabel.setFont(new Font("Arial", Font.BOLD, 20));
        hotstreakLabel.setForeground(Color.decode("#D72828"));
        
        achievementIconLabel = new JLabel();
        achievementIconLabel.setAlignmentX(CENTER_ALIGNMENT);
        achievementIconLabel.setBorder(new EmptyBorder(5, 20, 10, 20));
        
        achievementLabel = new JLabel();
        achievementLabel.setAlignmentX(CENTER_ALIGNMENT);
        achievementLabel.setAlignmentY(TOP_ALIGNMENT);
        achievementLabel.setBorder(new EmptyBorder(5, 20, 10, 20));
        achievementLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        achievementLabel.setForeground(Color.decode("#EEEEEE"));
        achievementLabel.setPreferredSize(new Dimension(200,800));
        

        infoLabelContainer.add(timeCaptionLabel);
        infoLabelContainer.add(timeLabel);
        infoLabelContainer.add(scoreCaptionLabel);
        infoLabelContainer.add(scoreLabel);
        infoLabelContainer.add(achievementIconLabel);
        infoLabelContainer.add(achievementLabel);
        infoLabelContainer.add(hotstreakLabel);

        add(infoLabelContainer, BorderLayout.LINE_START);

        
        // Create list containing guessed words
        JLabel wordCaptionLabel = new JLabel("Words found");
        wordCaptionLabel.setAlignmentX(CENTER_ALIGNMENT);
        wordCaptionLabel.setBorder(new EmptyBorder(10, 20, 0, 20));
        wordCaptionLabel.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
        wordCaptionLabel.setForeground(Color.decode("#F4B701"));

        wordPlaceholder = new JLabel("<html>Mark words by holding and<br />dragging from the first letter.</html>", SwingConstants.CENTER);
        wordPlaceholder.setAlignmentX(CENTER_ALIGNMENT);
        wordPlaceholder.setBorder(new EmptyBorder(15, 5, 5, 5));
        wordPlaceholder.setFont(new Font("Arial", Font.PLAIN, 12));
        wordPlaceholder.setForeground(Color.decode("#444444"));

        wordListModel = new DefaultListModel<String>();

        wordList = new JList<String>(wordListModel);
        wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        wordList.setLayoutOrientation(JList.VERTICAL);
        wordList.setVisibleRowCount(-1);
        wordList.setFont(new Font("Arial", Font.BOLD, 16));
        wordList.setCellRenderer(new NoSelectListRenderer());
        wordList.setBackground(Color.decode("#111111"));
        wordList.setForeground(Color.decode("#F4B701"));

        JScrollPane wordListScroller = new JScrollPane(wordList);
        wordListScroller.setPreferredSize(new Dimension(200, 800));
        wordListScroller.setBorder(BorderFactory.createEmptyBorder());

        JPanel wordListContainer = new JPanel();
        wordListContainer.setLayout(new BoxLayout(wordListContainer, BoxLayout.PAGE_AXIS));
        wordListContainer.setBorder(new EmptyBorder(10, 20, 10, 10));
        wordListContainer.setBackground(Color.decode("#111111"));

        wordListContainer.add(wordListScroller);
        
        // Create container layout for wordlist
        JPanel wordLabelContainer = new JPanel();
        wordLabelContainer.setPreferredSize(new Dimension(200, 500));
        wordLabelContainer.setLayout(new BoxLayout(wordLabelContainer, BoxLayout.PAGE_AXIS));
        wordLabelContainer.setBackground(Color.decode("#111111"));
        
        wordLabelContainer.add(wordCaptionLabel);
        wordLabelContainer.add(wordPlaceholder);
        wordLabelContainer.add(wordListContainer);
        
        add(wordLabelContainer, BorderLayout.EAST);
        
        // Add grid with widthxheight dices
        GridLayout gridLayout = new GridLayout(gridHeight, gridWidth);
        gridLayout.setHgap(30);
        gridLayout.setVgap(30);

        JPanel diceGridContainer = new JPanel();
        diceGridContainer.setLayout(gridLayout);
        diceGridContainer.setBorder(new EmptyBorder(30, 30, 30, 30));
        diceGridContainer.setBackground(Color.decode("#0074CC"));
        
        // Create button for each dice
        for (int y = 0; y < gridWidth; y++) {
            for (int x = 0; x < gridHeight; x++) {
                final JButton button = diceButtons[x][y] = new JDragButton();
                button.setFont(new Font(button.getFont().getFontName(), Font.PLAIN, 40));
                button.setForeground(Color.WHITE);
                button.setBorder(BorderFactory.createLineBorder(Color.decode("#00487F")));
                button.setBackground(Color.decode("#00538E"));
                button.setFocusPainted(false);
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
                button.setBackground(Color.decode("#00253F"));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                draggingWord = false;

                // Reset colors of dices
                for (DiceCoord coord : diceDragged) {
                    diceButtons[coord.x][coord.y].setBackground(Color.decode("#00538E"));
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
                    button.setBackground(Color.decode("#00253F"));
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
        wordPlaceholder.setVisible(words.size() == 0);
        wordList.setVisible(words.size() > 0);

        wordListModel.clear();

        //start at end to put last word first in list
        for (int i = words.size()-1; i>=0; i--) {
            // Space for left padding improves layout
            wordListModel.addElement(words.get(i).substring(0, 1).toUpperCase() + words.get(i).substring(1));
        }
    }

    public void setOnWordListener(OnWordListener listener) {
        onWordListener = listener;
    }

    public interface OnWordListener {
        public void onWord(String word);
    }

	@Override
	public void showAchievement(ArrayList<Achievement> achievements) {
		for(Achievement a : achievements){
			achievementsLeft.add(a);
		}
		
		if(achievementTimer == null){
		    achievementTimer = new Timer();
		    achievementTimer.scheduleAtFixedRate(new AchievementTask(), 0, 5000);
		}
	}

	@Override
	public void startHotstreak() {
	    hotstreakTimer = new Timer();
	    hotstreakTimer.scheduleAtFixedRate(new HotstreakTask(), 0, 150);
	    hotstreakLabel.setText("HOTSTREAK");
	}

    @Override
    public void stopHotstreak() {
        hotstreakTimer.cancel();
        hotstreakTimer.purge();
        hotstreakLabel.setText("");
    }
    
    class AchievementTask extends TimerTask{
        public void run(){
            if(!achievementsLeft.isEmpty()){
                Achievement a = achievementsLeft.get(0);
                achievementIconLabel.setIcon(new ImageIcon(a.getImage().getScaledInstance(128, 128, Image.SCALE_SMOOTH)));
                achievementLabel.setText(a.getName());
                achievementLabel.setForeground(Color.decode("#0074CC"));
                achievementsLeft.remove(a);
            }
            else{
                achievementIconLabel.setIcon(null);
                achievementLabel.setText("No achievement");
                achievementLabel.setForeground(Color.decode("#444444"));
                achievementTimer.cancel();
                achievementTimer.purge();
                achievementTimer = null;
            }
        }
    }
    
    class HotstreakTask extends TimerTask{
        private boolean on;
        
        public void run() {
            if(on){
                hotstreakLabel.setForeground(Color.decode("#56A739"));
                on = false;
            }
            else{
                hotstreakLabel.setForeground(Color.decode("#D72828"));
                on = true;
            }
            hotstreakLabel.repaint(0);
        }
    }
}
