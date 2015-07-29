package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import CsvTerminkalender.CSVTerminReader;
import calendar.Appointment;


public class CSVAppointmentReaderTest {

	private CSVTerminReader csvAppointmentReader = new CSVTerminReader();
	
	
	@Test
	public void testeReader() throws IOException  {
    List<Appointment> expectedCollection = TestData.getTermin2(1);
    List<Appointment> collection = csvAppointmentReader.readAppointment("Lernen", "::");
		
		assertNotEquals(expectedCollection, collection);
	}

}
