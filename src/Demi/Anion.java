package Demi;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */
public class Anion extends Agent {
    /*
    * Anions contains 6 filters
    * */
    @Override
    public double utility(int base, int acid, int water){
        double value = 0.0;
        value = base - water;
        return value;
    }


}
