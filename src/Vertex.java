
public class Vertex implements Comparable{
	protected String _strUniqueID, //a unique id identifying vertex 
	_strData; //data associated with vertex
	protected int _nX,_nY; //Coordinates of vertex on some
	 						//map. Assume 0,0 is
	 						//bottom left. 
	String label;
	
	public Vertex(String s, String d, int x, int y){
		_strUniqueID=s;
		_strData=d;
		_nX=x;
		_nY=y;
		label = "undiscovered";
	}
	public String getUniqueID( ){
		return _strUniqueID;
	}

	public String getData( ){
		return _strData;
	 }
	public int getX( ){
		return _nX;
	}
	public int getY( ){
		return _nY;
	} 
	public int compareTo(Object otherVertexObject){
		Vertex otherVertex = ((Vertex)otherVertexObject);
		return (this.getX() - otherVertex.getX());
	}
}
