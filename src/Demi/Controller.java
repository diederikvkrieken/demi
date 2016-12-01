package Demi;

import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by Diederik van Krieken on 13-9-2016.
 * The controller runs the negotiation of the agents.
 *
 * We decide on a simplified world where no change occurs
 *
 *
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

    void runSimulation(int method){
        int i =1;
        //For each agent, calculate the average of the
        for (Agent a:mod.getAgents()) {
            a.calculateWeight(mod, i);
        }
        while(!isConverge && i < nRounds) {
            System.out.println("Round #: "+i);
            mod.newRound(i);
            run(i, method);
            i++;
        }

        System.out.println("simulation run, rounds = "+i+" isConverge = "+isConverge);
        try {
            mod.writeToCSV(method);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void run(int t, int method) {
        //Start new proposal round
        //Determine agent to propose
        int propose_agent = t % mod.getN_agents();
        String name = mod.getNameAgents(propose_agent);

        //Store the values for the CSV writes
        ArrayList<Double> concessionArray = new ArrayList<>();
        ArrayList<Double> distanceAvgArray = new ArrayList<>();
        ArrayList<Double> desireArray = new ArrayList<>();

        for (Agent agent : mod.getAgents()) {
            // Two options, if it is agent to do concession, make offer
            // Else update the agent.

            //If it is the agent which is supposed to create the offer
            if (agent.getName().equals(name)) {
                System.out.println("Proposal by agent: " + name);

                //Start reactive concession strategy
                // Calculate reactive concession for each agent
                double deltaU[] = new double[mod.getN_agents()+1];
                int i = 0;
                double concession = Double.POSITIVE_INFINITY;

                // Calculate concession
                // This is dependent on the method called.
                // 1 for normal reactive, 2 for nonreactive, 3 for only mixbed reactive
                boolean nonreactive = false;
                boolean reactive_mixbed = false;
                if (method == 2){
                    nonreactive = true;
                }else if (method == 3){
                    nonreactive = true;
                    reactive_mixbed = true;
                }

                //Calculate the concession
                for (Agent ag : mod.getAgents()) {
                    // Nonreactive can be true, meaning the statement is always true.

                    // Some tricks for reactive Mixbed
                    if (!ag.getName().equals("Mixbed")&& reactive_mixbed){
                        nonreactive = false;
                    }

                    //Calculate nonreactive if offer >= minimum
                    if ((agent.utility(ag.getOffer()) >= agent.getMinimumUtility())||nonreactive) {
                        deltaU[i] = agent.nonreactiveConcessionStrategyReturn(t);
                        i++;
                    } else {
                        //calculate reactive if offer < minimum
                        deltaU[i] = agent.reactiveConcessionStrategy(t, mod.getAgentNumber(ag), mod.getStandingOffer(ag), mod.getRecentOffers(0).get(mod.getAgentNumber(ag)), mod.getRecentOffers(t - 1).get(mod.getAgentNumber(agent)));
                        i++;
                    }
                    if (!ag.getName().equals("Mixbed")&& reactive_mixbed){
                        nonreactive = true;
                    }
                }
                // Get the minimal concession.
                deltaU[i] = agent.nonreactiveConcessionStrategyReturn(t);
                for (int j = 0; j < mod.getN_agents(); j++) {
                    if (deltaU[j] < concession) {
                        concession = deltaU[j];
                    }
                }

                // Determining s_i
                agent.setDesirableUtility(concession);

                //Calculating w
                State weight = agent.calculateWeight(mod, t);

                State proposal;
                //Projection P on indifference curve if needed
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

                //Save values for CSV Files
                desireArray.add(agent.getDesirableUtility());
                concessionArray.add(concession);

            } else {
                System.out.println("\t Other agent update "+agent.getName());

                //Add last offer tot the new list
                State x = agent.getOffer();
                State of = mod.getStandingOffer(propose_agent);
                agent.addCurrentOffer(x);
                mod.propose(agent, x, t);
                //update the weighted average
                agent.calculateWeight(mod, t);

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

        //For CSV files
        mod.addDistance(maxDistance);
        mod.addConcession(t-1, concessionArray);
        mod.addDistanceAvg(t-1, distanceAvgArray);
        mod.addDesire(t-1, desireArray);
    }

}
