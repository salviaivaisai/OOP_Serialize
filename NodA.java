public class NodA extends Node {
	
	public NodA(String n){
		this.name = n;
		this.visited = false;
	}
	
	public int getVersion(){
		if( this.adjNode instanceof MyVector )
			return 1;
		if( this.adjNode instanceof MyList )
			return 2;
		return 3;
	}
	
	public NodA(String name, NodContainer mode){
		this.name = name;
		this.visited = false;
		this.adjNode = mode;
	}
	
	public void addNode(Node n){
		this.adjNode.add(n);
		n.adjNode.add(this);
	}
	
	
	public void removeNode(Node n){
		this.adjNode.remove(n);
		n.adjNode.remove(this);
	}

}
