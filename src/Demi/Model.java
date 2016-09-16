package Demi;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */

public class Model {
//TODO initialize;

    private ArrayList<ArrayList<State>> states;

    private State currentState;

    private Agent[] agents;

    public Model(){
        Settings set = new Settings();

        //init agents
        this.agents = set.initializeAgents();

        //init states
        this.states = set.initializeStates();
        this.currentState = set.initializeFirstState();
//        for (Agent agent : agents) {
//            String name = agent.getName();
//            System.out.println(name);
//        }

        System.out.println(currentState.toString());

        for (Agent agent : agents) {
            agent.belief = currentState;
        }

    }

//TODO use settings;
    public Model(Settings S){

    }

    public Agent[] getAgents() {
        return agents;
    }

    public State getCurrentState() {
        return currentState;
    }
}
