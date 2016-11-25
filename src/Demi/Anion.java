package Demi;

import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.sqrt;
import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by Diederik van Krieken on 13-9-2016.
 * The anion agent, has the utility function -Water + Base
 * Given a state, has its own utility,
 * And point to indifference curve function
 * Ensures that the new offer is [0,1]
 */
public class Anion extends Agent {

    @Override
    public double utility(State offer){
        double value;
        value = exp(offer.getBase() - offer.getWater())/exp(1);
        return value;
    }

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

        //0 = x -y +log(utility)
        //a = 1; b = -1; c = log(u)+1
//        System.out.println("1st Cor: " + first_cor + " 2nd " + second_cor + " utility " + this.desirableUtility);
        double[] xy = lineToPoint(1,-1,log(this.desirableUtility)+1,first_cor, second_cor);
        //double newWater = 0.5 * (-(log(this.desirableUtility) + 1) + first_cor + second_cor);
        //double newBase =0.5 * ((log(this.desirableUtility) + 1) + first_cor + second_cor);

        double newWater = xy[0];
        double newBase = xy[1];
//        System.out.println("water = "+newWater+" base = "+newBase);
//        System.out.println("water2 = "+newWater2+" base2 = "+newBase2);


        //y = x +log(utility)
        //x = y -log(utility)

        xPlusOne.setWater(newWater);
        xPlusOne.setBase(newBase);
        xPlusOne.setAcid(min(max(x.getAcid(),0),1));
        return xPlusOne;
    }

    @Override
    public State pointWithinRange(State x){
        if(x.getWater() <0){
            x.setWater(0);
            x.setBase(x.getWater()+log(this.desirableUtility)+1);
        }else if (x.getBase() <0){
            x.setBase(0);
            x.setWater(x.getBase() - (log(this.desirableUtility)+1));
        }else if(x.getWater() >1){
            x.setWater(1);
            x.setBase(x.getWater() +(log(this.desirableUtility)+1));
        }else if (x.getBase()>1){
            x.setBase(1);
            x.setWater(x.getBase()- (log(this.desirableUtility)+1));
        }

        return x;
    }

    Anion(String name){
        super(name);
    }




}
