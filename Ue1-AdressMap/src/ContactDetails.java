

public class ContactDetails {
private String name = null;
private String vorname = null;
private String adresse = null;

//Konstruktor
	public ContactDetails(String name,String vorname,String adresse) {
		//this.vorname = vorname;
		//this.name = name;
		//this.adresse = adresse;
		this.name = name;
		this.vorname = vorname;
		this.adresse = adresse;
	}
	
	
	
	//get Methode
	
	public String getName() {
		return name;
	}
	public String getVorname() {
		return vorname;
	}
	public String getAdresse() {
		return adresse;
	}

	//set-Methode

	public void setName(String name) {
		this.name = name;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
}
