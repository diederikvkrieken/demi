package Demi;

import com.opencsv.CSVWriter;
import com.sun.xml.internal.bind.v2.TODO;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */

public class Model {


    public Settings getSet() {
        return set;
    }

    //private ArrayList<State> states;
    private Settings set = new Settings();

    //private ArrayList[] offers = new ArrayList[getn_agents()];
    //private ArrayList[] offers = new ArrayList[this.getn_agents()];
    private ArrayList<ArrayList<State>> offers = new ArrayList<>();

    public State[] getStandingOffers() {
        return standingOffers;
    }

    public State getStandingOffer(Agent ag){
        return standingOffers[set.name2number(ag.getName())];
    }

    public State getStandingOffer(int i){
        return standingOffers[i];
    }

    private State[] standingOffers = new State[this.getn_agents()];


    //private State currentState;



    private Agent[] agents;

    public Model(){

        //init agents
        this.agents = this.set.initializeAgents();

        //init states
        //this.states = this.set.initializeStates();
        int i =0;
        //for (int i = 0; i < this.getn_agents(); i++) {
        offers.add(i, new ArrayList<State>());
        for (Agent a:agents) {
            int ag = set.name2number(a.getName());
            offers.get(i).add(ag, set.getStartStates()[ag]);
            standingOffers[ag] = set.getStartStates()[ag];
            a.addOffer(set.getStartStates()[ag]);
            a.setPrevBestOffer(set.getStartStates());
        }
        //Each agent proposes their offer
        for (Agent a :agents) {
            a.setPrevOffer(offers.get(i));

            //            System.out.println(a.getPrevOffer());
            //System.out.println(a.toString());
        }
//        System.out.println(offers.get(i).toString());


        //}


        //this.currentState = this.set.initializeFirstState();

//        for (Agent agent : agents) {
//            agent.belief = currentState;
//        }
//        Iterator test = offers.get(i).iterator();
//        System.out.println("TEST123");
//        while (test.hasNext()){
//            System.out.println("sTATE"+test.next().toString());
//        }

    }

    public State[] standingOffers(){
        return standingOffers;
    }

    public ArrayList<State> getRecentOffers(int t){
        return offers.get(t);
//        for (int i = 0; i < getn_agents(); i++) {
//
////            if (!this.offers[i].isEmpty()) {
////                System.out.println("OOFFFFEERRR: "+i+offers[i].get(t-1).getClass());
////                //State test = (State) offers[i].get(t-1);
////
////                of.add((State)this.offers[i].get(t));
////            }else{
////                for (int j = 0; j < set.getnAgents(); j++) {
////                    of.add(currentState);
////                }
////            }
//        }
//        return of;
    }
    public Agent[] getAgents() {
        return agents;
    }

    //public State getCurrentState() {
    //    return currentState;
//    }

    public int getn_agents(){
        return this.set.getnAgents();
    }

    public String getnameAgents(int i){
        return this.set.getName(i);
    }


    public void propose(Agent ag, State offer, int t){
        offers.get(t).add(set.name2number(ag.getName()), offer);
        standingOffers[set.name2number(ag.getName())] = offer;
    }

    public int getAgentNumber(Agent ag){
        return set.name2number(ag.getName());
    }
    public void newRound(int t){
        offers.add(t, new ArrayList<State>());
    }

    public void writeToCSV() throws IOException {

        String csv = "C:\\Users\\Diederik\\IdeaProjects\\demi\\result\\output.csv";
        CSVWriter writer = null;

        try {
            writer = new CSVWriter(new FileWriter(csv));
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(ArrayList<State> each: offers){
            for (State s:each) {
                writer.writeNext(s.toStringForCSV());
            }
//            Iterator it = each.iterator();
//            while (it.hasNext()){
//            }
        }


        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end of writeTOCSV
}
