package Demi;

import static java.lang.Math.exp;
import static java.lang.Math.log;

/**
 * Created by Diederik van Krieken on 13-9-2016.
 * The anion agent, has the utility function (l) Water + Acid + Base
 * The l stands for the ratio water vs base/acid
 * Given a state, has its own utility,
 * And point to indifference plane
 * Ensures that the new offer is [0,1] ^3
 */

public class Mixbed extends Agent {

    //set parameters
    //sum is the sum of the variables in the utility function such that
    // exp(a*base + b*acid +c * water)/(a+b+c)
    // (a+b+c) == sum

    private int water_ratio = 1;

    private int sum = 2+water_ratio;
    @Override
    public double utility(State offer){
        double value;
        value = exp(offer.getBase() + offer.getAcid() + water_ratio*offer.getWater())/exp(sum);
        return value;
    }

    /*
    * Calculation from point to line
    *
    * Indifference curve van e^(x+y+z)/e^3 is gelijk aan log(u)+3 = x + y + z
    * Oftewel 0 = -z -x -y -log(utility)
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
        xPlusOne.setAcid(first_cor +((log(u)+sum-(first_cor+second_cor+third_cor))/sum));
        xPlusOne.setBase(second_cor +((log(u)+sum-(first_cor+second_cor+third_cor))/sum));
        xPlusOne.setWater(third_cor +((log(u)+sum-(first_cor+second_cor+third_cor))/sum));
        return xPlusOne;
    }

    @Override
    public State pointOnConcessionLine(State x){

        double first_cor = x.getAcid();
        double second_cor = x.getBase();
        double third_cor = x.getWater();
        State xPlusOne = new State();
//        System.out.println("1st Cor: "+first_cor+" 2nd "+second_cor+" 3d "+third_cor+" utility "+this.desirableUtility);
        xPlusOne.setAcid(first_cor +((log(this.desirableUtility)+sum-(first_cor+second_cor+third_cor))/sum));
        xPlusOne.setBase(second_cor +((log(this.desirableUtility)+sum-(first_cor+second_cor+third_cor))/sum));
        xPlusOne.setWater(third_cor +((log(this.desirableUtility)+sum-(first_cor+second_cor+third_cor))/sum));


//        System.out.println("Our Mixbed Proposal is: "+xPlusOne.toString());
        return xPlusOne;
    }

    @Override
    public State pointWithinRange(State x){
        double first_cor = x.getAcid();
        double second_cor = x.getBase();
        double third_cor = x.getWater();
        if(third_cor < 0){
            third_cor = 0;
            //lineToPoint(1, 1, log(this.desirableUtility)+3, first_cor, second_cor);
        }else if(third_cor >1){
            third_cor =1;
            //lineToPoint(1, 1, -log(this.desirableUtility)+3, first_cor, second_cor);
        }
        if(second_cor < 0){
            second_cor =0;
            //lineToPoint(1, 1, log(this.desirableUtility)+3, first_cor, third_cor);
        }else if(second_cor >1){
            second_cor = 1;
            //lineToPoint(1, 1, -log(this.desirableUtility)+3, first_cor, third_cor);
        }
        if(first_cor < 0){
            first_cor = 0;
            //lineToPoint(1, 1, log(this.desirableUtility)+3, first_cor, second_cor);
        }else if(first_cor >1){
            first_cor = 1;
            //lineToPoint(1, 1, -log(this.desirableUtility)+3, first_cor, second_cor);
        }
        State newX = new State();
        newX.setAcid(first_cor);
        newX.setBase(second_cor);
        newX.setWater(third_cor);
        return newX;
    }

    Mixbed(String name){
        super(name);
    }

    Mixbed(String name, int ratio){
        super(name);
        this.water_ratio = ratio;
    }

    int getWater_ratio(){
        return this.water_ratio;
    }
}
