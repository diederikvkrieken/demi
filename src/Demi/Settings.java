package Demi;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Created by diederik.van.krieken on 13-9-2016.
 */
public class Settings {

    private int nAgents = 4;
    // The agents: each agent must have an unique name
    // Name is checked to see if it is the agent to check
    private String[] names = {"Anion", "Cation", "Mixbed", "Neut"};
    //What value does the first state have?
    private double[] firstStateValues = {0.100, 0.120, 0.150};

    public Agent[] initializeAgents(){
        //initialize nAgents agents
        Agent[] agents = new Agent[nAgents];

        for (int i = 0; i < nAgents; i++) {
            agents[i] = new Agent(names[i]);
        }
        return agents;
    }

    public ArrayList<State> initializeStates(){
        ArrayList<State> states = new ArrayList<State>(); // empty state list
        return states;
    }

    public ArrayList<ArrayList<State>> initializeOffers(){
        ArrayList<ArrayList<State>> offers = new ArrayList<ArrayList<State>>(); // empty state list
        return offers;
    }

    public State initializeFirstState(){
        State state = new State(firstStateValues[0],firstStateValues[1], firstStateValues[2]);
        return state;
    }
    public Settings(){

    }

    //GETTERS & SETTERS
    public String[] getNames() {
        return names;
    }

    public String getName(int i){
        return names[i];
    }

    public int getnAgents() {
        return nAgents;
    }

    public int name2number(String name){
        int number = Arrays.asList(names).indexOf(name);
        return number;
    }

    public String number2name(int i){
        return names[i];
    }
}
