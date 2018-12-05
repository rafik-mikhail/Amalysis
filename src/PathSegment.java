
public class PathSegment {
	protected Vertex _vertex; // the vertex in this path segment
	 protected Edge _edge; // the edge associated with this vertex
	 public Vertex getVertex( ){
	 return _vertex;
	 }
	 public Edge getEdge( ){
	 return _edge;
	 }
	 public PathSegment(){

	 }
	 public PathSegment(Vertex v, Edge e){
	 	this._vertex = v;
	 	this._edge = e;
	 }
}
