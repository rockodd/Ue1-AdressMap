package AdressBook;

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

	private StringProperty datum = new SimpleStringProperty();

	public String getDatum() {
		return datum.get();

	}

	public void setDatum(String datum) {
		this.datum.set(datum);
	}

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
		this.setTerminBezeichnung(terminBezeichnung);
	}
	private StringProperty terminBeschreibung = new SimpleStringProperty();
	public String getTerminBeschreibung(){
		return terminBeschreibung.get();
	}
	public void setTerminBeschreibung(String terminBeschreibung){
		this.terminBeschreibung.set(terminBeschreibung);
	}

}
