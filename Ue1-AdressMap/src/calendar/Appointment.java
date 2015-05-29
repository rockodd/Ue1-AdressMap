package calendar;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Year;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {

	private StringProperty terminKategorie = new SimpleStringProperty();

	public String getTerminkategorie() {
		return terminKategorie.get();

	}

	public void setTerminkategorie(String terminKategorie) {
		this.terminKategorie.set(terminKategorie);
	}

	//### DATUM  #### ///
	//private ObjectProperty<LocalDate> datum = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
	private ObjectProperty<LocalDate> datum = new SimpleObjectProperty<LocalDate>();
	public LocalDate getDatum() {return datum.get();}
	public void setDatum(LocalDate date) {	this.datum.set(date);	}

	
	private StringProperty startUhrzeit = new SimpleStringProperty();

	public String getStartUhrzeit() {
		return startUhrzeit.get();
	}

	public void setStartUhrzeit(String startUhrzeit) {
		this.startUhrzeit.set(startUhrzeit);
	}

	private StringProperty endUhrzeit = new SimpleStringProperty();

	public String getEndUhrzeit() {
		return endUhrzeit.get();
	}

	public void setEndUhrzeit(String endUhrzeit) {
		this.endUhrzeit.set(endUhrzeit);
	}
	private StringProperty terminBezeichnung = new SimpleStringProperty();
	public String getTerminBezeichnung(){
		return terminBezeichnung.get();
		
	}
	public void setTerminBezeichnung(String terminBezeichnung){
		this.terminBezeichnung.set(terminBezeichnung);
	}
	private StringProperty terminBeschreibung = new SimpleStringProperty();
	public String getTerminBeschreibung(){
		return terminBeschreibung.get();
	}
	public void setTerminBeschreibung(String terminBeschreibung){
		this.terminBeschreibung.set(terminBeschreibung);
	}

	
	public static void main(String[] args) {
		Appointment termin = new Appointment();
		termin.setTerminBezeichnung("Bez-ONE");
		termin.setStartUhrzeit("19:30");
		termin.setEndUhrzeit("18:00");
		termin.setDatum(LocalDate.of(2015, 05, 29));
		System.out.println(termin.getStartUhrzeit());
		System.out.println(termin.getEndUhrzeit());
		System.out.println(termin.getTerminBezeichnung());
		System.out.println(termin.getDatum());
		
	}
	
}
