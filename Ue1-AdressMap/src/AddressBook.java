import java.util.TreeMap;


public class AddressBook implements AddressBookInterface {
	private TreeMap<String, ContactDetails> meinetreemap = new TreeMap<>();
	
	
	
	@Override
	public ContactDetails getDetails(String key) throws KeinKontaktException {
		
		if (!this.keyInUse(key)) throw new KeinKontaktException();
		return meinetreemap.get(key);
	}

	@Override
	public boolean keyInUse(String key) {
		return meinetreemap.containsKey(key);
	}

	@Override
	public void addDetails(ContactDetails details) {
		//Schlüssel ist Vorname und Nachname
		if (this.keyInUse(details.getVorname())||this.keyInUse(details.getName())) throw new DoppelException();
		meinetreemap.put(details.getName(), details);
		meinetreemap.put(details.getVorname(), details);

	}

	@Override
	public void changeDetails(String oldKey, ContactDetails details) {
		if (this.keyInUse(oldKey) && details!= null) {			
			
			// alten EIntrag entfernen
			this.removeDetails(oldKey);
			
			// neuer Eintrag
			this.addDetails(details);
		}
	}

	@Override
	public int getNumberOfEntries() {
		return meinetreemap.size() ;
	}

	@Override
	public void removeDetails(String key) {
		// prüfen ob Key überhaupt vorhanden
		if (this.keyInUse(key)) {
			String name= meinetreemap.get(key).getName();
			String vorname= meinetreemap.get(key).getVorname();
			meinetreemap.remove(name);
			meinetreemap.remove(vorname);
		}

	}

}
