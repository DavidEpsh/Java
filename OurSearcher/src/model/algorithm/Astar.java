package model.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Astar extends HeuristicSearchers {

	
	public Astar(H_functions heuristic ) {
		super(heuristic);
	}
	
	public Astar(){
		super();
	}
	

	@Override
	public ArrayList<Action> search(SearchDomain domain) {
		HashMap <Action,State> successors = new HashMap<Action, State>();
		PriorityQueue<State> openSet = new PriorityQueue<State>();
		ArrayList<State> closedSet = new ArrayList<State>();
		
		openSet.add(domain.getStartState());	
		
		while(!openSet.isEmpty())
		{
			State current = openSet.poll();
			
			if(current.equals(domain.getGoalState()))
			{
				ArrayList<Action> actions = generatePathToGoal(current);
				return actions;
			}
			closedSet.add(current);
			successors = domain.getAllPossibleMoves(current);
			
			for(Action currentAction : successors.keySet())
			{
				State nextState = successors.get(currentAction);
				double newPathPrice = current.getPrice() + currentAction.getPrice();

			
			
			if(closedSet.contains(nextState))
			    {
                   continue;				
		    	}
			if(!openSet.contains(nextState) || newPathPrice < nextState.getPrice())
			{
				nextState.setCameFrom(current);
				nextState.setCameWithAction(currentAction);
				nextState.setPrice(newPathPrice + heuristic.h_function(current, domain.getGoalState()));
				if(!openSet.contains(nextState))
				{
					openSet.add(nextState);
				}
			}
			
			}	
		}
			
			
		
		
		
		return null;
	}
	
	private ArrayList<Action> generatePathToGoal(State state) {

		ArrayList<Action> actions = new ArrayList<Action>();
		
		while (state.cameFrom != null)
		{
			actions.add(0, state.cameWithAction);
			state = state.cameFrom;	
		}
		
		return actions;
	}
	

}
