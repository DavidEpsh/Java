package view;

import java.util.Observable;
import java.util.Scanner;

import model.Solution;
import model.SolutionManager;
import model.ThreadManager;
import model.algorithm.Action;

public class MyConsoleView extends Observable implements View {

	private String action;
	
	@Override
	public void start() {
		System.out.println("Welcome to my project \nto create random maze input should be: SelectDomain Maze:5,5,10 (default: without :5,5,10) \n"
				+ "to create random 8puzzle, input should be: SelectDomain 8puzzle (user puzzle: SelectDomain 8puzzle:1,2,3,4,5,6,7,8,0)");
		this.action = "";
		Scanner scanner = new Scanner(System.in);
		do
		{
			System.out.print("Enter command: ");
			this.action = scanner.nextLine();
			
			if (!(this.action.equals("exit")))
			{
				this.setChanged();
				this.notifyObservers();
			}
			else
			{
				SolutionManager.getInstance().saveSolutionsInFile();
			}
		} while (!(this.action.equals("exit")));
		
		scanner.close();
	}

	@Override
	public void displayCurrentState() {
		
	}

	@Override
	public void displaySolution(Solution solution) {
		for(Action a : solution.getActions())
			System.out.println(a);
	}

	@Override
	public String getUserAction() {		
		return action;
	}

	@Override
	public void doTask() {
		this.start();
		
	}
	
	public void returnBool(Boolean b){
		System.out.println(b);
	}
}
