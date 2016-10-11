package Demi;

public class Main {

    public static void main(String[] args)
    {

        Model mod = new Model();

        Controller ctrl = new Controller(mod);

        ctrl.runSimulation();

//        State s = new State();
//        s.setWater(0.3);
//        s.setBase(0.3);
//        s.setAcid(0.3);
//        Anion a = new Anion();
//        a.setUtility(0.4415);
//        State s2 = a.pointOnConcessionLine(s);
//        System.out.println(s2.toString());



    }


}
