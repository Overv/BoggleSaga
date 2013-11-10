package Model.Achievements;

import java.util.ArrayList;

import Model.Settings.GameType;
import Model.Time.TimeListener;

public class AchievementManager implements TimeListener {
	private Statistics statistics;
	private GameType gametype;
	private ArrayList<AchievementListener> achievementListeners;
	private ArrayList<Achievement> achievements;
	
	public AchievementManager(Statistics statistics, GameType type) {
		this.statistics = statistics;
		this.gametype = type;
		this.achievementListeners = new ArrayList<AchievementListener>();
		
		//Add achievements applicable to this gametype
		achievements = new ArrayList<Achievement>();
		
		//general achievements
		achievements.add(new Achievement("You started a game!", "src/Resources/Achievements/newgame.png"));
		//bigboggle achievements
		
		//boggle achievements
	}
	
	//Achievements
    public ArrayList<Achievement> getAchievements(){
        ArrayList<Achievement> alist = new ArrayList<Achievement>();

        for(Achievement a : achievements){
        	if(a.isAchieved())
        		alist.add(a);
        }
        
        return alist;
    }

    public void printAchievements(){
        String s = "\nAchievements:\n";
        ArrayList<Achievement> achievements = getAchievements();

        for(Achievement a : achievements){
            s += a.toString() + "\n";
        }

        System.out.println(s);
    }
    
    public void addAchievementListener(AchievementListener listener){
    	achievementListeners.add(listener);
    }

	@Override
	public void timesUp() {
		//Not applicable for achievements.
	}

	@Override
	public void onTimePassed(int timeLeft) {
		ArrayList<Achievement> newAchievements = new ArrayList<Achievement>();
		
		for(Achievement a : achievements){
			if(!a.isAchieved() && a.calculate()){
				newAchievements.add(a);
			}
		}
		
		for(AchievementListener listener : achievementListeners){
			listener.showAchievement(newAchievements);
			
			if(timeLeft == 55){
				listener.startHotstreak();
			}
		}
		
		
	}
}
