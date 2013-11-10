package Model.Achievements;

public class AchConTrue extends AchievementCondition {
	public AchConTrue(Statistics statistics){
		super(statistics);
	}
	
	@Override
	protected boolean calculate() {
		return true;
	}
	
}
