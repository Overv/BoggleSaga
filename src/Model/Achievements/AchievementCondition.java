package Model.Achievements;

public abstract class AchievementCondition {
	protected Statistics statistics;
	
	public AchievementCondition(Statistics statistics){
		this.statistics = statistics;
	}
	
	protected abstract boolean calculate(); 
}
