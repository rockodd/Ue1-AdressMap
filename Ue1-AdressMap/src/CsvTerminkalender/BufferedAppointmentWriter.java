package CsvTerminkalender;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import calendar.Appointment;

public class BufferedAppointmentWriter {
	
	//Pfad wird erzeugt
	//Stream geöffnet
	//Die übergebene Collection wird durchiteriert
	//Jeder Index wird formatiert und per Stream in den Speicher geschrieben
	//Flush schreibt dann aus dem Speicher in die Textdatei.
	//Dann wird der Stream geschlossen.
	public static void writeAppointment(List<Appointment> appointment, String filename, String splitter) throws IOException {
		String path = filename + ".txt";
		BufferedWriter output = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(path)));
		for (Appointment index : appointment) {
			output.write(formatAppointment(index, splitter));
		}
		output.flush();
		output.close();
	}

	//Formatierung
	//Am Ende eines Objektes kommt ebenfalls das Trennzeichen zum Einsatz, da wir nun Zeilenweise schreiben ermöglicht das uns die Objekte zu unterscheiden.
	private static String formatAppointment(Appointment appointment, String splitter) {
		return 
				appointment.getTerminkategorie().getValue() + splitter
				+ appointment.getDatum().getValue() + splitter
				+ appointment.getStartUhrzeit().getValue() + splitter
				+ appointment.getEndUhrzeit().getValue() + splitter
				+ appointment.getTerminBezeichnung().getValue() + splitter
				+ appointment.getTerminBeschreibung().getValue() + splitter;
	}

}
