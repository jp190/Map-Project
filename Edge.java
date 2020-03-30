/*completed by Jungmin Park and Yan Jiang
 * 
 */
class Edge {
	String name;
	Node str;
	Node dst;
	double distance;

	public Edge(String name, Node str, Node dst) {
		this.name = name;
		this.str = str;
		this.dst = dst;
	}

	// this method calculates the distance of path between two nodes(locations)
	// using Haversine formula
	public static double Distance(Node node1, Node node2) {
		double r = 3950; // radius of earth in miles
		double lo1 = node1.getLongitude();
		double lo2 = node2.getLongitude();
		double la1 = node1.getLatitude();
		double la2 = node2.getLatitude();
		// haversine formula to calculate distance //formula needs to be typed in
		double distance = 2 * r * Math.asin(Math.sqrt(Math.pow(Math.sin(Math.toRadians(la2 - la1) / 2), 2)
				+ Math.pow(Math.sin(Math.toRadians(lo2 - lo1) / 2), 2) * Math.cos(la1) * Math.cos(la2)));
		return distance;

	}

	public Node getStr() {
		return str;
	}

	public Node getDst() {
		return dst;
	}

	public double getDistance() {
		Distance(str, dst);
		return distance;
	}
}
