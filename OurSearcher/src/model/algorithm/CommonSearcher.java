package model.algorithm;

import java.util.LinkedList;
import java.util.Queue;

public abstract class CommonSearcher implements Searcher{

	protected Queue<State> openList = new LinkedList<State>();
	protected Queue<State> closedList = new LinkedList<State>();
	// place all common things to all searchers here
}
