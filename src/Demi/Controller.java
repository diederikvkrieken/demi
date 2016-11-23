package Demi;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */
public class Controller {

    private Model mod;
    private boolean isConverge = false;
    private double tolerance = 0.05;
    private int nRounds = 200;
    public Controller(Model model){
        this.mod = model;
    }


    private double calculateDistance(State offer, State currentWeight) {
        double acid = abs(offer.getAcid() - currentWeight.getAcid());
        double base = abs(offer.getBase() - currentWeight.getBase());
        double water = abs(offer.getWater() - currentWeight.getWater());
        return(acid +base + water);
    }

    public void runSimulation(int method){
        int i =1;
        //For each agent, calculate the average of the
        for (Agent a:mod.getAgents()) {
            a.calculateWeight(mod, i);
        }
        while(!isConverge && i < nRounds) {
            System.out.println("Round #: "+i);
            mod.newRound(i);
            run(method);
            i++;
        }
    }

    public void runSimulation(){
        int i =1;
        //Each agent calculate their average weight.
        for (Agent a:mod.getAgents()) {
            a.calculateWeight(mod, i);
        }
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
            mod.writeToCSVDistance();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mod.writeToCSVconcession();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mod.writeToCSVDistanceAvg();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mod.writeToCSVDesire();
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
        ArrayList<Double> distanceAvgArray = new ArrayList<>();
        ArrayList<Double> desireArray = new ArrayList<>();


        /*For each agent j in agents do:*/
        for (Agent agent : mod.getAgents()) {
            /*
            * We decide on a simplified world where no change occurs
            * */

            //IF it is the agent which is supposed to create the offer
            if (agent.getName().equals(name)) {
                System.out.println("Proposal by agent: " + name);
                //Start reactive concession strategy
                //Now calculate reactive concession for each agent
                double deltaU[] = new double[mod.getn_agents()+1];
                int i = 0;
                double concession = Double.POSITIVE_INFINITY;

                for (Agent ag : mod.getAgents()) {
                    if ((agent.utility(ag.getOffer()) >= agent.getMinimumUtility())||(!ag.getName().equals("Mixbed"))) {
                        //Calculate nonreactive if offer >= minumum
                        deltaU[i] = agent.nonreactiveConcessionStrategyReturn(t);
                        i++;
//                        System.out.println("nonreactive because offer is greater than minimum utiliy");
//                        System.out.println("Utility of offer = " + agent.utility(ag.getOffer())+" and minimum util = "+agent.getMinimumUtility());
                    } else {
                        //calculate reactive if offer < minimum
//                        System.out.println("reactive");
//                        System.out.println("agent: "+mod.getAgentNumber(ag)+" Standing "+ mod.getStandingOffer(ag)+" x0: "+ mod.getRecentOffers(0).get(mod.getAgentNumber(ag))+" Last " + mod.getRecentOffers(t - 1).get(mod.getAgentNumber(agent)));
                        deltaU[i] = agent.reactiveConcessionStrategy(t, mod.getAgentNumber(ag), mod.getStandingOffer(ag), mod.getRecentOffers(0).get(mod.getAgentNumber(ag)), mod.getRecentOffers(t - 1).get(mod.getAgentNumber(agent)));
                        i++;
//                        System.out.println(" reactive ");
                    }
                }
                deltaU[i] = agent.nonreactiveConcessionStrategyReturn(t);
                for (int j = 0; j < mod.getn_agents(); j++) {
//                    System.out.println(deltaU[j]);
                    if (deltaU[j] < concession) {
                        concession = deltaU[j];
                    }
                }

                // Determining s_i
//                System.out.println("Concession: " + concession);
                agent.setDesirableUtility(concession);

                //Calculating w
                State weight = agent.calculateWeight(mod, t);
//                System.out.println("The " + " weights is " + weight.toString());

                State proposal;
                //Projection P on indifference curve if needed
//                System.out.println("agent(utility): "+agent.utility(weight)+" agent desirable = " +agent.getDesirableUtility());
                if (agent.utility(weight) <= agent.getDesirableUtility()) {
                    proposal = agent.pointOnConcessionLine(weight);
                } else{
                    proposal = weight;
                }

                //Ensure that proposal is range [0 1]
                proposal = agent.pointWithinRange(proposal);


                System.out.println("Proposal is "+proposal.toString());

                //Propose
                mod.propose(agent, proposal, t);
                agent.addCurrentOffer(proposal);

                desireArray.add(agent.getDesirableUtility());
                concessionArray.add(concession);

            } else {
                System.out.println("\t Other agent update "+agent.getName());

                //Add last offer tot the new list
                State x = agent.getOffer();
                State of = mod.getStandingOffer(propose_agent);
                agent.addCurrentOffer(x);
                mod.propose(agent, x, t);

                //Update last best prev offer if necessary
                if (agent.utility(of) >= agent.utility(agent.getPrevBestOffer(propose_agent))){
                    agent.setPrevBestOffer(propose_agent, of);
                }
                desireArray.add(agent.getDesirableUtility());
            }
        }
        //Update the offer list for the agents
        for (Agent a :mod.getAgents()) {
            a.setPrevOffer(mod.getRecentOffers(t));
        }
        //Calculate distance
        double maxDistance = 0;
        for (Agent a:mod.getAgents()) {
            double distance = calculateDistance(a.getOffer(), a.getCurrentWeight());
            distanceAvgArray.add(distance);
            if (distance > maxDistance){
                maxDistance = distance;
            }
        }
        if (maxDistance < tolerance){
            isConverge = true;
        }

//        Agent at =  mod.getAgents()[2];
//        int wa = ((Mixbed)at).getWater_ratio();
//        System.out.println("Mixbed "+wa);

        //For CSV file
        mod.addDistance(maxDistance);
        mod.addConcession(t-1, concessionArray);
        mod.addDistanceAvg(t-1, distanceAvgArray);
        mod.addDesire(t-1, desireArray);
    }
}
