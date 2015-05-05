public interface AdressBookInterfaceException {

	public abstract ContactDetails getDetails(String key)
			throws KeinKontaktException, ParamKeyIsNullException,
			ParamKeyIsEmptyException;

	public abstract boolean keyInUse(String key)
			throws ParamKeyIsNullException, ParamKeyIsEmptyException;

	public abstract void addDetails(ContactDetails details)
			throws DoppelException, ParamKeyIsNullException,
			ParamKeyIsEmptyException;

	public abstract void changeDetails(String oldKey, ContactDetails details)
			throws ParamKeyIsNullException, ParamKeyIsEmptyException,
			DoppelException;

	public abstract int getNumberOfEntries();

	public abstract void removeDetails(String key)
			throws ParamKeyIsNullException, ParamKeyIsEmptyException;

	public abstract ContactDetails[] search(String keyPrefix)
			throws ParamKeyIsNullException, ParamKeyIsEmptyException;

}