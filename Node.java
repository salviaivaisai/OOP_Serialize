/*
 * Clasa generica pentru noduri
 */
public class Node {
	
	public String name;
	public NodContainer adjNode;
	public boolean visited;
	public int id = 0;

	
	public Node(){
		this.name = null;
		this.visited = false;
	}
	
	public Node(String n){
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
}
