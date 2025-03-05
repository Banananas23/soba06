//Uvozimo razred za delo z vrsto (buffer) iz paket za delo z vhodno-izhodnimi operacijami
import java.io.*;
import java.util.*;

/**
 * Razred za prikaz delovanja objekta soba
 * @author Ana Skrbinšek
 * @version vaja 30
 */

public class SobaUporaba{
	
	/**
	 * Statični atribut za branje vhoda iz konzole (tipkovnice)
	 */
	private static BufferedReader in;
	
	/**
	 * Seznam objektov tipa Soba
	 */
	private static ArrayList<Soba> sobe;

	/**
	 * Seznam objektov tipa SobaKuhinja
	 */
	private static ArrayList<SobaKuhinja> kuhinje;
	
	/**
	 * Seznam objektov tipa SobaKopalnica
	 */
	private static ArrayList<SobaKopalnica> kopalnice;
	
	/**
	 * Glavna metoda programa. Zažene se vedno ob zagonu.
	 *
	 * @param args Seznam argumentov iz ukazne vrstice
	 */
	public static void main(String[] args) {
		
		// inicializiramo objekt in za zajem podatkov iz tipkovnice preko konzole
		in = new BufferedReader(new InputStreamReader(System.in));
		
		// deklariramo in inicializiramo spremenljivke za kreiranje sobe
		String tipSobe = "Dnevna soba";
		int stOken = 0;
		int stVrat = 0;
		double kvadratura = 0.0;
		int stOmaric = 0;
		boolean velikiPralniStroj = false;
		boolean jeSusilniStroj = false;
		boolean jeBanja = false;
		
		// deklariramo in inicializiramo spremenljivko za preverjanje v While loopu
		String vnos;
		
		// inicializiramo sezname sob
		kuhinje = new ArrayList<>();
		kopalnice = new ArrayList<>();
		sobe = new ArrayList<>();
		
		System.out.println("Želiš ustvariti sobo? Če ne, pritsni enter. ");
		try{
			while((vnos = in.readLine()) != null && !vnos.isEmpty()){
				// poskusimo prebrati iz ukazne konzole		
				try{
					// vnos podatkov o tipu sobe
					System.out.print("Zapiši, katero sobo želiš ustvariti (kopalnica, kuhinja, dnevna soba ...): ");			
					tipSobe = in.readLine();
				}
					
				// lovljenje in obravnava izjeme pri branju vrstice
				catch(Exception e){
					System.out.println("Napaka pri branju tipa sobe " + e);
				}
			
				try{
					System.out.print("Koliko oken ima tvoja soba? ");			
					stOken = Integer.parseInt(in.readLine());
				}
				
				catch(Exception e){
					System.out.println("Napaka pri branju števila oken " + e);
				}
			
				try{
					System.out.print("Koliko m2 ima soba? ");			
					kvadratura = Double.parseDouble(in.readLine());
				}
				
				catch(Exception e){
					System.out.println("Napaka pri branju kvadrature sobe " + e);
				}		
			
				try{
					System.out.print("Koliko vrat ima tvoja soba? ");			
					stVrat = Integer.parseInt(in.readLine());
				}
				
				catch(Exception e){
					System.out.println("Napaka pri branju števila vrat " + e);
				}
			
				// Če je tip sobe kuhinja, vprašamo dodatna vprašanja, specifična za kuhinjo in jo nato kreiramo
				if(tipSobe.equals("kuhinja") || tipSobe.equals("Kuhinja")){
					try{
						System.out.print("Koliko kuhinjskih omaric imate? ");			
						stOmaric = Integer.parseInt(in.readLine());
					}	
				
					catch(Exception e){
						System.out.println("Napaka pri branju števila omaric " + e);
					}
					SobaKuhinja mojaKuhinja1 = new SobaKuhinja(stVrat, stOken, kvadratura, stOmaric);
				
					// Kuhinjo dodamo na seznam kuhinj			
					kuhinje.add(mojaKuhinja1);
				}

				// Če je tip sobe kuhinja, vprašamo dodatna vprašanja, specifična za kuhinjo in jo nato kreiramo		
				else if(tipSobe.equals("kopalnica") || tipSobe.equals("Kopalnica")){
					try{
						System.out.print("Ali je v kopalnici velik pralni stroj? (d/n) ");			
						if(in.readLine().equals("d")){
							velikiPralniStroj = true;
						}
					}
				
					catch(Exception e){
						System.out.println("Napaka pri branju tipa pralnega stroja " + e);
					}

					try{
						System.out.print("Ali je v kopalnici sušilni stroj? (d/n) ");			
						if(in.readLine().equals("d")){
							jeSusilniStroj = true;
						}
					}
				
					catch(Exception e){
						System.out.println("Napaka pri branju obstoja sušilnega stroja " + e);
					}

					try{
						System.out.print("Ali je v kopalnici banja? (d/n) ");			
						if(in.readLine().equals("d")){
							jeBanja = true;
						}
					}
				
					catch(Exception e){
						System.out.println("Napaka pri branju obstoja banje " + e);
					}
				
					SobaKopalnica mojaKopalnica1 = new SobaKopalnica(stOken, kvadratura, velikiPralniStroj, jeSusilniStroj, jeBanja);
				
					//kopalnico dodamo na seznam kopalnica
					kopalnice.add(mojaKopalnica1);
				}
			
					// Če je bil izbran drug tip sobe, kreiramo novo sobo tipa Soba
				else{
					Soba mojaSoba1 = new Soba(stVrat, stOken, kvadratura, tipSobe);
				
					// Dodamo novo sobo na seznam sob
					sobe.add(mojaSoba1);
				}
				System.out.println("Želiš ustvariti še eno? Če ne, pritsni enter. ");
			}
		}

		catch(Exception e){
			System.out.println("Priđlo je do splošne napake pri vnosu " + e);
		}
			
		System.out.println("Na seznamu je " + sobe.size() + " sob, " + kuhinje.size() + " kuhinj in " + kopalnice.size() + " kopalnic");
	}
}	
	