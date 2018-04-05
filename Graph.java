import java.util.*;
import java.io.*;

public class Graph {

	public ArrayList<Node>  neighborhood = new ArrayList<Node>();
	static int level = 0;
	
	public void add(Node n){
		this.neighborhood.add(n);
	}
	
	public void remove(Node n){
		if(!this.neighborhood.isEmpty()){
			this.neighborhood.remove(n);
			for(Node x : this.neighborhood){
				if(x.adjNode.contains(n))
					x.adjNode.remove(n);
			}
		}
	}
	
	public void addM(Node n1, Node n2){
		int index1 = this.neighborhood.indexOf(n1);
		int index2 = this.neighborhood.indexOf(n2);
		
		this.neighborhood.get(index1).adjNode.add(n2);
		this.neighborhood.get(index2).adjNode.add(n1);
	}
	
	public void removeM(Node n1, Node n2){
		int index1 = this.neighborhood.indexOf(n1);
		int index2 = this.neighborhood.indexOf(n2);
		
		this.neighborhood.get(index1).adjNode.remove(n2);
		this.neighborhood.get(index2).adjNode.remove(n1);
	}
	
	public void CreateGraph(Node[] n){
		for(Node x : n){
			this.add(x);
		}
	}
	/*
	 * sterge lista de noduri din graf
	 */
	public void destroy(){
		this.neighborhood.clear();
	}
	
	public Node getNeighbor(){
		for(Node x : this.neighborhood){
			if(x.visited)
				return x;
		}
		return null;
	}
	/*
	 * reseteaza nodurile vizitate din graf
	 */
	public void reset(){
		for(Node x : this.neighborhood){
			if(x.visited)
				x.visited = false;
		}
	}
/*
 * face serializarea prin cautare in adancime
 */
	
	public void serializeDFS(Node n, BufferedWriter bf){
		n.visited = true;
		Node aux = new Node();
		Iterator<Node> x = n.adjNode.getIterator();
		
		try {
			bf.write("<Object class=\""  + n.getClass().getSimpleName() + "\" ");
			bf.write( "Version=\"" + n.getVersion() + "\" ");
			bf.write("id=\"" + n.id + "\">\n");
			bf.write("<Nume>" + n.name + "</Nume>\n");
			if(n.adjNode.getClass().getName().contains("Vector"))
				bf.write("<VECTOR>\n" );
			else if(n.adjNode.getClass().getName().contains("List"))
				bf.write("<LIST>\n" );
			else  if(n.adjNode.getClass().getName().contains("Set"))
				bf.write("<SET>\n" );
			
			while(x.hasNext()){
				aux = x.next();
				if( ! aux.visited ){
					serializeDFS(aux, bf);
				}
				else if(! aux.name.equals(n.name) && aux.visited){
					bf.write("<Reference class=\"" + aux.getClass().getSimpleName()+ "\" ");
					bf.write("Version=\"" + aux.getVersion() + "\" " );
					bf.write("id=\"" + aux.id + "\">\n");
				}
			}
			if(n.adjNode.getClass().getName().contains("Vector"))
				bf.write("</VECTOR>\n" );
			else if(n.adjNode.getClass().getName().contains("List"))
				bf.write("</LIST>\n" );
			else  if(n.adjNode.getClass().getName().contains("Set"))
				bf.write("</SET>\n" );
			bf.write("</Object>\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
