package Model;

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
    int time;
    String word;
    boolean wordCorrect;
    GameType gametype;

    public StatisticsEntry(int time, String word, boolean wordCorrect, GameType gametype){
        this.time = time;
        this.word = word;
        this.wordCorrect = wordCorrect;
        this.gametype = gametype;
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
}
