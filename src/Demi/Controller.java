package Demi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.log;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */
public class Controller {

    private Model mod;
    private boolean isConverge = false;
    private double tolerance = 0.01;
    //int n_agents=4;
    int nRounds = 100;
    public Controller(Model model){
        this.mod = model;
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
        for (Agent a:mod.getAgents()) {
            a.calculateWeight(mod, 0);
        }
        int i =1;
        while(!isConverge && i < nRounds) {
            System.out.println("Running Simulation " + i);
            mod.newRound(i);
            run(i);
            i++;
        }


        System.out.println("simulation run, rounds = "+i+" isConverge = "+isConverge);
        try {
            mod.writeToCSV();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mod.writeToCSVdistance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mod.writeToCSVconcession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run(int t) {
        //Start new proposal round
        /*Determine agent to propose*/
        int propose_agent = t % mod.getn_agents();
        String name = mod.getnameAgents(propose_agent);
        ArrayList<Double> concessionArray = new ArrayList<>();

        /*For each agent j in agents do:*/
        for (Agent agent : mod.getAgents()) {
            /*
            * We decide on a simplified world where no change occurs
            * */

            //IF it is the agent which is supposed to create the offer
            //Do the thing below
            if (agent.getName().equals(name)) {
                System.out.println("Proposal by agent: " + name);
                //Start reactive concession strategy
                //First calculate the non reactive concession for t
                //agent.nonreactiveConcessionStrategy(t);


                //Now calculate reactive concession for each agent
                double deltaU[] = new double[mod.getn_agents()];
                int i = 0;
                double concession = Double.POSITIVE_INFINITY;
                //List<Agent> Gamma = new ArrayList<Agent>();

                for (Agent ag : mod.getAgents()) {
                    //Skip own agent???
                    // if (!ag.getName().equals(name)){}
                    //REMOVED THE EMPTY SET CHECK.
//                    if (agent.getPrevBestOffer(mod.getAgentNumber(ag)) == null) {
//                        deltaU[i] = agent.nonreactiveConcessionStrategyReturn(t);
//                        i++;
//                        System.out.println("nonreactive because set is empty");
//                        //System.out.println(agent.getPrevBestOffer(mod.getAgentNumber(ag)));
//
//                    } else
                    if (agent.utility(ag.getOffer()) >= agent.getMinimumUtility()) {
                        deltaU[i] = agent.nonreactiveConcessionStrategyReturn(t);
                        i++;
                        System.out.println("nonreactive because offer is greater than minimum utiliy");
                        //System.out.println("Utility of offer = " + agent.utility(ag.getOffer()));

                    } else {
                        //Check for Gamma: Agents for which the offer is lower than the reservation utility at t.
                        //if (agent.utility(mod.getStandingOffer(ag)) <= agent.getMinimumUtility()) {
                            deltaU[i] = agent.reactiveConcessionStrategy(t, mod.getAgentNumber(ag), mod.getStandingOffer(ag), mod.getRecentOffers(0).get(mod.getAgentNumber(ag)), mod.getRecentOffers(t - 1).get(mod.getAgentNumber(agent)));
                            i++;
                            System.out.println(" reactive ");
                        //}
                    }
                }
                for (int j = 0; j < mod.getn_agents(); j++) {
                    System.out.println(deltaU[j]);
                    if (deltaU[j] < concession) {
                        concession = deltaU[j];
                    }
                }
                concessionArray.add(concession);


                // Determining s_i
                System.out.println("Concession: " + concession);
                agent.setDesirableUtility(concession);

                //Calculating w
                System.out.println("The " + name + " weights");
                State weight = agent.calculateWeight(mod, t);
                System.out.println("The " + " weights is " + weight.toString());

                State proposal;
                //Projection P on indifference curve
                System.out.println("agent(utility): "+agent.utility(weight)+" agent desirable = " +agent.getDesirableUtility());
                if (agent.utility(weight) <= agent.getDesirableUtility()) {
                    proposal = agent.pointOnConcessionLine(weight);
                } else{
                    proposal = weight;
                }

                //TODO ensure that proposal is range [0 1]

                proposal = agent.pointWithinRange(proposal);


                System.out.println("Proposal is "+proposal.toString());

                //Propose
                mod.propose(agent, proposal, t);
                agent.addOffer(proposal);
                System.out.println("Proposal is: " + proposal);
            } else {
                System.out.println("Other agent update "+agent.getName());

                //Add last offer tot the list
                State x = agent.getOffer();
                State of = mod.getStandingOffer(propose_agent);
                agent.addOffer(x);
                mod.propose(agent, x, t);

                //Update last best prev offer if necessary
                if (agent.utility(of) >= agent.utility(agent.getPrevBestOffer(propose_agent))){
                    agent.setPrevBestOffer(propose_agent, of);
                }
//                System.out.println(agent.getName());
//                System.out.println(agent.getOffer().toString());
//                System.out.println(agent.getPrevBestOffer(t));
//                if (agent.utility(agent.getOffer()) > agent.utility(agent.getPrevBestOffer(t))) {
//                    agent.setPrevBestOffer(t,agent.getOffer());
//                }
            }
            //System.out.println(a.getPrevOffer());
            //System.out.println(a.toString());

        }
        for (Agent a :mod.getAgents()) {
            a.setPrevOffer(mod.getRecentOffers(t));
        }
        double maxDistance = 0;
        for (Agent a:mod.getAgents()) {
            double distance = calculateDistance(a.getOffer(), a.getCurrentWeight());
            if (distance > maxDistance){
                maxDistance = distance;
            }
            //System.out.println("maxdistance = "+distance+"  "+maxDistance);
        }
        if (maxDistance < tolerance){
            isConverge = true;
        }
        mod.addDistance(maxDistance);
        //System.out.println("Concession array: "+concessionArray.toString());
        mod.addConcession(t-1, concessionArray);
    }


//    public int checkOffers(){
//        int deal = 0;
//        double base = 0;
//        double acid = 0;
//        double water = 0;
//        for (Agent agent : mod.getAgents() ) {
//            acid += agent.getOffer().getAcid();
//            base += agent.getOffer().getBase();
//            water += agent.getOffer().getWater();
//        }
//        if (acid <= mod.getCurrentState().getAcid()){
//            System.out.println("Acid agreement achieved");
//            System.out.println("Acid = "+ Double.toString(mod.getCurrentState().getAcid())+", agreement = "+Double.toString(acid));
//            deal++;
//        }
//        if (base <= mod.getCurrentState().getBase()){
//            System.out.println("Base agreement achieved");
//            System.out.println("Base = "+ Double.toString(mod.getCurrentState().getBase())+", agreement = "+Double.toString(base));
//            deal++;
//        }
//        if (water >= mod.getCurrentState().getWater()){
//            System.out.println("Water agreement achieved");
//            System.out.println("Water = "+ Double.toString(mod.getCurrentState().getWater())+", agreement = "+Double.toString(water));
//            deal++;
//        }
//        return deal;
//
//    }

}
