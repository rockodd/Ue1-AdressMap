package CsvTerminkalender;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import application.ObservableContactDetails;
import calendar.Appointment;


public class CSVTerminReader {
	
	public static List<Appointment> readAppointment(String dateiname, String splitter) {
		Path source = Paths.get(dateiname + ".csv");
		return readAppointment(source, splitter);
	}
	
	public static List<Appointment> readAppointment(Path source, String splitter) {
		List<Appointment> target = new ArrayList<>();
		try {
		List<String> lines = Files.readAllLines(source);
		for (String line : lines){
			try{
				String[] detailsarray = line.split(splitter);
				target.add(new Appointment(detailsarray[0],LocalDate.parse(detailsarray[1]), LocalTime.parse(detailsarray[2]),LocalTime.parse(detailsarray[3]), detailsarray[4],detailsarray[5]));
		
		} catch (Exception e) {e.printStackTrace(System.err);
			target.add(new Appointment());
			}
		}
	} catch (IOException e)	{e.printStackTrace(System.err);
		
		
	}
	return target;
	}
}


