package Demi;

public class Main {

    public static void main(String[] args)
    {
        //Set up model, which stores agents and offers
        for (int i = 1; i < 14; i++) {
            Model mod = new Model(i);

            //Algorithm based on Zheng etal 2015.
            Controller ctrl = new Controller(mod);

            ctrl.runSimulation();

        }

    }


}
