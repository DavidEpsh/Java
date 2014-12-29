package model;

import java.util.ArrayList;

public class ThreadManager {
		
		private static ThreadManager instance = null;
		ArrayList<Thread> threads;
		
		private ThreadManager() {
			threads = new ArrayList<Thread>();
		}
		
		public static ThreadManager getInstance() {
			
			if(instance == null)
				instance = new ThreadManager();
			
			return instance;
		}
		
		public void addThread(Thread t){
			threads.add(t);
		}
		
		public Thread getNewThread() {
			Thread t = new Thread();
			threads.add(t);
			return t;
		}
		
		public boolean isAlive(int index) {
			return threads.get(index).isAlive();
		}
		
		public boolean areAlive() {
			for(Thread t : threads) {
				if(t.isAlive())
					return true;
			}
			return false;
		}
		
		@SuppressWarnings("deprecation")
		public void killOneThread(int index) {
			if(threads.get(index).isAlive())
				threads.get(index).stop();
		}
		
		public void KillThreads() {
			for(int i = 0 ; i < threads.size(); i++)
				killOneThread(i);
		}
		

}
