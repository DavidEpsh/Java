package model.domains;

import model.algorithm.H_functions;
import model.algorithm.State;
import java.lang.Math;
public class MazeHeuristic implements H_functions {

	@Override
	public double h_function(State current, State goal) {
		MazeGameState mazeCurrent = (MazeGameState)current;
		MazeGameState mazeGoal = (MazeGameState)goal;
		
		int currentX = mazeCurrent.getX();
		int currentY = mazeCurrent.getY();
		int goalX = mazeGoal.getX();
		int goalY = mazeGoal.getY();
		
		
		double answer = Math.sqrt((double)(((currentX - goalX)*(currentX - goalX)) + ((currentY - goalY)*(currentY - goalY))));
		return answer;
		
		
		
		
	}

}
