package Demi;

import static java.lang.Math.*;
import static java.lang.Math.max;

/**
 * Created by Diederik van Krieken on 13-9-2016.
 * The cation agent, has the utility function -Water + Acid
 * Given a state, has its own utility,
 * And point to indifference curve function
 * Ensures that the new offer is [0,1]
 */

public class Cation extends Agent {

    @Override
    public double utility(State offer){
        double value;
        value = exp(offer.getAcid() - offer.getWater())/exp(1);
        return value;
    }


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
        double second_cor = x.getAcid();
        State xPlusOne = new State();
        xPlusOne.setWater(0.5*(-(log(u)+1)+first_cor+second_cor));
        xPlusOne.setAcid(0.5*((log(u)+1)+first_cor+second_cor));
        return xPlusOne;
    }
    @Override
    public State pointOnConcessionLine(State x){
        double first_cor = x.getWater();
        double second_cor = x.getAcid();
        State xPlusOne = new State();
//        System.out.println("1st Cor: "+first_cor+" 2nd "+second_cor+"utility "+this.desirableUtility);

        double[] xy = lineToPoint(1,-1,log(this.desirableUtility)+1,first_cor, second_cor);

        double newWater = xy[0];
        double newAcid = xy[1];

        //y = x +log(utility)
        //x = y -log(utility)

        xPlusOne.setWater(newWater);
        xPlusOne.setAcid(newAcid);
        xPlusOne.setBase(min(max(x.getBase(),0),1));
        return xPlusOne;
    }

    @Override
    public State pointWithinRange(State x){
        if(x.getWater() <0){
            x.setWater(0);
            x.setAcid(x.getWater()+log(this.desirableUtility)+1);
        }else if (x.getAcid() <0){
            x.setAcid(0);
            x.setWater(x.getAcid() - (log(this.desirableUtility)+1));
        }else if(x.getWater() >1){
            x.setWater(1);
            x.setAcid(x.getWater() +(log(this.desirableUtility)+1));
        }else if (x.getAcid()>1){
            x.setAcid(1);
            x.setWater(x.getAcid()- (log(this.desirableUtility)+1));
        }
        return x;
    }

    Cation(String name){
        super(name);
    }

}
