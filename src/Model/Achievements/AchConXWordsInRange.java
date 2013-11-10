package Model.Achievements;

public class AchConXWordsInRange extends AchievementCondition{
    int lowerrange;
    int upperrange;
    
    public AchConXWordsInRange(Statistics statistics, int lowerrange, int upperrange){
        super(statistics);
        this.lowerrange = lowerrange;
        this.upperrange = upperrange;
    }

    @Override
    //checks for attempts in [lowerrange, upperrange)
    protected boolean calculate() {
        int attempts = statistics.getAttempts();
        return attempts >= lowerrange && attempts < upperrange;
    }
    
}
