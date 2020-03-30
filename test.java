/*completed by Jungmin Park and Yan Jiang
 * 
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class test extends JPanel {
	/**
	 *
	 */
	Node Startnode, Destnode;

	private static final long serialVersionUID = 1L;

	// this method calculate the resizing longitude and latitude positions for each
	// node as the canvas window is resizing
	public double[] scale(double lat, double longi) {

		String firstString = Graph.node.keySet().stream().findFirst().get();
		double latMin = Graph.node.get(firstString).getLatitude();
		double latMax = Graph.node.get(firstString).getLatitude();
		for (Node node : Graph.node.values()) {

			if (latMin > node.getLatitude())
				latMin = node.getLatitude();
			if (latMax < node.getLatitude())
				latMax = node.getLatitude();
		}

		double longiMin = Graph.node.get(firstString).getLongitude();
		double longiMax = Graph.node.get(firstString).getLongitude();
		for (Node node : Graph.node.values()) {
			if (longiMin > node.getLongitude())
				longiMin = node.getLongitude();
			if (longiMax < node.getLongitude())
				longiMax = node.getLongitude();
		}
		double i = getWidth() * (lat - latMin) / (latMax - latMin);
		double j = getHeight() * (longi - longiMin) / (longiMax - longiMin);
		double[] result = new double[2];
		result[0] = i;
		result[1] = j;
		return result;

	}

	public void paintComponent(Graphics g) {
		int width = getWidth();
		int height = getHeight();

		// loop through the node
		for (String key : Graph.node.keySet()) {
			Node node = Graph.node.get(key);
			//

			g.fillOval((int) node.getLatitude(), (int) node.getLongitude(), 5, 2); // here replace the first two
			// function with i and j;
		}

		for (Edge edge : Graph.edges.values()) {

			double[] scaleS = scale(edge.str.getLatitude(), edge.str.getLongitude());
			double stri = scaleS[0];
			double strj = scaleS[1];
			double[] scaleD = scale(edge.dst.getLatitude(), edge.dst.getLongitude());
			double dsti = scaleD[0];
			double dstj = scaleD[1];

			g.setColor(Color.BLACK);
			g.drawLine((int) stri, (int) strj, (int) dsti, (int) dstj);

		}

		for (int i = 0; i < Graph.nList.size() - 1; i++) {
			double[] scale1 = scale(Graph.nList.get(i).getLatitude(), Graph.nList.get(i).getLongitude());
			double a1 = scale1[0];
			double b1 = scale1[1];
			double[] scale2 = scale(Graph.nList.get(i + 1).getLatitude(), Graph.nList.get(i + 1).getLongitude()); // System.out.println(Graph.nList.get(1));
			double a2 = scale2[0];
			double b2 = scale2[1];
			g.setColor(Color.MAGENTA);
			g.drawLine((int) a1, (int) b1, (int) a2, (int) b2);
		}

	}

	// this is the main method
	public static void main(String[] args) throws FileNotFoundException {

		File file = new File(args[0]);
		Scanner sc = new Scanner(file);// read in the file

		Graph location = new Graph();

		while (sc.hasNext()) {
			String sentence = sc.nextLine();
			String[] array = sentence.split("\t");
			if (array[0].equals("i")) {

				String IntersectionID = array[1];
				double Latitude = Double.parseDouble(array[3]);
				double Longitude = -Double.parseDouble(array[2]);
				Node newnode = new Node(IntersectionID, Latitude, Longitude);
				Graph.node.put(IntersectionID, newnode);
			} else if (array[0].equals("r")) {
				String name = array[1];
				String str = array[2];
				String dst = array[3];
				Graph.edges.put(str + "-" + dst, new Edge(name, Graph.node.get(str), Graph.node.get(dst)));
				Graph.edges.put(dst + "-" + str, new Edge(name, Graph.node.get(dst), Graph.node.get(str)));
				Graph.node.get(str).adj.add(Graph.node.get(dst));
				Graph.node.get(dst).adj.add(Graph.node.get(str));

			}

		}

		if (args[1].equals("show")) {// canvas is present
			JFrame frame = new JFrame("Street Map");
			test canvas = new test();
			frame.add(canvas);
			frame.setSize(400, 400);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			if (args[2].equals("directions")) {// direction (path) is present

				List<Node> in = new ArrayList<>();

				try {
					String startPoint = args[3];
					String destPoint = args[4];
					// use the string name to create start node class and end node class
					Node Startnode = Graph.node.get(startPoint);
					Node Destnode = Graph.node.get(destPoint);

					try {
						if (Graph.node.containsKey(Startnode.name)) {
							in.add(Startnode);
						}

						if (Graph.node.containsKey(Destnode.name)) {
							in.add(Destnode);

						}

						Graph.dijkstra(location, Graph.node.get(Startnode.name), Graph.node.get(Destnode.name));
					} catch (NullPointerException e) {
						System.out.println("There is no such path found.");// if node/nodes are not found
					}

				} catch (ArrayIndexOutOfBoundsException e) { // if command is not correct
					System.out.println("Please enter command in correct format and try it again.");
				}
			}
		} else if (args[1].equals("direction")) {
			List<Node> in = new ArrayList<>();
			String startPoint = args[2];
			String destPoint = args[3];
			Node Startnode = Graph.node.get(startPoint);// nodes are added to the list
			Node Destnode = Graph.node.get(destPoint);

			if (Graph.node.containsKey(Startnode.name)) {
				in.add(Startnode);
			}

			if (Graph.node.containsKey(Destnode.name)) {
				in.add(Destnode);

			}

			Graph.dijkstra(location, Graph.node.get(Startnode.name), Graph.node.get(Destnode.name));// call Dijkstra to
																									// find the shortest
																									// path

		}

	}

}
