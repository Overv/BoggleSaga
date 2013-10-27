package Model;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created with IntelliJ IDEA.
 * User: rjwvandenberg
 * Date: 27/10/13
 * Time: 13:21
 * To change this template use File | Settings | File Templates.
 */
public class Statistics {

    ArrayList<StatisticsEntry> data;

    public Statistics(){
        data = new ArrayList<StatisticsEntry>();
    }

    public void addEntry(StatisticsEntry entry){
        data.add(entry);
    }


    public void print(){
        String s = "\nStatistics:\n";

        for(StatisticsEntry entry : data){
            s += entry.toString() + "\n";
        }

        System.out.println(s);
    }


    //Achievements
    public ArrayList<Achievement> getAchievements(){
        ArrayList<Achievement> alist = new ArrayList<Achievement>();

        addAchievement(alist, getFalseWordsAchievement());
        addAchievement(alist, getLongestWordAchievement());

        return alist;
    }

    //Achievement util methods
    private void addAchievement(ArrayList<Achievement> alist, Achievement ach1) {
        if(ach1 != null){
            alist.add(ach1);
        }
    }

    private void addAchievement(ArrayList<Achievement> alist, ArrayList<Achievement> achs) {
        if(!achs.isEmpty()){
            alist.addAll(achs);
        }
    }

    public void printAchievements(){
        String s = "\nAchievements:\n";
        ArrayList<Achievement> achievements = getAchievements();

        for(Achievement a : achievements){
            s += a.toString() + "\n";
        }

        System.out.println(s);
    }


    //Individual achievement calculation
    private Achievement<Integer> getFalseWordsAchievement(){
        int falsewords = 0;

        for(StatisticsEntry entry : data){
            if(!entry.isWordCorrect()){
                falsewords++;
            }
        }

        if(falsewords>=10){
            return new Achievement<Integer>("Number of False Words", falsewords, "flavourtext");
        }

        return null;
    }

    private Achievement<String> getLongestWordAchievement(){
        int longest = 0;
        String word = "";

        for(StatisticsEntry entry: data){
            if(entry.isWordCorrect() && entry.getWord().length() > longest){
                word = entry.getWord();
                longest = word.length();
            }
        }

        if(longest > 2){
            return new Achievement<String>("Longest Word", word, "flavourtext");
        }

        return null;
    }
}
