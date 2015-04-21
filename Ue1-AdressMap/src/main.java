
public class main {

	private static AdressBook meinAdressbuch = new AdressBook();
	
	
	public static void main(String[] args) {
	System.out.println("Anzahl der Kontakte:" + meinAdressbuch.getNumberOfEntries());
	testdata();
	
	
	System.out.println("Anzahl der Kontakte:" + meinAdressbuch.getNumberOfEntries());
	

		

	}
	
	
	
	private static void testdata(){
		
		ContactDetails a = new ContactDetails("Fox", "Peter","Fuchsbau 1");		
		ContactDetails b = new ContactDetails("Walker", "Paul","Catwalk 1");
		
		
				
		meinAdressbuch.addDetails(a);
		meinAdressbuch.addDetails(b);
		a.setAdresse("Fuchsbau 4");
		a.setAdresse("Fuchsbau 5");
		b.setAdresse("Catwalk 99");
		System.out.println(meinAdressbuch.getDetails("PeterFox").getAdresse());
		System.out.println(meinAdressbuch.getDetails("PaulWalker").getAdresse());
		
		meinAdressbuch.changeDetails("PeterFox",new ContactDetails("Montana", "Frank", "Gangway 6"));
		//System.out.println(meinAdressbuch.getDetails("PeterFox").getAdresse());
		//meinAdressbuch.removeDetails("PeterFox");
		System.out.println(meinAdressbuch.getDetails("FrankMontana").getAdresse());
		//System.out.println(meinAdressbuch.getDetails("PeterFox").getAdresse());
		meinAdressbuch.removeDetails("FrankMontana");
	}
	
	

}
