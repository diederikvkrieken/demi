package Demi;

import java.util.Arrays;

/**
 * Created by Diederik van Krieken on 13-9-2016.
 * A State consists of acid, base, and water
 */
public class State {
    private double acid;
    private double base;
    private double water;
    private int n_issues = 3;
    private int round;


    State(){
        this.base = 0;
        this.acid = 0;
        this.water = 0;

    }

    State (double a, double b, double w){
        this.acid = a;
        this.base = b;
        this.water = w;
    }

    public State (double a, double b, double w, int r){
        this.acid = a;
        this.base = b;
        this.water = w;
        this.round = r;
    }

    @Override
    public String toString() {
        return "State{" +
                "acid=" + acid +
                ", base=" + base +
                ", water=" + water +
                //", offer=" + Arrays.toString(offer) +
                '}';
    }

    //Getters and Setters

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public double getAcid() {
        return acid;
    }

    public void setAcid(double acid) {
        this.acid = acid;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double base) {
        this.base = base;
    }

    public double getWater() {
        return water;
    }

    public void setWater(double water) {
        this.water = water;
    }

    String [] toStringForCSV(){
        String [] output = new String[n_issues];
        output[0] = Double.toString(acid);
        output[1] = Double.toString(base);
        output[2] = Double.toString(water);
        return output;
    }
}
