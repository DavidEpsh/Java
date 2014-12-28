package tasks;

public class TaskRunnable implements Runnable {

	private Task t;
	
	public TaskRunnable(Task t) {
		this.t = t;
	}
	
	@Override
	public void run() {

		t.doTask();
	}

}
