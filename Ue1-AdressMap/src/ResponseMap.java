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
		// if key in use
		
		MapElement newMapelement = new MapElement(); // neues Objekt vom Typ MapElement erzeugen
		newMapelement.key = key;					// Hinzufügen der Strings
		newMapelement.value = msg;
		responseList.add(newMapelement);			//neues MapElement in die ArrayListe packen

		
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
