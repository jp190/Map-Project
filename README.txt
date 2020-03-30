Group project 3 team: 
Jungmin Park (jpark178@u.rochester.edu)
Yan Jiang (yjiang40@u.rochester.edu)
Lab session MW 12:30-1:45PM 

[files included: Edge.java, Graph.java, Node.java, test.java, README.txt]

We created Node, Edge, Graph, and Test java (which contains the main method) classes. 

The Node class contains the name, latitude, longitude for a node. An adjacency list is constructed here too. For each node it takes in a String name, double latitude, and double longitude as its parameter. There is a getLongitude() method to return longitude, getLatitude() to return latitude, an addToList(Node node) to add nodes to the adjacency list of a node, and a compareTo(Node o) method to check distance between two nodes. 


The Edge class contains the name, starting node, destination node, and distance of each nodes. Each edge has the String name, Node str (starting node), Node dst (destination node) as parameters. There is a Distance() method that takes the starting node and the ending node as parameters and return the distance value, which is double. It uses the haversine formula to calculate the distance between the nodes. 



The Graph class extends JPanel, and contains two Hashmaps: one stores the node with its name and other properties in Node, and the other stores the edges with its name( formatting as ‘from’-’to’) and other properties in Edge; both hashmaps are static. A getEdges() and getNode() methods are created to return edges and node hashmaps. A list is initialized as static to store the direction(path) later. The Dijkstra method takes in Graph and two Nodes as parameters: the startNode from input is added to the priority queue and then starts to reach out to other nodes which are not visited yet. A node is added to the priority queue after being visited. The distance between the current node and each of all other node is being compared, and the shortest distance is taken. Also the previous node of the current node is being written down (.prev). All nodes on the path found by Dijkstra are being added to a new list and stored. Another list, which is initialized at the beginning of the class is used to stores the names of nodes in the path to be printed out. Distance method from Edge class is called here to calculate the weight/ distance between each node and node.prev. 


The Test class, contains the main method, extends JPanel and contains two methods named scale() and paintComponent(). First, scale() method takes double latitude and double longitude as parameters and return a double array, which is named as ‘result’. In the scale() method, it gets the minimum latitude from the hashmap named as latMin, and get the maximum latitude from the hashmap named as latMax by looping through the nodes. Similarly it finds the minimum and maximum longitude from the hashmap named as longiMin() and longiMax by looping through the node values. Then it calculates the ratio and compares it with the width and height of the canvas being drawn. Values named i and j each is stored inside the result array. The next method, paintComponent(), takes the Graphics g as a parameter. It first gets the value of the width and height of the canvas. After initializing these values, it loops through the keyset of the node hashmap and makes each Node class. After every Node class is created, it draws each node on the map(canvas) using the latitude and longitude of the node. Then, it loops through the edges hashmap, where the String and Edge class is stored inside. In this for loop, it scales each edge class and stores the value in the scaleS and scaleD array, and returns the scaled starting and ending nodes’ latitude and longitude. The rescaled latitudes and longitudes of two nodes are used to draw the edge connects them. With the same way, it loops through the nlist in Graph class, which contains the nodes of the path. It similarly scales the starting node and the ending node of the path and draws a line in a different color. 
In the main method, it reads the text file as an argument. The scanner keeps reading the the file as long as there is a next line. Each element in the same line in the text file is split and stores as a variable based on their properties. Elements starting with i are stored in Node, and elements starting with r are stored in Edge. 
The loops offer three accepted command line formats as listed on the project handout.
The if loop checks if each part of command is present. When ‘show’ presents, the map is drawn and printed; when ‘directions’ is present, StartNode and DestNode are also taken in from command and stored in the list. NullPointerException error is thrown when the path formed by StartNode and DestNode is not found. ArrayIndexOutOfBoundException error is shown when the input command line is not in the correct format as listed on the project handout. 

	Both members worked together for this project and contributed about the equal amount of work. The code is running perfectly with ur.txt. But maybe because the code is not well structured, the running time for monroe.txt and nys.txt is fairly long. 


