package Demi;

/**
 * Created by diederik.van.krieken on 13-9-2016.
 */
public class Settings {

    private String[] names = {"Anion", "Cathion", "Mixbed", "Neut"};

    public Agent[] initializeAgents(){
        //initialize 4 agents
        Agent[] agents = new Agent[4];

        for (int i = 0; i < 4; i++) {
            agents[i] = new Agent(names[i]);
        }
        return agents;
    }

    //AgentDef = named tuple('AgentDef', ['name', 'res_priority']);

    //CSV_LOG_FILENAME = 'transactions.csv';

           //Name of the strategy class to use (which reside in datamodel/strategy).
    //STRATEGY = 'plain'

            //Definition of resources in the system.
    //RESOURCES = ['oil', 'wood', 'iron']
            //RESOURCES = ['oil', 'wood', 'iron', 'cynism', 'sarcasm', 'irony']

            //How to judge the value of resources based on agents' priorities, this is
            // used to compare how well agents are doing. First value is for priority
    //one, the second for priority two, etc.
    //RESOURCE_VALUATIONS = [10, 6, 4]
            //RESOURCE_VALUATIONS = [24, 12, 6, 3, 2, 1]

            //The total amount of resources in the system is AMOUNT_PER_RESOURCE * len(RESOURCES).
    //AMOUNT_PER_RESOURCE = 210

            //Definition of agents and their priorities; 1 is the highest priority.
    //AGENTS = [AgentDef(name='aad', res_priority={'oil': 1, 'wood': 2, 'iron': 3}),
    //AgentDef(name='henk', res_priority={'oil': 2, 'wood': 3, 'iron': 1}),
    //AgentDef(name='joop', res_priority={'oil': 2, 'wood': 1, 'iron': 3})]

    public Settings() {

    }
}
