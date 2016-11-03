package Demi;

import static java.lang.Math.*;

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

//    private double a,b;// knowledge about the acid and base being used

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
        System.out.println("1st Cor: "+first_cor+" 2nd "+second_cor+" utility "+this.desirableUtility);
        double newAcid = 0.5*(-(log(this.desirableUtility)) + first_cor - second_cor);
        double newBase = 0.5*(-(log(this.desirableUtility)) - first_cor + second_cor);
        System.out.println("Acid = "+newAcid+" base = "+newBase);

        //y = -x -log(utility)
        //x = -y -log(utility)

//        //TODO ensure to have [0 1]
//        if(newAcid <0){
//            newAcid = 0;
//            newBase = -newAcid -log(this.desirableUtility);
//        }else if (newBase <0){
//            newBase = 0;
//            newAcid = -newBase - log(this.desirableUtility);
//        }else if(newAcid >1){
//            newAcid = 1;
//            newBase = -newAcid -log(this.desirableUtility);
//        }else if (newBase>1){
//            newBase = 1;
//            newAcid = -newBase - log(this.desirableUtility);
//        }

//        if (abs(newAcid)-abs(newBase) > 0.2){
//            /*TODO make reservation check*/
//            /*Greater than or larger than*/
//
//
//
//        }

        xPlusOne.setAcid(newAcid);
        xPlusOne.setBase(newBase);
        xPlusOne.setWater(x.getWater());
        return xPlusOne;
    }

    @Override
    public State pointWithinRange(State x){
        double[] xy;
        if (abs(x.getAcid())-abs(x.getBase()) < 0.2) {
            xy = lineToPoint(1, -1, 0.2, x.getAcid(), x.getBase());
            //points = [acid, a, na;base, b, nb];
            //scatter(points(1,:), points(2,:))
            //scatter(na, nb)
        }
        else if (abs(x.getAcid())-abs(x.getBase()) > 0.2) {
            xy = lineToPoint(1, -1, -0.2, x.getAcid(), x.getBase());
//        points = [acid, a, na;base, b, nb];
//        scatter(points(1,:), points(2,:))
//        scatter(na, nb)
        }
        else{
            xy =new double[2];
            xy[0] = x.getAcid();
            xy[1] = x.getBase();
//        points = [acid, a;base, b];
//        scatter(points(1,:), points(2,:))
//        end
        }
        x.setAcid(xy[0]);
        x.setBase(xy[1]);
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
