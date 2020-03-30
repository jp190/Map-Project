/*completed by Jungmin Park and Yan Jiang
 * 
 */
import java.util.*;

class Node implements Comparable<Node> {
	String name;
	double latitude;
	double longitude;
	ArrayList<Node> adj;
	Node prev = null;
	boolean isVisited = false;
	double diss = Double.MAX_VALUE;
	Node path;

	public Node(String name, double latitude, double longitude) {

		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		adj = new ArrayList<Node>();// adjacency list for each node

	}

	public double getLongitude() {
		return longitude;

	}

	public double getLatitude() {
		return latitude;
	}

	public void addToList(Node node) {
		adj.add(node);
	}

	@Override
	public int compareTo(Node o) {

		if (diss > o.diss) {
			return 1;
		}
		if (diss < o.diss) {
			return -1;
		}
		return 0;
	}

}
