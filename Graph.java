/*completed by Jungmin Park and Yan Jiang
 * 
 */
import java.util.*;

import javax.swing.JPanel;

public class Graph extends JPanel {
	static HashMap<String, Node> node;
	static HashMap<String, Edge> edges;// one edge is mapping to several nodes

	static List<Node> nList = new ArrayList<>();

	public Graph() {
		node = new HashMap<>();
		edges = new HashMap<>();

	}

	public static void dijkstra(Graph graph, Node s, Node d) {



    	PriorityQueue<Node> queue = new PriorityQueue<>();
    	
    	s.diss = 0;
    	
    	queue.add(s);
    	
    	while(!queue.isEmpty()) {
    		Node n = queue.poll();
    		
    		if(!n.isVisited) {
    			
    			for(Node a:n.adj) {
    				queue.add(a);
    				
    				//distances from the current node to all other nodes are compared 
    				//the shortest distance is taken 
    				if(a.diss > n.diss + graph.edges.get(a.name+ "-" + n.name).distance) {
    				
    					a.diss = n.diss + graph.edges.get(a.name+ "-" + n.name).distance;

    					a.prev = n;//track the previous node
    
    				}
    
    			
    			
    			n.isVisited = true;
    		}
    	}
    		List<String> nodeList = new ArrayList<>();
    	
    		double totalDistance = 0;
        	while(d.prev != null) {
        		nodeList.add(d.name);
        		
        		nList.add(d); //track back and make the path 
        		Node dprev = d.prev;
        		double currentDistance=Edge.Distance(d, dprev);//length of each distance/edge is calculated 
 
        		totalDistance = totalDistance + currentDistance;//track total distance 
        
        		d = d.prev;
        	
        		
        	}

        	System.out.println("Total distance traveled is "+ totalDistance + " miles");
        	Collections.reverse(nodeList);
    		Collections.reverse(nList);

    		System.out.print("Travel path starts " + d.name+"-");
        	for(int i = 0; i<nodeList.size(); i++){
        		System.out.print(nodeList.get(i)+"-");//print out the path 
    		}
        	
        	System.out.println(" end.");


    }

	public static HashMap<String, Edge> getEdges() {
		return edges;
	}

	public static HashMap<String, Node> getNode() {
		return node;
	}

}
