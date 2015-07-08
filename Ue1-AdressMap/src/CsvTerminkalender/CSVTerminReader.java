package CsvTerminkalender;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import calendar.Appointment;

// Die CSVTerminReader Klasse ist im Grunde dafür verantwortlich, den Inhalt der erzeugten Textdateien einzulesen und ein Objekt daraus zu erzeugen
public class CSVTerminReader {
	
	//Die readAppointment Methode nimmt Dateinamen in Form eines Strings entgegen und bastelt aus diesen einen Pfad
	// 
	public static List<Appointment> readAppointment(String dateiname, String splitter) {
		Path source = Paths.get(dateiname);
		return readAppointment(source, splitter);
	}
	// Wir erzeugen eine Liste, die unsere Appointments als Strings entgegennimmt. Der Inhalt der beschriebenen Datei soll eingelesen werden
	// Unsere Liste wird mit einer erweiterten for-Schleife durchiteriert.
	public static List<Appointment> readAppointment(Path source, String splitter) {
		List<Appointment> target = new ArrayList<>();
		try {
		List<String> lines = Files.readAllLines(source);
		for (String line : lines){
			try{
				
		
		} catch (Exception e) {e.printStackTrace(System.err);
			target.add(new Appointment());
			}
		}
	} catch (IOException iox)	{iox.printStackTrace(System.err);
		target.addAll(null);
		
	}
	return target;
	}
}


