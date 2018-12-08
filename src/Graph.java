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
}
public class Graph {
	
	ArrayList<Voxe>graph = new ArrayList<Voxe>();
	AdjacencyList graph1 = new AdjacencyList();
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
		return "1";
	 }

	 // the following method adds a vertex to the graph [2 pts]
	public void insertVertex(String strUniqueID,String strData,int nX, int nY ) throws GraphException{
		if(vertexExists(strUniqueID)) throw new GraphException("Vertex with id "+strUniqueID+" already exists!");
		Vertex v= new Vertex(strUniqueID,strData,nX,nY);
		Voxe joey=new Voxe(v);
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
			GraphException hisham = new GraphException("One of the vertices is missssssiiiiiiinnnnnggggg");
			throw hisham;
		}
		else{
			Edge e = new Edge(strEdgeUniqueID, strEdgeData, nEdgeCost,strVertex2UniqueID,strVertex1UniqueID);
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
		//boolean flagil = false;
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
						Visitor.visit(voxe.vertex);
						stack.push(voxe);
						break;
					}
					else{
						edges.get(i).label = "BACK";
					}
				}
			}
			stack.pop();
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
		Queue<Voxe> queue = new Queue<Voxe>();
		Voxe voxe = this.graph.get(searchB(strStartVertexUniqueID));
		queue.add(voxe);
		voxe.vertex.label = "VISITED";
		visitor.visit(voxe.vertex);
		Voxe voxe2;
		while(!queue.isEmpty()){
			ArrayList<Edge> edges = queue.remove().edges;
			for(int i=0; i<edges.size(); i++){
				if(edges.get(i).label.equals("UNEXPLORED")){
					
					if(edges.get(i).lvid.equals(queue.peek().vertex.getUniqueID())){
						voxe2 = this.graph.get(searchB(edges.get(i).rvid));
					}
					else{
						voxe2 = this.graph.get(searchB(edges.get(i).lvid));
					}
					// other explored or not
					if(vox2.vertex.label.equals("UNEXPLORED")){
						visitor.visit(edges.get(i));
						edges.get(i).label = "DISCOVERY";
						voxe2.vertex.label = "VISITED";
						Visitor.visit(voxe2.vertex);
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
		//boolean flagil = false;
		B ziad=searchB(strStartVertexUniqueID);
		//ziad.vertex.label="visited";
		//visitor.visit(ziad.vertex);
		Stack <B> s=  new Stack<B>();
		Stack <A> s1=  new Stack<A>();
		B floor = new B(new Vertex("hahahahahahahahahahahahahahahahahahahahahahahahafgn;ajfehu","khfa",0,0));
		A floor1 = new A(new Edge("$","a",0,"a","w"));
		s.push(floor);
		s1.push(floor1);
		A mark = ziad.a;
		while(!(s.peek().vertex.getUniqueID().equals("hahahahahahahahahahahahahahahahahahahahahahahahafgn;ajfehu"))){
			//
			mark = ziad.a;
			if(!ziad.vertex.label.equals("visited")){
				ziad.vertex.label="visited";
				//visitor.visit(ziad.vertex);
				s.push(ziad);
				break;
			} 
			while(true){
				if(mark.edge.label.equals("undiscovered")){
					mark.edge.label= "back";
					B posNewZiad;
					//visitor.visit(mark.edge);
					if(mark.edge.rvid.equals(ziad.vertex.getUniqueID())){
						posNewZiad=searchB(ziad.a.edge.lvid);
					}
					else{
						posNewZiad=searchB(ziad.a.edge.rvid);
					}
					if(posNewZiad.vertex.label.equals("undiscovered")){
						ziad = posNewZiad;
						mark.edge.label= "discovery";
						s1.push(mark);
						break;
					}
				}
				mark=mark.next;
				if(mark==null){
					s.pop();
					s1.pop();
					ziad=s.peek();
					break;
				}
			}
			
			
			//
		}
		if(s.peek().vertex.getUniqueID().equals("hahahahahahahahahahahahahahahahahahahahahahahahafgn;ajfehu")){
			return null;
		}
		Vector<PathSegment> pathdfs = new Vector<PathSegment>();
		B[] verticies = new B[s.size()];
		A[] edges = new A[s.size()];
		int i;
		while(!(s.peek().vertex.getUniqueID().equals("hahahahahahahahahahahahahahahahahahahahahahahahafgn;ajfehu"))){
			verticies[s.size()-1]=s.pop();
		}
		while(!(s1.peek().edge.getUniqueID().equals("$"))){
			edges[s1.size()-1]=s1.pop();
		}
		if(verticies.length==(edges.length+1)){
			System.out.println("Correct maths");
		}
		PathSegment pathSegment;
		Vertex vertex;
		Edge edge;
		for(i=0;i<edges.length;i++){
			edge = edges[i].edge;
			vertex = verticies[i].vertex;
			pathSegment = new PathSegment(vertex, edge);
			pathdfs.add(pathSegment);
		}
		return pathdfs;
				
	}
//	// finds the closest pair of vertices using divide and conquer
//	// algorithm. Use X and Y attributes in each vertex. [30 pts]
	public Vertex[] closestPair() throws GraphException {
		if(this.graph1.head==null || this.graph1.head.next==null)
			throw new GraphException("Must initialize graph with atleast 2 verticies first");
		B bContainingVertex = this.graph1.head;
		Stack<B> tempBStack = new Stack<B>();
		while(bContainingVertex!=null){
			tempBStack.push(bContainingVertex);
			bContainingVertex=bContainingVertex.next;
		}
		ArrayList<Vertex> verticies = new ArrayList<Vertex>();
		while(!tempBStack.isEmpty()){
			verticies.add(tempBStack.pop().vertex);
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
	public static void main(String[]args) throws GraphException{
		// Vertex v1 = new Vertex("s","d",0,4);
		// Vertex v2 = new Vertex("s1","d1",1,4);
		//Vertex v3 = new Vertex("s2","d2",5,4);
		// ArrayList<Vertex> v0 = new ArrayList<Vertex>();
		// v0.add(v1);
		// v0.add(v2);
		// System.out.println(DistanceBetweenPoints(v0));
		Graph g = new Graph();
		// g.prinG();
		g.insertVertex("s","d",0,4);
		// g.prinG();
		g.insertVertex("s1","d1",1,4);
		// g.prinG();
		g.insertVertex("s2","d2",5,4);
		// g.prinG();
		g.insertEdge("s", "s1", "e", "ed", 1);
		g.insertEdge("s", "s2", "e2", "ed1", 1);
		g.insertEdge("s1", "s2", "e12", "ed2", 1);
		// System.out.println("hi joey");
		//g.prinG();
		// g.removeVertex("s2");
		// g.prinG();
		// g.removeEdge("e");
		// g.prinG();
		System.out.println("\n\n\n\n\n\n\n");
		Vector<Edge> incEdges = g.incidentEdges("s");
		System.out.println("incident for s");
		for(int i = 0; i<incEdges.size();i++){
			System.out.println(incEdges.get(i).getUniqueID());
		}
		// System.out.println("incident for s1");
		// incEdges = g.incidentEdges("s1");
		// for(int i = 0; i<incEdges.size();i++){
		// 	System.out.println(incEdges.get(i).getUniqueID());
		// }
		// System.out.println("incident for s2");
		// incEdges = g.incidentEdges("s2");
		// for(int i = 0; i<incEdges.size();i++){
		// 	System.out.println(incEdges.get(i).getUniqueID());
		// }
		// Vertex[] closestPai = g.closestPair();
		// System.out.println("X1: "+closestPai[0].getX()+" Y1: "+closestPai[0].getY()+" X2: "+closestPai[1].getX()+ " Y2: "+closestPai[1].getY()+'.');
		// boolean f5 = false;
		// System.out.println(60);

	}
	public void prinG(){
		B ml = graph1.head;
		while(ml != null){
			System.out.println("\nVertex: "+ml);
			A dw = ml.a;
			while(dw != null){
				System.out.println("Vertex: "+ml+"  Edge: "+dw+", ");
				dw = dw.next;
			}
			ml = ml.next;
		}
		if(graph1.head == null){
			System.out.println("nothing yet :)");
		}
	}
//
//	
//	
}
