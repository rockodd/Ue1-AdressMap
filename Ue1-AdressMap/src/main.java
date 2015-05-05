import SupportLine.SupportLine;


public class main {

	private static AdressBookExceptionsInterface meinAdressbuch = new AddressBook();
	
	
	public static void main(String[] args) {
		// Anzahl der gespeicherten Adressen
		boolean cancel = false;
		String input = null, vorname = null, nachname = null, adresse = null;
		while (!cancel){
			System.out.println();
			System.out.println("--------------------------------------------------");
			System.out.println("Was möchten Sie tun? Bitte eins der folgenden Kommandos eingeben.");
			System.out.println("bye 	-> 	Beendet das Adressbuch");
			System.out.println("anzahl 	-> 	Gibt die Anzahl der Einträge aus");
			System.out.println("neu 	-> 	Legt einen neuen Kontakt an");
			System.out.println("suche 	-> 	Sucht nach eingegebenen Kontakt");
			System.out.println("refresh -> 	Ändert die Details zu einem Kontakt");
			System.out.println("--------------------------------------------------");
			
			input = SupportLine.InputReader();
			System.out.println();
			switch (input.toLowerCase()){
			case "bye" :	
				cancel = true;
				break;
			case "anzahl"	: 
				System.out.println("Anzahl der Kontakte: " + meinAdressbuch.getNumberOfEntries());
				break;
			case "neu"	:
				System.out.println("Bitte Vornamen eingeben:");
				vorname = SupportLine.InputReader();
				System.out.println("Bitte Nachnamen eingeben:");
				nachname = SupportLine.InputReader();
				System.out.println("Bitte Adresse eingeben:");
				adresse = SupportLine.InputReader();
				try {
					try {
						meinAdressbuch.addDetails(new ContactDetails(nachname,vorname,adresse));
						System.out.println("Neuer Kontakt wurde angelegt.");
					} catch (ParamKeyIsNullException e) {
						System.out.println("Kontakt wurde nicht geändert aufgrund des Fehlers: " + e);
					} catch (ParamKeyIsEmptyException e) {
						System.out.println("Kontakt wurde nicht geändert aufgrund des Fehlers: " + e);
					}
					
				}
				catch (DoppelException e){ 
					System.out.println("Kontakt schon vorhanden.");
				}
				break;
			case "suche"	:
				System.out.println("Bitte Vornamen oder Nachnamen für die Suche iengeben:");
				input = SupportLine.InputReader();
				try {
					try {
						System.out.println(meinAdressbuch.getDetails(input).getVorname()+","+meinAdressbuch.getDetails(input).getName());
					} catch (ParamKeyIsNullException e) {
						System.out.println("Suche konnte nicht ausgeführt werden aufgrund des Fehlers: " + e);
					} catch (ParamKeyIsEmptyException e) {
						System.out.println("Suche konnte nicht ausgeführt werden aufgrund des Fehlers: " + e);
					}
					try {
						System.out.println(meinAdressbuch.getDetails(input).getAdresse());
					} catch (ParamKeyIsNullException e) {
						System.out.println("Adresse konnte nicht gefunden werden aufgrund des Fehlers: " + e);
					} catch (ParamKeyIsEmptyException e) {
						System.out.println("Adresse konnte nicht gefunden werden aufgrund des Fehlers: " + e);
					}
				}
				catch (KeinKontaktException e){ 
					System.out.println("Kein Kontakt vorhanden.");
				}
				break;
				
			case "refresh"	:
				System.out.println("Bitte Vornamen oder Nachnamen des Kontaktes, der geändert werden soll eingeben:");
				input = SupportLine.InputReader();
				System.out.println("Bitte neuen Vornamen eingeben:");
				vorname = SupportLine.InputReader();
				System.out.println("Bitte neuen Nachnamen eingeben:");
				nachname = SupportLine.InputReader();
				System.out.println("Bitte neue Adresse eingeben:");
				adresse = SupportLine.InputReader();
				try {
					meinAdressbuch.changeDetails("input",new ContactDetails(nachname,vorname, adresse));
				} catch (ParamKeyIsNullException e) {
					System.out.println(e);
					e.printStackTrace();
				} catch (ParamKeyIsEmptyException e) {
					System.out.println(e);
					e.printStackTrace();
				} catch (DoppelException e) {
					System.out.println(e);
					e.printStackTrace();
				}
				break;
			}
			
		}
	}
	
//	private static void testdata(){
//		
//		ContactDetails a = new ContactDetails("Fox", "Peter","Fuchsbau 1");		
//		ContactDetails b = new ContactDetails("Walker", "Paul","Catwalk 1");
//		ContactDetails c = new ContactDetails("Walfred", "PJ","Hood 1");
//		a.setAdresse("Fuchsbau 4");
//		a.setAdresse("Fuchsbau 5");
//		b.setAdresse("Catwalk 99");
//		
//		meinAdressbuch.addDetails(a);
//		meinAdressbuch.addDetails(b);
//		meinAdressbuch.addDetails(c);
//		
//		System.out.println(meinAdressbuch.getDetails("Peter").getAdresse());
//		System.out.println(meinAdressbuch.getDetails("Paul").getAdresse());
//		
//		meinAdressbuch.changeDetails("Fox",new ContactDetails("Montana", "Frank", "Gangway 6"));
//		//System.out.println(meinAdressbuch.getDetails("PeterFox").getAdresse());
//		//meinAdressbuch.removeDetails("PeterFox");
//		
//		System.out.println(meinAdressbuch.getDetails("Montana").getAdresse());
//		//System.out.println(meinAdressbuch.getDetails("PeterFox").getAdresse());
//		meinAdressbuch.removeDetails("Frank");
//		
//		meinAdressbuch.search("Walker");
//		for(ContactDetails result : meinAdressbuch.search("Wa")){
//			System.out.println(result.getVorname());
//			System.out.println(result.getName());
//		}
//		
//	}
	
	

}
