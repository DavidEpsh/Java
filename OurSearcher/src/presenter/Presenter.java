package presenter;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import presenter.SpecificModelCommands.mCommand;
import presenter.UserCommands.Command;
import presenter.UserThreadCommands.Func;
import model.Model;
import model.MyModel;
import model.Solution;
import model.SolutionManager;
import tasks.TaskRunnable;
import view.MyConsoleView;
import view.View;

public class Presenter implements Observer {
	
	private Model model; // the current model
	private View view;
	private UserCommands commands;
	private UserThreadCommands tCommands;
	private SpecificModelCommands mCommands;
	private ArrayList<Model> models; // all running models
	
	public Presenter(Model model, View view)
	{
		this.model = model;
		this.view = view;
		commands = new UserCommands();
		tCommands = new UserThreadCommands();
		mCommands = new SpecificModelCommands();
		models = new ArrayList<Model>();
		models.add(model);
	}

	@Override
	public void update(Observable observable, Object arg1) {
		if (observable instanceof Model)
		{
			SolutionManager.getInstance();
		
			Solution solution = ((Model)observable).getSolution();
			view.displaySolution(solution);
			
		}
		else if (observable == view)
		{
			String action = view.getUserAction();
			String[] arr = action.split(" ");
			
			String commandName = arr[0];
			
			String args = null;
			if (arr.length > 1)
				args = arr[1];
			
			if (tCommands.cheackIfCommandExist(commandName))  // For thread commands
			{
				if (args == null)
					args ="0";
				
				Func func = tCommands.selectFunc(commandName);
				view.returnBool(func.doCommand(Integer.parseInt(args)));
			}
			
			else if (mCommands.cheackIfCommandExist(commandName)){
				mCommand mCom = mCommands.selectCommand(commandName);
				 view.displaySolution(mCom.doCommand(models.get(Integer.parseInt(args))));
			}
			
			else
			{
			Command command = commands.selectCommand(commandName);   // For Domain commands
			Model m = command.doCommand(model, args);
			
			// Check if we got a new model from the command
			if (m != model) {
				this.model = m;
				models.add(m);
				m.addObserver(this);
			}
			}
		}
	}
	
	public static void main(String[] args) {
		MyModel model = new MyModel();
		MyConsoleView ui = new MyConsoleView();
		Presenter presenter = new Presenter(model, ui);
		
		model.addObserver(presenter);
		ui.addObserver(presenter);
		
		Thread t = new Thread (new TaskRunnable(ui));
		t.start();
	}
	
}
