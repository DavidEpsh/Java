package model;

import java.util.Observer;

import tasks.Task;


public interface Model extends Task {

	void selectDomain(String domainName);
	
	void selectAlgorithm(String algorithmName);
	
	void selectHeuristic(String heuristicName);
	
	void solveDomain();
	
	Solution getSolution();
	
	void addObserver(Observer o);
	
}
