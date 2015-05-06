import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


public class AdressBookTest {
	
	private static AdressBookExceptionsInterface meinAdressbuch = new AddressBook();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@Test
	public void Test1() {
		assertTrue(meinAdressbuch.getNumberOfEntries()==0);
		
	}

}
