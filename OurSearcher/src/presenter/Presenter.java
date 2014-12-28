package presenter;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import presenter.UserCommands.Command;
import presenter.UserThreadCommands.Func;
import model.Model;
import model.MyModel;
import model.Solution;
import tasks.TaskRunnable;
import view.MyConsoleView;
import view.View;

public class Presenter implements Observer {
	
	private Model model; // the current model
	private View view;
	private UserCommands commands;
	private UserThreadCommands tCommands;
	private ArrayList<Model> models; // all running models
	
	public Presenter(Model model, View view)
	{
		this.model = model;
		this.view = view;
		commands = new UserCommands();
		tCommands = new UserThreadCommands();
		models = new ArrayList<Model>();
		models.add(model);
	}

	@Override
	public void update(Observable observable, Object arg1) {
		if (observable instanceof Model)
		{
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
			
			if (tCommands.cheackIfCommandExist(commandName))
			{
				Func func = tCommands.selectFunc(commandName);
				Boolean b = func.doCommand(Integer.parseInt(args));
				view.returnBool(b);
			}
			else
			{
			Command command = commands.selectCommand(commandName);
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
		/////ahahahahaa
	}
	
}
