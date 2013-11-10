package Model.Achievements;

import java.util.ArrayList;

import Model.Time;
import Model.Time.TimeListener;

/**
 * Created with IntelliJ IDEA.
 * User: rjwvandenberg
 * Date: 27/10/13
 * Time: 13:21
 * To change this template use File | Settings | File Templates.
 */
public class Statistics implements TimeListener{

	private int timeLeft;
	
    private ArrayList<StatisticsEntry> data;
    private int falseAttempts;
    private int correctAttempts;
    

    public Statistics(){
    	timeLeft = 0;
    	
        data = new ArrayList<StatisticsEntry>();
        falseAttempts = 0;
        correctAttempts = 0;
    }

    public void addEntry(StatisticsEntry entry){
        data.add(entry);
        
        if(entry.isWordCorrect())
        	correctAttempts++;
        else
        	falseAttempts++;
    }


    public void print(){
        String s = "\nStatistics:\n";

        for(StatisticsEntry entry : data){
            s += entry.toString() + "\n";
        }

        System.out.println(s);
    }
    
    public int getAttempts(){
    	return falseAttempts + correctAttempts;
    }
    
    public int getFalseAttempts(){
    	return falseAttempts;
    }
    
    public int getCorrectAttempts(){
    	return correctAttempts;
    }
    
    public int getAttemptsLastXSeconds(int seconds){
        int upToTime = timeLeft - seconds;
        
        int attempts = 0;
        //as long as not at beginning or time is not beyond period
        for(int i = data.size()-1; i>=0 && data.get(i).getTime() >= upToTime ;i--){
            attempts++;
        }
        
        return attempts;
    }
    
    public int getFalseAttemptsLastXSeconds(int seconds){
        int upToTime = timeLeft - seconds;
        
        int attempts = 0;
        //as long as not at beginning or time is not beyond period
        for(int i = data.size()-1; i>=0 && data.get(i).getTime() >= upToTime ;i--){
            if(!data.get(i).isWordCorrect())
                attempts++;
        }
        
        return attempts;
    }
    
    public int getCorrectAttemptsLastXSeconds(int seconds){
        int upToTime = timeLeft - seconds;
        
        int attempts = 0;
        //as long as not at beginning or time is not beyond period
        for(int i = data.size()-1; i>=0 && data.get(i).getTime() >= upToTime ;i--){
            if(data.get(i).isWordCorrect())
                attempts++;
        }
        
        return attempts;
    }


	@Override
	public void timesUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTimePassed(int timeLeft) {
		this.timeLeft = timeLeft;
	}
}
