package State;

import java.util.*;

public class StateNode implements Comparable<StateNode> {
	private String name;
	private int cost;
	private ArrayList<String> trail;
	private ArrayList<String> children;
	
	public StateNode(String _name, int _cost,ArrayList<String> _trail,boolean makeChild) {
		name = _name;
		cost = _cost;
		trail = _trail;
		if(makeChild) {
			children = makeChildren(name);
		} else {
			children = new ArrayList<String>();
		}
	}
	
	public StateNode(StateNode sn) {
		name = sn.name;
		cost = sn.cost;
		trail = sn.trail;
		children = sn.children;
	}
	
	//getter
	public String getName() {
		return name;
	}
	
	public int getCost() {
		return cost;
	}
	
	public ArrayList<String> getTrail(){
		return trail;
	}
	
	public ArrayList<String> getChildren(){
		return children;
	}
	
	//setter or modifier
	public void setName(String _name) {
		this.name = _name;
	}
	
	public void setCost(int _cost) {
		this.cost = _cost;
	}
	
	public ArrayList<String> addTrail(String s) {
		ArrayList<String> returnValue = trail;
		returnValue.add(s);
		return returnValue;
	}
	
	public void setChildren(){
		children = makeChildren(name);
	}
	
	//method
	public ArrayList<String> makeChildren(String _name) {
	    StringCheck checker = new StringCheck("words_alpha.txt");
	    ArrayList<String> children = new ArrayList<String>();

	    for (int i = 0; i < _name.length(); i++) {
	        for (int j = 1; j <= 26; j++) {
	            StringBuilder sb = new StringBuilder(_name);
	            char shiftedChar = (char) (((_name.charAt(i) - 'A' + j) % 26) + 'A');
	            sb.setCharAt(i, shiftedChar);
	            String possibleChild = sb.toString();
	            if (checker.isValidWord(possibleChild) && j != 26) {
	                children.add(possibleChild);
	            }
	        }
	    }
	    return children;
	}
	
	public void printChildren() {
		for(int i=0; i < children.size();i++) {
			System.out.println(children.get(i).toString());
		}
	}
	
	public int compareTo(StateNode other) {
		if(cost == other.cost) {
			return 0;
		} else if(cost < other.cost) {
			return -1;
		} else {
			return 1;
		}
	}
	
	//main for debugging
//	public static void main(String[] args) {
//		ArrayList<String> originalTrail = new ArrayList<String>();
//		StateNode origin = new StateNode("MAIN",0,originalTrail,true);
//		System.out.println(origin.children.size());
//		origin.printChildren();
//		System.out.println("CHECKPOINT");
//	}
}
