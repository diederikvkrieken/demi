package Demi;

public class Main {

    public static void main(String[] args)
    {
        //Set up model, which stores agents and offers
        Model mod = new Model();

        //Algorithm based on Zheng etal 2015.
        Controller ctrl = new Controller(mod);

        ctrl.runSimulation();

    }


}
