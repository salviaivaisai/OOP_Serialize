import java.util.*;

public class MySet implements NodContainer {
	
	public HashSet<Node> set;
	public final String type = "SET";
	
	public MySet(){
		this.set = new HashSet<Node>();
	}
	
	public Iterator<Node> getIterator(){
		return this.set.iterator();
	}
	
	public boolean contains(Node n){
		for(Node x : this.set){
			if(x.equals(n))
				return true;
		}
		return false;
	}
	
	public void add(Node n){
		this.set.add(n);
	}
	
	public void remove(Node n){
		this.set.remove(n);
	}

}
