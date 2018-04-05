import java.util.*;

public class MyVector implements NodContainer {
	
	public ArrayList<Node> vector;
	public final String type = "VECTOR";
	
	public MyVector(){
		this.vector = new ArrayList<Node>();
	}
	
	public Iterator<Node> getIterator(){
		return this.vector.iterator();
	}
	
	public boolean contains(Node n){
		for(Node x : this.vector){
			if(x.equals(n))
				return true;
		}
		return false;
	}
	
	public String getName(int index){
		if(!this.vector.isEmpty())
			return this.vector.get(index).name;
		return null;
	}
	
	public void add(Node n){
		this.vector.add(n);
	}
	
	public void remove(Node n){
		if(!this.vector.isEmpty())
			this.vector.remove(n);
	}

}
