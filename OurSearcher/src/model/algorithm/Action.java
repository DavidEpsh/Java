package model.algorithm;

import java.io.Serializable;

public class Action implements Serializable{

	String description;
	
	public Action(String description) {
		this.description=description;
	}
	
	@Override
	public int hashCode(){
		return description.hashCode();
	}
	
	@Override
	public String toString(){
		return description;
	}

	public int getPrice() {
		return 1;
	}
}
