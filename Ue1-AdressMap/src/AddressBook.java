import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class AddressBook implements AddressBookInterface {
	private TreeMap<String, ContactDetails> meinetreemap = new TreeMap<>();

	@Override
	public ContactDetails getDetails(String key) throws KeinKontaktException {
		// Prüfen ob Key schon in TreeMap
		if (!this.keyInUse(key))
			throw new KeinKontaktException();
		return meinetreemap.get(key);
	}

	@Override
	public boolean keyInUse(String key) {
		return meinetreemap.containsKey(key);
	}

	@Override
	public void addDetails(ContactDetails details) {
		// Prüfen auf vorhandensein beider Schlüssel
		if (this.keyInUse(details.getVorname())
				|| this.keyInUse(details.getName()))
			throw new DoppelException();
		// zwei Einträge mit verschiedenen Schlüsseln
		meinetreemap.put(details.getName(), details);
		meinetreemap.put(details.getVorname(), details);

	}

	@Override
	public void changeDetails(String oldKey, ContactDetails details) {
		if (this.keyInUse(oldKey) && details != null) {

			// alten EIntrag entfernen
			this.removeDetails(oldKey);

			// neuer Eintrag
			this.addDetails(details);
		}
	}

	@Override
	public int getNumberOfEntries() {
		// Rückgabe der Größe geteilt durch zwei,
		// da alle Details doppelt eingetragen sind
		return meinetreemap.size() / 2;
	}

	@Override
	public void removeDetails(String key) {
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

	@Override
	public ContactDetails[] search(String keyPrefix) {
		List<ContactDetails> found = new ArrayList<ContactDetails>();
		for (String key : meinetreemap.keySet()) {
			if (key.contains(keyPrefix)) {
				found.add(meinetreemap.get(key));
			}
		}
		return (found.toArray(new ContactDetails[found.size()]));

	}

}
