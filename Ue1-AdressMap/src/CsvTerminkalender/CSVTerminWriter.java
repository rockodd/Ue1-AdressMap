package CsvTerminkalender;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import calendar.Appointment;
// Die Writer Klasse schreibt in die Textdatei eines Objektes.
public class CSVTerminWriter {

	final static Charset ENCODING = StandardCharsets.UTF_8;
// Die writeAppointment Methode bastelt ähnlich wie die Reader Methode aus einem String einen Pfad

	public void writeAppointment(List<Appointment> appointment,
			String filename, String splitter) throws IOException {
		Path path = Paths.get( filename);
		writeAppointment(appointment, path, splitter);
	}
// Wir erzeugen eine Liste die Appointments entgegennimmt und in der erweiterten for- Schleife durchiteriert.
/* in unserer Variablen index werden die einzelnen Termine gespeichert und unserer Liste die Strings entgegenemnimmt hinzugefügt.
Diese Liste wird gleichzeitig in die Datei geschrieben
*/
	public void writeAppointment(List<Appointment> appointment, Path path,
			String splitter) throws IOException {
		List<String> lines = new ArrayList<>();
		for (Appointment index : appointment) {
			lines.add(formatAppointmentToCSV(index, splitter));
		}
		Files.write(path, lines, ENCODING);
	}
// Wir trennen unsere Objekte aus Appointment durch Trennzeichen und lassen diese als Strings ausgeben
	private String formatAppointmentToCSV(Appointment appointment,	String splitter) {
		return appointment.getDatum() + splitter
				+ appointment.getTerminBezeichnung() + splitter
				+ appointment.getStartUhrzeit() + splitter
				+ appointment.getEndUhrzeit() + splitter
				+ appointment.getTerminkategorie() + splitter;

	}
// Hier wird ebenfalls aus dem eingegebenen String ein Pfad erzeugt
	public void writeAppointment(Appointment appointment, String filename,String splitter) throws IOException {
		Path path = Paths.get( filename + ".csv");
		writeAppointment(appointment, path, splitter);
	}
// In dieser Methode wird ein einzelnes Appointment geschrieben
	public void writeAppointment(Appointment appointment, Path path,
			String splitter) throws IOException {
		List<String> lines = new ArrayList<>();
		lines.add(formatAppointmentToCSV(appointment, splitter));
		Files.write(path, lines, ENCODING);
	}
}
