package Demi;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 * The simulation consists of a controller, and a model.
 * The model consists of the agent, which can be any of 4 types (extend Agent)
 * And the Offers, which are different possible states.
 *
 * The Controller controls the behaviour of the model and the possible actions
 * This algorithm is Based on the paper of Zheng et.al 2016
 */

public class Main {

    public static void main(String[] args)
    {
        //Set up model, which stores agents and offers

        //Settings, 1 for normal reactive, 2 for nonreactive, 3 for only mixbed reactive
        int method = 2;
        //Setting for Mixbed water $l$, no value results in 1.
        int water_ratio = 1;

        /*Run the model multiple times for different reservation curve values
        * These are set in the Settings File
        */
        for (int i = 1; i <= 13; i++) {
            //IF no loop, use the default method.
            //Model mod = new Model();
            //ctrl.runSimulation();

            Model mod = new Model(i, water_ratio);
            //Algorithm based on Zheng etal 2015.
            Controller ctrl = new Controller(mod);
            ctrl.runSimulation(method);
        }

    }


}
