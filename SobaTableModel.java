import javax.swing.table.*;

/**
 * Razred za delo s tabelo sob
 * Razširja privzeti razred tabele
 *
 * @author Ana Skrbinšek
 * @version vaja 35
 */
 
public class SobaTableModel extends DefaultTableModel {
	
	/**
	 * Konstruktor, ki ustvari tabelo steklenic na mizi
	 */
	public SobaTableModel () {
		
		// Pokličemo konstruktor nadrazreda
		super();
		
		// Dodamo stolpce v tabelo
		addColumn("Tip sobe");
		addColumn("Število oken");
		addColumn("Število vrat");
		addColumn("Velikost sobe");		
		
		// Ustvarimo seznam objektov (nizov), ki predstavlja vrstico tabele
		Object[] vrstica = new Object[] {"Testna soba", "3", "2", "20"};
		
		// Vrstico vstavimo v tabelo
		addRow(vrstica);
		}
	
	/**
	 * Javna metoda, ki doda pivsko steklenico v tabelo
	 * @param ps Objekt, ki dodamo v tabelo
	 */
	public void addSoba(Soba s){
		// Ustvarimo seznam objektov (nizov), ki predstavlja vrstico tabele
		Object[] vrstica = new Object[] {s.getTipSobe(), s.getStOken(), s.getStVrat(), s.getVelikostSobe()};
		
		// Vrstico vstavimo v tabelo
		addRow(vrstica);
	}
}