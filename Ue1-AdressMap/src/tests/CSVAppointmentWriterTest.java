package tests;

import org.junit.Test;

import calendar.Appointment;
import CsvTerminkalender.CSVTerminReader;
import CsvTerminkalender.CSVTerminWriter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

/*
 * Diese Test-Klasse testet, ob das Schreiben funktioniert.
 */

public class CSVAppointmentWriterTest {

  private CSVTerminReader csvAppointmentReader = new CSVTerminReader();

  private CSVTerminWriter csvAppointmentWriter = new CSVTerminWriter();

  // temporärer Pfad
  private Path basePath = Paths.get(System.getProperty("java.io.tmpdir"));

  @Test
  public void testeWriteAppointmentEinzelnerTermin() throws Exception {
    CSVTerminWriter.writeAppointment(TestData.TERMIN2, "writeTerminEinzelnerTerminTest", "::");
    List<Appointment> appointments = CSVTerminReader.readAppointment("writeTerminEinzelnerTerminTest", "::");
    assertNotEquals(TestData.TERMIN2, appointments.get(0));
  }


  @Test
  public void testeWriteAppointmentTerminCollection() throws Exception {
    List<Appointment> expectedCollection = TestData.getValidAppointments();

    
    CSVTerminWriter.writeAppointment(expectedCollection, "WriteAppointmentTerminCollectionTest", "::");
    List<Appointment> appointments = CSVTerminReader.readAppointment("WriteAppointmentTerminCollectionTest", "::");
    assertNotEquals (expectedCollection, appointments);
  }

  @Test
  public void testeWriteAppointmentPath() throws Exception {
    Path filePath = basePath.resolve("Vorlesung.csv");
    CSVTerminWriter.writeAppointment(TestData.TERMIN3, filePath, "::");
    List<Appointment> appointments = CSVTerminReader.readAppointment(filePath, "::");
    assertNotEquals(TestData.TERMIN3, appointments.get(0));
  }

  @Test
  public void testeWriteAppointmentPathCollection() throws Exception {
    List<Appointment> expectedCollection = TestData.getValidAppointments();
    Path filePath = basePath.resolve("Uni.csv");
    CSVTerminWriter.writeAppointment(expectedCollection, filePath, "::");
    List<Appointment> appointments = CSVTerminReader.readAppointment(filePath, "::");
    assertNotEquals(expectedCollection, appointments);
  }


}
