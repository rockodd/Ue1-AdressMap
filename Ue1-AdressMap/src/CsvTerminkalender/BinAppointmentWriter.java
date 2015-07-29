package CsvTerminkalender;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import calendar.Appointment;

public class BinAppointmentWriter {
	//Pfad wird erstellt
	//Stream wird geöffnet
	//Hier ist wichtig zu wissen, wir können nicht jedes Attribut oder Objekt einzeln mit writeUTF schreiben.
	//Das macht Probleme, wir müssen dann beim Einlesen wissen wie viele Objekte wir geschrieben haben...
	//Also machen wir alles in einem einzigen Schreibbefehl.
	//Aus diesem Grund werden alle Attribute der Objekte formatiert und hintereinander gesetzt.
	//Dieser String wird dann geschrieben.
	//Der Stream wird geschlossen
	public static void writeAppointment(List<Appointment> appointment, String dateiname, String splitter) throws IOException {
		String path = dateiname + ".bin";
		DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(path)));
		String outputString = "";
		for(Appointment index : appointment) {
			outputString = outputString + formatAppointment(index, splitter);
			
		}
		
		output.writeUTF(outputString);
		output.flush();
		output.close();
	}
	
	public static String formatAppointment(Appointment appointment, String splitter) {
		return 
				appointment.getTerminkategorie().getValue() + splitter
				+ appointment.getDatum().getValue() + splitter
				+ appointment.getStartUhrzeit().getValue() + splitter
				+ appointment.getEndUhrzeit().getValue() + splitter
				+ appointment.getTerminBezeichnung().getValue() + splitter
				+ appointment.getTerminBeschreibung().getValue() + splitter;
	}
}
