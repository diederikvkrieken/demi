package Demi;

import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.sqrt;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */
public class Anion extends Agent {
    /*
    * Anions contains 6 filters
    * */


//   int n_filters = 6;
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

//    private double[] a = new double[n_filters];
//    private double[] b = new double[n_filters];
//    private double[] c = new double[n_filters];
//    private double[] d = new double[n_filters];
//    private double[] e = new double[n_filters];
//    private int[] z = new int[n_filters]; //filter being used for water cleaning, 1 or zero when not being used
//    private int[] y = new int[n_filters]; //filter being cleaned, 1 or 0 when not being used
//    //Using default utility funtion ax+bx
//    private double alpha = 0.3;
//    private double beta = -0.4;
//
//    private double x,w;// knowledge about the water and base being used

//    public double getUtility() {
//        return utility;
//    }
//
//    public void setUtility(double utility) {
//        this.utility = utility;
//    }

    @Override
    public double utility(State offer){
        double value;
        value = exp(offer.getBase() - offer.getWater())/exp(1);
        return value;
    }

//    public double consessionStrategy(State offer){
//        double value;
//        value = -alpha / beta;
//        this.utility = value;
//        return value;
//    }

    /*
    * Below calculates the point closest to state on the concession line.
    * Since the utility function is e^(base-water)/e
    * The solutions lie on the indifference curve using the cartesian solution
    * See: https://en.wikipedia.org/wiki/Distance_from_a_point_to_a_line
    *
    * Two options, calculation using the minimum utility as saved in the agent,
    * Or by giving an updated utility
    * */

    /*
    * Calculation from point to line
    *
    * Indifference curve van e^(x-y) is gelijk aan y = x +log(utility)
    * Oftewel 0 = x -y +log(utility)
    * a = - 1; b = +1; c = -1;
    * x = \frac{b(bx_0 - ay_0)-ac}{a^2 + b^2}
    * y = \frac{a(-bx_0 + ay_0)-bc}{a^2 + b^2}
    *
    * x = 0.5*(x+y-(log(u)+1))
    * y = 0.5*(x+y+(log(u)+1))
    * */


    public State pointOnConcessionLine(State x, double u){
        double first_cor = x.getWater();
        double second_cor = x.getBase();
        State xPlusOne = new State();
        xPlusOne.setWater(0.5*(-(log(u)+1)+first_cor+second_cor));
        xPlusOne.setBase(0.5*((log(u)+1)+first_cor+second_cor));

        return xPlusOne;
    }

    @Override
    public State pointOnConcessionLine(State x){
        //CHeck whether point is not accepted on concession line
        double first_cor = x.getWater();
        double second_cor = x.getBase();
        State xPlusOne = new State();
        System.out.println("1st Cor: " + first_cor + " 2nd " + second_cor + " utility " + this.desirableUtility);
        double newWater = 0.5 * (-(log(this.desirableUtility) + 1) + first_cor + second_cor);
        double newBase =0.5 * ((log(this.desirableUtility) + 1) + first_cor + second_cor);
        System.out.println("water = "+newWater+" base = "+newBase);

        //y = x +log(utility)
        //x = y -log(utility)
        if(newWater <0){
            newWater = 0;
            newBase = newWater +(log(this.desirableUtility)+1);
        }else if (newBase <0){
            newBase = 0;
            newWater = newBase - (log(this.desirableUtility)+1);
        }else if(newWater >1){
            newWater = 1;
            newBase = newWater +(log(this.desirableUtility)+1);
        }else if (newBase>1){
            newBase = 1;
            newWater = newBase - (log(this.desirableUtility)+1);
        }
        xPlusOne.setWater(newWater);
        xPlusOne.setBase(newBase);
        xPlusOne.setAcid(x.getAcid());
        //System.out.println("The point is: " + xPlusOne.toString());
        return xPlusOne;
    }


    // Anion reservation function is base  = water^2
    // So base has to be equal or less than sqrt(water)

//    @Override
//    public boolean reservationCurveCheck(State offer){
//        double first_cor = offer.getWater();
//        double second_cor = offer.getBase();
//        if (second_cor <= sqrt(first_cor)) {
//            return true;
//        }else{
//            return false;
//        }
//    }

    Anion(String name){
        super(name);
    }




}
