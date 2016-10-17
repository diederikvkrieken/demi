package Demi;

import static java.lang.Math.abs;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */
public class Controller {

    private Model mod;
    private boolean isConverge = false;
    private double tolerance = 0.1;
    //int n_agents=4;
    int nRounds = 30;
    public Controller(Model model){
        this.mod = model;
    }

    public void run(int t) {
        //Start new proposal round
        int propose_agent = t % mod.getn_agents();
        String name = mod.getnameAgents(propose_agent);

        for (Agent agent : mod.getAgents()) {
            /*
            * Import here is to decide whether to update the current belief of the agent
            * Feasible?
            * We decide on a simplified world where no change occurs
            * */
            if(agent.getPrevOffer().size()>4){
                System.out.println(mod.toString());
                System.out.println(t);
                System.out.println(agent.getPrevOffer().size());
            }
            if (agent.getName().equals(name)) {
                System.out.println("Proposal by agent: " + name);
                System.out.println("The " + name + " Concedes");
                agent.concessionStrategy(t);
                System.out.println("The " + name + " weights");
                State weight = agent.calculateWeight(mod, t);
                System.out.println("The " + " weights is "+ weight.toString());
                State proposal = agent.pointOnConcessionLine(weight);
                System.out.println(proposal.toString());
                mod.propose(agent, proposal, t);
                System.out.println("Proposal is: " + proposal);

            } else {
                System.out.println("Other agent update "+agent.getName());
                State x = agent.getOffer();
                agent.addOffer(x);
                mod.propose(agent, x, t);
//                System.out.println(agent.getName());
//                System.out.println(agent.getOffer().toString());
//                System.out.println(agent.getPrevBestOffer(t));
//                if (agent.utility(agent.getOffer()) > agent.utility(agent.getPrevBestOffer(t))) {
//                    agent.setPrevBestOffer(t,agent.getOffer());
//                }
            }

            //agent.observe(mod.getCurrentState());

        }
        double maxdistance = 0;
        for (Agent a:mod.getAgents()) {
            double distance = calculateDistance(a.getOffer(), a.getCurrentWeight());
            if (distance > maxdistance){
                maxdistance = distance;
            }
        }
        if (maxdistance < tolerance){
            isConverge = true;
        }
    }

    private double calculateDistance(State offer, State currentWeight) {
        double acid = abs(offer.getAcid() - currentWeight.getAcid());
        double base = abs(offer.getBase() - currentWeight.getBase());
        double water = abs(offer.getWater() - currentWeight.getWater());
        return(acid +base + water);
    }
//
//        //check offers
////        int deal = checkOffers();
////        if (deal == 3){
//            System.out.println("WE HAVE AN AGREEMENT");;
////        }
//    }


    public void runSimulation(){
        //until
        for (Agent a:mod.getAgents()) {
            a.calculateWeight(mod, 0);

        }
        int i =0;
        while(!isConverge && i < nRounds) {
            System.out.println("Running Simulation " + i);
            run(i);
            i++;
            mod.newRound(i);
        }


        System.out.println("simulation run");
    }

    public int checkOffers(){
        int deal = 0;
        double base = 0;
        double acid = 0;
        double water = 0;
        for (Agent agent : mod.getAgents() ) {
            acid += agent.getOffer().getAcid();
            base += agent.getOffer().getBase();
            water += agent.getOffer().getWater();
        }
        if (acid <= mod.getCurrentState().getAcid()){
            System.out.println("Acid agreement achieved");
            System.out.println("Acid = "+ Double.toString(mod.getCurrentState().getAcid())+", agreement = "+Double.toString(acid));
            deal++;
        }
        if (base <= mod.getCurrentState().getBase()){
            System.out.println("Base agreement achieved");
            System.out.println("Base = "+ Double.toString(mod.getCurrentState().getBase())+", agreement = "+Double.toString(base));
            deal++;
        }
        if (water >= mod.getCurrentState().getWater()){
            System.out.println("Water agreement achieved");
            System.out.println("Water = "+ Double.toString(mod.getCurrentState().getWater())+", agreement = "+Double.toString(water));
            deal++;
        }
        return deal;

    }

}
