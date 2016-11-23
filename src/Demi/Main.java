package Demi;

public class Main {

    public static void main(String[] args)
    {
        //Set up model, which stores agents and offers

        //Settings, 1 for normal reactive, 2 for nonreactive, 3 for only mixbed reactive
        int method = 1;
        //Setting for Mixbed water $l$, no value results in 1.
        int water_ratio = 10;

        /*Run the model multiple times for different reservation curve values
        * These are set in the Settings File
        */
        for (int i = 1; i <= 13; i++) {
            //Model mod = new Model();
            Model mod = new Model(i, 1);
            //Algorithm based on Zheng etal 2015.
            Controller ctrl = new Controller(mod);
            ctrl.runSimulation();
        }

    }


}
