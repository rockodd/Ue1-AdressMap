package tests;

import org.junit.Test;

import calendar.Appointment;
import calendar.BeforeException;
import CsvTerminkalender.BufferedAppointmentReader;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BufferedAppointmentReaderTest {
	
	private BufferedAppointmentReader bufferedReader = new BufferedAppointmentReader();
	
	@Test
	public void testeBufferedReader() throws IOException, BeforeException {
    List<Appointment> expectedCollection = TestData.getTermin2(5);
    List<Appointment> collection = bufferedReader.readAppointment("BufferedTermine", "::");

		assertEquals(expectedCollection, collection);
	}

}
