import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.print.attribute.standard.PrinterMakeAndModel;

public class AddressBook implements AdressBookExceptionsInterface {
	private TreeMap<String, ContactDetails> meinetreemap = new TreeMap<>();

	/* (non-Javadoc)
	 * @see AdressBookInterfaceException#getDetails(java.lang.String)
	 */
	@Override
	public ContactDetails getDetails(String key) throws KeinKontaktException, ParamKeyIsNullException, ParamKeyIsEmptyException {
		// Prüfen ob Key schon in TreeMap
		if (!this.keyInUse(key))
			throw new KeinKontaktException();
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
	public void addDetails(ContactDetails details) throws DoppelException, ParamKeyIsNullException, ParamKeyIsEmptyException {
		// Prüfen auf vorhandensein beider Schlüssel
		if (this.keyInUse(details.getVorname())
				|| this.keyInUse(details.getName()))
			throw new DoppelException();
		else
		// zwei Einträge mit verschiedenen Schlüsseln
		meinetreemap.put(details.getName(), details);
		meinetreemap.put(details.getVorname(), details);

	}

	
	/* (non-Javadoc)
	 * @see AdressBookInterfaceException#changeDetails(java.lang.String, ContactDetails)
	 */
	@Override
	public void changeDetails(String oldKey, ContactDetails details) throws ParamKeyIsNullException, ParamKeyIsEmptyException, DoppelException {
		if (oldKey == null) throw new ParamKeyIsNullException ("Der alte Key ist null"); 
		else if (oldKey.isEmpty()) throw new ParamKeyIsEmptyException("Der alte Key ist leer"); 
		else 
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
		// Rückgabe der Größe geteilt durch zwei,
		// da alle Details doppelt eingetragen sind
		return meinetreemap.size() / 2;
	}

	
	/* (non-Javadoc)
	 * @see AdressBookInterfaceException#removeDetails(java.lang.String)
	 */
	@Override
	public void removeDetails(String key) throws ParamKeyIsNullException, ParamKeyIsEmptyException {
		// prüfen ob Key überhaupt vorhanden
		if (this.keyInUse(key)) {
			// Vorname und Name aus den Details lesen um beide EInträge
			// entfernen zu können
			String name = meinetreemap.get(key).getName();
			String vorname = meinetreemap.get(key).getVorname();
			meinetreemap.remove(name);
			meinetreemap.remove(vorname);
		}

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
