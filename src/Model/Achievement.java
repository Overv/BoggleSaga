package Model;

/**
 * Created with IntelliJ IDEA.
 * User: rjwvandenberg
 * Date: 27/10/13
 * Time: 14:09
 * To change this template use File | Settings | File Templates.
 */
public class Achievement<T> {
    private String flavourtext;
    private String name;
    private T realValue;


    public Achievement(String name, T realValue, String flavourtext){
        this.name = name;
        this.realValue = realValue;
        this.flavourtext = flavourtext;
    }

    public String getName(){
        return name;
    }

    public String getFlavourText(){
        return flavourtext;
    }

    public String getValue(){
        return realValue.toString();
    }

    public T getRealValue(){
        return realValue;
    }

    public String toString(){
        return "Achievement - n: " + name + "  v: " + realValue + "  f: " + flavourtext;
    }
}
