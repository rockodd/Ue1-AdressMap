import java.util.ArrayList;
import java.util.List;


public class ResponseMap implements ResponseMapInterface {
	private ArrayList<MapElement> responseList = new ArrayList<>();
	
	
	@Override
	public String get(String key) {
		for (MapElement result : responseList){
			if (result.equals(key)) return result.value;	
		}
		return null;
	}

	@Override
	public void put(String key, String msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(String key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> allKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}


}
