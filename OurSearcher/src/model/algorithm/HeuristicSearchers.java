package model.algorithm;

public abstract class HeuristicSearchers extends CommonSearcher {

  	H_functions  heuristic;
  	
  	public HeuristicSearchers(H_functions  heuristic) {
  		
  		this.heuristic =  heuristic;
	}
  	public HeuristicSearchers(){
  	}
  	
  	public void setHeuristic(H_functions  heuristic) {
  		
  		this.heuristic =  heuristic;
	}
}
	