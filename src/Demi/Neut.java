package Demi;

import static java.lang.Math.*;

/**
 * Created by Diederik van Krieken on 13-9-2016.
 * The Neut agent, has the utility function -Acid - Base
 * Given a state, has its own utility,
 * And point to indifference curve function
 * Ensures that the new offer is [0,1]
 */

public class Neut extends Agent {
    /*
    * Anions contains 6 filters
    * */


    @Override
    public double utility(State offer){
        double value;
        value = exp(-offer.getBase() - offer.getAcid());
        return value;
    }

    // Utility if a resource has to be ignored.
    // i = 1 is acid, 2 is base, 3 = water
    public double utility(State offer, int i){
        double value;
        if (i == 1){
            value = exp(-offer.getBase() - 0);
        }else if (i ==2 ){
            value = exp( 0 - -offer.getAcid());
        }else {
            value = exp(-offer.getBase() - offer.getAcid());
        }
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
    @Override
    public State pointOnConcessionLine(State x){
        double first_cor = x.getAcid();
        double second_cor = x.getBase();
        State xPlusOne = new State();
//        System.out.println("1st Cor: "+first_cor+" 2nd "+second_cor+" utility "+this.desirableUtility);
        double newAcid = 0.5*(-(log(this.desirableUtility)) + first_cor - second_cor);
        double newBase = 0.5*(-(log(this.desirableUtility)) - first_cor + second_cor);
//        System.out.println("Acid = "+newAcid+" base = "+newBase);

        //y = -x -log(utility)
        //x = -y -log(utility)

        xPlusOne.setAcid(newAcid);
        xPlusOne.setBase(newBase);
        xPlusOne.setWater(min(max(x.getWater(),0),1));
        return xPlusOne;
    }

    @Override
    public State pointWithinRange(State x){
        double[] xy;
        if (abs(x.getAcid())-abs(x.getBase()) < 0.2) {
            xy = lineToPoint(1, -1, 0.2, x.getAcid(), x.getBase());
         }
        else if (abs(x.getAcid())-abs(x.getBase()) > 0.2) {
            xy = lineToPoint(1, -1, -0.2, x.getAcid(), x.getBase());
        }
        else{
            xy =new double[2];
            xy[0] = x.getAcid();
            xy[1] = x.getBase();
        }
        x.setAcid(min(max(xy[0],0),1));
        x.setBase(min(max(xy[1],0),1));
        return x;
    }

    // Anion reservation function is acid = water +- 0.1
    // So base has to be equal or less than sqrt(water)

//    @Override
//    public boolean reservationCurveCheck(State offer){
//        double first_cor = offer.getBase();
//        double second_cor = offer.getAcid();
//        if (second_cor >= (first_cor-0.2)) {
//            if (second_cor <= (first_cor+0.2)) {
//                return true;
//            }
//        }
//        return false;
//    }

    Neut(String name){
        super(name);
    }



}
