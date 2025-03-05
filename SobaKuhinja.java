/**
 * Razred za prikaz uporabe dedovanja
 * Razširja razred soba
 *
 * @author Ana Skrbinšek
 * @version vaja 30
 */
 
public class SobaKuhinja extends Soba implements SobeZVodo{
	
	/**
	 * tip štedilnik (idukcijski, plinski, električni ...)
	 */	
	private String tipStedilnika;
	
	/**
	 * ali ima kuhinja pečico ali ne
	 * true - kuhinja ima pečico
	 * false - kuhinja nima pečice
	 */
	private boolean jePecica;
	
	/**
	 * število kuhinjskih omaric
	 */
	private int stOmaric;
	
	/**
	 * ali ima hladilnik tudi zmrzovalni del
	 * true - ima zmrzovalnik
	 * false - nima zmrzovalnika
	 */
	private boolean imaZmrzovalnik;
	
	/**
	 * kapaciteta celotnega hladilnika (z zmrzovalnikom) v litrih
	 */
	private int kapacitetaHladilnika;
	
	/**
	 * ali je pipa v kuhinjskem umivalniku zaprta ali odprta
	 * true - pipa je odprta
	 * false - pipa je zaprta
	 */
	private boolean pipaOdprta;
	
	/**
	 * temperatura vode iz pipe v °C
	 */
	private int temperaturaVode;

	
	/**
	 * Konstruktor za inicializacijo nove kuhinje
	 * Inicializira vse lastnosti za predvideno standardno kuhinjo
	 * @param v Število vrat v sobi
	 * @param o Število oken v sobi
	 * @param vs Velikost sobe (m2)
	 * @param t Temperatura (stopinje celzija)
	 */
	public SobaKuhinja(int stVrat, int stOken, double velikostSobe, int stOmaric){
		super(stVrat, stOken, velikostSobe, "kuhinja");
		this.stOmaric = stOmaric;
		this.tipStedilnika = "indukcijski";
		this.jePecica = true;
		this.imaZmrzovalnik = true;
		this.kapacitetaHladilnika = 300;
		this.pipaOdprta = false;
		this. temperaturaVode = 20;
	}
	
	/**
	 * metoda za natakanje vode v grelnik vode, ki drži 1.8L vode, po litrih
	 * @param l Natočeni litri
	 * @return kolicinaVode Količina vode v litrih
	 * @throws Exception če želimo natočiti preveč vode 
	 */
	public double natociVodoVL(double l) throws Exception{
		double kolicinaVode = 0;		
		if(l<=1.8){
			kolicinaVode = l;
			System.out.println("Natočili ste " + l + "l vode");
		}
		else{
			throw new Exception("Ne morem natočiti vode, ker je presežena zgornja meja 1,8 litra.");
		}
		return kolicinaVode;
	}
	
	/**
	 * metoda za natakanje vode v grelnik vode, ki drži 1.8L vode, po skodelicah, ki držijo 3dl (0.3l) tekočine
	 * @param skodelice Število skodelic
	 * @return kolicinaVode Količina vode v litrih
	 */	
	public double natociVodoSkodelice(int skodelice){
		double litri = skodelice*0.3;
		
		try{
			return natociVodoVL(litri);
		}
		
		catch(Exception e){
			System.out.println("Napaka pri natakanju vode.");
			return 0.0;
		}
	}
	
	/**
	 * metoda, na koliko stopinj mora biti voda segreta
	 @param tipČaja Tip čaja, ki ga želimo pripraviti
	 @return temperatura Temperatura vode, na katero je potrebno segreti
	 */
	
	public int temperaturaZaTipCaja(String tipCaja){
		int temperatura = 0;
		if(tipCaja.equals("beli") || tipCaja.equals("Beli") || tipCaja.equals("zeleni") || tipCaja.equals("Zeleni")){
			temperatura = 70;
		}
		
		else{
			temperatura = 100;
		}
		
		System.out.println("Vaš " + tipCaja + " čaj je potrebno segreti na " + temperatura + "°C.");
		return temperatura;
	}

	/**
	 * metoda za segrevanje vode na želeno temperaturo
	 * @param zelenaTemperatura Želena temperatura vode
	 * @return temperaturaPoGretju Temperatura vode po gretju
	 * @throws Exception, če je želena temperatura previsoka
	 */
	public int segrejVodo(int zelenaTemperatura) throws Exception{
		int temperaturaPoGretju = this.temperaturaVode;		
		if(zelenaTemperatura<=100 && zelenaTemperatura>temperaturaPoGretju){
			System.out.println("Trenutna temperatura vode je " + temperaturaVode + "°C. Segrevam na " + zelenaTemperatura + "°C....");
			temperaturaPoGretju = zelenaTemperatura;
			System.out.println("Voda je bila uspešno segreta na " + temperaturaPoGretju + "°C.");
		}
		
		else{
			throw new Exception("Želena temperatura je previsoka, poskusi ponovno");
		}
		return(zelenaTemperatura);
	}
	
	public void pripraviCaj(String tipCaja, double litri){
		int zelenaTemperatura = temperaturaZaTipCaja(tipCaja);
		int stSkodelic = (int)(litri/0.3);
		
		System.out.println("Pripravljam " + stSkodelic + " skodelic čaja...");
		
		try{
			segrejVodo(temperaturaZaTipCaja(tipCaja));
			System.out.println("Vaš " + tipCaja + " čaj je pripravljen.");			
		}
		
		catch(Exception e){
			System.out.println("Prišlo je do napake pri segrevanju čaja");
		}

	}
		
	/**
	 *************************************
	 * Setter metode za variabile objekta
	 *************************************
	 */

	/**
	 * metoda za nastavljanje tipa številnika
	 * @param tipStedilnika Tip štedilnika
	 */	
	public void setTipStedilnika(String tipStedilnika){
		this.tipStedilnika = tipStedilnika;
	}

	/**
	 * metoda za ali imamo pečico
	 * @param jePecica Ali je pečica
	 * true - kuhinja ima pečico
	 * false - kuhinja nima pečice
	 */		
	public void setJePecica(boolean jePecica){
		this.jePecica = jePecica;
	}
	
	/**
	 * metoda za nastavljanje števila omaric v kuhinji
	 * @param stOmaric Število omaric
	 */		
	public void setStOmaric(int stOmaric){
		this.stOmaric = stOmaric;
	}
	
	/**
	 * metoda za nastavljanje ali ima hladilnik zmrzovalnik
	 * @param imaZmrzovalnik Ali ima hladilnik zmrzovalnik
	 * true - hladilnik ima zmrzovalnik
	 * false - hladilnik nima zmrzovalnika
	 */	
	public void setImaZmrzovalnik(boolean imaZmrzovalnik){
		this.imaZmrzovalnik = imaZmrzovalnik;
	}
	
	/**
	 * metoda za nastavljanje kapacitete hladilnika
	 * @param kapacitetaHladilnika Kapaciteta hladilnika v litrih
	 */		
	public void setKapacitetaHladilnika(int kapacitetaHladilnika){
		this.kapacitetaHladilnika = kapacitetaHladilnika;
	}
	
	/**
	 * metoda za nastavljanje ali je pipa odprta ali ne
	 * @param pipaOdprta ali je pipa odprta
	 * true - pipa je odprta
	 * false - pipa je zaprta
	 */	
	public void setPipaOdprta(boolean pipaOdprta){
		this.pipaOdprta = pipaOdprta;
	}
	
	/**
	 * metoda za nastavljanje temperature vode
	 * @param temperaturaVode Temperatura vode v °C
	 */		
	public void setTemperaturaVode(int temperaturaVode){
		this.temperaturaVode = temperaturaVode;
	}
	
	/**
	 *************************************
	 * Getter metode za variabile objekta
	 ************************************
	 */
	
	/**
	 * metoda za pridobivanje informacije o tipu številnika
	 * @return tipStedilnika Tip štedilnika
	 */		
	public String getTipStedilnika(){
		return this.tipStedilnika;
	}

	/**
	 * metoda za pridobivanje informacije o tem, ali imamo pečico
	 * @return jePecica Ali je pečica
	 * true - kuhinja ima pečico
	 * false - kuhinja nima pečice
	 */		
	public boolean getJePecica(){
		return this.jePecica;
	}
	
	/**
	 * metoda za pridobivanje informacije o številu omaric v kuhinji
	 * @return stOmaric Število omaric
	 */		
	public int getStOmaric(){
		return this.stOmaric;
	}
	
	/**
	 * metoda za pridobivanje informacije o tem, ali ima hladilnik zmrzovalnik
	 * @return imaZmrzovalnik Ali ima hladilnik zmrzovalnik
	 * true - hladilnik ima zmrzovalnik
	 * false - hladilnik nima zmrzovalnika
	 */	
	public boolean getImaZmrzovalnik(){
		return this.imaZmrzovalnik;
	}
	
	/**
	 * metoda za pridobivanje informacije o kapaciteti hladilnika
	 * @return kapacitetaHladilnika Kapaciteta hladilnika v litrih
	 */		
	public int getKapacitetaHladilnika(){
		return this.kapacitetaHladilnika;
	}
	
	/**
	 * metoda za pridobivanje informacije o tem ali je pipa odprta ali ne
	 * @return pipaOdprta ali je pipa odprta
	 * true - pipa je odprta
	 * false - pipa je zaprta
	 */	
	public boolean getPipaOdprta(){
		return this.pipaOdprta;
	}
	
	/**
	 * metoda za pridobivanje informacije o temperaturi vode
	 * @return temperaturaVode Temperatura vode v °C
	 */		
	public int getTemperaturaVode(){
		return this.temperaturaVode;
	}	
	
}