package Demi;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */
public class Controller {

    private Model mod;
    int n_agents=3;

    public Controller(Model model){
        this.mod = model;
    }

    public void run(int t){
        //Start new proposal round
        int propose_agent = t % mod.getn_agents();
        String name = mod.getnameAgents(propose_agent);

        for (Agent agent : mod.getAgents() ) {
            /*
            * Import here is to decide whether to update the current belief of the agent
            * Feasible?
            * We decide on a simplified world where no change occurs
            * */
            if (agent.getName().equals(name)){
                System.out.println("Proposal by agent: "+ name);
                agent.concessionStrategy(t);
                State weight = agent.calculateWeight(mod, t);
                State proposal = agent.calculateProposal(weight);
                mod.propose(agent,proposal,t);
                System.out.println("Proposal is: "+proposal);

            }else{
                System.out.println("Other agent update");
                State x = agent.getOffer();
                agent.addOffer(x);
                System.out.println(agent.getName());
                System.out.println(agent.getOffer().toString());
                System.out.println(agent.getPrevBestOffer());
                if (agent.utility(agent.getOffer())> agent.utility(agent.getPrevBestOffer())){
                    agent.setPrevBestOffer(agent.getOffer());
                }
            }
            agent.observe(mod.getCurrentState());

        }

        //check offers
//        int deal = checkOffers();
//        if (deal == 3){
            System.out.println("WE HAVE AN AGREEMENT");;
//        }
    }
    public void runSimulation(){
        //until
        for (int i = 0; i < 3; i++) {
            System.out.println("Running Simulation" +i);
            run(i);
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
