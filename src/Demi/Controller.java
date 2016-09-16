package Demi;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */
public class Controller {

    private Model mod;

    public Controller(Model model){
        this.mod = model;
    }
    public void run(int n){
        //Let every agent update it's belief
        for (Agent agent : mod.getAgents() ) {
            agent.observe(mod.getCurrentState());

        }
        //Let every agent generate new offer
        for (Agent agent : mod.getAgents() ) {
            agent.generateOffer(n);
        }

        //check offers
        int deal = checkOffers();
        if (deal == 2){
            System.out.println("WE HAVE AN AGREEMENT");;
        }




    }
    public void runSimulation(){
        //until
        for (int i = 0; i < 15; i++) {
            System.out.println("Running Simulation");
            run(i);
        }
        System.out.println("simulation run");
    }

    public int checkOffers(){
        int deal = 0;
        int base = 0;
        int acid = 0;
        int water = 0;
        for (Agent agent : mod.getAgents() ) {
            acid += agent.getOffer().getAcid();
            base += agent.getOffer().getBase();
            water += agent.getOffer().getWater();
        }
        if (acid <= mod.getCurrentState().getAcid()){
            System.out.println("Acid agreement achieved");
            System.out.println("Acid = "+ Integer.toString(mod.getCurrentState().getAcid())+", agreement = "+Integer.toString(acid));
            deal++;
        }
        if (base <= mod.getCurrentState().getBase()){
            System.out.println("Base agreement achieved");
            System.out.println("Base = "+ Integer.toString(mod.getCurrentState().getBase())+", agreement = "+Integer.toString(base));
            deal++;
        }
        if (water >= mod.getCurrentState().getWater()){
            System.out.println("Water agreement achieved");
            System.out.println("Water = "+ Integer.toString(mod.getCurrentState().getWater())+", agreement = "+Integer.toString(water));
            deal++;
        }
        return deal;

    }

}
