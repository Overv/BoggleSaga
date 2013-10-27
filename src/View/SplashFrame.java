package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created with IntelliJ IDEA.
 * User: Overv
 * Date: 28-10-13
 * Time: 0:03
 * To change this template use File | Settings | File Templates.
 */
public class SplashFrame extends JFrame {
    public SplashFrame() {
        // Use native look and feel (if this fails, we have bigger problems)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        // Initialize window properties
        setSize(250, 280);
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

        JButton boggleButton = new JButton("Boggle");
        boggleButton.setAlignmentX(CENTER_ALIGNMENT);
        boggleButton.setBorder(new EmptyBorder(10, 90, 10, 90));
        boggleButton.setFont(new Font(boggleButton.getFont().getFontName(), Font.PLAIN, 14));
        boggleButtonContainer.add(boggleButton);

        viewContainer.add(boggleButtonContainer);

        // Big boggle button
        JPanel bigBoggleButtonContainer = new JPanel();
        bigBoggleButtonContainer.setLayout(new BoxLayout(bigBoggleButtonContainer, BoxLayout.PAGE_AXIS));
        bigBoggleButtonContainer.setBorder(new EmptyBorder(0, 0, 5, 0));

        JButton bigBoggleButton = new JButton("Big Boggle");
        bigBoggleButton.setAlignmentX(CENTER_ALIGNMENT);
        bigBoggleButton.setBorder(new EmptyBorder(10, 79, 10, 79));
        bigBoggleButton.setFont(new Font(bigBoggleButton.getFont().getFontName(), Font.PLAIN, 14));
        bigBoggleButtonContainer.add(bigBoggleButton);

        viewContainer.add(bigBoggleButtonContainer);

        // Settings button
        JPanel settingsButtonContainer = new JPanel();
        settingsButtonContainer.setLayout(new BoxLayout(settingsButtonContainer, BoxLayout.PAGE_AXIS));
        settingsButtonContainer.setBorder(new EmptyBorder(0, 0, 5, 0));

        JButton settingsButton = new JButton("Settings");
        settingsButton.setAlignmentX(CENTER_ALIGNMENT);
        settingsButton.setBorder(new EmptyBorder(10, 86, 10, 86));
        settingsButton.setFont(new Font(settingsButton.getFont().getFontName(), Font.PLAIN, 14));
        settingsButtonContainer.add(settingsButton);

        settingsButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new SettingsFrame();
            }

            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

        viewContainer.add(settingsButtonContainer);

        // Add controls to frame
        add(viewContainer);
    }
}
