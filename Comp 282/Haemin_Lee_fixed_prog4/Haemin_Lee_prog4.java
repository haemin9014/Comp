import java.io.*; // for BufferedReader
import java.util.*; // for StringTokenizer

class Edge_Node {
	private Vertex_Node target;
	private Edge_Node next;
	public Edge_Node(Vertex_Node t, Edge_Node e) {
		target = t;
		next = e;
	}
	public Vertex_Node getTarget() {
		return target;
	}
	public Edge_Node getNext() {
		return next;
	}
			// no setters needed
}

class Vertex_Node {
	private String name;
	private Edge_Node edge_head;
	private int distance;
	private Vertex_Node parent;
	private Vertex_Node next;
	private boolean check;
	
	public Vertex_Node(String s, Vertex_Node v) {
		name = s;
		next = v;
		distance = -1;
	}
	public String getName() {
		return name;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int d) {
		distance = d;
	}
	public Edge_Node getNbrList() {
		return edge_head;
	}
	public void setNbrList(Edge_Node e) {
		edge_head = e;
	}
	public Vertex_Node getNext() {
		return next;
	}
	public Vertex_Node getParent() {
		return parent;
	}
	public void setParent(Vertex_Node n) {
		parent = n;
	}
	public void setMark(boolean mark) {
		check = mark;
	}
	public boolean getMark() {
		return check;
	}
}

class Graph {
	private Vertex_Node head;
	private int size;
	
	public Graph() {
		head = null;
		size = 0;
	
	}
	// reset all distance values to -1
	public void clearDist() {
      Vertex_Node tempVertex = head.getNext();
		head.setDistance(-1);
      while(tempVertex.getNext()!=null){
          tempVertex.setDistance(-1);
          tempVertex = tempVertex.getNext();
      }
	}
	
   public void clearMark(){
      Vertex_Node tempVertex = head.getNext();
      head.setMark(false);
      while(tempVertex.getNext()!=null){
          tempVertex.setMark(false);
          tempVertex = tempVertex.getNext();
      }
   }
	public Vertex_Node findVertex(String s) {
		Vertex_Node pt = head;
		while (pt != null && s.compareTo(pt.getName()) != 0)
			pt = pt.getNext();
		return pt;
	}
	
	public Vertex_Node input(String fileName) throws IOException {
		String inputLine, sourceName, targetName;
		Vertex_Node source = null, target;
		Edge_Node e;
		StringTokenizer input;
		BufferedReader inFile = new BufferedReader(new FileReader(fileName));
		inputLine = inFile.readLine();
		
		while (inputLine != null) {
			input = new StringTokenizer(inputLine);
			sourceName = input.nextToken();
			source = findVertex(sourceName);
		
			if (source == null) {			
				head = new Vertex_Node(sourceName, head);
				source = head;
				size++;
			}
			
			if (input.hasMoreTokens()) {
				targetName = input.nextToken();
				target = findVertex(targetName);
			
				if (target == null) {
					head = new Vertex_Node(targetName, head);
					target = head;
					size++;
				}
		// put edge in one direction -- while checking for repeat
				e = source.getNbrList();
				while (e != null) {
					if (e.getTarget() == target) {
						System.out.print("Multiple edges from "
								+ source.getName() + " to ");
						System.out.println(target.getName() + ".");
						break;
					}
					e = e.getNext();
				}
				source.setNbrList(new Edge_Node(target, source.getNbrList()));
		
				// put edge in the other direction
				e = target.getNbrList();
				while (e != null) {
					if (e.getTarget() == source) {
						System.out.print("Multiple edges from "
								+ target.getName() + " to ");
						System.out.println(source.getName() + ".");
						break;
					}
					e = e.getNext();
				}
				target.setNbrList(new Edge_Node(source, target.getNbrList()));
			}
			inputLine = inFile.readLine();
		}
		inFile.close();
		return source;
	}
		// You might find this helpful when debugging so that you
		// can see what the graph actually looks like
	public void output() {
		Vertex_Node v = head;
		Edge_Node e;
		while (v != null) {
			System.out.print(v.getName() + ": ");
			e = v.getNbrList();
			while (e != null) {
				System.out.print(e.getTarget().getName() + " ");
				e = e.getNext();
			}
		System.out.println();
		v = v.getNext();
		}
	}
	public void output_bfs(Vertex_Node s) {
      clearDist();
      clearMark();
		//set java array
		Queue<Vertex_Node> queue = new LinkedList<Vertex_Node>();		
		//starting point
		Vertex_Node tempVertex = s;
		Edge_Node tempEdge;
		//set starting point as visited
      s.setMark(true);
      //set starting point distance as 0
		s.setDistance(0);
      //print out
		System.out.println(s.getName() + ",  " 
				+ s.getDistance() + ",  " + "null");
		queue.add(s);
		
		while(!queue.isEmpty()) {
			
			tempVertex = queue.remove();
			tempEdge = tempVertex.getNbrList();
			while(tempEdge != null) {
				if(tempEdge.getTarget().getMark()==false) {
					//mark it
					tempEdge.getTarget().setMark(true);
					//set parent
					//tempEdge.getTarget().setParent(tempVertex);
					//set distance
					tempEdge.getTarget().setDistance(tempVertex.getDistance()+1);
					//add to queue
					queue.add(tempEdge.getTarget());
					//print out
					System.out.println(tempEdge.getTarget().getName() + ",  " 
							+ tempEdge.getTarget().getDistance() + ",  "
								+ tempVertex.getName());
				}
				tempEdge = tempEdge.getNext();
			}			
		}
		//repeat the same thing, only differnt is that
		//we will start from head and search for
		//unconnected one
		Vertex_Node start = head;
		//do untill end of the point
		while(start != null) {
			//if head is false, which mean unmarked
			if(start.getMark()==false) {
				tempVertex = start;
				start.setMark(true);
				start.setDistance(0);
				System.out.println(start.getName() + ",  " 
						+ start.getDistance() + ",  " + "null");
				//set print for system.out.print by using remove
				queue.add(start);
				while(!queue.isEmpty()) {				
					tempVertex = queue.remove();
					tempEdge = tempVertex.getNbrList();
					while(tempEdge != null) {
						if(tempEdge.getTarget().getMark()==false) {
							//mark it
							tempEdge.getTarget().setMark(true);
							//set parent
							//tempEdge.getTarget().setParent(tempVertex);
							//set distance
							tempEdge.getTarget().setDistance(tempVertex.getDistance()+1);
							//add to queue
							queue.add(tempEdge.getTarget());
							//print out
							System.out.println(tempEdge.getTarget().getName() + ",  " 
									+ tempEdge.getTarget().getDistance() + ",  "
										+ tempVertex.getName());
						}
						tempEdge = tempEdge.getNext();
					}			
				}
			}
			start = start.getNext();
		}
		
	}
	
	public void output_dfs(Vertex_Node s) {
		
	}
		// If you implemented DFS then leave this method the way it is
		// If you did not implement DFS then change the ¡°true¡± to ¡°false¡±
	public static boolean implementedDFS() {
		return false;
	}
	public static String myName() {
		return "Haemin Lee prog 4 MW";
	}
}