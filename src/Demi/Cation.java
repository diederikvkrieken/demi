package Demi;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */
public class Cation extends Agent {
    /*
    * Anions contains 6 filters
    * */

//      1. Knowledge of anion head about the sub-agents:
//          {A1, ..., A6} can process a amount of water
//          {A1, ..., A6} needs to be cleaned after b water
//          {A1, ..., A6} has filtered c amount of water
//          {A1, ..., A6} needs d acid to clean
//          {A1, ..., A6} needs e time to clean
//
//      2. Currently x amount of water being filtered
//      3. Currently Z \in {A1, ..., A6} filter being used for water filtering
//      4. Currently Y \in {A1, ..., A6} filter being used for cleaning
//      5. Currently w amount of acid being used for cleaning

    private double[] a = new double[6];
    private double[] b = new double[6];
    private double[] c = new double[6];
    private double[] d = new double[6];
    private double[] e = new double[6];
    private int[] z = new int[6]; //filter being used for water cleaning, 1 or zero when not being used
    private int[] y = new int[6]; //filter being cleaned, 1 or 0 when not being used

    private double x,w;// knowledge about the water and acid being used

    @Override
    public double utility(State offer){
        double value = 0.0;
        value = offer.getBase() - offer.getWater();
        return value;
    }


}
