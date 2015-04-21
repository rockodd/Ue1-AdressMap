import java.util.TreeMap;


public class AdressBook implements AddressBookInterface {
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
		if (this.keyInUse(details.getVorname()+details.getName())) throw new DoppelException();
		meinetreemap.put(details.getVorname()+details.getName(), details);

	}

	@Override
	public void changeDetails(String oldKey, ContactDetails details) {
		// TODO Auto-generated method stub
		if (this.keyInUse(oldKey) && details!= null) {
			
			// alten EIntrag entfernen
			this.removeDetails(oldKey);

			// neuer Eintrag
			this.addDetails(details);
		}

	}

	@Override
	public int getNumberOfEntries() {
		// TODO Auto-generated method stub
		return meinetreemap.size() ;
	}

	@Override
	public void removeDetails(String key) {
		
		if (this.keyInUse(key)) {
			meinetreemap.remove(key);
		}

	}

}
