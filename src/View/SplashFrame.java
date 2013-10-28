package View;

import Model.Settings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created with IntelliJ IDEA.
 * User: Overv
 * Date: 28-10-13
 * Time: 0:03
 * To change this template use File | Settings | File Templates.
 */
public class SplashFrame extends JFrame implements ActionListener {
    private JCheckBox musicCheckbox, soundCheckbox;
    private JButton boggleButton, bigBoggleButton;

    private OnStartGameListener listener;

    public SplashFrame() {
        // Use native look and feel (if this fails, we have bigger problems)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        // Initialize window properties
        setSize(250, 325);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Boggle Saga Inc.");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create layout
        createLayout();

        // Show window
        setVisible(true);
    }

    public void setOnStartGameListener(OnStartGameListener listener) {
        this.listener = listener;
    }

    private void createLayout() {
        // Show views vertically
        JPanel viewContainer = new JPanel();
        viewContainer.setLayout(new BoxLayout(viewContainer, BoxLayout.PAGE_AXIS));

        // Title label
        JLabel titleLabel = new JLabel("Boggle Saga Inc.");
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
        titleLabel.setFont(new Font(titleLabel.getFont().getFontName(), Font.BOLD, 25));
        viewContainer.add(titleLabel);

        // Credits label
        JLabel creditsLabel = new JLabel("<html>Alexander Overvoorde<br>Robert Carosi<br>Rene van den Berg</html>", SwingConstants.CENTER);
        creditsLabel.setAlignmentX(CENTER_ALIGNMENT);
        creditsLabel.setBorder(new EmptyBorder(10, 0, 30, 0));
        creditsLabel.setFont(new Font(creditsLabel.getFont().getFontName(), Font.PLAIN, 10));
        viewContainer.add(creditsLabel);

        // Normal boggle button
        JPanel boggleButtonContainer = new JPanel();
        boggleButtonContainer.setLayout(new BoxLayout(boggleButtonContainer, BoxLayout.PAGE_AXIS));
        boggleButtonContainer.setBorder(new EmptyBorder(0, 0, 5, 0));

        boggleButton = new JButton("Boggle");
        boggleButton.addActionListener(this);
        boggleButton.setAlignmentX(CENTER_ALIGNMENT);
        boggleButton.setBorder(new EmptyBorder(10, 90, 10, 90));
        boggleButton.setFont(new Font(boggleButton.getFont().getFontName(), Font.PLAIN, 14));
        boggleButtonContainer.add(boggleButton);

        viewContainer.add(boggleButtonContainer);

        // Big boggle button
        JPanel bigBoggleButtonContainer = new JPanel();
        bigBoggleButtonContainer.setLayout(new BoxLayout(bigBoggleButtonContainer, BoxLayout.PAGE_AXIS));
        bigBoggleButtonContainer.setBorder(new EmptyBorder(0, 0, 5, 0));

        bigBoggleButton = new JButton("Big Boggle");
        bigBoggleButton.addActionListener(this);
        bigBoggleButton.setAlignmentX(CENTER_ALIGNMENT);
        bigBoggleButton.setBorder(new EmptyBorder(10, 79, 10, 79));
        bigBoggleButton.setFont(new Font(bigBoggleButton.getFont().getFontName(), Font.PLAIN, 14));
        bigBoggleButtonContainer.add(bigBoggleButton);

        viewContainer.add(bigBoggleButtonContainer);

        // Music setting checkbox
        JPanel musicCheckboxContainer = new JPanel();
        musicCheckboxContainer.setLayout(new BoxLayout(musicCheckboxContainer, BoxLayout.PAGE_AXIS));
        musicCheckboxContainer.setBorder(new EmptyBorder(30, 0, 0, 0));

        musicCheckbox = new JCheckBox("Music");
        musicCheckbox.setSelected(Settings.isMusicEnabled());
        musicCheckbox.addActionListener(this);
        musicCheckbox.setAlignmentX(CENTER_ALIGNMENT);
        musicCheckbox.setFont(new Font(musicCheckbox.getFont().getFontName(), Font.BOLD, 14));
        musicCheckboxContainer.add(musicCheckbox);

        viewContainer.add(musicCheckboxContainer);

        // Sound setting checkbox
        JPanel soundCheckboxContainer = new JPanel();
        soundCheckboxContainer.setLayout(new BoxLayout(soundCheckboxContainer, BoxLayout.PAGE_AXIS));
        soundCheckboxContainer.setBorder(new EmptyBorder(0, 0, 0, 0));

        soundCheckbox = new JCheckBox("Sound");
        soundCheckbox.setSelected(Settings.isSoundEnabled());
        soundCheckbox.addActionListener(this);
        soundCheckbox.setAlignmentX(CENTER_ALIGNMENT);
        soundCheckbox.setFont(new Font(soundCheckbox.getFont().getFontName(), Font.BOLD, 14));
        soundCheckboxContainer.add(soundCheckbox);

        viewContainer.add(soundCheckboxContainer);

        // Add controls to frame
        add(viewContainer);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == musicCheckbox){
            Settings.setMusic(Settings.isMusicEnabled() ? Settings.MusicSetting.OFF : Settings.MusicSetting.ON);
        } else if (source == soundCheckbox){
            Settings.setSound(Settings.isSoundEnabled() ? Settings.SoundSetting.OFF : Settings.SoundSetting.ON);
        } else if (source == boggleButton || source == bigBoggleButton) {
            Settings.setGametype(source == boggleButton ? Settings.GameType.BOGGLE : Settings.GameType.BIGBOGGLE);

            setVisible(false);
            dispose();

            listener.onStart();
        }
    }

    public interface OnStartGameListener {
        public void onStart();
    }
}
