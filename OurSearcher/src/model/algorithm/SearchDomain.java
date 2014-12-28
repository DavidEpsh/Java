package model.algorithm;

import java.util.HashMap;

// the expected functionality from a search problem 
public interface SearchDomain {

	// this functoin ......
	State getStartState();
	State getGoalState();
	HashMap<Action,State> getAllPossibleMoves(State current);
	void init(String args);
	void init();	
	String getProblemDescription();
}
