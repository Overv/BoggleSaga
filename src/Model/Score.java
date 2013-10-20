package Model;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/18/13
 * Time: 10:10 PM
 */
public class Score {
    
    private int currentScore;

    public Score() {
        currentScore = 0;
    }
    
    public int calculateScore(ArrayList<String> foundWords) {
        for(String word : foundWords) {
            switch(word.length()) {
                case 0 : System.out.println("Error, word length is 0");
                    break;
                case 4 : currentScore += 1;
                    break;
                case 5 : currentScore += 2;
                    break;
                case 6 : currentScore += 3;
                    break;
                case 7 : currentScore += 5;
                    break;
                default: currentScore += 11; // word is 8 or longer
            }
        }
        return currentScore;
    }
    
    public int getCurrentScore() {
        return this.currentScore;
    }
    
}
