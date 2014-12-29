package model.domains;

import model.MyModel;
import model.algorithm.*;

public class EightPuzzleState extends State {
	int[][] state;
	int currZeroX,currZeroY;
	
	
	public EightPuzzleState() {     // Default Constructor when puzzle not given
		this.state = new int[3][3];
		int [] tempIntegers;
		tempIntegers = new int[]{1,2,3,4,5,6,7,8};
		
		for ( int i=0 ; i<8 ; i++){
			int rX = (int)(Math.random()*3);
			int rY = (int)(Math.random()*3);
			
			if (this.state[rX][rY] == 0)
				state[rX][rY]=tempIntegers[i];
			else
				i--;
		}
	}
	
	public EightPuzzleState(String args){       // Constructor when puzzle is given
		String[] EightPuzzleProperties = args.split(",");
		this.state = new int[3][3];
		
		int movingIndex=0;
		
		for (int i=0 ; i<3 ; i++){
			for (int j=0 ; j<3 ; j++){
				this.state[i][j] = Integer.parseInt(EightPuzzleProperties[movingIndex]);
				movingIndex++;
			}
		}
	}
	public int[][] getState(){
		return this.state;
	}
	
	@Override
	public int getEvaluation(State goal) {
		if(goal == null)
			goal = new EightPuzzleState("1,2,3,4,5,6,7,8,0");
		
		EightPuzzleState goalState = (EightPuzzleState) goal;
			int distance=0,tempInt;
			
			for(int x=0 ; x<3 ; x++){
				for (int y=0 ; y<3 ; y++){
					if(this.state[x][y]==0)
						continue;
					
					tempInt = this.state[x][y];
					
					for (int i=0 ; i<3 ; i++){
						for (int j=0 ; j<3 ; j++){
							if (tempInt == goalState.getPoint(i, j))
								distance += Math.abs(i-x)+Math.abs(y-j);
						}
					}
				}
			}
		
/*		int distance = 0;	
		String[] EightPuzzleProperties = goal.toString().split(",");
			int [] temp = new int[9];
			for (int i=0 ; i<9 ; i++){
					temp[i] = Integer.parseInt(EightPuzzleProperties[i]);
				}
			
			for(int i=0 ;   i < 9 ; i++){
				for (int j=i ; j<9 ; j++){
					if(temp[j]>temp[i]&&temp[i]!=0)
						distance++;
				}
			}
	*/		
			return distance;
		}
	
	public void setCurrZeroLocation(){
		
		for (int i=0 ; i<3 ; i++){
			for (int j=0 ; j<3 ; j++){
				if (this.state[i][j] == 0)
				{
					this.currZeroX = i;
					this.currZeroY = j;
				}
			}
		}
	}
	
	public int getCurrZeroY(){
		setCurrZeroLocation();
		return this.currZeroY;
	}
	
	public int getCurrZeroX(){
		setCurrZeroLocation();
		return this.currZeroX;
	}
	
	@Override
	public String toString() {
		String ret = "";
		
		for(int i=0 ; i<3 ; i++){
			for (int j=0 ; j<3 ; j++){
				if (i == 2 && j ==2 ){
					ret += this.state[i][j];
					continue;
				}
				ret += this.state[i][j]+",";
			}
		}
		return  ret; 
	}
	
	public int getPoint(int x,int y){
		return this.state[x][y];
	}
	
	public void setPoint(int x, int y, int newInt){
		this.state[x][y] = newInt;
	}
	
	public String getNewString(int x, int y, int newX, int newY){
		EightPuzzleState newState;
		newState = new EightPuzzleState();
		
		for(int i=0 ; i<3 ; i++){
			for (int j=0 ; j<3 ; j++)
				newState.setPoint(i,j,this.state[i][j]);
		}
			int temp= newState.getPoint(x,y);
			newState.setPoint(x, y, this.state[newX][newY]);
			newState.setPoint(newX,newY,temp);
			
			return ""+newState.toString();
	}

	@Override
	public int compareTo(State o) {
		 
		 return this.getEvaluation(null) - o.getEvaluation(null);

	}
	
	
}
