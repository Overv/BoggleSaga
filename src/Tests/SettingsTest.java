package Tests;

import Model.Settings;
import Model.Settings.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SettingsTest {
    //save before test and restore after test: src/Resources/settings.txt
    private static final File SETTINGSFILE = new File("src/Resources/settings.txt");
    private static String originalSettings;

    public static void main(String[] args) {
        //setup
        originalSettings = "";

        Scanner sc = null;
        try {
            sc = new Scanner(SETTINGSFILE);
            while(sc.hasNextLine()) {
                originalSettings += sc.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        System.out.println("temp file:\n" + originalSettings);
        SETTINGSFILE.delete();
        Settings.loadSettings();

        //tests
        System.out.println(Settings.getGameType() + " " + GameType.def());
        System.out.println(Settings.getGameType() == GameType.def());
        System.out.println(Settings.isMusicEnabled() == true);
        System.out.println(Settings.isSoundEnabled() == true);

        Settings.setMusic(MusicSetting.OFF);
        System.out.println(Settings.isMusicEnabled() == false);

        Settings.setSound(SoundSetting.OFF);
        System.out.println(Settings.isSoundEnabled() == false);

        Settings.setGametype(GameType.BIGBOGGLE);
        System.out.println(Settings.getGameType() == GameType.BIGBOGGLE);


        //return original settings
        SETTINGSFILE.delete();
        PrintWriter bo = null;

        try {
            bo = new PrintWriter(SETTINGSFILE);
            bo.write(originalSettings);
            bo.flush();
        } catch (FileNotFoundException e) {
            System.err.println("Cannot write settings to file.");
        } finally {
            if (bo != null) {
                bo.close();
            }
        }
    }
}
