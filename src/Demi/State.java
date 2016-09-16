package Demi;

import java.util.Arrays;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */
public class State {
    private int acid;
    private int base;
    private int water;
    private Offer[] offer;
    private int round;


    public State(){

    }
    public State (int a, int b, int w){
        this.acid = a;
        this.base = b;
        this.water = w;
    }

    @Override
    public String toString() {
        return "State{" +
                "acid=" + acid +
                ", base=" + base +
                ", water=" + water +
                ", offer=" + Arrays.toString(offer) +
                '}';
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getAcid() {
        return acid;
    }

    public void setAcid(int acid) {
        this.acid = acid;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }
}
