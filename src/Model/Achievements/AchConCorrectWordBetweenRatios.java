package Model.Achievements;

public class AchConCorrectWordBetweenRatios extends AchievementCondition {
    double lowerratio;
    double upperratio;
    
    public AchConCorrectWordBetweenRatios(Statistics statistics, double lowerratio, double upperratio){
        super(statistics);
        this.lowerratio = lowerratio;
        this.upperratio = upperratio;
    }

    @Override
    //Checks for correct word ratio in [lowerratio, upperratio)
    protected boolean calculate() {
        if(statistics.getAttempts() > 0){
            double ratio = ((double)statistics.getCorrectAttempts())/((double)statistics.getAttempts());
            return lowerratio <= ratio && upperratio > ratio;
        }
        
        return false;
    }
    
    
}
