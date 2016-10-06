package Demi;

import java.util.Arrays;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */
public class State {
    private double acid;
    private double base;
    private double water;
    //private Offer[] offer;
    private int round;


    public State(){
        this.base = 0;
        this.acid = 0;
        this.water = 0;

    }

    public State (double a, double b, double w){
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
}
