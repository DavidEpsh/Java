package model.domains;

import model.algorithm.*;

public class MazeGameState  extends State{
	
	private int x,y;
	
	public MazeGameState(int x, int y) {
		super();
		
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}
	@Override
	public int getEvaluation(State goal) {

		MazeGameState currGoal = (MazeGameState)goal;
		int s = currGoal.getX() - this.x;
		int t = currGoal.getY() - this.y;
		
		if (s<t)
			return t;
		else
			return s;
	}
	@Override 
	public String toString(){ 
		return (Integer.toString(this.x) + ","  + Integer.toString(this.y));
		
	}

	@Override
	public int compareTo(State o) {
		return pitagoras(this)- pitagoras((MazeGameState)(o))   ;
	}
	public int pitagoras(MazeGameState x)
	{
			return pitagoras(getX(), getY());
	}
	
	public int pitagoras(int x , int y){	
		return x*x+y*y;
	}
	
	

}
