package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class SolutionManager{
	private HashMap<String, Solution> solutionsMap;
	private static SolutionManager instance = null;
	private static final String FILE_NAME = "resources/solution.dat";
	
	protected SolutionManager() {
		
		loadSolutionsFromFile();
	}
	
	public static SolutionManager getInstance() {
		if (instance == null) {
			instance = new SolutionManager();			
		}
		return instance;
	}
	
	public void addSolution(Solution solution) {
		solutionsMap.put(solution.getProblemDescription(), solution);
	}
	
	public Solution getSolution(String problemDescription) {
		return solutionsMap.get(problemDescription);
	}
	
	public void loadSolutionsFromFile() {
		FileInputStream in = null;
		ObjectInputStream ins = null;
		try {
			in = new FileInputStream(FILE_NAME);
			ins = new ObjectInputStream(in);
		
		
			solutionsMap = (HashMap<String, Solution>) ins.readObject();
			
			//ins.readObject(solutionsMap);

		} catch (ClassNotFoundException | IOException e) {
			solutionsMap = new HashMap<String, Solution>();
		} finally {
			if (ins != null) {
				try {
					ins.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 
			}
		}
	}
	
	public void saveSolutionsInFile() {
		FileOutputStream out = null;
		ObjectOutputStream oos = null;
		try {
			out = new FileOutputStream(FILE_NAME);
			oos = new ObjectOutputStream(out);
			oos.writeObject(solutionsMap);
		} catch (IOException e) {
			;
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null)  {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
