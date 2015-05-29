/**
 * 
 */
package calendar;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author rocko
 *
 */
public class AppointmentTest {
	
	Appointment termin = new Appointment();
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		termin.setTerminBezeichnung("Bez-ONE");
		termin.setStartUhrzeit("19:30");
		termin.setEndUhrzeit("18:00");
	}

	@Test
	public void test() {
		
		assertTrue(termin.getStartUhrzeit()=="19:30");
		assertTrue(termin.getEndUhrzeit()=="18:00");
		assertTrue(termin.getTerminBezeichnung()=="Bez-ONE");
		
	}

}
