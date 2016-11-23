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

    //Setup variables.
    //Settings, States: standingOffers, offers & agents.
    private Settings set = new Settings();

    private ArrayList<ArrayList<State>> offers = new ArrayList<>();

    private State[] standingOffers = new State[this.getn_agents()];

    private Agent[] agents;

    private int roundnumber = 99;

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
            a.addCurrentOffer(set.getStartStates()[ag]);
            a.setPrevBestOffer(set.getStartStates());
        }
        //Each agent stores the preferred offer of the other agents
        for (Agent a :agents) {
            a.setPrevOffer(offers.get(i));
        }
    }

    public Model(int round, int method){

        this.roundnumber = round;
        //init agents
        this.agents = this.set.initializeAgents(round);

        //init states
        int i =0;

        offers.add(i, new ArrayList<State>());
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
    public void propose(Agent ag, State offer, int t){
        offers.get(t).add(set.name2number(ag.getName()), offer);
        standingOffers[set.name2number(ag.getName())] = offer;
    }

    public void newRound(int t){
        offers.add(t, new ArrayList<State>());
    }

    //Write offers to csv file
    public void writeToCSV() throws IOException {

        String temp;
        if (this.roundnumber == 99 ){
            temp = "C:\\Users\\Diederik\\IdeaProjects\\demi\\result\\output.csv";
        }else{
            temp = "C:\\Users\\Diederik\\IdeaProjects\\demi\\result\\output_"+roundnumber+".csv";
        }
        String csv = temp;
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

    //Write distance to CSV file
    //Below is for the csv write of distance
    private ArrayList<Double> maxdistance =new ArrayList<>();
    public void addDistance(double distance){
        maxdistance.add(distance);
    }

    public void writeToCSVdistance() throws IOException {

        String temp;
        if (this.roundnumber == 99 ){
            temp = "C:\\Users\\Diederik\\IdeaProjects\\demi\\result\\output_distance.csv";
        }else{
            temp = "C:\\Users\\Diederik\\IdeaProjects\\demi\\result\\output_"+roundnumber+"_distance.csv";
        }
        String csv = temp;

//        String csv = "C:\\Users\\Diederik\\IdeaProjects\\demi\\result\\output_distance.csv";
        CSVWriter writer = null;

        try {
            writer = new CSVWriter(new FileWriter(csv));
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(double dist: maxdistance){
            String[] temp2 = new String[1];
            temp2[0] = Double.toString(dist);
            writer.writeNext(temp2);
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end of writeTOCSV

    //Write concessions to CSV file
    //Below is for the csv write of consession
    public void addConcession(int i, ArrayList<Double> con){
//        System.out.println("Concession is: "+con.toString());
        concession.add(i, con);
    }

    private ArrayList<ArrayList<Double>> concession = new ArrayList<>();

    public void writeToCSVconcession() throws IOException {

        String temp;
        if (this.roundnumber == 99 ){
            temp = "C:\\Users\\Diederik\\IdeaProjects\\demi\\result\\output_concession.csv";
        }else{
            temp = "C:\\Users\\Diederik\\IdeaProjects\\demi\\result\\output_"+roundnumber+"_concession.csv";
        }
        String csv = temp;
//        String csv = "C:\\Users\\Diederik\\IdeaProjects\\demi\\result\\output_concession.csv";
        CSVWriter writer = null;

        try {
            writer = new CSVWriter(new FileWriter(csv));
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(ArrayList<Double> each: concession){
            Iterator it = each.iterator();
            String[] temp2 = new String[4];
            int i=0;
            while(it.hasNext()) {
                temp2[i] = it.next().toString();
                i++;
            }
            writer.writeNext(temp2);
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



    public void addDistanceAvg(int i, ArrayList<Double> dis){
        distance_avg.add(i,dis);
    }
    private ArrayList<ArrayList<Double>> distance_avg = new ArrayList<>();


    public void writeToCSVDistanceAvg() throws IOException {

        String temp;
        if (this.roundnumber == 99 ){
            temp = "C:\\Users\\Diederik\\IdeaProjects\\demi\\result\\output_distance_avg.csv";
        }else{
            temp = "C:\\Users\\Diederik\\IdeaProjects\\demi\\result\\output_"+roundnumber+"_distance_avg.csv";
        }
        String csv = temp;
//        String csv = "C:\\Users\\Diederik\\IdeaProjects\\demi\\result\\output_concession.csv";
        CSVWriter writer = null;

        try {
            writer = new CSVWriter(new FileWriter(csv));
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(ArrayList<Double> each: distance_avg){
            Iterator it = each.iterator();
            String[] temp2 = new String[4];
            int i=0;
            while(it.hasNext()) {
                temp2[i] = it.next().toString();
                i++;
            }
            writer.writeNext(temp2);
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

    public void addDesire(int i, ArrayList<Double> dis){
        desire.add(i,dis);
    }
    private ArrayList<ArrayList<Double>> desire = new ArrayList<>();


    public void writeToCSVDesire() throws IOException {

        String temp;
        if (this.roundnumber == 99 ){
            temp = "C:\\Users\\Diederik\\IdeaProjects\\demi\\result\\output_desire.csv";
        }else{
            temp = "C:\\Users\\Diederik\\IdeaProjects\\demi\\result\\output_"+roundnumber+"_desire.csv";
        }
        String csv = temp;
//        String csv = "C:\\Users\\Diederik\\IdeaProjects\\demi\\result\\output_concession.csv";
        CSVWriter writer = null;

        try {
            writer = new CSVWriter(new FileWriter(csv));
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(ArrayList<Double> each: desire){
            Iterator it = each.iterator();
            String[] temp2 = new String[4];
            int i=0;
            while(it.hasNext()) {
                temp2[i] = it.next().toString();
                i++;
            }
            writer.writeNext(temp2);
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

    public ArrayList<State> getRecentOffers(int t){
        return offers.get(t);
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
