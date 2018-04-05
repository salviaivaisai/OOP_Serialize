public class NodB extends Node {
	
	public NodB(String name, NodContainer mode){
		this.name = name;
		this.adjNode = mode;
		this.visited = false;
	}
	
	public NodB(String n) {
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

	public void addNode(Node n){
		this.adjNode.add(n);
		n.adjNode.add(this);
	}
	
	public void removeNode(Node n){
		this.adjNode.remove(n);
		n.adjNode.remove(this);
	}

}
