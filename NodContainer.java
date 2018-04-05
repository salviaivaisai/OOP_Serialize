/*
 * Interfata pentru modul de mentinere a nodurilor adiacente
 */

import java.util.Iterator;

public interface NodContainer {

	public void add(Node n);
	public void remove(Node n);
	public Iterator<Node> getIterator();
	public boolean contains(Node n);
}
