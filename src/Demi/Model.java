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


    //UNUSED VARIABLES
    //private ArrayList<State> states;
    //private ArrayList[] offers = new ArrayList[getn_agents()];
    //private ArrayList[] offers = new ArrayList[this.getn_agents()];
    //private State currentState;
    //public State getCurrentState() {
    //    return currentState;
    //}

    private ArrayList<Double> maxdistance =new ArrayList<>();

    public void addDistance(double distance){
        maxdistance.add(distance);
    }


    //Get settings
    private Settings set = new Settings();

    private ArrayList<ArrayList<State>> offers = new ArrayList<>();

    private State[] standingOffers = new State[this.getn_agents()];

    private Agent[] agents;

    public Model(){

        //init agents
        this.agents = this.set.initializeAgents();

        //init states
        int i =0;

        offers.add(i, new ArrayList<State>());
        for (Agent a:agents) {
            int ag = set.name2number(a.getName());
            offers.get(i).add(ag, set.getStartStates()[ag]);
            standingOffers[ag] = set.getStartStates()[ag];
            a.addOffer(set.getStartStates()[ag]);
            a.setPrevBestOffer(set.getStartStates());
        }
        //Each agent stores the preferred offer of the other agents
        for (Agent a :agents) {
            a.setPrevOffer(offers.get(i));
        }

        //CODETESTING
        //System.out.println(offers.get(i).toString());

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

    public void propose(Agent ag, State offer, int t){
        offers.get(t).add(set.name2number(ag.getName()), offer);
        standingOffers[set.name2number(ag.getName())] = offer;
    }

    public void newRound(int t){
        offers.add(t, new ArrayList<State>());
    }

    //Write offers to csv file
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
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end of writeTOCSV

    public void writeToCSVdistance() throws IOException {

        String csv = "C:\\Users\\Diederik\\IdeaProjects\\demi\\result\\output_distance.csv";
        CSVWriter writer = null;

        try {
            writer = new CSVWriter(new FileWriter(csv));
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(double dist: maxdistance){
            String[] temp = new String[1];
            temp[0] = Double.toString(dist);
            writer.writeNext(temp);
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end of writeTOCSV


    public void addConcession(int i, ArrayList<Double> con){
        System.out.println("Concession is: "+con.toString());
        concession.add(i, con);
    }
    private ArrayList<ArrayList<Double>> concession = new ArrayList<>();



    public void writeToCSVconcession() throws IOException {

        String csv = "C:\\Users\\Diederik\\IdeaProjects\\demi\\result\\output_concession.csv";
        CSVWriter writer = null;

        try {
            writer = new CSVWriter(new FileWriter(csv));
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(ArrayList<Double> each: concession){
            Iterator it = each.iterator();
            String[] temp = new String[4];
            int i=0;
            while(it.hasNext()) {
                temp[i] = it.next().toString();
            }
            writer.writeNext(temp);
        }
//        for(double dist: maxdistance){
//            String[] temp = new String[1];
//            temp[0] = Double.toString(dist);
//            writer.writeNext(temp);
//        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end of writeTOCSV


    //Getters & Setters

    public Settings getSet() {
        return set;
    }

    public State[] getStandingOffers() {
        return standingOffers;
    }

    public State getStandingOffer(Agent ag){
        return standingOffers[set.name2number(ag.getName())];
    }

    public State getStandingOffer(int i){
        return standingOffers[i];
    }

    public State[] standingOffers(){
        return standingOffers;
    }

    public ArrayList<State> getRecentOffers(int t){
        return offers.get(t);
        //CODETESTING
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

    public int getAgentNumber(Agent ag){
        return set.name2number(ag.getName());
    }

    public Agent[] getAgents() {
        return agents;
    }

    public int getn_agents(){
        return this.set.getnAgents();
    }

    public String getnameAgents(int i){
        return this.set.getName(i);
    }

}
