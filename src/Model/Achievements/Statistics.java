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
public class Statistics{

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
}
