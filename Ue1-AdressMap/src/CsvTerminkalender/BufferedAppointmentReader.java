package CsvTerminkalender;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import calendar.Appointment;
import calendar.BeforeException;

public class BufferedAppointmentReader {
	//Aus filename wird der pfad generiert.
	//Es wird ein Stream erstellt der Analog zum BufferedWriter "zusammengesteckt" ist.
	//Der Inhalt der Textdatei (einzeilig) wird in einen String gelesen und mit dem Splitter, attributweise, in ein Array aufgeteilt.
	//Aus dem Array-indizes werden Appointment-Objekte erstellt, diese werden in einer Collection gespeichert.
	//Die Collection ist der Rückgabewert der Methode.
	public static List<Appointment> readAppointment(String filename, String splitter) throws IOException, BeforeException {
		String path = filename + ".txt";
		List<Appointment> inputCollection = new ArrayList();
		BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream (path)));
		String lesen = input.readLine();
		String [] attribute = lesen.split(splitter);
		int index = 0;
		for(int i = 0; i < attribute.length / 6; i++) {
			
			Appointment appointment = new Appointment();
			appointment.setTerminkategorie(attribute[index++]);
			appointment.setDatum(LocalDate.parse(attribute[index++]));
			appointment.setStartUhrzeit(LocalTime.parse(attribute[index++]));
			appointment.setEndUhrzeit(LocalTime.parse(attribute[index++]));
			appointment.setTerminBezeichnung(attribute[index++]);
			appointment.setTerminBeschreibung(attribute[index++]);
			inputCollection.add(appointment);
		}
		input.close();
		return inputCollection;
	}
	
}
