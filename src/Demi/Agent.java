package Demi;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Iterator;

import static java.lang.Math.pow;

/**
 * Created by diederik.van.krieken on 12-9-2016.
 */
public class Agent {

    private State currentOffer; //x^j_t
    private State currentWeight; // w_{t-1}
    private ArrayList<State> prevOffer = new ArrayList<State>(); //The previous 4 offers

    private State[] prevBestOf; //Let xj [i,−1](t) be Agent j’s next-to-last best offer,
    // which is the offer that provides the highest utility to Agent i among all offers
    // made by Agent j until Agent j’s next-to-last offer
    // (i.e., not including Agent j’s standing offer) in period t.

    //desirableUtility = the monotonically decreasing concession strategy
    //current desired utililty.
    double desirableUtility;

    //Reservation curve (in our model line):
    private double minimumUtility = 0.4;

    public Agent(){

        this.desirableUtility = 1;
        this.prevBestOf = new State[4];
    }

    protected String name;

    public Agent(String name) {
        this.name = name;
        desirableUtility = 1;
        this.prevBestOf = new State[4];
    }


    public double reactiveConcessionStrategy(int t, int ag, State of, State ofFirst, State ofLast){
        double deltaUij;
        double deltaUij1 = utility(of) - utility(prevBestOf[ag]);
        double deltaUij2 = utility(of) - utility(ofFirst) - (1-utility(ofLast));
        deltaUij = Math.max(deltaUij1, deltaUij2);
        deltaUij = Math.max(deltaUij, 0);

//        System.out.println("delta 1: "+deltaUij1+" delta 2: "+deltaUij2+" delta u ij is: "+deltaUij);
        return deltaUij;

    }

     public double nonreactiveConcessionStrategyReturn(int t){

        if (t>100){
            t=100;
        }
        double concession = (t*0.01);
        return concession;
    }

    public State calculateWeight(Model mod, int t){
        State weight = new State();
        double base =0;
        double acid = 0;
        double water = 0;
        Iterator<State> it = prevOffer.iterator();
//        Iterator<State> it = mod.getRecentOffers(t-1).iterator();
        while (it.hasNext()){
            State offer = it.next();
            base += offer.getBase();
            acid += offer.getAcid();
            water += offer.getWater();
        }
        weight.setAcid(acid/mod.getn_agents());
        weight.setBase(base/mod.getn_agents());
        weight.setWater(water/mod.getn_agents());
        //TODO Ensure only the correct weight are updated?
//        weight.setAcid(acid/3);
//        weight.setBase(base/3);
//        weight.setWater(water/3);
        if(weight.getAcid() >1 || weight.getBase() >1||weight.getBase() >1){
            System.out.println("Something weird in t="+t);
        }
        this.currentWeight = weight;
        if( this.getName().equals("Cation")){
            System.out.println(weight.toString());
            System.out.println(this.desirableUtility);
        }
        return weight;
    }

    public State pointOnConcessionLine(State x){
        //Should be overridden to be agent specific
        System.out.println("Doing the agent projection, not the anion/mixbed/neut/cation");
        return x;
    }

    public double utility(State offer){
        //Should be overridden to be agent specific
        System.out.println("THERE IS SOMETHING WRONG. THE AGENTS UTILITY IS WATER");
        return offer.getWater();
    }

    public State pointWithinRange(State x){
        //Should be overridden to be agent specific
        System.out.println("THERE IS SOMETHING WRONG! THE AGENT FUNCTION IS NOT USED");
        return x;
    }


    //HERE POINT ON LINE IS CALCULATED
    //ax+by+c = 0
    //x0, y0 is original point
    public double[] lineToPoint(double a, double b, double c, double x, double y){
        double[] xy = new double[2];
        //x = \frac{b(bx_0 - ay_0)-ac}{a^2 + b^2}
        xy[0] = (((b*((b*x) - (a*y)))-a*c)/(pow(a,2)+pow(b,2)));
        //y = \frac{a(-bx_0 + ay_0) - bc}{a^2+b^2}.</math>
        xy[1] = (((a*(-(b*x) + (a*y)))-b*c)/(pow(a,2)+pow(b,2)));
        return xy;
    }


     @Override
    public String toString() {
        return "Agent{" +
                "currentOffer=" + currentOffer +
                ", prevOffer=" + prevOffer.toString() +
                ", prevBestOf=" + prevBestOf.toString() +
                ", desirableUtility=" + desirableUtility +
                ", name='" + name + '\'' +
                '}';
    }

    //GETTERS & SETTERS

    public State getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(State currentWeight) {
        this.currentWeight = currentWeight;
    }

    // Here the preffered offers of the other agents.
    public void setPrevBestOffer(int i,State offer){
        this.prevBestOf[i] = offer;
    }

    public void setPrevBestOffer(State[] offer){
        this.prevBestOf = offer;
    }

    public State getPrevBestOffer(int i){
        //System.out.println(name+" for agent "+i+" has prevBestOf "+prevBestOf[i]+" with utility "+ +utility(prevBestOf[i]));
        return prevBestOf[i];
    }

    public State[] getPrevBestOffer(){
        return prevBestOf;
    }

    public double getDesirableUtility() {
        return desirableUtility;
    }

    public void setDesirableUtility(double delta) {
        if (this.desirableUtility-delta > minimumUtility) {
            this.desirableUtility = this.desirableUtility  - delta;
        }else{
            this.desirableUtility = minimumUtility;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getOffer(){
        return currentOffer;
    }

    public void addCurrentOffer(State of){
        currentOffer = of;
    }

    public double getMinimumUtility() {
        return minimumUtility;
    }

    public void setMinimumUtility(double minimumUtility) {
        this.minimumUtility = minimumUtility;
    }

    public ArrayList<State> getPrevOffer() {
        return prevOffer;
    }

    public void setPrevOffer(ArrayList<State> prevOffer) {
        this.prevOffer = prevOffer;
    }
}
