package presenter;

import java.util.HashMap;

import tasks.TaskRunnable;
import model.*;

public class UserCommands {

	private HashMap<String, Command> commands = new HashMap<String,Command>();

	
	public UserCommands()
	{
		commands.put("SD", new SelectDomainCommand());
		commands.put("SA", new SelectAlgorithmCommand());
		commands.put("SH", new SelectHeuristicCommand());
		commands.put("SolveDomain", new SolveDomainCommand());
		commands.put("SIT", new SolveDomainInThread());
	}
	

/*	public void doCommand(Model model, String commandName, String args)
	{
		Command command = commands.get(commandName);
		if (command != null)
		{
			command.doCommand(model, args);
		}
		
	}
*/
	public Command selectCommand(String commandName)
	{
		Command command = commands.get(commandName);
		return command;
	}
	
	public interface Command
	{
		Model doCommand(Model model, String args);
	}
	
	
	private class SelectDomainCommand implements Command
	{
		@Override
		public Model doCommand(Model model, String args) {
			Model m = new MyModel();
			m.selectDomain(args);	
			return m;
		}		
	}
	
	private class SelectAlgorithmCommand implements Command
	{
		@Override
		public Model doCommand(Model model, String args) {
			model.selectAlgorithm(args);
			return model;
		}		
	}
	
	private class SolveDomainCommand implements Command
	{
		@Override
		public Model doCommand(Model model, String args) {
			model.solveDomain();	
			return model;
		}		
	}
	
	private class SolveDomainInThread implements Command
	{

		@Override
		public Model doCommand(Model model, String args) {
			Thread t = new Thread(new TaskRunnable(model));
			ThreadManager.getInstance().addThread(t);
			t.start();
			return model;
		}
		
	}
	
	private class SelectHeuristicCommand implements Command
	{
		@Override
		public Model doCommand(Model model, String args) {
			model.selectHeuristic(args);	
			return model;
		}		
	}
	
	
	
}
