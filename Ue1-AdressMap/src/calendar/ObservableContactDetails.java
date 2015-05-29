package calendar;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import AdressBook.ContactDetails;

public class ObservableContactDetails extends ContactDetails {

	public ObservableContactDetails(String vorname, String name, String adresse) {
		super(vorname, name, adresse);
		// TODO Auto-generated constructor stub
	}
private StringProperty name = new SimpleStringProperty();
private StringProperty vorname = new SimpleStringProperty();
private StringProperty adresse = new SimpleStringProperty();

public String getName(){return name.get();}
public void setName(String nachname){this.name.set(nachname);}

public String getVorname(){return vorname.get();}
public void setVorname(String vorName){this.vorname.set(vorName);}

public String getAdresse(){return adresse.get();}
public void setAdresse(String anschrift) {this.adresse.set(anschrift);}
}
