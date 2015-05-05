import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class AddressBook implements AddressBookInterface {
	private TreeMap<String, ContactDetails> meinetreemap = new TreeMap<>();

	@Override
	public ContactDetails getDetails(String key) throws KeinKontaktException {
		// Pr�fen ob Key schon in TreeMap
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
		// Pr�fen auf vorhandensein beider Schl�ssel
		if (this.keyInUse(details.getVorname())
				|| this.keyInUse(details.getName()))
			throw new DoppelException();
		// zwei Eintr�ge mit verschiedenen Schl�sseln
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
		// R�ckgabe der Gr��e geteilt durch zwei,
		// da alle Details doppelt eingetragen sind
		return meinetreemap.size() / 2;
	}

	@Override
	public void removeDetails(String key) {
		// pr�fen ob Key �berhaupt vorhanden
		if (this.keyInUse(key)) {
			// Vorname und Name aus den Details lesen um beide EIntr�ge
			// entfernen zu k�nnen
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
