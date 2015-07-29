package tests;

import static org.junit.Assert.*;


import java.util.List;

import org.junit.Test;

import calendar.Appointment;
import CsvTerminkalender.BufferedAppointmentReader;
import CsvTerminkalender.BufferedAppointmentWriter;


public class BufferedAppointmentWriterTest {

  private BufferedAppointmentReader bufferedReader = new BufferedAppointmentReader();

  // TEstklasse
	private BufferedAppointmentWriter bufferedWriter = new BufferedAppointmentWriter();

	@Test
	public void testeBufferedWriter() throws Exception {
    List<Appointment> expectedCollection = TestData.getValidAppointments();
    BufferedAppointmentWriter.writeAppointment(expectedCollection, "BufferedAppointmentWriterTest", "::");
    List<Appointment> collection = BufferedAppointmentReader.readAppointment("BufferedAppointmentWriterTest", "::");
		
		assertNotEquals(expectedCollection, collection);
	}

}
