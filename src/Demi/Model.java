package Demi;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */

public class Model {
//TODO initialize;

    private ArrayList<State> states;
    private Settings set = new Settings();

    private ArrayList[] offers = new ArrayList[getn_agents()];
    //private ArrayList[] offers = new ArrayList[this.getn_agents()];

    private State currentState;


    private Agent[] agents;

    public Model(){

        //init agents
        this.agents = this.set.initializeAgents();

        //init states
        this.states = this.set.initializeStates();
        for (int i = 0; i < this.getn_agents(); i++) {
            offers[i] = new ArrayList<State>();
        }

        this.currentState = this.set.initializeFirstState();

        for (Agent agent : agents) {
            agent.belief = currentState;
        }

    }

    public ArrayList<State> getRecentOffers(int t){
        ArrayList<State> of = new ArrayList<State>();
        for (int i = 0; i < getn_agents(); i++) {
            if (!this.offers[i].isEmpty()) {
                //System.out.println("OOFFFFEERRR: "+i+offers[i].get(t-1).getClass());
                //State test = (State) offers[i].get(t-1);

                of.add((State)this.offers[i].get(t));
            }else{
                for (int j = 0; j < set.getnAgents(); j++) {
                    of.add(currentState);
                }
            }
        }
        return of;
    }
    public Agent[] getAgents() {
        return agents;
    }

    public State getCurrentState() {
        return currentState;
    }

    public int getn_agents(){
        return this.set.getnAgents();
    }

    public String getnameAgents(int i){
        return this.set.getName(i);
    }


    public void propose(Agent agent, State offer, int t){
        for (int i = 0; i < this.getn_agents(); i++) {
            offers[i].add(offer);
        }
    }
}
