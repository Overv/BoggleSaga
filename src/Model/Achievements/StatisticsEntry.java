package Model.Achievements;

import Model.Settings;
import Model.Settings.*;
/**
 * Created with IntelliJ IDEA.
 * User: rjwvandenberg
 * Date: 27/10/13
 * Time: 13:21
 * To change this template use File | Settings | File Templates.
 */
/*
    StatisticsEntry is a class to provide convenient acces to a single
    statistics capture in a game.
 */
public class StatisticsEntry {
    // time is currently recorded as the time interval it occured in. So if it occured after
    // 10,5 seconds from start of game. and gamelength is 60 seconds, then time will have the value
    // 60 - 10 = 50
    private int time;
    private String word;
    private boolean wordCorrect;
    private GameType gametype;
    private boolean isNew;

    public StatisticsEntry(int time, String word, boolean wordCorrect, GameType gametype, boolean isNew){
        this.time = time;
        this.word = word;
        this.wordCorrect = wordCorrect;
        this.gametype = gametype;
        this.isNew = isNew;
    }

    public int getTime(){
        return time;
    }

    public String getWord(){
        return word;
    }

    public boolean isWordCorrect(){
        return wordCorrect;
    }

    public GameType getGameType(){
        return gametype;
    }

    public String toString(){
        return "StatisticsEntry - t: " + time + "  w: " + word + "  c: " + wordCorrect + "  g: " + gametype;
    }
    
    public boolean isNew(){
        return isNew;
    }
}
