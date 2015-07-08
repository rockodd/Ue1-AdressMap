package bufferedIO;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.io.OutputStreamWriter;

import calendar.Appointment;

public class BufferedAppointmentWriter {
	
	public void writeAppointment(List<Appointment> appointment, String filename, String splitter) throws IOException {
		String path = filename + "csv.";
		BufferedWriter outBuf = new BufferedWriter(new OutputStreamWriter( new FileOutputStream(path)));
		for (Appointment index : appointment) {
			outBuf.write(formatAppointmentToCSV(index, splitter));
		}
		outBuf.flush();
		outBuf.close();
	}

	
	private String formatAppointmentToCSV(Appointment appointment, String splitter) {
		return appointment.getDatum()+ splitter
				+ appointment.getTerminBezeichnung() + splitter
				+ appointment.getStartUhrzeit() + splitter
				+ appointment.getEndUhrzeit() + splitter
				+ appointment.getTerminkategorie() + splitter
				+ appointment.getDatum() + splitter;
	}


}
