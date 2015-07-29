package CsvTerminkalender;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import calendar.Appointment;
import calendar.BeforeException;

public class BinAppointmentReader {
			
			//Mit der Methode split() wird der String aufgeteilt in Attribute.
			//Diese werden indexweise in einem Array gespeichert.
			//Da wir wissen wie viele Attribute ein Objekt hat, gehen wir es index für index durch und erstellen Objekte aus den Attributen.
			//Die Objekte werden einer Collection hinzugefügt die, nach dem schließen des Streams, zurückgegeben wird.
			public static List<Appointment> readAppointment(String dateiname, String splitter) throws IOException, BeforeException{
				String path = dateiname + ".bin";
				List<Appointment> inputCollection = new ArrayList();
				DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
				String inputString = input.readUTF();
				String[] attribute = inputString.split(splitter);
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
