import java.io.*;
import java.util.*;

public class ReadGraph {

	BufferedReader bf;
	FileReader fr;
	String[] settings = new String[100];
	static int nr = 0;
	static int nrm = 0;
	static ArrayList<Node> list = new ArrayList<Node>();
	static ArrayList<Node> muchii = new ArrayList<Node>();

	public ReadGraph(){

	}
	public ReadGraph(FileReader f, BufferedReader b){
		this.fr = f;
		this.bf = b;
	}

	/*
	 * metoda pentru citirea setarilor
	 */

	public String[] readSettings(){
		try{
			String sett;
			sett = bf.readLine();
			if(sett.startsWith("Settings"))
				settings = sett.split(" ");

		} catch ( IOException e){
			e.printStackTrace();
		}
		return settings;
	}

	/*
	 * metode ce numara cate operatii de Add, Del, AddM si DelM se fac
	 */

	public int NodesNumber(FileReader f){
		int nr = 0;
		BufferedReader aux = new BufferedReader(f);
		String line;
		String[] words = new String[4];
		try {
			while((line = aux.readLine()) != null){
				if(line.startsWith("Add")){
					words = line.split(" ");
					if(words[0].equals("Add"))
						nr++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return nr;
	}

	public int DelNumber(FileReader f){
		int nr = 0;
		BufferedReader aux = new BufferedReader(f);
		String line;
		String[] words = new String[4];
		try {
			while((line = aux.readLine()) != null){
				if(line.startsWith("Del")){
					words = line.split(" ");
					if(words[0].equals("Del"))
						nr++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return nr;
	}

	public int EdgeNumber(FileReader f){
		int nr = 0;
		BufferedReader aux = new BufferedReader(f);
		String line;
		String[] words = new String[4];
		try {
			while((line = aux.readLine()) != null){
				if(line.startsWith("AddM")){
					words = line.split(" ");
					if(words[0].equals("AddM"))
						nr++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nr;
	}

	public int DelMNumber(FileReader f){
		int nr = 0;
		BufferedReader aux = new BufferedReader(f);
		String line;
		String[] words = new String[4];
		try {
			while((line = aux.readLine()) != null){
				if(line.startsWith("DelM")){
					words = line.split(" ");
					if(words[0].equals("DelM"))
						nr++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return nr;
	}
	/*
	 * creeaza nod de tip NodA,B,C in functie de setarile citite
	 */
	public Node chooseType(String type, String name){
		Node n = new Node();
		switch(type){
		case "NodA":  n = new NodA(name, chooseSettings(settings[1])); break;
		case "NodB": n = new NodB(name, chooseSettings(settings[2]) ); break;
		case "NodC": n = new NodC(name, chooseSettings(settings[3]) ); break;
		}
		return n;
	}

	public NodContainer chooseSettings(String setts){
		if(setts.equals("1"))
			return new MyVector();
		if(setts.equals("2"))
			return new MyList();
		return new MySet();
	}

	/*
	 * citeste si creeaza nodul
	 */

	public Node readNodes(){
		Node n = new Node();
		try {
			String line = new String();
			String[] node = new String[10];

			line = bf.readLine();
			if(line.startsWith("Add") ){
				node = line.split(" ");

				n = chooseType( node[1],node[2] );
				list.add(n);
				n.id = nr;
				Node nn[] = new Node[node.length];
				for(int i = 3; i < node.length ; i++ ){
					if(node[i] != null){
						for(Node s : list){
							if(s.name.equals(node[i]))
								nn[i-3] = s;
						}
						n.addNode(nn[i-3]);
					}
				}
				nr++;
			}
		} catch ( IOException e){
			e.printStackTrace();
		}
		return n;
	}

	/*
	 * citeste nodurile ce vor fi sterse
	 */

	public void toBeRemoved(Graph gr){
		String line = new String();
		String[] node = new String[10];

		try {
			line = bf.readLine();

			if(line.startsWith("Del") ){
				node = line.split(" ");

				for(Node x : list){
					if(x.name.equals(node[1]))
						gr.remove(x);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * citeste muchiile adaugate
	 */
	public void readM(Node[] n){

		try {
			String line = new String();
			String[] node = new String[10];

			line = bf.readLine();
			if(line.startsWith("AddM") ){
				node = line.split(" ");
				if(node[0].equals("AddM")){
					nrm++;
					for(Node x : list){
						if(x.name.equals(node[1]))
							muchii.add(x);
						if(x.name.equals(node[2]))
							muchii.add(x);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * citeste nodul si fisierul pentru serializare si intoarce fisierul
	 */
	public FileWriter readSerialization(ArrayList<Node> n){
		FileWriter file = null;
		try{
			String line;
			String[] serial = new String[3];
			line = bf.readLine();
			if(line.startsWith("Serialize")){
				serial = line.split(" ");
				for(Node x : n){
					if(x.name.equals(serial[1])){
						file = new FileWriter(serial[2] );
						x.visited = true;
						return file;
					}
				}
			}
			else{
				file = readSerialization(n);
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		return file;
	}

	/*
	 * citeste fisierul ce va deserializat si il intoarce
	 */

	public FileReader readDeserialization(){
		String[] des = new String[2];
		FileReader file = null;
		try{
			String line = bf.readLine();
			if(line.startsWith("Deserialize")){
				des = line.split(" ");
				file = new FileReader(des[1]);
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		return file;
	}

	/*
	 * creeaza graful cu nodurile citite
	 */

	public Graph createGraph(Node[] n){
		Graph graph = new Graph();
		for(Node i : n){
			if(i != null)
				graph.add(i);
		}
		if(nrm != 0){
			for(int i = 0; i < muchii.size() ; i+=2){
				graph.addM(muchii.get(i), muchii.get(i+1));
			}
		}
		return graph;
	}


}
