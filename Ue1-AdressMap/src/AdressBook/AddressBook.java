package AdressBook;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;


public class AddressBook implements AdressBookExceptionsInterface {
	private TreeMap<String, ContactDetails> meinetreemap = new TreeMap<>();

	/* (non-Javadoc)
	 * @see AdressBookInterfaceException#getDetails(java.lang.String)
	 */
	@Override
	public ContactDetails getDetails(String key) throws KeinKontaktException, ParamKeyIsNullException, ParamKeyIsEmptyException {
		// Pr�fen ob Key schon in TreeMap
		if (!this.keyInUse(key))
			throw new KeinKontaktException("Kontakt nicht vorhanden");
		return meinetreemap.get(key);
	}

	
	/* (non-Javadoc)
	 * @see AdressBookInterfaceException#keyInUse(java.lang.String)
	 */
	@Override
	public boolean keyInUse(String key) throws ParamKeyIsNullException, ParamKeyIsEmptyException {
		if (key == null) throw new ParamKeyIsNullException ("Der Key ist null");  
		else if (key.isEmpty()) throw new ParamKeyIsEmptyException("Der Key ist leer");
		else return meinetreemap.containsKey(key);
	}

	
	/* (non-Javadoc)
	 * @see AdressBookInterfaceException#addDetails(ContactDetails)
	 */
	@Override
	public void addDetails(ContactDetails details) throws DoppelException, ParamKeyIsNullException, ParamKeyIsEmptyException, ParamContactIsNullException, ParamContactIsEmptyException {
		// Pr�fen auf vorhandensein beider Schl�ssel
		if (details == null) throw new ParamContactIsNullException("ContactDetails ist null");
		if (details.equals("")) throw new ParamContactIsEmptyException("ContactDetails ist Empty");
		if (this.keyInUse(details.getVorname())) throw new DoppelException("Der Vorname ist schon vorhanden");
		else if (this.keyInUse(details.getName())) throw new DoppelException("Der Nachname ist schon vorhanden");
		else
		// zwei Eintr�ge mit verschiedenen Schl�sseln
		meinetreemap.put(details.getName(), details);
		meinetreemap.put(details.getVorname(), details);
	}

	
	/* (non-Javadoc)
	 * @see AdressBookInterfaceException#changeDetails(java.lang.String, ContactDetails)
	 */
	@Override
	public void changeDetails(String oldKey, ContactDetails details) throws ParamKeyIsNullException, ParamKeyIsEmptyException, DoppelException, ParamContactIsNullException, ParamContactIsEmptyException, KeinKontaktException {
		if (oldKey == null) throw new ParamKeyIsNullException ("Der alte Key ist null"); 
		else if (oldKey.isEmpty()) throw new ParamKeyIsEmptyException("Der alte Key ist leer"); 
			// alten EIntrag entfernen
			this.removeDetails(oldKey);
			// neuer Eintrag
			this.addDetails(details);
		}


	
	/* (non-Javadoc)
	 * @see AdressBookInterfaceException#getNumberOfEntries()
	 */
	@Override
	public int getNumberOfEntries() {
		if ((meinetreemap.size() % 2) > 0) throw new InkonsistentException("Adressbuch ist fehlerhaft, ungerade Anzahl von Eintr�gen");
		// R�ckgabe der Gr��e geteilt durch zwei,
		// da alle Details doppelt eingetragen sind
		return meinetreemap.size() / 2;
	}
	
	/* (non-Javadoc)
	 * @see 
	 */
	// gibt alle Keys aus dem Adressbuch zur�ck
	public Set<String> allKeys() {
		return meinetreemap.keySet();
	}
	//gibt alle ContactDetails Objekte aus dem Adressbuch zur�ck
	public Collection<ContactDetails> allDetails() {
		return meinetreemap.values();
	}
	
	
	/* (non-Javadoc)
	 * @see AdressBookInterfaceException#removeDetails(java.lang.String)
	 */
	@Override
	public void removeDetails(String key) throws ParamKeyIsNullException, ParamKeyIsEmptyException, KeinKontaktException {
		// pr�fen ob Key �berhaupt vorhanden
		if(key == null)	throw new ParamKeyIsNullException("L�sch-Key war null");
		if(key.isEmpty())	throw new ParamKeyIsEmptyException("L�sch-Key war leer");
		if (this.keyInUse(key)){
			// Vorname und Name aus den Details lesen um beide EIntr�ge
			// entfernen zu k�nnen
			String name = meinetreemap.get(key).getName();
			String vorname = meinetreemap.get(key).getVorname();
			meinetreemap.remove(name);
			meinetreemap.remove(vorname);			
		}
		else throw new KeinKontaktException("Kein Kontakt zum l�schen vorhanden");
			

			
			
		}
	/* (non-Javadoc)
	 * @see AdressBookInterfaceException#search(java.lang.String)
	 */
	@Override
	public ContactDetails[] search(String keyPrefix) throws ParamKeyIsNullException, ParamKeyIsEmptyException {
		if (keyPrefix == null) throw new ParamKeyIsNullException ("Der Such-Key ist null"); 
		else if (keyPrefix.isEmpty()) throw new ParamKeyIsEmptyException("Der Such-Key ist leer"); 
		List<ContactDetails> found = new ArrayList<ContactDetails>();
		for (String key : meinetreemap.keySet()) {
			if (key.contains(keyPrefix)) {
				found.add(meinetreemap.get(key));
			}
		}
		return (found.toArray(new ContactDetails[found.size()]));
	}
}
