import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


public class ResponseMapTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testNullParams() {
		new MapElement(null,null);
	}
}