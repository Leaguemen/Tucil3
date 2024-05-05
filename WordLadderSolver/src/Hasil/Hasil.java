package Hasil;
import java.util.*;

public class Hasil {
	public ArrayList<String> Path;
	public int ammountOfNode;
	
	public Hasil(ArrayList<String> _path,int amm) {
		Path = _path;
		ammountOfNode = amm;
	}
	
	public Hasil() {
		Path = new ArrayList<String>();
		ammountOfNode = 0;
	}
}
