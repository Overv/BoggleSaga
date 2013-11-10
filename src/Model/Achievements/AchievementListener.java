package Model.Achievements;

import java.util.ArrayList;

public interface AchievementListener {
	public void showAchievement(ArrayList<Achievement> achievements);
	public void startHotstreak();
}
