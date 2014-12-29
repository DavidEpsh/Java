package model;

import java.util.HashMap;


import model.algorithm.H_functions;

import model.domains.*;

public class HeuristicFactory {
	private HashMap<String , HeuristicCreator> heuristics;
	
	public HeuristicFactory() {
		heuristics = new HashMap<String , HeuristicCreator>();
		heuristics.put("Maze", new MazeCreator());
		heuristics.put("8puzzle", new EightPuzzleCreator());
	}
	
	public H_functions createHeuristic(String name)
	{
		HeuristicCreator creator = heuristics.get(name);
	H_functions huristic = null;
	if (creator != null)  {
		huristic = creator.create();			
	}
	return huristic;
	}
	
	
	private interface HeuristicCreator
	{
		H_functions create();
	}
	
	private class MazeCreator implements HeuristicCreator
	{
		@Override
		public H_functions create() {
			
			return new MazeHeuristic();
		}
	}
		
	private class EightPuzzleCreator implements HeuristicCreator
	{
			@Override
			public H_functions create() {
				
				return new EightPuzzleHeuristic();
			}
			
		
	}	

}
