
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
	

public class Main {
	
	public static String START_MSG = "--- START APLIKACJI --------------------------------------------- ";
	
	private static Logger logger= Logger.getLogger(Main.class);
	
	
	
	static void	addToList( List<Plyta> pLista, Plyta pPlyta ) throws MojWyjatek {
		
		Calendar kalendarz = Calendar.getInstance(); 		// ustalanie bie¿¹cego roku wewn¹trz metody dodawania p³yty
		int rokBiezacy = kalendarz.get( Calendar.YEAR ); 	// umo¿liwia poprawn¹ reakcjê na wejœcie w nowy rok (z 31.12 na 01.01) 
		
		if ( pLista == null )
			throw new MojWyjatek("Lista jest niezainicjowana");
		if ( pPlyta.Rok() > rokBiezacy)
			throw new MojWyjatek("BLAD: Podany rok jest pozniejszy niz obecny. Wpis bledny: "
					+ pPlyta.Wykonawca() + " | " + pPlyta.Tytul() + " | " + pPlyta.Gatunek() + " | " + pPlyta.Rok() );
		if ( pPlyta.Rok() < 1885)
			throw new MojWyjatek("Pierwsze plyty nagrano w 1885, podano zbyt wczesny rok. Wpis bledny: "
					+ pPlyta.Wykonawca() + " | " + pPlyta.Tytul() + " | " + pPlyta.Gatunek() + " | " + pPlyta.Rok() );
		
		int 	pozycja 	= pLista.size()-1;
		boolean	jest	= false;
		
		while (!jest && pozycja >= 0 ) {
			jest	= pLista.get(pozycja).Tytul().equals(pPlyta.Tytul() ) && pLista.get(pozycja).Wykonawca().equals( pPlyta.Wykonawca() ) && pLista.get(pozycja).Gatunek().equals( pPlyta.Gatunek() ) && ( pLista.get(pozycja).Rok() == pPlyta.Rok() );
			pozycja--;
		}
		
		if (jest) 
			throw new MojWyjatek("Taka p³yta ju¿ istnieje na liscie. Wpis bledny: "
					+ pPlyta.Wykonawca() + " | " + pPlyta.Tytul() + " | " + pPlyta.Gatunek() + " | " + pPlyta.Rok() );
		
		pLista.add( pPlyta );
		logger.info( "Dodano p³ytê do kolekcji: Wykonawca-" + pPlyta.Plyta() );
	}
	
	
	
	
	private static void	dodajPlyte( List<Plyta> pLista, String pWykonawca, String pTytul, PlytaGatunek pGatunek, int pRok ) {
		try {
			addToList( pLista, new Plyta( pWykonawca, pTytul, pGatunek, pRok ) );
		}
		catch (MojWyjatek e) {
			logger.error(e);
			logger.fatal(e);
			logger.info(e);
			logger.warn(e);
			//System.out.println( e );
		}
	}

	
	public static void main(String args[]) {
		
		PropertyConfigurator.configure("Log4J.properties");
		logger.error(START_MSG); // komunikat wpisywany do dziennika po to, aby oddzieliæ komunikaty z kolejnych uruchomieñ programu
									// poziom 'error' - aby wpis dotyczy³ równie¿ dziennika administratora, 
									// do którego nie s¹ wpisywane logi typu 'info' czy 'warn' (wg ustawieñ w Log4J.properties)  
		
		List<Plyta> plyty;
		
		
		plyty = new ArrayList<Plyta>();
		dodajPlyte( plyty, "Budka Suflera", "Akustycznie", PlytaGatunek.rock, 1998 );
		dodajPlyte( plyty, "Budka Suflera", "Nic nie boli, tak jak zycie", PlytaGatunek.rock, 1997);
		dodajPlyte( plyty, "Modern Talking", "Back For Good", PlytaGatunek.eurodisco, 1998);
		dodajPlyte( plyty, "Modern Talking", "Back For Good", PlytaGatunek.eurodisco, 1998);

		Osoba o1 = new Osoba("Andrzej", "Nowak", plyty);
		
		
		plyty = new ArrayList<Plyta>();
		dodajPlyte( plyty, "The Prodigy", "Invaders Must Die", PlytaGatunek.techno, 2009);
		dodajPlyte( plyty, "Scooter", "Jumpin' All Over the World", PlytaGatunek.techno, 2008);
		dodajPlyte( plyty, "Gigi d'Agostino", "L'Amour Toujours", PlytaGatunek.techno, 1999);
	
		Osoba o2 = new Osoba("Jan", "Kluska", plyty);
		
		o1.wyswietlPlyty();
		o2.wyswietlPlyty();
	}

}
