package Demi;

import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.sqrt;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */
public class Neut extends Agent {
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

    private double a,b;// knowledge about the acid and base being used

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
        State xPlusOne = new State();
        xPlusOne.setAcid(0.5*( -(log(u)) + first_cor - second_cor));
        xPlusOne.setBase(0.5*((log(u)) + first_cor + second_cor));
        return xPlusOne;
    }

    public State pointOnConcessionLine(State x){
        double first_cor = x.getAcid();
        double second_cor = x.getBase();
        State xPlusOne = new State();
        xPlusOne.setAcid(0.5*(-(log(this.utility)) + first_cor - second_cor));
        xPlusOne.setBase(0.5*((log(this.utility)) + first_cor + second_cor));
        return xPlusOne;
    }

    // Anion reservation function is acid = water +- 0.1
    // So base has to be equal or less than sqrt(water)

    @Override
    public boolean reservationCurveCheck(State offer){
        double first_cor = offer.getBase();
        double second_cor = offer.getAcid();
        if (second_cor >= (first_cor-0.2)) {
            if (second_cor <= (first_cor+0.2)) {
                return true;
            }
        }
        return false;
    }




}
