package AdressBook;

public class AdressBookDefault extends AddressBook {
	// Konstruktor
	public AdressBookDefault() {
		
		super();
		fillDefaultData();
	}

	private void fillDefaultData() {
		try {
			addDetails(new ContactDetails("Peter", "Pan", "Nimmerland 3"));
		} catch (DoppelException | ParamKeyIsNullException
				| ParamKeyIsEmptyException | ParamContactIsNullException
				| ParamContactIsEmptyException e) {
			e.printStackTrace();
		}
		try {
			addDetails(new ContactDetails("Francho", "Santana", "Gangway 1"));
		} catch (DoppelException | ParamKeyIsNullException
				| ParamKeyIsEmptyException | ParamContactIsNullException
				| ParamContactIsEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			addDetails(new ContactDetails("Frank", "The Tank", "Miltiaryaprom 2"));
		} catch (DoppelException | ParamKeyIsNullException
				| ParamKeyIsEmptyException | ParamContactIsNullException
				| ParamContactIsEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			addDetails(new ContactDetails("Frodo", "Beutling", "Auenland 4"));
		} catch (DoppelException | ParamKeyIsNullException
				| ParamKeyIsEmptyException | ParamContactIsNullException
				| ParamContactIsEmptyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
