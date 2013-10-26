package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
* The settings class uses the singleton pattern.
* loadSettings() is used to initialize the object with default settings path
*
*/
public class Settings {

    // Default settings path
    private static final String SETTINGSPATH = "src/Resources/settings.txt";

    // Settings singleton variable
    private static Settings settings;

    // Settings object attributes initialized with default values if not set in constructor
    private SoundSetting soundSetting = SoundSetting.def();
    private MusicSetting musicSetting = MusicSetting.def();
    private GameType gameType = GameType.def();

    /*
    * Private settings constructor (Singleton)
    *
    * Loads settingsfile if available else initializes with default settings.
    */
    private Settings(String settingsPath) {
        File settingsFile = new File(settingsPath);
        Scanner sc = null;
        try {
            sc = new Scanner(settingsFile);

            while (sc.hasNextLine()) {
                Scanner linesc = new Scanner(sc.nextLine());
                String s = "";
                if(linesc.hasNext())
                    s = linesc.next();

                if (s.equals("sound")) {
                    soundSetting = SoundSetting.read(linesc);
                } else if (s.equals("music")) {
                    musicSetting = MusicSetting.read(linesc);
                } else if (s.equals("gametype")) {
                    gameType = GameType.read(linesc);
                }
            }
        } catch (FileNotFoundException e) {
            // File not found, so create default settings.
            soundSetting = SoundSetting.def();
            musicSetting = MusicSetting.def();
            gameType = GameType.def();
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    //
    public static void loadSettings() {
        if (settings == null){
            settings = new Settings(SETTINGSPATH);
            saveSettings();
        }
    }

    public static void saveSettings() {
        if (settings != null) {
            String settingsStr = "";
            settingsStr += settings.soundSetting.write() + "\n";
            settingsStr += settings.musicSetting.write() + "\n";
            settingsStr += settings.gameType.write() + "\n";


            File f = new File(SETTINGSPATH);
            PrintWriter bo = null;

            try {
                bo = new PrintWriter(f);
                bo.write(settingsStr);
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

    public static boolean isSoundEnabled() {
        return (settings.soundSetting == null) ? (SoundSetting.def() == SoundSetting.ON )
                : (settings.soundSetting == SoundSetting.ON);
    }

    public static boolean isMusicEnabled() {
        return (settings.musicSetting == null) ? (MusicSetting.def() == MusicSetting.ON)
                : (settings.musicSetting == MusicSetting.ON);
    }

    public static GameType getGameType() {
        return (settings.gameType == null) ? GameType.def() : settings.gameType;
    }

    public static SoundSetting getSoundSetting() {
        return (settings.soundSetting == null) ? SoundSetting.def() : settings.soundSetting;
    }

    public static MusicSetting getMusicSetting() {
        return (settings.musicSetting == null) ? MusicSetting.def() : settings.musicSetting ;
    }

    public static void setMusic(MusicSetting s){
        settings.musicSetting = s;
        saveSettings();
    }

    public static void setSound(SoundSetting s){
        settings.soundSetting = s;
        saveSettings();
    }

    public static void setGametype(GameType t){
        settings.gameType = t;
        saveSettings();
    }
    // Sound setting enum for reading, writing soundsetting
    public static enum SoundSetting {
        OFF, ON;

        public static SoundSetting def() {
            return SoundSetting.ON;
        }

        private static SoundSetting read(Scanner sc) {
            String s = sc.next();
            if (s.equals("OFF")) {
                return SoundSetting.OFF;
            } else {
                //ON or something else
                return SoundSetting.ON;
            }
        }

        private String write() {
            return "sound " + this;
        }
    }

    // music setting for reading writing music setting
    public static enum MusicSetting {
        OFF, ON;

        public static MusicSetting def() {
            return MusicSetting.ON;
        }

        private static MusicSetting read(Scanner sc) {
            String s = sc.next();
            if (s.equals("OFF")) {
                return MusicSetting.OFF;
            } else {
                //ON or something else
                return MusicSetting.ON;
            }
        }

        private String write() {
            return "music " + this;
        }
    }

    // Gametype setting for reading and writing gametype
    public static enum GameType {
        BIGBOGGLE, BOGGLE;

        public static GameType def() {
            return GameType.BOGGLE;
        }

        private static GameType read(Scanner sc) {
            String s = sc.next();
            if (s.equals("BIGBOGGLE")) {
                return GameType.BIGBOGGLE;
            } else {
                return GameType.BOGGLE;
            }
        }

        private String write() {
            return "gametype " + this;
        }
    }
}
