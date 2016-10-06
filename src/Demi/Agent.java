package Demi;

import java.util.ArrayList;
import java.lang.Math;

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

    private State currentOffer;
    private ArrayList<State> prevOffer = new ArrayList<State>();
    private State prevBestOf;

    State belief;


    public Agent(){

        belief = new State();
    }

    protected String name;

    //protected Preferences preferences;

    public void observe(State state){
        belief = state;
    }


    public void generateOffer(int i){
        State of = new State();
        if (i ==0){
            if (name.equals("Anion")){
                of.setBase(belief.getBase());
                of.setAcid(0);
                of.setWater(0);
                System.out.println(name+": " + of.toString());
                this.prevOffer.add(of);

            }else if (name.equals("Cation")){
                of.setBase(0);
                of.setAcid(belief.getAcid());
                of.setWater(0);
                System.out.println(name+": " + of.toString());

                this.prevOffer.add(of);

            }else if (name.equals("Mixbed")){
                of.setBase(0);
                of.setAcid(0);
                of.setWater(belief.getWater() );
                System.out.println(name+": " + of.toString());

                prevOffer.add(of);

            }else if (name.equals("Neut")){
                of.setBase(belief.getBase());
                of.setAcid(belief.getAcid());
                of.setWater(0);
                System.out.println(name+": " + of.toString());

                prevOffer.add(of);

            }else {
                //ERRROOORRRR
                System.out.println("ERROR!!!!! NAME NOT CORRECT");
                System.out.println(name+" is not recognize1");

            }
            this.currentOffer = of;
        }else {
            i--;
            if (name.equals("Anion")) {
                of.setBase(prevOffer.get(i).getBase() - 10);
                of.setAcid(prevOffer.get(i).getAcid());
                of.setWater(prevOffer.get(i).getWater() + 10);
                this.prevOffer.add(of);
                System.out.println(name+": " + of.toString());




            } else if (name.equals("Cation")) {
                of.setBase(prevOffer.get(i).getBase());
                of.setAcid(prevOffer.get(i).getAcid() - 10);
                of.setWater(prevOffer.get(i).getWater() + 10);
                this.prevOffer.add(of);
                System.out.println(name+": " + of.toString());


            } else if (name.equals("Mixbed")) {
                of.setBase(prevOffer.get(i).getBase() + 5);
                of.setAcid(prevOffer.get(i).getAcid() + 5);
                of.setWater(prevOffer.get(i).getWater() - 10);
                this.prevOffer.add(of);
                System.out.println(name+": " + of.toString());


            }else if (name.equals("Neut")) {
                of.setBase(prevOffer.get(i).getBase() - 5);
                of.setAcid(prevOffer.get(i).getAcid() - 5);
                of.setWater(prevOffer.get(i).getWater());
                this.prevOffer.add(of);
                System.out.println(name+": " + of.toString());


            } else {
                //ERRROOORRRR
                System.out.println("ERROR!!!!! NAME NOT CORRECT");
                System.out.println(name+" is not recognize2");

            }
            this.currentOffer = of;
        }


    }




    public Agent(String name) {
        this.name = name;
        belief = new State();
    }

    public Agent (String name, String[] pref){
        this.name = name;
        //this.preferences = new Preferences();
        //this.preferences.preferences = pref;
        belief = new State();
    }
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
        prevOffer.add(of);
    }

    public void concessionStrategy(int t){
        //updateConcession(t);
        //See algorithm 3 in Zheng 2015

    }



    //Should be updated to only calculate weight of those with no interest
    //eg Anion no say in Acid.
    public State calculateWeight(Model mod, int t){
        State weight = new State();
        int base =0;
        int acid = 0;
        int water = 0;
        for (State offer : mod.getRecentOffers(t)){
            base += offer.getBase();
            acid += offer.getAcid();
            water += offer.getWater();
        }
        weight.setAcid(acid/mod.getn_agents());
        weight.setBase(base/mod.getn_agents());
        weight.setWater(water/mod.getn_agents());
        return weight;
    }

    public State calculateProposal(State weight){
        State offer = weight;
        this.currentOffer = offer;
        this.prevOffer.add(offer);
        return offer;
    }

    //Default utility
    //Maximize the Water
    public double utility(State offer){
        return offer.getWater();
    }

    public void setPrevBestOffer(State offer){
        this.prevBestOf = offer;
    }

    public State getPrevBestOffer(){
        return prevBestOf;
    }


}
