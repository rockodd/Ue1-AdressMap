package bufferedIO;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import calendar.Appointment;

public class BufferedAppointmentReader {
	
		public List<Appointment> readAppointment(String filename, String splitter) throws IOException {
			String path = filename + ".txt";
			List<Appointment> inputListe = new ArrayList();
			BufferedReader inBuf = new BufferedReader(new InputStreamReader(new FileInputStream (path)));
			String einlesen = inBuf.readLine();
			LocalDate [] attribute = einlesen.split(splitter);
			//String [] wert = einlesen.split(splitter);
			int index = 0;
			for(int i = 0; i < attribute.length / 5; i++) {
				Appointment appointment = new Appointment();
				appointment.setDatum(attribute[index++]);
				appointment.setTerminbezeichnung(attribute[index++]);
				appointment.setStartUhrzeit(attribute[index++]);
				appointment.setEndUhrzeit(attribute[index++]);
				appointment.setTerminkategorie(attribute[index++]);
				inputListe.add(appointment);
			}
			inBuf.close();
			return inputListe;
		}

}


