public class AdjacencyList {
	B head;
}
class B {
	Vertex vertex;
	A a;
	B next;
	public B(){
		vertex=null;
		a=null;
	}
	public B(Vertex v){
		vertex=v;
		a=null;
	}
	public String toString(){
		if(vertex != null)
			return vertex.getUniqueID();
		else
			return null;
	}
}
class A{
	Edge edge;
	A next;
	public A(){
		edge=null;
		next=null;
	}
	public A(Edge e){
		edge=e;
		next=null;
	}
	public String toString(){
		if(edge != null)
			return edge.getUniqueID();
		else
			return null;
	}
}