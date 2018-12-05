import java.awt.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;
import java.util.Collections;
import java.lang.*;
//import java.util.List;

public class Graph {
	
	 AdjacencyList graph = new AdjacencyList();
	 
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
		Vertex v= new Vertex(strUniqueID,strData,nX,nY);
		B joey=new B(v);
		if(graph.head == null){
			graph.head=joey;
		}
		else{
			//search
			boolean flagil = false;
			B ziad=graph.head;
			while(true){
				if(ziad.vertex.getUniqueID().equals(strUniqueID)){
					flagil = true;
					break;
				}
				if(ziad.next==null)
					break;
				ziad=ziad.next;
			}
			//search donee
			if(flagil){
				GraphException hisham = new GraphException("vertex already existsssssss!");
				throw hisham;
			}
			else{
				ziad=graph.head;
				while(true){
					if(ziad.next==null)
						break;
					ziad=ziad.next;
				}
				ziad.next=joey;
			}
		}
	}
	// inserts an edge between 2 specified vertices [2 pts]
	public void insertEdge(String strVertex1UniqueID,String strVertex2UniqueID,String strEdgeUniqueID,String strEdgeData,int nEdgeCost) throws GraphException{
		
		boolean flagil1 = false;
		boolean flagil2= false;
		
		B ziad=graph.head;
		if(graph.head == null){
			throw new GraphException("Must insert verticies before you insert edges");
		}
		// System.out.println("CHECKING BOTH VERTICIES EXIST");
		while(true){
			if(ziad.vertex.getUniqueID().equals(strVertex1UniqueID)){
				flagil1 = true;
				// System.out.println("vertex exists::::"+strVertex1UniqueID);
			}
			if(ziad.vertex.getUniqueID().equals(strVertex2UniqueID)){
				flagil2 = true;
				// System.out.println("vertex exists::::"+strVertex2UniqueID);
			}
			if(flagil1&&flagil2)
				break;
			if(ziad.next==null)
				break;
			ziad=ziad.next;
			// System.out.println(".");
		}
		if(!flagil1 || !flagil2){
			GraphException hisham = new GraphException("One of the vertices is missssssiiiiiiinnnnnggggg");
			throw hisham;
		}
		else{
			Edge e = new Edge(strEdgeUniqueID, strEdgeData, nEdgeCost,strVertex2UniqueID,strVertex1UniqueID);
			flagil1= false;
			flagil2= false;
			A a =null;
			//A aa =null;
			A joey= new A(e);
			ziad = graph.head;
			// System.out.println("Gonna insert");
			while(true){
				if(ziad.vertex.getUniqueID().equals(strVertex1UniqueID)){
					a = ziad.a;
					if(a != null){
						while(a.next!=null){
							a=a.next;
							System.out.println(".");
						}
						a.next= joey;
					}
					else{
						ziad.a = joey;
					}
					flagil1= true;
					// System.out.println("insert in vertex 1");
				}
				if(ziad.vertex.getUniqueID().equals(strVertex2UniqueID)){
					a = ziad.a;
					if(a != null){
						while(a.next!=null){
							System.out.println(".");
							a=a.next;
						}
						a.next= joey;
					}
					else{
						ziad.a = joey;
					}
					flagil2= true;
					// System.out.println("insert in vertex 2");
				}
				if(flagil1&&flagil2)
					break;
				
				ziad=ziad.next;
				// System.out.println(".");
			}
		}
		
	}

	// removes vertex and its incident edges [1 pt]
	public void removeVertex(String strVertexUniqueID) throws GraphException{

		boolean flagil = false;
		B ziad=graph.head;
		if(graph.head == null){
			throw new GraphException("Must initialize graph before you remove Vertex");
		}
		if(ziad.vertex.getUniqueID().equals(strVertexUniqueID)){
			flagil = true;
			graph.head = ziad.next;
		}
		B prev = graph.head;
		if(graph.head != null){
			ziad = graph.head.next;
		}
		else{
			return;
		}
		while(true){
			if(flagil)
				break;
			if(ziad.vertex.getUniqueID().equals(strVertexUniqueID)){
				flagil = true;
				prev.next = ziad.next;
				break;
			}
			if(ziad.next==null)
				break;
			prev= ziad;
			ziad=ziad.next;
		}
		if(!flagil){
			GraphException hisham = new GraphException("the vertex is missssssiiiiiiinnnnnggggg");
			throw hisham;
		}
		if(flagil){
			ziad=graph.head;
			while(true){
				A oshet= ziad.a;
				if(oshet != null){
					while(oshet.next!=null){
						if((oshet.edge.lvid.equals(strVertexUniqueID))||((oshet.edge.rvid.equals(strVertexUniqueID)))){
							removeEdge(oshet.edge.getUniqueID());
						}
						oshet=oshet.next;
					}
				}
				if(ziad.next==null){
					break;
				}
				ziad=ziad.next;
			}
		}
	}

	// removes an edge from the graph [1 pt]
	public void removeEdge(String strEdgeUniqueID) throws GraphException{
		if(graph.head == null){
			throw new GraphException("Must initialize graph before you remove Edges");
		}
		B ziad=graph.head;
		A ziada=ziad.a;
		A perv=ziada;
		boolean flagil1 = false;
		boolean flagil2 = false;
		boolean flagil3 = false;
		while(true){
			ziada=ziad.a;
			if(ziada != null){
				if(ziada.edge.getUniqueID().equals(strEdgeUniqueID)){
					ziad.a=ziada.next;
					if(flagil1){
						if(flagil2){
							if(flagil3){
								return;
							}
							else{
								flagil3 = true;
							}
						}
						else{
							flagil2 = true;
						}
					}
					else{
						flagil1 = true;
					}
				}	
				perv=ziada;
				while(ziada.next!=null){
					if(ziada.edge.getUniqueID().equals(strEdgeUniqueID)){
						perv.next=ziada.next;
						if(flagil1){
							if(flagil2){
								if(flagil3){
									return;
								}
								else{
									flagil3 = true;
								}
							}
							else{
								flagil2 = true;
							}
						}
						else{
							flagil1 = true;
						}
					}
					perv = ziada;
					ziada=ziada.next;
				}
			}
			if(ziad.next==null){
				break;
			}
			ziad=ziad.next;
		}
	}

	// returns a vector of edges incident to vertex whose
	 // id is strVertexUniqueID [1 pt]
	public Vector<Edge> incidentEdges(String strVertexUniqueID) throws GraphException{
		Vector<Edge> v = new Vector<Edge>();
		if(graph.head == null){
			throw new GraphException("Must initialize graph before you try to perform this kind of operation");
		}
		B ziad=graph.head;
		A ziada=ziad.a;

		while(true){
			if(ziad.vertex.getUniqueID().equals(strVertexUniqueID)){
				ziada=ziad.a;
				System.out.println("vertex exists::::"+strVertexUniqueID);
				int k =0;
				while((ziada != null)&(k<10)){
					v.add(ziada.edge);
					ziada = ziada.next;
					k++;
				}
			}
			if(ziad.next==null){
				break;
			}
			// System.out.println(".");
			ziad=ziad.next;
		}
		return v;
	}

	 

	 // returns all vertices in the graph [1 pt]
	public Vector<Vertex> vertices()throws GraphException{
		Vector<Vertex> v = new Vector<Vertex>();
		B ziad=graph.head;
		while(ziad.next!=null){
			v.add(ziad.vertex);
			ziad=ziad.next;
		}
		return v;
	}

	// returns all edges in the graph [1 pt]
	public Vector<Edge> edges() throws GraphException{
		Vector<Edge> v = new Vector<Edge>();
		B ziad=graph.head;
		A ziada=ziad.a;
		

		while(true){
			ziada=ziad.a;
			while(ziada.next!=null){
				v.add(ziada.edge);
				ziada=ziada.next;
			}
			if(ziad.next==null){
				break;
			}
			ziad=ziad.next;
		}
		return v;
	}

	// returns an array of the two end vertices of the
	 // passed edge [1 pt]
	public Vertex[] endVertices(String strEdgeUniqueID)throws GraphException{
		Vertex[]v =new Vertex[2];
		boolean flagil1 = false;
		boolean flagil2 = false;
		B ziad=graph.head;
		A ziada=ziad.a;
		A perv=ziada;
		String ID1="";
		String ID2="";
		while(true){
			ziada=ziad.a;
			
			while(ziada.next!=null){
				if((ziada.edge.getUniqueID().equals(strEdgeUniqueID))){
					ID1=(ziada.edge.lvid);
					ID2=(ziada.edge.rvid);
					break;
				
				}
				ziada=ziada.next;
			}
			if(ziad.next==null){
				break;
			}
			ziad=ziad.next;
		}
		 ziad=graph.head;
		while(true){
			if(ziad.vertex.getUniqueID().equals(ID1)){
				v[0]= ziad.vertex;
				flagil1=true;
			}
			if(ziad.vertex.getUniqueID().equals(ID2)){
				v[1]= ziad.vertex;
				flagil2=true;
			}
			if(flagil1&&flagil2)
				break;
			if(ziad.next==null)
				break;
			ziad=ziad.next;
		}
		return v;
	}
	// returns the vertex opposite of another vertex [1 pt]
	public Vertex opposite(String strVertexUniqueID,String strEdgeUniqueID) throws GraphException{
		boolean flagil = false;
		B ziad=graph.head;
		A ziada=ziad.a;
		String savar="";
		while(true){
			ziada=ziad.a;
			while(ziada.next!=null){
				if(ziada.edge.getUniqueID().equals(strEdgeUniqueID)){
					if(ziada.edge.lvid.equals(strVertexUniqueID)){
						savar = ziada.edge.rvid;
					}else{
						savar = ziada.edge.lvid;
					}
					break;
				}
				ziada=ziada.next;
			}
			if(ziad.next==null){
				break;
			}
			ziad=ziad.next;
		}
		ziad=graph.head;
		while(true){
			if(ziad.vertex.getUniqueID().equals(savar)){
				return ziad.vertex;
			}
			if(ziad.next==null)
				break;
			ziad=ziad.next;
		}
		return null;
	}

//	// performs depth first search starting from passed vertex
//	// visitor is called on each vertex and edge visited. [12 pts]
	public void dfs(String strStartVertexUniqueID, Visitor visitor) throws GraphException{ 
		//boolean flagil = false;
		B ziad=searchB(strStartVertexUniqueID);
		//ziad.vertex.label="visited";
		//visitor.visit(ziad.vertex);
		Stack <B> s=  new Stack<B>();
		B floor = new B(new Vertex("hahahahahahahahahahahahahahahahahahahahahahahahafgn;ajfehu","khfa",0,0));
		s.push(floor);
		A mark = ziad.a;
		while(!(s.peek().vertex.getUniqueID().equals("hahahahahahahahahahahahahahahahahahahahahahahahafgn;ajfehu"))){
			//
			mark = ziad.a;
			if(!ziad.vertex.label.equals("visited")){
				ziad.vertex.label="visited";
				visitor.visit(ziad.vertex);
				s.push(ziad);
			} 
			while(true){
				if(mark.edge.label.equals("undiscovered")){
					visitor.visit(mark.edge);
					if(mark.edge.rvid.equals(ziad.vertex.getUniqueID())){
						ziad=searchB(ziad.a.edge.lvid);
					}
					else{
						ziad=searchB(ziad.a.edge.rvid);
					}
					if(ziad.vertex.label.equals("undiscovered"))
						break;
				}
				mark=mark.next;
				if(mark==null){
					s.pop();
					ziad=s.peek();
					break;
				}
			}
			
			
			//
		}
		
		
	}
	public B searchB(String UID){
		boolean flagil = false;
		B ziad=graph.head;
		while(true){
			if(ziad.vertex.getUniqueID().equals(UID)){
				flagil = true;
				break;
			}
			if(ziad.next==null)
				break;
			ziad=ziad.next;
		}
		if(flagil)
			return ziad;
		else 
			return null;
	}
		
//
//
//	 // performs breadth first search starting from passed vertex
//	 // visitor is called on each vertex and edge visited. [17 pts]
	 public void bfs(String strStartVertexUniqueID,
	 Visitor visitor) throws GraphException{
		//boolean flagil = false;
			B ziad=searchB(strStartVertexUniqueID);
			ziad.vertex.label="visited";
			visitor.visit(ziad.vertex);
			Queue <B> q=  new LinkedList<>();
			q.add(ziad);
			A mark = ziad.a;
			while(!q.isEmpty()){
				//
				ziad = q.remove();
				mark = ziad.a;
//				if(!ziad.vertex.label.equals("visited")){
//					ziad.vertex.label="visited";
//					visitor.visit(ziad.vertex);
//					q.add(ziad);
//				} 
				while(true){
					if(mark.edge.label.equals("undiscovered")){
						visitor.visit(mark.edge);
						if(mark.edge.rvid.equals(ziad.vertex.getUniqueID())){
							ziad=searchB(ziad.a.edge.lvid);
						}
						else{
							ziad=searchB(ziad.a.edge.rvid);
						}
						if(ziad.vertex.label.equals("undiscovered"))
							ziad.vertex.label="visited";
							visitor.visit(ziad.vertex);
							q.add(ziad);
					}
					mark=mark.next;
					if(mark==null){
//						q.remove();
//						ziad=q.peek();
						break;
					}
				}
//				
				
				//
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
		if(this.graph.head==null || this.graph.head.next==null)
			throw new GraphException("Must initialize graph with atleast 2 verticies first");
		B bContainingVertex = this.graph.head;
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
		B ml = graph.head;
		while(ml != null){
			System.out.println("\nVertex: "+ml);
			A dw = ml.a;
			while(dw != null){
				System.out.println("Vertex: "+ml+"  Edge: "+dw+", ");
				dw = dw.next;
			}
			ml = ml.next;
		}
		if(graph.head == null){
			System.out.println("nothing yet :)");
		}
	}
//
//	
//	
}
