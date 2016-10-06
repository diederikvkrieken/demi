package Demi;

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

    @Override
    public double utility(State offer){
        double value = 0.0;
        value = offer.getBase() - offer.getWater();
        return value;
    }




}
