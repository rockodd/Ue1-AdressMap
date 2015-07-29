package CsvAdressbook;

import application.ObservableContactDetails;

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

	// Konstruktor
	public static void writeEntityList(List<ObservableContactDetails> contact,
			String filename, String splitter) throws IOException {
		Path path = Paths.get(filename + ".csv");
		writeEntityList(contact, path, splitter);
	}

	// Methode liest jeden Kontakt einzeiln ein lässt von jedem Kontakt ein String erzeugen, 
	// welcher dann in eine Datei geschriben wird.
	public static void writeEntityList(List<ObservableContactDetails> contact,
			Path path, String splitter) throws IOException {
		List<String> lines = new ArrayList<>();
		for (ObservableContactDetails contacts : contact) {
			System.out.println(contacts);
			lines.add(ObservableContactDetailsAsCSVLine(contacts, splitter));
		}
		// Datei wird mit übergebnenen Pfad geschrieben
		Files.write(path, lines, ENCODING);
		
	}
	
	// String wird erzeugt mit den Propertys aus ContactDetails
	private static String ObservableContactDetailsAsCSVLine(
			ObservableContactDetails c, String splitter) {
		return  c.getVornameProperty().getValue() + splitter + c.getNameProperty().getValue() + splitter
				+ c.getAdresseProperty().getValue() + splitter;

	}

}
