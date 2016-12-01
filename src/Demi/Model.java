package Demi;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Diederik van Krieken on 13-9-2016.
 * The model consist of the agents, and the offers.
 * Furthermore does it contain the entire history,
 * Containing the concessions, desires, and distance
 */

public class Model {

    //Setup variables.
    //Settings, States: standingOffers, offers & agents.
    private Settings set = new Settings();

    private ArrayList<ArrayList<State>> offers = new ArrayList<>();

    private State[] standingOffers = new State[this.getN_agents()];

    private Agent[] agents;

    //IF single situation is run, only single file, check with round nummbr.
    // Round number stands for the reservation utility used.
    // These are stored in the settings.
    private int roundNumber = 99;

    private int waterRatio = 1;

    public Model(){
        //init agents
        this.agents = this.set.initializeAgents();
        //init states
        initializeStates();
    }

    public Model(int round, int water_ratio){
        this.roundNumber = round;
        this.waterRatio  =water_ratio;
        //init agents
        this.agents = this.set.initializeAgents(round, water_ratio);
        //init states
        initializeStates();
    }


    //initialize states in model
    private void initializeStates(){
        int i =0;
        offers.add(i, new ArrayList<>());
        //For each agent get the preferred initial state
        //and add these to each agents knowledge
        for (Agent a:agents) {
            int ag = set.name2number(a.getName());
            offers.get(i).add(ag, set.getStartStates()[ag]);
            standingOffers[ag] = set.getStartStates()[ag];
            a.addCurrentOffer(set.getStartStates()[ag]);
            a.setPrevBestOffer(set.getStartStates());
        }
        //Each agent stores the preferred offer of the other agents
        for (Agent a :agents) {
            a.setPrevOffer(offers.get(i));
        }
    }

    // A proposal: the offer is added to the list and to the current offers
    void propose(Agent ag, State offer, int t){
        offers.get(t).add(set.name2number(ag.getName()), offer);
        standingOffers[set.name2number(ag.getName())] = offer;
    }

    void newRound(int t){
        offers.add(t, new ArrayList<>());
    }

    //Write offers to csv file
    //Below is for the csv write of distance
    private ArrayList<Double> maxDistance =new ArrayList<>();
    void addDistance(double distance){
        maxDistance.add(distance);
    }
    //Below is for the csv write of concession
    void addConcession(int i, ArrayList<Double> con){
        concession.add(i, con);
    }
    private ArrayList<ArrayList<Double>> concession = new ArrayList<>();
    //Below is for the csv write of average distance
    void addDistanceAvg(int i, ArrayList<Double> dis){
        distance_avg.add(i,dis);
    }
    private ArrayList<ArrayList<Double>> distance_avg = new ArrayList<>();
    //Below is for the csv write of desire
    void addDesire(int i, ArrayList<Double> dis){
        desire.add(i,dis);
    }
    private ArrayList<ArrayList<Double>> desire = new ArrayList<>();


    void writeToCSV(int method) throws IOException{

        String fileFolder;
        fileFolder = "C:\\Users\\\\Diederik\\IdeaProjects\\demi\\result\\";
        //fileFolder = "C:\\Users\\\\diederik.van.krieken\\IdeaProjects\\demi-neg\\result\\";
        System.out.println("Printing!!!");
        if (method == 1){
            fileFolder = fileFolder + "reactive "+set.getReservationString();
        }else if (method == 2){
            fileFolder = fileFolder + "nonreactive "+set.getReservationString();
        }else if (method == 3){
            fileFolder = fileFolder + "nonreactive "+set.getReservationString()+" mixbedrea";
        }
        if (waterRatio != 1){
            fileFolder = fileFolder +" mixbed"+waterRatio;
        }

//        if (this.roundNumber == 99 ){
//            temp = "C:\\Users\\Diederik\\IdeaProjects\\demi\\result\\output.csv";
//        }else{
//            temp = "C:\\Users\\Diederik\\IdeaProjects\\demi\\result\\output_"+ roundNumber +".csv";


        String csv = fileFolder+"\\output_"+ roundNumber +".csv" ;
        File file = new File(csv);
        file.getParentFile().mkdirs();
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
        csv = fileFolder+"\\output_"+ roundNumber +"_distance.csv" ;
        writer = null;
        try {
            writer = new CSVWriter(new FileWriter(csv));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(double dist: maxDistance){
            String[] temp2 = new String[1];
            temp2[0] = Double.toString(dist);
            writer.writeNext(temp2);
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        csv = fileFolder+"\\output_"+ roundNumber +"_concession.csv" ;
        writer = null;
        try {
            writer = new CSVWriter(new FileWriter(csv));
        } catch (IOException e) {
            e.printStackTrace();
        }

        printArrayListDouble(concession, writer);

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        csv = fileFolder+"\\output_"+ roundNumber +"_desire.csv" ;
        writer = null;
        try {
            writer = new CSVWriter(new FileWriter(csv));
        } catch (IOException e) {
            e.printStackTrace();
        }
        printArrayListDouble(desire, writer);

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        csv = fileFolder+"\\output_"+ roundNumber +"_distance_avg.csv" ;
        writer = null;

        try {
            writer = new CSVWriter(new FileWriter(csv));
        } catch (IOException e) {
            e.printStackTrace();
        }
        printArrayListDouble(distance_avg, writer);

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void printArrayListDouble(ArrayList<ArrayList<Double>> array, CSVWriter writer){
        for(ArrayList<Double> each: array){
            Iterator it = each.iterator();
            String[] temp2 = new String[4];
            int i=0;
            while(it.hasNext()) {
                temp2[i] = it.next().toString();
                i++;
            }
            writer.writeNext(temp2);
        }
    }

  //Getters & Setters

    public Settings getSet() {
        return set;
    }

    public State[] getStandingOffers() {
        return standingOffers;
    }

    State getStandingOffer(Agent ag){
        return standingOffers[set.name2number(ag.getName())];
    }

    State getStandingOffer(int i){
        return standingOffers[i];
    }

    ArrayList<State> getRecentOffers(int t){
        return offers.get(t);
    }

    int getAgentNumber(Agent ag){
        return set.name2number(ag.getName());
    }

    public Agent[] getAgents() {
        return agents;
    }

    int getN_agents(){
        return this.set.getNAgents();
    }

    String getNameAgents(int i){
        return this.set.getName(i);
    }

}
