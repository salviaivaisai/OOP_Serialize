import java.util.*;

public class MyList implements NodContainer {
	
	public LinkedList<Node> list;
	
	public MyList(){
		list = new LinkedList<Node>();
	}
	
	public Iterator<Node> getIterator(){
		return this.list.iterator();
	}
	
	public boolean contains(Node n){
		for(Node x : this.list){
			if(x.equals(n))
				return true;
		}
		return false;
	}
	
	public String getName(int index){
		if(!this.list.isEmpty())
			return this.list.get(index).name;
		return null;
	}
	
	public void add(Node n){
		this.list.add(n);
	}
	
	public void remove(Node n){
		if(!this.list.isEmpty())
			this.list.remove(n);
	}

}
