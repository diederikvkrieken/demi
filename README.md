# Demi water production negotiation
In this thesis we study how negotiation in a Multi-Agent System (MAS) can be used in business and production context.   
##Synopsis
Firstly, an overview of production and manufacturing is given, after which the possible agent solutions are discussed. Here we determine the specific requirement for business if one where to implement a MAS in a process to optimize. 

Afterwards we cover the different possible negotiation techniques that can be used for the agents to communicate. Here a knowledge gap in multi-issue multi-lateral negotiation, with private utility functions is found. Using the alternation projection method to negotiate, process optimization should be possible. 

This is tested with an usecase, and using the reactive compared to the non-reactive concession strategy the optimal concession strategy is discussed. It is found that the reactive concession strategy is not as well performing as the non-reactive to the optimal (Nash and Pareto) solution, since it can stall while the agreement-zone is non-empty. However, if a single agents uses the reactive strategy, the system performs well.  We conclude with a possible solution for the initial problem and future research steps to be performed.

##Implementation
The simulation has been implemented following the algorithm  by Zheng et.al. 2016. In our model the agent negotiate, depending on the for the others private utility function.
There are 3 options, all agents reactive, all agents nonreactive or only the Mixbed agent reactive. The Mixbed is the most ``important'' agent since it is responsible for the final output. 

The Model Class consists of the Agents and Offers. The class Agent is extended by Anion, Cation, Mixbed and Neut. The offers are part of the Class State, which consist of a normalized value of the acid, base and water.
 
The Control Class runs the simulation, and the algorithm.

##Installation

Run main in your Java IDE after branching on your own computer.
Ensure that the file director, which is saved in the Model Class is correct.
The Matlab code is attached and method specific.

##Tests
There are three possible concession possibilities. In main these correspond to 1, 2, 3. The other option is the water ratio for the mixbed, which can be adjusted in the main file as such:
(l*water):acid:base
##License

A short snippet describing the license (MIT, Apache, etc.)

