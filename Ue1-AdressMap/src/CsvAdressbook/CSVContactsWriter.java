package CsvAdressbook;

import AdressBook.ContactDetails;
import application.ObservableContactDetails;
import calendar.Appointment;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVContactsWriter {

	final static Charset ENCODING = StandardCharsets.UTF_8;
// Der Dateiname wird als String übergeben und anschließend die Methode writeEntityList aufgerufen
//Ebenfalls wird aus dem String ein Pfad erzeugt
	public static void writeEntityList(List<ObservableContactDetails> contact,
			String filename, String splitter) throws IOException {
		Path path = Paths.get(filename + ".csv");
		writeEntityList(contact, path, splitter);
	}
/* In dieser Methode werden unsere ObservableContactDetails als Liste als Parameter übergeben. Zusätzlich wird der gespeicherte Pfad 
*aus der Methode zuvor mitgeteilt und  der splitter 
*Die übergegebene OberservableContactDetails Liste wird durch iteriert und die einzelnen Objekte ObservableContactDetailsAsCSVLine übergeben
*ObservableContactDetailsAsCSVLine trägt uns unser Objekt als String , mit dem dazugehörigen splitter in unserer Liste ein
*Diese Liste wird letztendlich an die Files.write Methode übergeben. 
*Dabei werden die einzelnen Kontakte jeweils in einer Zeile ausgegeben
*/
	public static void writeEntityList(List<ObservableContactDetails> contact,
			Path path, String splitter) throws IOException {
		List<String> lines = new ArrayList<>();
		for (ObservableContactDetails contacts : contact) {
			lines.add(ObservableContactDetailsAsCSVLine(contacts, splitter));
		}
		Files.write(path, lines, ENCODING);
	}
// die ObserableContactDetailsAsCSVLine Methode arbeitet nun mit einem einzelnen Kontakt aus der übergegeben OberserableContactDetails
// und fügt mit Hilfe der getter Methoden die einzelnen Attribute hinzu.
// die splitter werden rangehängt, und als String zurückgegeben.
	private static String ObservableContactDetailsAsCSVLine(
			ObservableContactDetails c, String splitter) {
		return c.getName() + splitter + c.getVorname() + splitter
				+ c.getAdresse() + splitter;

	}

}
