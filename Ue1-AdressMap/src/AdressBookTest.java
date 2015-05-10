import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


public class AdressBookTest {
	// Adressbuch anlegen,
	private static AdressBookExceptionsInterface meinAdressbuch = new AddressBook();

	//einen Kontakt für tests anlegen,
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		meinAdressbuch.addDetails(new ContactDetails("Sam", "Som", "Adresse1"));
	}

	//Test1 getDetails muss KeinKontaktException liefern
	@Test (expected = KeinKontaktException.class)
	public void testGetDetails () throws KeinKontaktException, ParamKeyIsNullException, ParamKeyIsEmptyException {
		meinAdressbuch.getDetails("nix");
	}
	
	//Test2 keyinUse muss ParamKeyIsNullException liefern
	@Test (expected = ParamKeyIsNullException.class)
	public void testkeyinuseNull () throws KeinKontaktException, ParamKeyIsNullException, ParamKeyIsEmptyException {
		meinAdressbuch.keyInUse(null);
	}
	
	//Test3 keyinUse muss ParamKeyIsEmptyException liefern
	@Test (expected = ParamKeyIsEmptyException.class)
	public void testkeyinuseEmpty () throws ParamKeyIsNullException, ParamKeyIsEmptyException{
		meinAdressbuch.keyInUse("");
	}

	//Test4 addDetails muss ParamContactIsNullException liefern
	@Test (expected = ParamContactIsNullException.class)
	public void testadddetailsNull () throws DoppelException, ParamKeyIsNullException, ParamKeyIsEmptyException, ParamContactIsNullException, ParamContactIsEmptyException{
		meinAdressbuch.addDetails(null);
	}
	
	//Test5 addDetails muss DoppelException liefern bei doppelt eingegebenen Vornamen
	@Test (expected = DoppelException.class)
	public void testadddetailsVornameDoppel () throws DoppelException, ParamKeyIsNullException, ParamKeyIsEmptyException, ParamContactIsNullException, ParamContactIsEmptyException{
		meinAdressbuch.addDetails(new ContactDetails("Peter", "Sam", "adr1"));
	}
	
	//Test6 addDetails muss DoppelException liefern bei doppelt eingegebenen Nachnamen
	@Test (expected = DoppelException.class)
	public void testchangedetailNull () throws DoppelException, ParamKeyIsNullException, ParamKeyIsEmptyException, ParamContactIsNullException, ParamContactIsEmptyException  {
		meinAdressbuch.addDetails(new ContactDetails("Som", "Pan", "adr2"));
	}
	
	//Test7 addDetails muss ParamKeyIsNullException liefern 
	@Test (expected = ParamKeyIsNullException.class)
	public void testchangeNull () throws ParamKeyIsNullException, ParamKeyIsEmptyException, DoppelException, ParamContactIsNullException, ParamContactIsEmptyException{	
		meinAdressbuch.changeDetails(null, new ContactDetails("fail", "wrong", "street"));
	}
	
	//Test8 changeDetails muss ParamKeyIsEmptyException liefern 
	@Test (expected = ParamKeyIsEmptyException.class)
	public void testchangeEmpty () throws ParamKeyIsNullException, ParamKeyIsEmptyException, DoppelException, ParamContactIsNullException, ParamContactIsEmptyException{
		meinAdressbuch.changeDetails("", new ContactDetails("fail", "wrong", "street"));
	}

	//Test9 getNumberofEntries muss 1 liefern 
	@Test
	public void TestgetNumberofEntries() {
		assertTrue(meinAdressbuch.getNumberOfEntries()==1);
		
	}
	
	//Test10 search muss  ParamKeyIsNullException bei null liefern
	@Test (expected = ParamKeyIsNullException.class)
	public void testsearchNull() throws ParamKeyIsNullException, ParamKeyIsEmptyException {		
		meinAdressbuch.search(null);
	}
	
	//Test11 search muss  ParamKeyIsEmptyException bei "" liefern
	@Test (expected = ParamKeyIsEmptyException.class)
	public void testsearchEmpty() throws ParamKeyIsNullException, ParamKeyIsEmptyException {		
		meinAdressbuch.search("");
	}
	
}
