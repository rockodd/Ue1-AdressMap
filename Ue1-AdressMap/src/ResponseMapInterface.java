import java.util.List;


public interface ResponseMapInterface {
	public String get (String key);
	public void put (String key,String msg );
	public boolean contains (String key);
	public List<String> allKeys();
	public int size ();

}
