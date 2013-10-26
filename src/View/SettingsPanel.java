package View;

import Model.Settings;
import Model.Settings.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsPanel extends JPanel implements ActionListener {
    //buttons for setting options

    private JButton soundButton;
    private JButton musicButton;
    private JButton gametypeButton;

    //button to return to previous menu/close menu
    private JButton close;


    public SettingsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        JLabel setting = new JLabel("Settings");

        JLabel soundLabel = new JLabel("Sound:");
        soundButton = new JButton(Settings.isSoundEnabled() ? "ON" : "OFF");
        JPanel soundPanel = new JPanel();
        soundPanel.add(soundLabel);
        soundPanel.add(soundButton);

        JLabel musicLabel = new JLabel("Music:");
        musicButton = new JButton(Settings.isMusicEnabled() ? "ON" : "OFF");
        JPanel musicPanel = new JPanel();
        musicPanel.add(musicLabel);
        musicPanel.add(musicButton);

        JLabel gametypeLabel = new JLabel("Gametype:");
        gametypeButton = new JButton(Settings.getGameType().toString());
        JPanel gametypePanel = new JPanel();
        gametypePanel.add(gametypeLabel);
        gametypePanel.add(gametypeButton);

        close = new JButton("close");

        //add actionlistener to the buttons
        musicButton.addActionListener(this);
        soundButton.addActionListener(this);
        gametypeButton.addActionListener(this);
        close.addActionListener(this);

        this.add(setting);
        this.add(soundPanel);
        this.add(musicPanel);
        this.add(gametypePanel);
        this.add(close);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == musicButton){
            Settings.setMusic(Settings.isMusicEnabled() ? MusicSetting.OFF : MusicSetting.ON);
            musicButton.setText(Settings.getMusicSetting().toString());
        }
        else if(source == soundButton){
            Settings.setSound(Settings.isSoundEnabled() ? SoundSetting.OFF : SoundSetting.ON);
            soundButton.setText(Settings.getSoundSetting().toString());
        }
        else if(source == gametypeButton){
            Settings.setGametype(Settings.getGameType() == GameType.BOGGLE ? GameType.BIGBOGGLE : GameType.BOGGLE);
            gametypeButton.setText(Settings.getGameType().toString());
        }
        else if(source == close){
            //do something
        }
    }
}
