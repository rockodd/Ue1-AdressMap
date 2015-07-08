package CsvAdressbook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import application.Appointment;
import AdressBook.ContactDetails;

public class CSVContactsReader {

	public static List<Appointment> readEntityList(String dateiname, String splitter) {
		Path source = Paths.get(dateiname + ".csv");
		return readEntityList(source, splitter);
	}
	public static List<Appointment> readEntityList(Path source, String splitter) {
		List<Appointment> target = new ArrayList<>();
		try {
		List<String> lines = Files.readAllLines(source);
		for (String line : lines){
			try{
				String[] detailsarray = line.split(splitter);
				target.add(new Appointment(detailsarray[0],detailsarray[1],detailsarray[2]));
		
		} catch (Exception e) {e.printStackTrace(System.err);
			target.add(new Appointment("new","new","new"));
			}
		}
	} catch (IOException e)	{e.printStackTrace(System.err);
		target.addAll(null);
		
	}
	return target;
	}
}
