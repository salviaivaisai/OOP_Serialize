import java.io.*;

public class Main {

	public static void main(String[] args) {
		try{
			FileReader fr = new FileReader(args[0]);
			ReadGraph r = new ReadGraph(fr, new BufferedReader(fr));
			
			int numar = r.NodesNumber(new FileReader(args[0]));
			int delete = r.DelNumber(new FileReader(args[0]));
			int nrMuchii = r.EdgeNumber(new FileReader(args[0]));
			
			r.settings = r.readSettings();

			int m = r.settings.length;
			Node[] ns = new Node[m];
			for(int i = 0; i < numar ; i++){
				 ns[i] = r.readNodes(); 
			}
			for(int j = 0 ; j < nrMuchii ; j++)
				r.readM(ns);

			Graph graph = r.createGraph(ns);

			for(int j = 0; j < delete ; j++)
				r.toBeRemoved(graph);
			
			FileWriter file = r.readSerialization(graph.neighborhood);
			BufferedWriter bf = new BufferedWriter(file);
			
			Node startSerial = graph.getNeighbor();
			graph.reset();
			
			graph.serializeDFS(startSerial, bf);
			bf.close();
			
			if((r.settings = r.readSettings()) != null){
				graph.destroy();
			}
			
			Deserialize dess = new Deserialize();
			ns = dess.deserializeFile(r.readDeserialization(), r.settings);
			
			graph.CreateGraph(ns);
			
		} catch (ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
