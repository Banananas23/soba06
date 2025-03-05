//Uvozimo razrede za delo z vhodno-izhodnimi operacijami ter pripomočke
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Razred za prikaz delovanja grafičnega uporabniškega vmesnika
 * Razširja razred za delo z okni
 *
 * @author Ana Skrbinšek
 * @version Vaja 35
 */

public class Hisa extends JFrame implements ActionListener {
	
	// Deklariramo zasebne lastnosti GUI
	private JPanel povrsina;
	private SobaTableModel modelTabele;	
	
	private JButton dodajButton;
	private JTextField tipSobeTextField;
	private JTextField stOkenTextField;
	private JTextField stVratTextField;
	private JTextField kvadraturaTextField;	

	
	
	/**
	 * Glavna metoda programa. Zažene se vedno ob zagonu.
	 *
	 * @param args Seznam argumentov iz ukazne vrstice
	 */
	
	public static void main(String[] args){
		
		//izpišemo začetek
		System.out.println("Zaganjam GUI ...");
		
		// Ustvarimo objekt razreda miza (kličemo konstruktor razreda Miza)
		
		Hisa m = new Hisa();
	}
	
	/**
	 * Konstruktor, ki postavi osnovne lastnosti okna
	 */
	public Hisa(){
		
		// Pokličemo konstruktor nadrazreda
		super("Hiša s sobami");

		// Nastavimo obnašanje križca [x] tako, da konča aplikacijo
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Nastavimo velikost okna
		setSize(800,600);
		
		// Inicializiramo elemente GUI
		povrsina = new JPanel();
		
		// Vnosna polja za dodajanje sobe
		tipSobeTextField = new JTextField(32);
		stOkenTextField = new JTextField(2);
		stVratTextField = new JTextField(2);
		kvadraturaTextField = new JTextField(7);			
		
		// Dodamo vnosna polja na površino
		povrsina.add(new JLabel("Tip sobe: "));
		povrsina.add(tipSobeTextField);
		
		povrsina.add(new JLabel("Število oken: "));		
		povrsina.add(stOkenTextField);

		povrsina.add(new JLabel("Število vrat: "));		
		povrsina.add(stVratTextField);		

		povrsina.add(new JLabel("Površina sobe: "));		
		povrsina.add(kvadraturaTextField);			

		
		// Gumb za dodajanje steklenice
		dodajButton = new JButton("Dodaj sobo");
		
		// Gumbu dodamo action listener za dodajanje steklenic
		dodajButton.addActionListener(this);
		
		// Dodamo gumb na površino
		povrsina.add(dodajButton);
		
		// Inicializiramo model tabele
		modelTabele = new SobaTableModel();
		
		// Tabelo postavimo na površino
		// Ustvarimo anonimen objekt razreda JTable, z modelomTabele
		povrsina.add(new JTable(modelTabele));
		
		// Površino dodamo na okno
		add(povrsina);
		
		// Prikažemo okno
		setVisible(true);
	}
	
	/**
	 * Metoda, ki jo predpisuje vmesnik ActionListener
	 * se kliče vedno, ko je pritisnjen gumb
	 * @param e Dogodek ob kliku
	 */
	public void actionPerformed(ActionEvent e){
		
		// Izpišemo lastnosti iz vnosnih pollj
		System.out.println("Dodajam sobo ...");
		System.out.println("Tip sobe: " + tipSobeTextField.getText());
		System.out.println("Število oken: " + stOkenTextField.getText());		
		System.out.println("Število vrat: " + stVratTextField.getText());
		System.out.println("Velikost sobe: " + kvadraturaTextField.getText());			

	
		// Spremenljivka za stopnjo alkohola
		int so = Integer.parseInt(stOkenTextField.getText());
		int sv = Integer.parseInt(stVratTextField.getText());	
		double k = Double.parseDouble(kvadraturaTextField.getText());		
	
		// Ustvarimo nov objekt tipa PivskaSteklenica
		Soba mojaSoba = new Soba(sv, so, k, tipSobeTextField.getText());
		
		// Objekt dodamo v tabelo
		modelTabele.addSoba(mojaSoba);
	}	
}