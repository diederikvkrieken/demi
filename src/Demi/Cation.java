package Demi;

import static java.lang.Math.exp;
import static java.lang.Math.log;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */
public class Cation extends Agent {
    /*
    * Cation contains 6 filters
    * */
    int n_filters = 6;

//      1. Knowledge of cation head about the sub-agents:
//          {C1, ..., C6} can process a amount of water
//          {C1, ..., C6} needs to be cleaned after b water
//          {C1, ..., C6} has filtered c amount of water
//          {C1, ..., C6} needs d acid to clean
//          {C1, ..., C6} needs e time to clean
//
//      2. Currently x amount of water being filtered
//      3. Currently Z \in {A1, ..., A6} filter being used for water filtering
//      4. Currently Y \in {A1, ..., A6} filter being used for cleaning
//      5. Currently w amount of acid being used for cleaning

//    private double[] a = new double[n_filters];
//    private double[] b = new double[n_filters];
//    private double[] c = new double[n_filters];
//    private double[] d = new double[n_filters];
//    private double[] e = new double[n_filters];
//    private int[] z = new int[n_filters]; //filter being used for water cleaning, 1 or zero when not being used
//    private int[] y = new int[n_filters]; //filter being cleaned, 1 or 0 when not being used
//
//    private double x,w;// knowledge about the water and acid being used

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
        value = exp(offer.getAcid() - offer.getWater())/exp(1);
        return value;
    }


    public State pointOnConcessionLine(State x, double u){
        double first_cor = x.getWater();
        double second_cor = x.getAcid();
        State xPlusOne = new State();
        xPlusOne.setWater(0.5*(-(log(u)+1)+first_cor+second_cor));
        xPlusOne.setAcid(0.5*((log(u)+1)+first_cor+second_cor));
        return xPlusOne;
    }

    public State pointOnConcessionLine(State x){
        double first_cor = x.getWater();
        double second_cor = x.getAcid();
        State xPlusOne = new State();
        xPlusOne.setWater(0.5*(-(log(this.utility)+1)+first_cor+second_cor));
        xPlusOne.setAcid(0.5*((log(this.utility)+1)+first_cor+second_cor));
        return xPlusOne;
    }


}
