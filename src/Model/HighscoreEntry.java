package Model;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/22/13
 * Time: 12:52 AM
 */
public class HighscoreEntry {
    
    private int score;
    private String name;

    public HighscoreEntry(String name, int score) {
        this.score = score;
        this.name = name;
    }
    
    public Integer getScore() {
        return this.score;
    }

    public String getName() {
        return name;
    }
}
