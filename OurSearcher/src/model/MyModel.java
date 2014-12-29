package model;

import java.util.ArrayList;
import java.util.Observable;

import tasks.Task;
import model.algorithm.Action;
import model.algorithm.Astar;
import model.algorithm.H_functions;
import model.algorithm.SearchDomain;
import model.algorithm.Searcher;
import model.domains.*;

public class MyModel extends Observable implements Model {
	
	private SearchDomain domain;
	private Searcher algorithm;
	private H_functions heuristic;
	private SearchAlgorithmsFactory algorithmsFactory;
	private HeuristicFactory heurisitecFactory;
	private Solution solution;
	private SolutionManager solutionManager;
	
	public MyModel()
	{
		algorithmsFactory = new SearchAlgorithmsFactory();
		heurisitecFactory = new HeuristicFactory();
		solutionManager = SolutionManager.getInstance();
	}

	@Override
	public void selectDomain(String args) {

		String[] arr = args.split(":");
		
		String domainName = arr[0];
		
		if (arr.length>1){
			String domainArgs = arr[1];
			this.domain = DomainFactory.createDomain(domainName);
			this.domain.init(domainArgs);
		}
		else {
		this.domain = DomainFactory.createDomain(domainName);
		this.domain.init();
		}
	}

	@Override
	public void selectAlgorithm(String algorithmName) {
		algorithm = algorithmsFactory.createAlgorithm(algorithmName);
	}
	
	public void selectHeuristic(String heuristicName){
		heuristic = heurisitecFactory.createHeuristic(heuristicName);
		if (algorithm.getClass() == Astar.class)
			((Astar)algorithm).setHeuristic(heuristic);
	}

	@Override
	public void solveDomain() {	
		String problemDescription = domain.getProblemDescription();
		this.solution = solutionManager.getSolution(problemDescription);
		
		if (solution != null)
			System.out.println("Solution Exists in file");
		    
		
		if (solution == null) {		
			ArrayList<Action> actions = algorithm.search(domain);
			solution = new Solution();
			solution.setActions(actions);
			solutionManager.addSolution(solution);
		}
		
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public Solution getSolution() {
		return solution;
	}

	@Override
	public void doTask() {
		solveDomain();
	}
}
