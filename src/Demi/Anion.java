package Demi;

import static java.lang.Math.exp;
import static java.lang.Math.log;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */
public class Anion extends Agent {
    /*
    * Anions contains 6 filters
    * */

//      1. Knowledge of anion head about the sub-agents:
//          {A1, ..., A6} can process a amount of water
//          {A1, ..., A6} needs to be cleaned after b water
//          {A1, ..., A6} has filtered c amount of water
//          {A1, ..., A6} needs d base to clean
//          {A1, ..., A6} needs e time to clean
//
//      2. Currently x amount of water being filtered
//      3. Currently Z \in {A1, ..., A6} filter being used for water filtering
//      4. Currently Y \in {A1, ..., A6} filter being used for cleaning
//      5. Currently w amount of base being used for cleaning

    private double[] a = new double[6];
    private double[] b = new double[6];
    private double[] c = new double[6];
    private double[] d = new double[6];
    private double[] e = new double[6];
    private int[] z = new int[6]; //filter being used for water cleaning, 1 or zero when not being used
    private int[] y = new int[6]; //filter being cleaned, 1 or 0 when not being used
    //Using default utility funtion ax+bx
    private double alpha = 0.3;
    private double beta = -0.4;

    private double x,w;// knowledge about the water and base being used

    public double getUtility() {
        return utility;
    }

    public void setUtility(double utility) {
        this.utility = utility;
    }

    private double utility;

    @Override
    public double utility(State offer){
        double value;
        value = exp(offer.getBase() - offer.getWater());
        return value;
    }

    public double consessionStrategy(State offer){
        double value;
        value = -alpha / beta;
        this.utility = value;
        return value;
    }


    public State pointOnConcessionLine(State x, double u){
        double first_cor = x.getWater();
        double second_cor = x.getBase();
        State xPlusOne = new State();
        xPlusOne.setWater((double)-0.5*(log(u)+first_cor-second_cor));
        xPlusOne.setBase((double) 0.5*(log(u)+first_cor+second_cor));
        return xPlusOne;
    }

    public State pointOnConcessionLine(State x){
        double first_cor = x.getWater();
        double second_cor = x.getBase();
        State xPlusOne = new State();
        xPlusOne.setWater(0.5*(-log(this.utility)+first_cor+second_cor));
        xPlusOne.setBase(0.5*(log(this.utility)+first_cor+second_cor));
        return xPlusOne;
    }





}
