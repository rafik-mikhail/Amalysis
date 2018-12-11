import java.util.Vector;

public class Try{
    public static void main(String[]args){
        Vector<String> c = new Vector<String>();
        Vector<String> b = (Vector)c.clone();
        System.out.println(b);
    }
}