package model.domains;

import model.algorithm.H_functions;
import model.algorithm.State;

public class EightPuzzleHeuristic implements H_functions {

	@Override
	public double h_function(State current, State goal) {

				EightPuzzleState EightPuzzleCurrent = (EightPuzzleState)current;
				EightPuzzleState EightPuzzleGoal = (EightPuzzleState)goal;
				
				int distance=0,tempInt;
				
				for(int x=0 ; x<3 ; x++){
					for (int y=0 ; y<3 ; y++){
						if(EightPuzzleCurrent.getPoint(x,y)==0)
							continue;
						
						tempInt = EightPuzzleCurrent.getPoint(x,y);
						
						for (int i=0 ; i<3 ; i++){
							for (int j=0 ; j<3 ; j++){
								if (tempInt == EightPuzzleGoal.getPoint(i, j))
									distance += Math.abs(i-x)+Math.abs(y-j);
							}
							}
						}
					}
				
				return distance;
				}
				
				}
