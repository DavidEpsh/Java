package presenter;

import java.util.HashMap;

import model.ThreadManager;


public class UserThreadCommands {

	private HashMap<String, Func> tCommands = new HashMap<String, Func>();
	
	public UserThreadCommands(){
		tCommands.put("IsAlive", new checkThreadCommand());
		tCommands.put("StillAlive", new checkStillAliveCommand());
	}
	
	public boolean cheackIfCommandExist(String args){
		return tCommands.containsKey(args);
	}
	
	public interface Func{
		Boolean doCommand(int index);
	}
	
	public Func selectFunc(String funcName){
		Func func = tCommands.get(funcName);
		return func;
	}
	
	private class checkThreadCommand implements Func {

		@Override
		public Boolean doCommand(int index) {
			return ThreadManager.getInstance().isAlive(index);
		}
		
	}
	
	private class checkStillAliveCommand implements Func {

		@Override
		public Boolean doCommand(int index) {
			return ThreadManager.getInstance().areAlive();
		}
		
	}
	
	
}
