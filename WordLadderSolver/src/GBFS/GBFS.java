package GBFS;
import Hasil.*;
import State.*;
import java.util.*;

public class GBFS {
	
	private Queue<StateNode> urutanTraversal;
	
	public GBFS() {
		urutanTraversal = new PriorityQueue<StateNode>();
	}
	
	public int heuristicCost(String current,String target) {
		int cost =0;
		for(int i=0; i < current.length();i++) {
			if(current.charAt(i) != target.charAt(i)) {
				cost++;
			}
		}
		return cost;
	}
	
	public Hasil find(String origin, String target) {
		int nodeAmmount = 0;
		ArrayList<String> temp = new ArrayList<String>();
		temp.add(origin);
		Hasil retVal = new Hasil(temp,nodeAmmount);
		int originalCost = heuristicCost(origin,target);
		StateNode seed = new StateNode(origin,originalCost,temp,false);
		urutanTraversal.add(seed);
		boolean found = false;
		if(origin.length() == target.length()) {
			StateNode expandNode = new StateNode(urutanTraversal.poll());
			while(!found) {
				if(nodeAmmount != 0) {expandNode = new StateNode(urutanTraversal.poll());}
				System.out.println(expandNode.getName());
				if(expandNode.getName().equals(target) ) {
					found = true;
				} else {
					nodeAmmount++;
					expandNode.setChildren();
					ArrayList<String> enChildren = expandNode.getChildren();
					for(int i=0;i<enChildren.size();i++) {
						ArrayList<String> tempTrail = new ArrayList<>(expandNode.getTrail());
						tempTrail.add(enChildren.get(i));
						int tempCost = heuristicCost(enChildren.get(i),target);
						StateNode child = new StateNode(enChildren.get(i),tempCost,tempTrail,false);
						urutanTraversal.add(child);
					}					
				}
			}
			retVal.Path = expandNode.getTrail();
			retVal.ammountOfNode = nodeAmmount;
		}
		return retVal;
	}
	
//	public static void main(String[] args) {
//		GBFS pengucs = new GBFS();
//		Hasil hasil = pengucs.find("WEST", "LICK");
//		System.out.println(hasil.Path);
//		System.out.println("CHECKPOINT");
//	}
}
