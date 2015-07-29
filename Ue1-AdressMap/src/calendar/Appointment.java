package calendar;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {
	// Konstruktor
	public Appointment() {
		super();
		this.terminKategorie = new SimpleStringProperty("Termin-Kategorie");
		this.datum = new SimpleObjectProperty<LocalDate>((LocalDate.of(2000, 1, 1)));
		this.startUhrzeit = new SimpleObjectProperty<LocalTime>(LocalTime.of(00, 00));
		this.endUhrzeit = new SimpleObjectProperty<LocalTime>(LocalTime.of(23, 59));
		this.terminBezeichnung = new SimpleStringProperty("Termin-Bezeichung");
		this.terminBeschreibung = new SimpleStringProperty("Termin-Beschreibung");
		
	}
	
	// Konstruktor
	public Appointment(String terminKategorie,
			LocalDate datum,
			LocalTime startUhrzeit,
			LocalTime endUhrzeit,
			String terminBezeichnung, 
			String terminBeschreibung) {
		super();
		this.terminKategorie = new SimpleStringProperty(terminKategorie);
		this.datum = new SimpleObjectProperty<LocalDate>(datum);
		this.startUhrzeit = new SimpleObjectProperty<LocalTime>(startUhrzeit);
		this.endUhrzeit = new SimpleObjectProperty<LocalTime>(endUhrzeit);
		this.terminBezeichnung = new SimpleStringProperty(terminBezeichnung);
		this.terminBeschreibung = new SimpleStringProperty(terminBeschreibung);
	}


	/// ### TERMINKATEGORIE ###///
	private StringProperty terminKategorie = new SimpleStringProperty();
	public StringProperty getTerminkategorie() {return terminKategorie;}
	public void setTerminkategorie(String terminKategorie) {this.terminKategorie.set(terminKategorie);}

	//### DATUM  #### ///
	//private ObjectProperty<LocalDate> datum = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
	private ObjectProperty<LocalDate> datum = new SimpleObjectProperty<LocalDate>();
	public ObjectProperty<LocalDate> getDatum() {return datum;}
	public void setDatum(LocalDate date) {	this.datum.set(date);	}

	//### STARTUHRZEIT ###///
	private ObjectProperty<LocalTime> startUhrzeit = new SimpleObjectProperty<LocalTime>();
	public ObjectProperty<LocalTime> getStartUhrzeit() {return startUhrzeit;}
	public void setStartUhrzeit(LocalTime startTime) {this.startUhrzeit.set(startTime);}

	/// ### ENDUHRZEIT ###/// // 1B //
	private ObjectProperty<LocalTime> endUhrzeit = new SimpleObjectProperty<LocalTime>();
	public ObjectProperty<LocalTime> getEndUhrzeit() {return endUhrzeit;}
	public void setEndUhrzeit(LocalTime endTime) throws BeforeException {
		if(getStartUhrzeit()==null) throw new BeforeException();
		if(endTime.isBefore(getStartUhrzeit().get())) throw new BeforeException();
		this.endUhrzeit.set(endTime);}

	/// ### TERMINBEZEICHNUNG ###///
	private StringProperty terminBezeichnung = new SimpleStringProperty();
	public StringProperty getTerminBezeichnung(){return terminBezeichnung;}
	public void setTerminBezeichnung(String terminBezeichnung){this.terminBezeichnung.set(terminBezeichnung);}
	private StringProperty terminBeschreibung = new SimpleStringProperty();
	
	/// ### TERMINBESCHREIBUNG ###///
	public StringProperty getTerminBeschreibung(){return terminBeschreibung;}
	public void setTerminBeschreibung(String terminBeschreibung){this.terminBeschreibung.set(terminBeschreibung);}

	/// ### MAIN METHODE ###/// // 1B //
	public static void main(String[] args) {
		Appointment termin = new Appointment("Kat 1",LocalDate.of(2015, 05, 29),LocalTime.of(12, 20),LocalTime.of(11, 20), "Termin1", "TERMIN und so");
		
		
		
		System.out.println(termin.getStartUhrzeit());
		System.out.println(termin.getEndUhrzeit());
		System.out.println(termin.getTerminBezeichnung());
		System.out.println(termin.getDatum());
	}
	
}
