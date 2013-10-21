package Model;

import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/21/13
 * Time: 10:19 PM
 */
public class Highscore {

    private String pathToHighscoreFile = "src/Resources/highscores.txt";
    private ArrayList<HighscoreEntry> highscores;
    
    public Highscore() {
        highscores = new ArrayList<HighscoreEntry>();
        fillHighscoreList();
    }
    
    public void fillHighscoreList() {
        File f = new File(pathToHighscoreFile);
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(f));
            while ((sCurrentLine = br.readLine()) != null) {
                String[] nameAndScore = sCurrentLine.split("__");
                try {
                    this.addHighscore(nameAndScore[0], Integer.parseInt(nameAndScore[1])); // Add word to the highscore ArrayList
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("File format is corrupted.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Filled highscore list from: " + pathToHighscoreFile);
    }
    
    public void addHighscore(String name, int score) {
        highscores.add(new HighscoreEntry(name, score));
        this.saveHighscores();
        sortHighscores();
    }
    
    public void saveHighscores() {
        File f = new File(pathToHighscoreFile);
        try {
            FileWriter fw = new FileWriter(f.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.toString());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Could not write to file " + pathToHighscoreFile);
        } 
    }
    
    public String toString() {
        String res = "";
        for(HighscoreEntry entry : this.highscores) {
            res += entry.getName() + "__" + entry.getScore() + "\n";
        }
        return res;
    } 
    
    public void sortHighscores() {
        Collections.sort(this.highscores, new ascendingComparator());
    }

    public class ascendingComparator implements Comparator<HighscoreEntry> {
        @Override
        public int compare(HighscoreEntry o1, HighscoreEntry o2) {
            return o2.getScore().compareTo(o1.getScore());
        }
    }
}
