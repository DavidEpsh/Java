package model.algorithm;

public abstract class State implements Comparable<State>{
	// the string representation of the state
	
	public Action cameWithAction;

	public State cameFrom;
	public double pathPrice;
	
	// what is the state's evaluation with respect to the goal
	public abstract int getEvaluation(State goal);

	@Override
	public boolean equals(Object o){
		return this.toString().equals(o.toString());
	}

	public double getPrice(){
		return this.pathPrice;
	}

	public void setCameFrom(State state) {
		this.cameFrom = state;
		
	}

	public void setCameWithAction(Action a) {
		this.cameWithAction = a;
		
	}

	public void setPrice(double newPathPrice) {
		this.pathPrice = newPathPrice;
		
	}
}
