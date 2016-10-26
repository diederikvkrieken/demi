package Demi;

import static java.lang.Math.exp;
import static java.lang.Math.log;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */
public class Mixbed extends Agent {
    /*
    * Mixbed contains 6 filters
    * */
    int n_filters = 3;

//      1. Knowledge of anion head about the sub-agents:
//          {M1, M2 , M3} can process a amount of water
//          {M1, M2 , M3} needs to be cleaned after b water
//          {M1, M2 , M3} has filtered c amount of water
//          {M1, M2 , M3} needs d base to clean
//          {M1, M2 , M3} needs e base to clean
//          {M1, M2 , M3} needs f time to clean
//
//      2. Currently x amount of water being filtered
//      3. Currently Z \in {M1, M2 , M3} filter being used for water filtering
//      4. Currently Y \in {M1, M2 , M3} filter being used for cleaning
//      5. Currently w amount of base being used for cleaning
//      6. Currently v amount of acid being used for cleaning

//    private double[] a = new double[n_filters];
//    private double[] b = new double[n_filters];
//    private double[] c = new double[n_filters];
//    private double[] d = new double[n_filters];
//    private double[] e = new double[n_filters];
//    private double[] f = new double[n_filters];
//    private int[] z = new int[n_filters]; //filter being used for water cleaning, 1 or zero when not being used
//    private int[] y = new int[n_filters]; //filter being cleaned, 1 or 0 when not being used
//
//    private double x,w, v;// knowledge about the water and base and acid being used

//    public double getUtility() {
//        return utility;
//    }
//
//    public void setUtility(double utility) {
//        this.utility = utility;
//    }
//
//    private double utility;

    @Override
    public double utility(State offer){
        double value;
        value = exp(-offer.getBase() - offer.getAcid());
        return value;
    }

    /*
    * Calculation from point to line
    *
    * Indifference curve van e^(-x-y) is gelijk aan y = -x -log(utility)
    * Oftewel 0 = -x -y -log(utility)
    * a = - 1; b = - 1; c = -1;
    * x = \frac{b(bx_0 - ay_0)-ac}{a^2 + b^2}
    * y = \frac{a(-bx_0 + ay_0)-bc}{a^2 + b^2}
    *
    * x = 0.5*(x-y-log(u))
    * y = 0.5*(-x+y-log(u))
    * */

    public State pointOnConcessionLine(State x, double u){
        double first_cor = x.getAcid();
        double second_cor = x.getBase();
        double third_cor = x.getWater();
        State xPlusOne = new State();
        xPlusOne.setAcid(first_cor +((log(u)+3-(first_cor+second_cor+third_cor))/3));
        xPlusOne.setBase(second_cor +((log(u)+3-(first_cor+second_cor+third_cor))/3));
        xPlusOne.setWater(third_cor +((log(u)+3-(first_cor+second_cor+third_cor))/3));
        return xPlusOne;
    }

    @Override
    public State pointOnConcessionLine(State x){
        double first_cor = x.getAcid();
        double second_cor = x.getBase();
        double third_cor = x.getWater();
        State xPlusOne = new State();
        xPlusOne.setAcid(first_cor +((log(this.desirableUtility)+3-(first_cor+second_cor+third_cor))/3));
        xPlusOne.setBase(second_cor +((log(this.desirableUtility)+3-(first_cor+second_cor+third_cor))/3));
        xPlusOne.setWater(third_cor +((log(this.desirableUtility)+3-(first_cor+second_cor+third_cor))/3));
        return xPlusOne;
    }
    Mixbed(String name){
        super(name);
    }

}
