/*
 * clasa ce contine functiile pentru deserializare
 * **este incompleta
 */

import java.io.*;
import java.util.*;

public class Deserialize {

	FileReader fr;
	BufferedReader br;
	ReadGraph rr = new ReadGraph();
	static String[] vectnume = new String[10];
	static int nr = 0;
	static ArrayList<Node> list = new ArrayList<Node>();
	static ArrayList<Node> adjlist = new ArrayList<Node>();

	public Node readDesFile(String[] settings){

		rr.settings = settings;
		adjlist.clear();
		Node n = new Node();
		try {
			int id= 0;
			String line;
			String[] objclass = new String[10];
			String[] name = new String[10];

			line = br.readLine();

			if(line.startsWith("<VECTOR") || line.startsWith("<LIST") || line.startsWith("<SET"))
				line = br.readLine();
			
			if(line.startsWith("<Refere"))
				line = br.readLine();
		
			if(line.startsWith("<Object")){
				objclass = line.split("\"");
				id = Integer.parseInt(objclass[5]);
				nr++;
			}
			line = br.readLine();
			if(line.startsWith("<Nume")){
				name = line.split(">");
				name = name[1].split("<");
				System.out.println(name[0]);
				vectnume[id] = name[0];
			}

			System.out.println(id);
			n = rr.chooseType(objclass[1], vectnume[id]);
			list.add(n);
	
			System.out.println(n.name + " " + n.adjNode.getClass().getSimpleName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return n;
	}
	
	public Node[] deserializeFile(FileReader file, String[] setts){

		br = new BufferedReader(file);
		rr.settings = setts;
		Node[] ns = new Node[10];
		for(int i = 0; i < 3 ; i++){
			ns[i] = readDesFile(rr.settings);
		
		}

		return ns;
	}

}
