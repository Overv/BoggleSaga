package Model.Achievements;

public class AchConFalse extends AchievementCondition{
	public AchConFalse(Statistics statistics){
		super(statistics);
	}

	@Override
	protected boolean calculate() {
		return false;
	}
	
	
}
