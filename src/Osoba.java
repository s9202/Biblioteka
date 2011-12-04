

import java.util.ArrayList;
import java.util.List;

public class Osoba {
	
	public String imie;
	public String nazwisko;
	
	public List<Plyta> plyty = new ArrayList<Plyta>();
	
	public Osoba(String par_imie, String par_nazwisko, List<Plyta> par_plyty) {
		imie = par_imie;
		nazwisko = par_nazwisko;
		plyty = par_plyty;
	}
	
	public void wyswietlPlyty()
	{
		System.out.println("\nPLYTY W POSIADANIU OSOBY: " + imie + " " + nazwisko);
		Plyta robPlyty;
		for (int i = 0; i< plyty.size(); i++ ) {
			robPlyty = plyty.get(i);
			robPlyty.wyswietlPlyte();
		}
	}

}
