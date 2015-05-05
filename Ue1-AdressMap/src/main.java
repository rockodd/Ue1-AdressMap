
public class main {

	private static AddressBook meinAdressbuch = new AddressBook();
	
	
	public static void main(String[] args) {
		// Anzahl der gespeicherten Adressen
		System.out.println("Anzahl der Kontakte:" + meinAdressbuch.getNumberOfEntries());
		testdata();
		System.out.println("Anzahl der Kontakte:" + meinAdressbuch.getNumberOfEntries());
	}
	
	private static void testdata(){
		
		ContactDetails a = new ContactDetails("Fox", "Peter","Fuchsbau 1");		
		ContactDetails b = new ContactDetails("Walker", "Paul","Catwalk 1");
		ContactDetails c = new ContactDetails("Walfred", "PJ","Hood 1");
		a.setAdresse("Fuchsbau 4");
		a.setAdresse("Fuchsbau 5");
		b.setAdresse("Catwalk 99");
		
		meinAdressbuch.addDetails(a);
		meinAdressbuch.addDetails(b);
		meinAdressbuch.addDetails(c);
		
		System.out.println(meinAdressbuch.getDetails("Peter").getAdresse());
		System.out.println(meinAdressbuch.getDetails("Paul").getAdresse());
		
		meinAdressbuch.changeDetails("Fox",new ContactDetails("Montana", "Frank", "Gangway 6"));
		//System.out.println(meinAdressbuch.getDetails("PeterFox").getAdresse());
		//meinAdressbuch.removeDetails("PeterFox");
		
		System.out.println(meinAdressbuch.getDetails("Montana").getAdresse());
		//System.out.println(meinAdressbuch.getDetails("PeterFox").getAdresse());
		meinAdressbuch.removeDetails("Frank");
		
		meinAdressbuch.search("Walker");
		for(ContactDetails result : meinAdressbuch.search("Wa")){
			System.out.println(result.getVorname());
			System.out.println(result.getName());
		}
		
	}
	
	

}
