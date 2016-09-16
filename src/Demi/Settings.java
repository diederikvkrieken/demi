package Demi;

import java.util.ArrayList;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */
public class Settings {

    private String[] names = {"Anion", "Cation", "Mixbed", "Neut"};
    private int[] firstStateValues = {100, 120, 150};

    public Agent[] initializeAgents(){
        //initialize 4 agents
        Agent[] agents = new Agent[4];

        for (int i = 0; i < 4; i++) {
            agents[i] = new Agent(names[i]);
        }
        return agents;
    }

    public ArrayList<ArrayList<State>> initializeStates(){
        ArrayList<ArrayList<State>> states = new ArrayList<ArrayList<State>>(); // empty state list
        return states;
    }

    public State initializeFirstState(){
        State state = new State(firstStateValues[0],firstStateValues[1], firstStateValues[2]);
        return state;
    }
    public Settings(){

    }

}
