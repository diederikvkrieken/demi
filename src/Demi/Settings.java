package Demi;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.reflect.*;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */
public class Settings {

    private int nAgents = 4;
    // The agents: each agent must have an unique name
    // Name is checked to see if it is the agent to check
    private String[] names = {"Anion", "Cation", "Mixbed", "Neut"};

    // Order is acid base water
    private State[] startStates = new State[nAgents];

    //Reseration curve values.
    private double[] reservation = {0.05, 0.10, 0.15,0.20,0.25,0.3,0.35, 0.4,0.45,0.5,0.55,0.6, 0.65};

    //Set the states for which the agents have the highest utility.
    public Settings(){
        //Anion
        startStates[0] = new State(0,1,0);
        //Cation
        startStates[1] = new State(1,0,0);
        //Mixbed
        startStates[2] = new State(1,1,1);
        //Neut
        startStates[3] = new State(0,0,0);
    }

    public Agent[] initializeAgents(){
        //initialize nAgents agents
        Agent[] agents = new Agent[nAgents];
        agents[0] = new Anion(names[0]);
        agents[1] = new Cation(names[1]);
        agents[2] = new Mixbed(names[2]);
        agents[3] = new Neut(names[3]);

        return agents;
    }

    public Agent[] initializeAgents(int round){
        //initialize nAgents agents
        Agent[] agents = new Agent[nAgents];
        agents[0] = new Anion(names[0]);
        agents[1] = new Cation(names[1]);
        agents[2] = new Mixbed(names[2]);
        agents[3] = new Neut(names[3]);

        for (int i = 0; i < nAgents; i++) {
            agents[i].setMinimumUtility(reservation[round-1]);
        }
        return agents;
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

    public State[] getStartStates() {
        return startStates;
    }

    public void setStartStates(State[] startStates) {
        this.startStates = startStates;
    }


}
