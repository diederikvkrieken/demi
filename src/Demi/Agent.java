package Demi;

/**
 * Created by diederik.van.krieken on 12-9-2016.
 */
public class Agent {
    public Agent(){

    }

    protected String name;

    protected Preferences preferences;

    public Agent(String name) {
        this.name = name;
    }
    public Agent (String name, String[] pref){
        this.name = name;
        this.preferences = new Preferences();
        this.preferences.preferences = pref;
    }
    public class Preferences {
        String[] preferences;
        int counter;

    }
}
