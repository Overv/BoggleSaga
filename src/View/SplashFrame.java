package View;

import Model.Settings;
import com.sun.corba.se.impl.orb.ORBVersionImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashFrame extends JFrame implements ActionListener {
    private JCheckBox musicCheckbox, soundCheckbox;
    private JButton boggleButton, bigBoggleButton;

    private OnStartGameListener listener;

    public SplashFrame() {
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

    public void setOnStartGameListener(OnStartGameListener listener) {
        this.listener = listener;
    }

    private void createLayout() {
        // Show views vertically
        JPanel viewContainer = new JPanel();
        viewContainer.setPreferredSize(new Dimension(244, 345));
        viewContainer.setBackground(Color.decode("#1a1a1a"));
        viewContainer.setLayout(new BoxLayout(viewContainer, BoxLayout.PAGE_AXIS));

        // Title label
        JLabel titleLabel = new JLabel("Boggle Saga Inc.");
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.decode("#33B5E5"));
        viewContainer.add(titleLabel);

        // Credits label
        JLabel creditsLabel = new JLabel("<html>Alexander Overvoorde<br>Robert Carosi<br>Rene van den Berg</html>", SwingConstants.CENTER);
        creditsLabel.setAlignmentX(CENTER_ALIGNMENT);
        creditsLabel.setBorder(new EmptyBorder(10, 0, 50, 0));
        creditsLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        creditsLabel.setForeground(Color.decode("#444444"));
        viewContainer.add(creditsLabel);

        // Normal boggle button
        JPanel boggleButtonContainer = new JPanel();
        boggleButtonContainer.setLayout(new BoxLayout(boggleButtonContainer, BoxLayout.PAGE_AXIS));
        boggleButtonContainer.setBorder(new EmptyBorder(0, 0, 5, 0));
        boggleButtonContainer.setBackground(Color.decode("#1a1a1a"));

        boggleButton = new JDragButton("Boggle");
        boggleButton.addActionListener(this);
        boggleButton.setAlignmentX(CENTER_ALIGNMENT);
        boggleButton.setBorder(new EmptyBorder(10, 90, 10, 90));
        boggleButton.setFont(new Font(boggleButton.getFont().getFontName(), Font.PLAIN, 14));
        boggleButton.setForeground(Color.WHITE);
        boggleButton.setBackground(Color.decode("#56A739"));
        boggleButtonContainer.add(boggleButton);

        viewContainer.add(boggleButtonContainer);

        // Big boggle button
        JPanel bigBoggleButtonContainer = new JPanel();
        bigBoggleButtonContainer.setLayout(new BoxLayout(bigBoggleButtonContainer, BoxLayout.PAGE_AXIS));
        bigBoggleButtonContainer.setBorder(new EmptyBorder(0, 0, 5, 0));
        bigBoggleButtonContainer.setBackground(Color.decode("#1a1a1a"));

        bigBoggleButton = new JDragButton("Big Boggle");
        bigBoggleButton.addActionListener(this);
        bigBoggleButton.setAlignmentX(CENTER_ALIGNMENT);
        bigBoggleButton.setBorder(new EmptyBorder(10, 77, 10, 76));
        bigBoggleButton.setFont(new Font(bigBoggleButton.getFont().getFontName(), Font.PLAIN, 14));
        bigBoggleButton.setForeground(Color.WHITE);
        bigBoggleButton.setBackground(Color.decode("#D72828"));
        bigBoggleButtonContainer.add(bigBoggleButton);

        viewContainer.add(bigBoggleButtonContainer);

        // Music setting checkbox
        JPanel musicCheckboxContainer = new JPanel();
        musicCheckboxContainer.setLayout(new BoxLayout(musicCheckboxContainer, BoxLayout.PAGE_AXIS));
        musicCheckboxContainer.setBorder(new EmptyBorder(50, 0, 0, 0));
        musicCheckboxContainer.setBackground(Color.decode("#1a1a1a"));

        musicCheckbox = new JCheckBox("Music");
        musicCheckbox.setSelected(Settings.isMusicEnabled());
        musicCheckbox.addActionListener(this);
        musicCheckbox.setAlignmentX(CENTER_ALIGNMENT);
        musicCheckbox.setFont(new Font(musicCheckbox.getFont().getFontName(), Font.BOLD, 14));
        musicCheckbox.setBackground(Color.decode("#1a1a1a"));
        musicCheckbox.setForeground(Color.WHITE);
        musicCheckbox.setBorder(new EmptyBorder(10, 80, 5, 80));
        musicCheckbox.setFocusPainted(false);
        musicCheckboxContainer.add(musicCheckbox);

        viewContainer.add(musicCheckboxContainer);

        // Sound setting checkbox
        JPanel soundCheckboxContainer = new JPanel();
        soundCheckboxContainer.setLayout(new BoxLayout(soundCheckboxContainer, BoxLayout.PAGE_AXIS));
        soundCheckboxContainer.setBorder(new EmptyBorder(0, 0, 0, 0));
        soundCheckboxContainer.setBackground(Color.decode("#1a1a1a"));

        soundCheckbox = new JCheckBox("Sound");
        soundCheckbox.setSelected(Settings.isSoundEnabled());
        soundCheckbox.addActionListener(this);
        soundCheckbox.setAlignmentX(CENTER_ALIGNMENT);
        soundCheckbox.setFont(new Font(soundCheckbox.getFont().getFontName(), Font.BOLD, 14));
        soundCheckbox.setBackground(Color.decode("#1a1a1a"));
        soundCheckbox.setForeground(Color.WHITE);
        soundCheckbox.setBorder(new EmptyBorder(5, 80, 10, 80));
        soundCheckbox.setFocusPainted(false);
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
