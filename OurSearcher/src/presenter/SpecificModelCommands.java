package presenter;

import java.util.HashMap;

import model.Model;
import model.Solution;


public class SpecificModelCommands {

	private HashMap<String, mCommand> mCommands = new HashMap<String,mCommand>();
	
	public SpecificModelCommands(){
		mCommands.put("DS", new displaySolutionCommand());
		
	}
	
	public boolean cheackIfCommandExist(String args){
		return mCommands.containsKey(args);
	}
	
	public mCommand selectCommand(String mCommandName){
		return mCommands.get(mCommandName);
	}
	
	public interface mCommand{
		Solution doCommand(Model model);
	}
	
	public class displaySolutionCommand implements mCommand{

		@Override
		public Solution doCommand(Model model) {
			return model.getSolution();
		}
		
	}
		
}
