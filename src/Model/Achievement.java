package Model;

/**
 * Created with IntelliJ IDEA.
 * User: roberto
 * Date: 10/24/13
 * Time: 9:44 PM
 */
public abstract class Achievement {
    public boolean achieved; // is true when a player achieves this achievement
    public int id; // distinguishes the achievements
    public String description; // short description of what is achieved
    public int points; // points received for this achievement
    
    public abstract int getAchievementId(); // get the id variable
    public abstract int getAchievementPoints(); // get the points variable
    public abstract String getAchievementDescription(); // get the description variable
    public abstract void checkAchieved(); // check if the achievement is achieved
    public abstract boolean getAchieved(); // get the achieved variable
    
}
