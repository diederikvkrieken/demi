package Demi;


public class Main {

    public static void main(String[] args)
    {
        Model mod = new Model();

        Controller ctrl = new Controller(mod);

        ctrl.runSimulation();



    }
}
