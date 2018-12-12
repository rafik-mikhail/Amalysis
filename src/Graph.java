import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;
import java.util.Collections;
import java.lang.*;
//import java.util.List;
class Voxe {
	Vertex vertex;
	ArrayList<Edge> edges;
	public Voxe (Vertex vertex){
		this.vertex = vertex;
		this.edges = new ArrayList<Edge>();
	}
	public String toString(){
		String toBeReturned = "Vertex: ";
		toBeReturned += this.vertex.getUniqueID()+" Edges:\n";
		for(int i=0; i<this.edges.size(); i++){
			toBeReturned += this.edges.get(i).getUniqueID()+" ";
		}
		return toBeReturned;
	}
}
class Finalq{
	double distance = Double.POSITIVE_INFINITY;
	Vector<PathSegment> path = new Vector<PathSegment>();
}
public class Graph {
	
	ArrayList<Voxe>graph = new ArrayList<Voxe>();
	ArrayList<Edge>allEdges = new ArrayList<Edge>();
	public boolean edgeExists(String strEdgeUniqueID){
		for(int i=0; i<this.allEdges.size(); i++){
			if(this.allEdges.get(i).getUniqueID().equals(strEdgeUniqueID)){
				return true;
			}
		}
		return false;
	}
	public boolean vertexExists(String strVertexUniqueID){
		for(int i=0; i<this.graph.size(); i++){
			if(this.graph.get(i).vertex.getUniqueID().equals(strVertexUniqueID)){
				return true;
			}
		}
		return false;
	}
	public void removeEasyEdge(String strEdgeUniqueID){
		for(int i=0; i<this.allEdges.size(); i++){
			if(this.allEdges.get(i).getUniqueID().equals(strEdgeUniqueID)){
				this.allEdges.remove(i);
				return;
			}
		}
		System.out.println("edge "+strEdgeUniqueID+" wasn't found(removeEasyEdge)");
	}
	public void removeEasyVertex(String strVertexUniqueID){
		for(int i=0; i<this.graph.size();i++){
			if(this.graph.get(i).vertex.getUniqueID().equals(strVertexUniqueID)){
				this.graph.remove(i);
				return;
			}
		}
		System.out.println("vertex "+strVertexUniqueID+" wasn't found(removeEasyVertex)");
	}
	// returns the name you have given to this graph library [1 pt]
	 // choose whatever name you like!
	public String getLibraryName( ){
		return "MY Library";
	 }
	 // returns the current version number [1 pt]
	// read the following if you are wondering what this is ☺
	// https://en.wikipedia.org/wiki/Software_versioning
	public String getLibraryVersion( ){
		return "1.0.03";
	 }

	 // the following method adds a vertex to the graph [2 pts]
	public void insertVertex(String strUniqueID,String strData,int nX, int nY ) throws GraphException{
		if(vertexExists(strUniqueID)) throw new GraphException("Vertex with id "+strUniqueID+" already exists!");
		Vertex v= new Vertex(strUniqueID,strData,nX,nY);
		Voxe joey=new Voxe(v);
		this.graph.add(joey);
	}
	public void insertVertex(Vertex vertex) throws GraphException{
		if(vertexExists(vertex.getUniqueID())) throw new GraphException("Vertex with id "+vertex.getUniqueID()+" already exists!");
		Voxe joey=new Voxe(vertex);
		this.graph.add(joey);
	}
	// inserts an edge between 2 specified vertices [2 pts]
	public void insertEdge(String strVertex1UniqueID,String strVertex2UniqueID,String strEdgeUniqueID,String strEdgeData,int nEdgeCost) throws GraphException{
		if(edgeExists(strEdgeUniqueID)) throw new GraphException("Edge with id "+strEdgeUniqueID+" already exists!");
		boolean flagil1 = false;
		boolean flagil2 = false;
		int vertex1Pos = 0, vertex2Pos = 0;
		for(int i=0; i<this.graph.size();i++){
			if(this.graph.get(i).vertex.getUniqueID().equals(strVertex1UniqueID)){
				flagil1 = true;
				vertex1Pos = i;
			}
			else if(this.graph.get(i).vertex.getUniqueID().equals(strVertex2UniqueID)){
				flagil2 = true;
				vertex2Pos = i;
			}
		}
		if(!flagil1 || !flagil2){
			GraphException hisham = new GraphException("One of the vertices is missing");
			throw hisham;
		}
		else{
			Edge e = new Edge(strEdgeUniqueID, strEdgeData, nEdgeCost,strVertex2UniqueID,strVertex1UniqueID);
			this.graph.get(vertex1Pos).edges.add(e);
			this.graph.get(vertex2Pos).edges.add(e);
			this.allEdges.add(e);
		}
	}
	public void insertEdge(Edge e) throws GraphException{
		if(edgeExists(e.getUniqueID())) throw new GraphException("Edge with id "+e.getUniqueID()+" already exists!");
		boolean flagil1 = false;
		boolean flagil2 = false;
		int vertex1Pos = 0, vertex2Pos = 0;
		for(int i=0; i<this.graph.size();i++){
			if(this.graph.get(i).vertex.getUniqueID().equals(e.lvid)){
				flagil1 = true;
				vertex1Pos = i;
			}
			else if(this.graph.get(i).vertex.getUniqueID().equals(e.rvid)){
				flagil2 = true;
				vertex2Pos = i;
			}
		}
		if(!flagil1 || !flagil2){
			GraphException hisham = new GraphException("One of the vertices is missing");
			throw hisham;
		}
		else{
			this.graph.get(vertex1Pos).edges.add(e);
			this.graph.get(vertex2Pos).edges.add(e);
			this.allEdges.add(e);
		}
	}
	// removes vertex and its incident edges [1 pt]
	public void removeVertex(String strVertexUniqueID) throws GraphException{
		if(!vertexExists(strVertexUniqueID)) throw new GraphException("Vertex "+strVertexUniqueID+" Does not exist!");
		removeEasyVertex(strVertexUniqueID);
		for(int i=0; i<this.graph.size(); i++){
			for(int j=0; j<this.graph.get(i).edges.size(); j++){
				if((this.graph.get(i).edges.get(j).lvid.equals(strVertexUniqueID)) || 
					(this.graph.get(i).edges.get(j).rvid.equals(strVertexUniqueID))){
					
					String edgeid = this.graph.get(i).edges.get(j).getUniqueID();
					removeEasyEdge(edgeid);
					this.graph.get(i).edges.remove(j);
				}
			}
		}
	}
	// removes an edge from the graph [1 pt]
	public void removeEdge(String strEdgeUniqueID) throws GraphException{
		if(!edgeExists(strEdgeUniqueID)) throw new GraphException("Edge "+strEdgeUniqueID+" Does not exist!");
		removeEasyEdge(strEdgeUniqueID);
		int q = 0;
		for(int i=0; i<this.graph.size(); i++){
			for(int j=0; j<this.graph.get(i).edges.size(); j++){
				if(q>=2){break;}
				if(this.graph.get(i).edges.get(j).getUniqueID().equals(strEdgeUniqueID)){
					this.graph.get(i).edges.remove(j);
					q++;
				}
			}
		}
	}
	// returns a vector of edges incident to vertex whose
	 // id is strVertexUniqueID [1 pt]
	public Vector<Edge> incidentEdges(String strVertexUniqueID) throws GraphException{
		if(!vertexExists(strVertexUniqueID)) throw new GraphException("Vertex "+strVertexUniqueID+" Does not exist!");
		Vector<Edge> v = new Vector<Edge>();
		for(int i=0; i<this.graph.size(); i++){
			if(this.graph.get(i).vertex.getUniqueID().equals(strVertexUniqueID)){
				for(int j=0; j<this.graph.get(i).edges.size(); j++){
					v.add(this.graph.get(i).edges.get(j));
				}
				break;
			}
		}
		return v;
	}
	 // returns all vertices in the graph [1 pt]
	public Vector<Vertex> vertices()throws GraphException{
		Vector<Vertex> v = new Vector<Vertex>();
		for(int i=0; i<this.graph.size(); i++){
			v.add(this.graph.get(i).vertex);
		}
		return v;
	}
	// returns all edges in the graph [1 pt]
	public Vector<Edge> edges() throws GraphException{
		Vector<Edge> v = new Vector<Edge>();
		for(int i=0; i<this.allEdges.size(); i++){
			v.add(this.allEdges.get(i));
		}
		return v;
	}
	// returns an array of the two end vertices of the
	 // passed edge [1 pt]
	public Vertex[] endVertices(String strEdgeUniqueID)throws GraphException{
		if(!edgeExists(strEdgeUniqueID)) throw new GraphException("Edge "+strEdgeUniqueID+" Does not exist!");
		Vertex[]v =new Vertex[2];
		String id1 = "";
		String id2 = "";
		for(int i=0; i<this.allEdges.size(); i++){
			if(this.allEdges.get(i).getUniqueID().equals(strEdgeUniqueID)){
				id1 = this.allEdges.get(i).lvid;
				id2 = this.allEdges.get(i).rvid;
				break;
			}
		}
		int count = 0;
		for(int i=0; i<this.graph.size(); i++){
			if(count>=2){break;}
			if(this.graph.get(i).vertex.getUniqueID().equals(id1)){
				v[0] = this.graph.get(i).vertex;
				count++;
			}
			if(this.graph.get(i).vertex.getUniqueID().equals(id2)){
				v[1] = this.graph.get(i).vertex;
				count++;
			}
		}
		return v;
	}
	// returns the vertex opposite of another vertex [1 pt]
	public Vertex opposite(String strVertexUniqueID,String strEdgeUniqueID) throws GraphException{
		if(!edgeExists(strEdgeUniqueID)) throw new GraphException("Edge "+strEdgeUniqueID+" Does not exist!");
		String id1 = "";
		for(int i=0; i<this.allEdges.size(); i++){
			if(this.allEdges.get(i).getUniqueID().equals(strEdgeUniqueID)){
				if(this.allEdges.get(i).lvid.equals(strVertexUniqueID)){
					id1 = this.allEdges.get(i).rvid;
				}
				else{
					id1 = this.allEdges.get(i).lvid;
				}
				break;
			}
		}
		for(int i=0; i<this.graph.size(); i++){
			if(this.graph.get(i).vertex.getUniqueID().equals(id1)){
				return this.graph.get(i).vertex;
			}
		}
		return null;
	}

//	// performs depth first search starting from passed vertex
//	// visitor is called on each vertex and edge visited. [12 pts]
	public void dfs(String strStartVertexUniqueID, Visitor visitor) throws GraphException{ 
		if(!vertexExists(strStartVertexUniqueID)) throw new GraphException("Vertex "+strStartVertexUniqueID+" Does not exist!");
		for(int i=0; i<this.graph.size(); i++){
			this.graph.get(i).vertex.label = "UNEXPLORED";
		}
		for(int i=0; i<this.allEdges.size(); i++){
			this.allEdges.get(i).label = "UNEXPLORED";
		}
		Voxe voxe = this.graph.get(searchB(strStartVertexUniqueID));
		Stack <Voxe> stack=  new Stack<Voxe>();
		stack.push(voxe);
		boolean flagil = true;
		voxe.vertex.label="VISITED";
		visitor.visit(voxe.vertex);
		while(!stack.isEmpty()){
			ArrayList<Edge> edges = stack.peek().edges;
			for(int i=0; i<edges.size(); i++){
				if(edges.get(i).label.equals("UNEXPLORED")){
					
					if(edges.get(i).lvid.equals(stack.peek().vertex.getUniqueID())){
						voxe = this.graph.get(searchB(edges.get(i).rvid));
					}
					else{
						voxe = this.graph.get(searchB(edges.get(i).lvid));
					}
					// other explored or not
					if(voxe.vertex.label.equals("UNEXPLORED")){
						visitor.visit(edges.get(i));
						edges.get(i).label = "DISCOVERY";
						voxe.vertex.label = "VISITED";
						visitor.visit(voxe.vertex);
						stack.push(voxe);
						flagil = false;
						break;
					}
					else{
						edges.get(i).label = "BACK";
					}
				}
			}
			if(flagil){
				stack.pop();
			}
			flagil = true;
		}
	}
	public int searchB(String UID){
		int i;
		for(i=0; i<this.graph.size(); i++){
			if(this.graph.get(i).vertex.getUniqueID().equals(UID)){
				break;
			}
		}
		return i;  
	}	
//
//
//	 // performs breadth first search starting from passed vertex
//	 // visitor is called on each vertex and edge visited. [17 pts]
	 public void bfs(String strStartVertexUniqueID,
	 Visitor visitor) throws GraphException{

		if(!vertexExists(strStartVertexUniqueID)) throw new GraphException("Vertex "+strStartVertexUniqueID+" Does not exist!");
		for(int i=0; i<this.graph.size(); i++){
			this.graph.get(i).vertex.label = "UNEXPLORED";
		}
		for(int i=0; i<this.allEdges.size(); i++){
			this.allEdges.get(i).label = "UNEXPLORED";
		}
		Queue<Voxe> queue = new LinkedList<Voxe>();
		Voxe voxe = this.graph.get(searchB(strStartVertexUniqueID));
		queue.add(voxe);
		voxe.vertex.label = "VISITED";
		visitor.visit(voxe.vertex);
		Voxe voxe2;
		while(!queue.isEmpty()){
			voxe = queue.remove();
			ArrayList<Edge> edges = voxe.edges;
			for(int i=0; i<edges.size(); i++){
				if(edges.get(i).label.equals("UNEXPLORED")){
					
					if(edges.get(i).lvid.equals(voxe.vertex.getUniqueID())){
						voxe2 = this.graph.get(searchB(edges.get(i).rvid));
					}
					else{
						voxe2 = this.graph.get(searchB(edges.get(i).lvid));
					}
					// other explored or not
					if(voxe2.vertex.label.equals("UNEXPLORED")){
						visitor.visit(edges.get(i));
						edges.get(i).label = "DISCOVERY";
						voxe2.vertex.label = "VISITED";
						visitor.visit(voxe2.vertex);
						queue.add(voxe2);
					}
					else{
						edges.get(i).label = "CROSS";
					}
				}
			}
		}
	 }
//	// returns a path between start vertex and end vertex
//	// if exists using dfs. [18 pts]
	public Vector<PathSegment> pathDFS(String strStartVertexUniqueID, String strEndVertexUniqueID) throws GraphException{
		if(!vertexExists(strStartVertexUniqueID)) throw new GraphException("Vertex "+strStartVertexUniqueID+" Does not exist!");
		if(!vertexExists(strEndVertexUniqueID)) throw new GraphException("Vertex "+strEndVertexUniqueID+" Does not exist!");
		for(int i=0; i<this.graph.size(); i++){
			this.graph.get(i).vertex.label = "UNEXPLORED";
		}
		for(int i=0; i<this.allEdges.size(); i++){
			this.allEdges.get(i).label = "UNEXPLORED";
		}
		Voxe voxe = this.graph.get(searchB(strStartVertexUniqueID));
		Stack <Voxe> stack=  new Stack<Voxe>();
		Stack <Edge> edgeStack = new Stack<Edge>();
		boolean flagil = true;
		stack.push(voxe);
		voxe.vertex.label="VISITED";
		while(!(stack.peek().vertex.getUniqueID().equals(strEndVertexUniqueID))){
			ArrayList<Edge> edges = stack.peek().edges;
			for(int i=0; i<edges.size(); i++){
				if(edges.get(i).label.equals("UNEXPLORED")){
					
					if(edges.get(i).lvid.equals(stack.peek().vertex.getUniqueID())){
						voxe = this.graph.get(searchB(edges.get(i).rvid));
					}
					else{
						voxe = this.graph.get(searchB(edges.get(i).lvid));
					}
					// other explored or not
					if(voxe.vertex.label.equals("UNEXPLORED")){
						edges.get(i).label = "DISCOVERY";
						edgeStack.push(edges.get(i));
						voxe.vertex.label = "VISITED";
						stack.push(voxe);
						flagil = false;
						break;
					}
					else{
						edges.get(i).label = "BACK";
					}
				}
			}
			if(flagil){
				if(stack.size()>1){
					edgeStack.pop();
				}
				stack.pop();
				if(stack.isEmpty()){
					throw new GraphException("NO PATH EXISTS!");
				}
			}
			flagil = true;
		}
		Vector<PathSegment> pathdfs = new Vector<PathSegment>();
		Vertex[] verticies = new Vertex[stack.size()];
		Edge[] edges = new Edge[edgeStack.size()];
		int i;
		while(!(stack.isEmpty())){
			verticies[stack.size()-1]=stack.pop().vertex;
		}
		while(!(edgeStack.isEmpty())){
			edges[edgeStack.size()-1]=edgeStack.pop();
		}
		PathSegment pathSegment;
		Vertex vertex;
		Edge edge;
		for(i=0;i<edges.length;i++){
			edge = edges[i];
			vertex = verticies[i];
			pathSegment = new PathSegment(vertex, edge);
			pathdfs.add(pathSegment);
		}
		return pathdfs;		
	}
//	// finds the closest pair of vertices using divide and conquer
//	// algorithm. Use X and Y attributes in each vertex. [30 pts]
	public Vertex[] closestPair() throws GraphException {
		if(this.graph.size()<2) throw new GraphException("Graph must contain atleast two verticies before you can find closest PAIR");
		ArrayList<Vertex> verticies = new ArrayList<Vertex>();
		for(int i=0; i<this.graph.size(); i++){
			verticies.add(this.graph.get(i).vertex);
		}
		Collections.sort(verticies);

		ArrayList<Vertex> tobereturnedaftermod = closestRecursivePair(verticies);
		Vertex[] tobereturned = new Vertex[2];
		tobereturned[0] = tobereturnedaftermod.get(0);
		tobereturned[1] = tobereturnedaftermod.get(1);
		return tobereturned;

	}
	public static ArrayList<Vertex> closestRecursivePair(ArrayList<Vertex> points) throws  GraphException{
		if(points.size()==2)
			return points;
		if(points.size()==1)
			return null;
		if(points.size()==0)
			throw new GraphException("Logical closest recursive pair error");
		ArrayList<Vertex> call1 = new ArrayList<Vertex>();
		ArrayList<Vertex> call2 = new ArrayList<Vertex>();
		int i = 0;
		boolean switchh = false;   
		while(i<points.size()/2){
			call1.add(points.get(i));
			i++;
		}
		while(i<points.size()){
			call2.add(points.get(i));
			i++;
		}
		ArrayList<Vertex> firstCall = closestRecursivePair(call1);
		ArrayList<Vertex> secondCall = closestRecursivePair(call2);
		double distance1 = 709;
		double distance2 = 911;
		boolean d1, d2;
		double min = 77;
		if(firstCall != null){
			d1 = true;
			distance1 = DistanceBetweenPoints(firstCall);
		}
		else d1 = false;
		if(secondCall != null){
			d2 = true;
			distance2 = DistanceBetweenPoints(secondCall);
		}
		else d2 = false;
		// picking minimum
		if(!d1){
			if(d2){   // d2 & !d1
				min = distance2;
			}
			else{  // shouldn't hit,, meaans both came are size 1, aka hit size == 2 anchor case
				return null;
			}
		}
		else{
			if(d2){  // d2 & d1
				if(distance1 < distance2){
					switchh = false;
					min = distance1;
				}
				else{
					switchh = true;
					min = distance2;
				}
			}
			else{  // !d2 & d1
				min = distance1;
			}
		}
		ArrayList<Vertex> mCall = closeronborder(points,min);
		if(mCall != null){
			return mCall;
		}
		if(switchh){
			return secondCall;
		}
		return firstCall;
	}
	public static ArrayList<Vertex> closeronborder(ArrayList<Vertex> points, double min){
	   	int xFirst = points.get((points.size()/2)-1).getX();        // x value of last vertex in first... will be used to check verticies in second that fall within "min value"
	   	int xSecond = points.get(points.size()/2).getX();        // x value of first vertex in second... will be used to check verticies in first that fall within "min value"
		Vertex vertex;
		ArrayList<Vertex> brutalBruteToBeBruten1 = new ArrayList<Vertex>();
		ArrayList<Vertex> brutalBruteToBeBruten2 = new ArrayList<Vertex>();
		//  getting elements in range
		for(int i=(points.size()/2)-1; i>=0; i--){
			vertex = points.get(i);
			if(Math.abs(xSecond - vertex.getX()) < min){
				brutalBruteToBeBruten1.add(vertex);
			}
			else{
				break;
			}
		}

		for(int i=points.size()/2; i<points.size(); i++){
			vertex = points.get(i);
			if(Math.abs(vertex.getX() - xFirst) < min){
				brutalBruteToBeBruten2.add(vertex);
			}
			else{
				break;
			}
		}
		Vertex vertex2;
		ArrayList<Vertex> disdancer = new ArrayList<Vertex>();  // for distances
		ArrayList<Vertex> series = null;  //to be continued... (returned?)
		//  
		for(int i=0; i<brutalBruteToBeBruten1.size(); i++){                             // getting brutal
			vertex = brutalBruteToBeBruten1.get(i);
			for(int j=0; j<brutalBruteToBeBruten2.size(); j++){
				vertex2 = brutalBruteToBeBruten2.get(j);
				disdancer.clear();
				disdancer.add(vertex);
				disdancer.add(vertex2);
				double dist = DistanceBetweenPoints(disdancer);
				if(dist < min){
					min = dist;
					series = disdancer;
				}

			}
		}

		return series;
	}
	public static double DistanceBetweenPoints(ArrayList<Vertex> points){
		Vertex vertex1 =points.get(0);
		Vertex vertex2 =points.get(1);
		int x1 =vertex1.getX();
		int y1 =vertex1.getY();
		int x2 =vertex2.getX();
		int y2 =vertex2.getY();
		int difference1 = x1-x2;
		int difference2 = y1-y2;
		int square1 = difference1 * difference1;
		int square2 = difference2 * difference2;
		double distanceBetweenPoints = Math.sqrt(square2 + square1);
		return distanceBetweenPoints;
	}
	public static boolean dfsspan(Graph g) throws GraphException{ 
		for(int i=0; i<g.graph.size(); i++){
			g.graph.get(i).vertex.label = "UNEXPLORED";
		}
		for(int i=0; i<g.allEdges.size(); i++){
			g.allEdges.get(i).label = "UNEXPLORED";
		}
		Voxe voxe = g.graph.get(0);
		Stack <Voxe> stack=  new Stack<Voxe>();
		stack.push(voxe);
		boolean flagil = true;
		voxe.vertex.label="VISITED";
		while(!stack.isEmpty()){
			ArrayList<Edge> edges = stack.peek().edges;
			for(int i=0; i<edges.size(); i++){
				if(edges.get(i).label.equals("UNEXPLORED")){
					
					if(edges.get(i).lvid.equals(stack.peek().vertex.getUniqueID())){
						voxe = g.graph.get(g.searchB(edges.get(i).rvid));
					}
					else{
						voxe = g.graph.get(g.searchB(edges.get(i).lvid));
					}
					// other explored or not
					if(voxe.vertex.label.equals("UNEXPLORED")){
						edges.get(i).label = "DISCOVERY";
						voxe.vertex.label = "VISITED";
						stack.push(voxe);
						flagil = false;
						break;
					}
					else{
						edges.get(i).label = "BACK";
						return true;
					}
				}
			}
			if(flagil){
				stack.pop();
			}
			flagil = true;
		}
		return false;
	}	
	
	// finds a minimum spanning tree using kruskal greedy algorithm
// and returns the path to achieve that. Use Edge._nEdgeCost
// attribute in finding the min span tree
public Vector<PathSegment> minSpanningTree() throws GraphException{
	ArrayList<Edge> hesham = new ArrayList<Edge>();
	for(int i=0; i<this.allEdges.size(); i++){
		hesham.add(this.allEdges.get(i));
	}
	Collections.sort(hesham);
	Graph g = new Graph();
	for(int i=0; i<this.graph.size(); i++){
		g.insertVertex(this.graph.get(i).vertex);
	}
	for(int i=0; i<hesham.size(); i++){
		g.insertEdge(hesham.get(i));
		if(dfsspan(g)){
			g.removeEdge(hesham.get(i).getUniqueID());
		}
	}
	Vector<PathSegment> answer = new Vector<PathSegment>();
	for(int i=0; i<g.allEdges.size(); i++){
		answer.add(new PathSegment(null, g.allEdges.get(i)));
	}
	return answer;
}
// finds shortest paths using bellman ford dynamic programming
// algorithm and returns all such paths starting from given
// vertex. Use Edge._nEdgeCost attribute in finding the
// shortest path
public Vector<Vector<PathSegment>> findShortestPathBF(String strStartVertexUniqueID) throws GraphException{
	if(!vertexExists(strStartVertexUniqueID)) throw new GraphException("Start vertex does not exist!");
	Finalq[][] distances = new Finalq[this.graph.size()][this.graph.size()-1];
	int t = searchB(strStartVertexUniqueID);
	for(int i=0; i<this.graph.size(); i++){
		for(int j=0; j<this.graph.size()-1; j++){
			distances[i][j] = new Finalq();
			if(i==t){
				distances[i][j].distance = 0;
			}
		}
	}
	for (int i=1 ; i < this.graph.size()-1; i++) {
        for (int j = 0; j < this.graph.size(); j++) {
			if(j!=t){
				double min = distances[j][i-1].distance;
				Vector<PathSegment> pat = (Vector)distances[j][i-1].path.clone();
				for(int k=0; k<this.graph.get(j).edges.size(); k++){
					int pos;
					if(this.graph.get(j).edges.get(k).lvid.equals(this.graph.get(j).vertex.getUniqueID())){
						pos = searchB(this.graph.get(j).edges.get(k).rvid);
					}
					else{
						pos = searchB(this.graph.get(j).edges.get(k).lvid);
					}
					if(distances[pos][i-1].distance+this.graph.get(j).edges.get(k).getCost()<min){
						min = distances[pos][i-1].distance+this.graph.get(j).edges.get(k).getCost();
						pat =(Vector) distances[pos][i-1].path.clone();
						pat.add(new PathSegment(this.graph.get(pos).vertex,this.graph.get(j).edges.get(k)));
					}
				}
				distances[j][i].distance = min;
				distances[j][i].path = pat;
			}
        } 
	} 
	Vector<Vector<PathSegment>> answerResult = new Vector<Vector<PathSegment>>();
	for(int i=0; i<this.graph.size(); i++){
		if(i!=t){
			answerResult.add(distances[i][this.graph.size()-2].path);
		}
	}
	return answerResult;
}
// finds all shortest paths using Floyd–Warshall dynamic
// programming algorithm and returns all such paths. Use
// Edge._nEdgeCost attribute in finding the shortest path
//
public Vector<Vector<PathSegment>> findAllShortestPathsFW() throws GraphException {
	Finalq[][] distances = new Finalq[this.graph.size()][this.graph.size()];
	for(int i=0; i<this.graph.size(); i++){
		for(int j=0; j<this.graph.size(); j++){
			distances[i][j] = new Finalq();
		}
	}
	for(int i=0; i<this.graph.size(); i++){
		distances[i][i].distance = 0;
	}
	for(int i=0; i<this.allEdges.size(); i++){
		distances[searchB(this.allEdges.get(i).lvid)][searchB(this.allEdges.get(i).rvid)].distance = this.allEdges.get(i).getCost();
		distances[searchB(this.allEdges.get(i).rvid)][searchB(this.allEdges.get(i).lvid)].distance = this.allEdges.get(i).getCost();
		distances[searchB(this.allEdges.get(i).lvid)][searchB(this.allEdges.get(i).rvid)].path.add(new PathSegment(this.graph.get(searchB(this.allEdges.get(i).lvid)).vertex ,this.allEdges.get(i)));
		distances[searchB(this.allEdges.get(i).rvid)][searchB(this.allEdges.get(i).lvid)].path.add(new PathSegment(this.graph.get(searchB(this.allEdges.get(i).rvid)).vertex ,this.allEdges.get(i)));
	}
	for(int k=0; k<this.graph.size(); k++){
		for(int i=0; i<this.graph.size(); i++){
			for(int j=0; j<this.graph.size(); j++){
				if(distances[i][j].distance > distances[i][k].distance + distances[k][j].distance){
					distances[i][j].distance = distances[i][k].distance + distances[k][j].distance;
					distances[i][j].path = (Vector) (distances[i][k].path.clone());
					distances[i][j].path.addAll(distances[k][j].path);
				}
			}
		}
	}
	Vector<Vector<PathSegment>> answerResult = new Vector<Vector<PathSegment>>();
	for(int i=0; i<this.graph.size(); i++){
		for(int j=i+1; j<this.graph.size(); j++){
			if(i!=j){
				answerResult.add(distances[i][j].path);
			}
		}
	}
	return answerResult;
}
	public static void main(String[]args) throws GraphException{
		Graph g = new Graph();
		System.out.println("LIBRARY NAME");
		System.out.println(g.getLibraryName());
		System.out.println("LIBRARY VERSION");
		System.out.println(g.getLibraryVersion());
		System.out.println("INSERTIONS");
		g.insertVertex("A", "`", 0, 0);
		g.insertVertex("B", "`", 0, 3);
		g.insertVertex("C", "`", 0, 5);
		g.insertVertex("D", "`", 0, 7);
		g.insertVertex("E", "`", 0, 9);
		g.insertVertex("F", "`", 0, 11);
		g.insertVertex("G", "`", 6, 1);
		g.insertVertex("H", "`", 6, 1);
		g.insertEdge("A", "C", "1", "`", 5);
		g.insertEdge("A", "D", "2", "`", 2);
		g.insertEdge("D", "B", "3", "`", 4);
		g.insertEdge("B", "F", "4", "`", 8);
		g.insertEdge("F", "H", "5", "`", 6);
		g.insertEdge("D", "H", "6", "`", 7);
		g.insertEdge("E", "H", "7", "`", 2);
		g.insertEdge("E", "G", "8", "`", 5);
		g.insertEdge("G", "C", "9", "`", 4);
		g.insertEdge("E", "D", "0", "`", 3);
		g.prinG();
		System.out.println("REMOVE A");
		g.removeVertex("A");
		g.prinG();
		System.out.println("REMOVE EDGE 7");
		g.removeEdge("7");
		g.prinG();
		System.out.println("RE-INSERT BOTH AND A's EDGES");
		g.insertEdge("E", "H", "7", "`", 2);
		g.insertVertex("A", "`", 0, 0);
		g.insertEdge("A", "C", "1", "`", 5);
		g.insertEdge("A", "D", "2", "`", 2);
		g.prinG();
		System.out.println("INCIDENT EGDES FOR C");
		Vector<Edge> incEdges = g.incidentEdges("C");
		for(int i = 0; i<incEdges.size();i++){
			System.out.println(incEdges.get(i).getUniqueID());
		}
		System.out.println("VERTICES:");
		Vector<Vertex> vs = g.vertices();
		for(int i = 0; i<vs.size();i++){
			System.out.println(vs.get(i).getUniqueID());
		}
		System.out.println("EDGES:");
		Vector<Edge> es = g.edges();
		for(int i = 0; i<es.size();i++){
			System.out.println(es.get(i).getUniqueID());
		}
		System.out.println("END VERTICES EDGE 2");
		System.out.println(g.endVertices("2")[0].getUniqueID());
		System.out.println(g.endVertices("2")[1].getUniqueID());
		System.out.println("OPPOSITE OF B ON EDGE 4");
		System.out.println(g.opposite("B","4").getUniqueID());
		Visitor visitor = new Visitor();
		System.out.println("DFS");
		g.dfs("A",visitor);
		System.out.println("BFS");
		g.bfs("A", visitor);
		System.out.println("PATH DFS FROM A TO F");
		Vector<PathSegment> patte = g.pathDFS("A", "F");
		System.out.println(patte);
		System.out.println("CLOSEST PAIR");
		Vertex[] closestPai = g.closestPair();
		System.out.println("p1: "+closestPai[0].getUniqueID()+" p2: "+closestPai[1].getUniqueID()+'.');
		System.out.println("MIN_SPANNING_TREE:");
		Vector<PathSegment> minty = g.minSpanningTree();
		for(int i = 0; i<minty.size();i++){
			System.out.println(minty.get(i).getEdge().getUniqueID());
		}
		System.out.println("BELLMAN FORD");
		Vector<Vector<PathSegment>> vect = g.findShortestPathBF("A");
		for(int i=0; i<vect.size(); i++){
			System.out.println("Path "+(i+1)+": "+vect.get(i));
		}
		System.out.println("FLOYD-WARSHALL");
		Vector<Vector<PathSegment>> vecto = g.findAllShortestPathsFW();
		for(int i=0; i<vecto.size(); i++){
			System.out.println("Path "+(i+1)+": "+vecto.get(i));
		}
	}
	public void prinG(){
		for(int i=0; i<this.graph.size(); i++){
			System.out.println(this.graph.get(i));
		}
	}
}
class Visitor{
	public void visit(Vertex vertex){
		System.out.println(vertex.getUniqueID());
	}
	public void visit(Edge edge){
		System.out.println(edge.getUniqueID());
	}
}