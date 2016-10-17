package Demi;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Iterator;

/**
 * Created by diederik.van.krieken on 12-9-2016.
 */
public class Agent {
    /*
    * Agent contains BDI
    * Belief is a state
    * Desire is a goal
    * Intention is a plan
    * */

//    public State getCurrentOffer() {
//        return currentOffer;
//    }
//
//    public void setCurrentOffer(State currentOffer) {
//        this.currentOffer = currentOffer;
//    }

    private State currentOffer; //x^j_t

    public State getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(State currentWeight) {
        this.currentWeight = currentWeight;
    }

    private State currentWeight; // w_{t-1}

    public ArrayList<State> getPrevOffer() {
        return prevOffer;
    }

    public void setPrevOffer(ArrayList<State> prevOffer) {
        if (prevOffer.size()>4){
            System.out.println("Greater than 8");
        }
        this.prevOffer = prevOffer;
    }

    private ArrayList<State> prevOffer = new ArrayList<State>();
    private State[] prevBestOf; //Let xj [i,−1](t) be Agent j’s next-to-last best offer,
    // which is the offer that provides the highest utility to Agent i among all offers
    // made by Agent j until Agent j’s next-to-last offer
    // (i.e., not including Agent j’s standing offer) in period t.

//    State belief;
    double desirableUtility;
    double utility;


    public Agent(){
        this.desirableUtility = 1;
        this.utility = 1;
        //this.currentOffer = new State(0,0,0);
        //belief = new State();
    }



    protected String name;

    //protected Preferences preferences;

    public void observe(State state){
        //belief = state;
    }


//    public void generateOffer(int i){
//        State of = new State();
//        if (i ==0){
//            if (name.equals("Anion")){
//                //of.setBase(belief.getBase());
//                of.setAcid(0);
//                of.setWater(0);
//                System.out.println(name+": " + of.toString());
//                this.prevOffer.add(of);
//
//            }else if (name.equals("Cation")){
//                of.setBase(0);
//                //of.setAcid(belief.getAcid());
//                of.setWater(0);
//                System.out.println(name+": " + of.toString());
//
//                this.prevOffer.add(of);
//
//            }else if (name.equals("Mixbed")){
//                of.setBase(0);
//                of.setAcid(0);
//                of.setWater(belief.getWater() );
//                System.out.println(name+": " + of.toString());
//
//                prevOffer.add(of);
//
//            }else if (name.equals("Neut")){
//                of.setBase(belief.getBase());
//                of.setAcid(belief.getAcid());
//                of.setWater(0);
//                System.out.println(name+": " + of.toString());
//
//                prevOffer.add(of);
//
//            }else {
//                //ERRROOORRRR
//                System.out.println("ERROR!!!!! NAME NOT CORRECT");
//                System.out.println(name+" is not recognize1");
//
//            }
//            this.currentOffer = of;
//        }else {
//            i--;
//            if (name.equals("Anion")) {
//                of.setBase(prevOffer.get(i).getBase() - 10);
//                of.setAcid(prevOffer.get(i).getAcid());
//                of.setWater(prevOffer.get(i).getWater() + 10);
//                this.prevOffer.add(of);
//                System.out.println(name+": " + of.toString());
//
//
//
//
//            } else if (name.equals("Cation")) {
//                of.setBase(prevOffer.get(i).getBase());
//                of.setAcid(prevOffer.get(i).getAcid() - 10);
//                of.setWater(prevOffer.get(i).getWater() + 10);
//                this.prevOffer.add(of);
//                System.out.println(name+": " + of.toString());
//
//
//            } else if (name.equals("Mixbed")) {
//                of.setBase(prevOffer.get(i).getBase() + 5);
//                of.setAcid(prevOffer.get(i).getAcid() + 5);
//                of.setWater(prevOffer.get(i).getWater() - 10);
//                this.prevOffer.add(of);
//                System.out.println(name+": " + of.toString());
//
//
//            }else if (name.equals("Neut")) {
//                of.setBase(prevOffer.get(i).getBase() - 5);
//                of.setAcid(prevOffer.get(i).getAcid() - 5);
//                of.setWater(prevOffer.get(i).getWater());
//                this.prevOffer.add(of);
//                System.out.println(name+": " + of.toString());
//
//
//            } else {
//                //ERRROOORRRR
//                System.out.println("ERROR!!!!! NAME NOT CORRECT");
//                System.out.println(name+" is not recognize2");
//
//            }
//            this.currentOffer = of;
//        }
//
//
//    }




    public Agent(String name) {
        this.name = name;
        desirableUtility = 1;
        this.utility = 1;
 //       belief = new State();
    }

//    public Agent (String name, String[] pref){
//        this.name = name;
//        //this.preferences = new Preferences();
//        //this.preferences.preferences = pref;
////        belief = new State();
//    }
    //public class Preferences {
    //    String[] preferences;
    //    int counter;
    //}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getOffer(){
        return currentOffer;
    }

    public State consession_strategy(int n){
        State s = new State();
        return s;
    }
    public void addOffer(State of){
        currentOffer = of;
    }

    public void concessionStrategy(int t){
        //updateConcession(t);
        //See algorithm 3 in Zheng 2015
        if (t>100){
            t=100;
        }
        this.desirableUtility = 1 - (t*0.01);
        this.utility = 1-(t*0.01);
        System.out.println("Consession value =:"+this.desirableUtility);
        //TODO
        // Check for reservation curve
    }



    //Should be updated to only calculate weight of those with no interest
    //eg Anion no say in Acid.
    //TODO
    // Ensure only the correct weight are updated
    public State calculateWeight(Model mod, int t){
        State weight = new State();
        double base =0;
        double acid = 0;
        double water = 0;
        Iterator<State> it = prevOffer.iterator();
        while (it.hasNext()){
            State offer = it.next();
            //System.out.println(offer.toString());
            base += offer.getBase();
            acid += offer.getAcid();
            water += offer.getWater();
        }
        weight.setAcid(acid/mod.getn_agents());
        weight.setBase(base/mod.getn_agents());
        weight.setWater(water/mod.getn_agents());
        if(weight.getAcid() >1 || weight.getBase() >1||weight.getBase() >1){
            System.out.println("Something weird in t="+t);
        }
        this.currentWeight = weight;
        return weight;
    }

    public State pointOnConcessionLine(State x){
        System.out.println("Doing the agent projection, not the anion/mixbed/neut/cation");
        return x;
    }

    //Default utility
    //Maximize the Water
    public double utility(State offer){
        System.out.println("THERE IS SOMETHING WRONG. THE AGENTS UTILITY IS WATER");
        return offer.getWater();
    }

    public boolean reservationCurveCheck(State offer){
        System.out.println("THERE IS SOMETHING WRONG. THE AGENTS Reservation function is not used");
        return false;
    }

    /*
    * Implement of the Reactive Concession Strategy
    * */

    public double utilityChange(int t){
        return t*0.01;
    }

    public void setPrevBestOffer(int i,State offer){
        this.prevBestOf[i] = offer;
    }

    public State getPrevBestOffer(int i){
        return prevBestOf[i];
    }

    @Override
    public String toString() {
        return "Agent{" +
                "currentOffer=" + currentOffer +
                ", prevOffer=" + prevOffer +
                ", prevBestOf=" + prevBestOf +
                ", desirableUtility=" + desirableUtility +
                ", name='" + name + '\'' +
                '}';
    }

}
