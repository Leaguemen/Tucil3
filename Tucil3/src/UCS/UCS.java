package UCS;
import Hasil.*;
import State.*;
import java.util.*;

public class UCS {
	private Queue<StateNode> urutanTraversal;
	
	//constructor
	public UCS() {
		urutanTraversal = new PriorityQueue<StateNode>();
	}
	
	public Hasil find(String origin, String target) {
		int nodeAmmount = 0;
		ArrayList<String> temp = new ArrayList<String>();
		temp.add(origin);
		Hasil retVal = new Hasil(temp,nodeAmmount);
		StateNode seed = new StateNode(origin,0,temp,false);
		urutanTraversal.add(seed);
		boolean found = false;
		if(origin.length() == target.length()) {
			StateNode expandNode = new StateNode(urutanTraversal.poll());
			while(!found){
				if(nodeAmmount != 0) {expandNode = new StateNode(urutanTraversal.poll());}
				System.out.println(expandNode.getName());
				if(expandNode.getName().equals(target) ) {
					found = true;
				} else {
					nodeAmmount++;
					expandNode.setChildren();
					ArrayList<String> enChildren = expandNode.getChildren(); //expand node children
					for(int i=0;i<enChildren.size();i++) {
						ArrayList<String> tempTrail = new ArrayList<>(expandNode.getTrail());
						tempTrail.add(enChildren.get(i));
						StateNode child = new StateNode(enChildren.get(i),expandNode.getCost()+1,tempTrail,false);
						urutanTraversal.add(child);
					}
				}
			}
			retVal.Path = expandNode.getTrail();
			retVal.ammountOfNode = nodeAmmount;
		}
		return retVal;
	}
}