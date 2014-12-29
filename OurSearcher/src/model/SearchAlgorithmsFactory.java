package model;

import java.util.HashMap;

import model.algorithm.*;

public class SearchAlgorithmsFactory {
	
	private HashMap<String, AlgorithmCreator> algorithms;
	
	public SearchAlgorithmsFactory()
	{
		algorithms = new HashMap<String, AlgorithmCreator>();
		algorithms.put("BFS", new BFSCreator());
	 	algorithms.put("AStar", new AStarCreator());
	}
	
	public Searcher createAlgorithm(String algorithmName)
	{
		AlgorithmCreator creator = algorithms.get(algorithmName);
		Searcher searcher = null;
		if (creator != null)  {
			searcher = creator.create();			
		}
		return searcher;
	}
	
	private interface AlgorithmCreator
	{
		Searcher create();
	}
	
	private class BFSCreator implements AlgorithmCreator
	{
		@Override
		public Searcher create() {
			return new BFS();
		}		
	}
	
	private class AStarCreator implements AlgorithmCreator
	{
		@Override
		public Searcher create() {
			return new Astar();
		}		
	}

}
