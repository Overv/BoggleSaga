package Model.Achievements;

import java.util.ArrayList;

import Model.Settings.GameType;
import Model.Time.TimeListener;

public class AchievementManager implements TimeListener {
    private Statistics statistics;
	private GameType gametype;
	private ArrayList<AchievementListener> achievementListeners;
	private ArrayList<Achievement> achievements;
	
	private int starttimeHotstreak;
	private boolean inHotstreak;
	private static final int HOTSTREAKLENGTH = 5;
	private static final int HOTSTREAKCONDITIONLENGTH = 5;
	private static final int HOTSTREAKCONDITIONWORDS = 2;
	
	public AchievementManager(Statistics statistics, GameType type) {
	    
	    
		this.statistics = statistics;
		this.gametype = type;
		this.achievementListeners = new ArrayList<AchievementListener>();
		this.starttimeHotstreak = -1;
		this.inHotstreak = false;
		
		//Add achievements applicable to this gametype
		achievements = new ArrayList<Achievement>();
		
		//general achievements		
		achievements.add(new Achievement("Made 5 attempts.", false, "src/Resources/Achievements/5.png", new AchConXWordsInRange(statistics, 5, 6)));
		achievements.add(new Achievement("Made 10 attempts.", false, "src/Resources/Achievements/10.png", new AchConXWordsInRange(statistics, 10, 11)));
		achievements.add(new Achievement("Made 15 attempts.", false, "src/Resources/Achievements/15.png", new AchConXWordsInRange(statistics, 15, 16)));
		achievements.add(new Achievement("First word!", false, "src/Resources/Achievements/first.png", new AchConXWordsInRange(statistics, 1, 2)));
		//general endgame achievements
		achievements.add(new Achievement("Try harder!", true, "src/Resources/Achievements/0star.png", new AchConXWordsInRange(statistics, 0, 1)));
		achievements.add(new Achievement("Horrible player!", true, "src/Resources/Achievements/1star.png", new AchConCorrectWordBetweenRatios(statistics, 0.0, 0.5)));
		achievements.add(new Achievement("Mediocre player!", true, "src/Resources/Achievements/2star.png", new AchConCorrectWordBetweenRatios(statistics, 0.5, 0.7)));
		achievements.add(new Achievement("Good player!", true, "src/Resources/Achievements/3star.png", new AchConCorrectWordBetweenRatios(statistics, 0.7, 0.85)));
		achievements.add(new Achievement("Excellent player!", true, "src/Resources/Achievements/4star.png", new AchConCorrectWordBetweenRatios(statistics, 0.85, 1)));
		achievements.add(new Achievement("Perfect player!", true, "src/Resources/Achievements/5star.png", new AchConCorrectWordBetweenRatios(statistics, 1, 1.1)));
		
		
		//bigboggle achievements
		if(this.gametype == GameType.BIGBOGGLE){
		    achievements.add(new Achievement("Made 20 attempts.", false, "src/Resources/Achievements/20.png", new AchConXWordsInRange(statistics, 20, 21)));
		}
		//boggle achievements
		if(this.gametype == GameType.BOGGLE){
			
		}
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
    
    //Set hotstreak condition here
    private boolean hotstreakConditionMet(){
    	return statistics.getCorrectUniqueAttemptsLastXSeconds(HOTSTREAKCONDITIONLENGTH) >= HOTSTREAKCONDITIONWORDS;
    }

	@Override
	//AchievementManager gets added to timelistener in constructor of game
	//So it processes the events first
	public void timesUp() {
        //update all achievements
        for(Achievement a : achievements){
            if(!a.isAchieved()){
                a.calculate();
            }
        }
        
        //stop hotstreak
        for(AchievementListener listener : achievementListeners){
            listener.stopHotstreak();
        }
	}

	@Override
	public void onTimePassed(int timeLeft) {
		
		ArrayList<Achievement> newAchievements = new ArrayList<Achievement>();
		
		//Update all non endgame achievements
		for(Achievement a : achievements){
			if(!a.isAchieved() && !a.isEndgameCondition() && a.calculate()){
				newAchievements.add(a);
			}
		}
		
		//start hotstreak conditions
		boolean enableHotstreak = hotstreakConditionMet();
		if(enableHotstreak && !inHotstreak){
		    starttimeHotstreak = timeLeft;
		}
		
		//stop hotstreak conditions, at least HOTSTREAKLENGTH seconds have passed since start of hotstreak
		boolean stopHotstreak = starttimeHotstreak - timeLeft > HOTSTREAKLENGTH && !enableHotstreak;
		
		
		
		//call listeners
		for(AchievementListener listener : achievementListeners){
			listener.showAchievement(newAchievements);
			
			if(enableHotstreak && !inHotstreak){
	            listener.startHotstreak();
	        }
	        else if(stopHotstreak && inHotstreak){
	            listener.stopHotstreak();
	        }
		}
		
		if(enableHotstreak && !inHotstreak){
            inHotstreak = true;
        }
        else if(stopHotstreak && inHotstreak){
            inHotstreak = false;
        }
		
	}
}
