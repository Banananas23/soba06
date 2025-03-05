/**
 * Razred za prikaz uporabe dedovanja
 * Razširja razred soba
 *
 * @author Ana Skrbinšek
 * @version vaja 30
 */
 
public class SobaKopalnica extends Soba implements SobeZVodo {
	
	/**
	 * tip pralenga stroja (veli ali mali)
	 * true - velik pralni stroja
	 * false - mali pralni stroj
	 */
	private boolean velikPralniStroj;

	/**
	 * ali ima kopalnica sušilni stroj
	 * true - ima sušilni storj
	 * false - nima sušilnega stroja
	 */
	private boolean imaSusilniStroj;
	
	/**
	 * ali ima kopalnica banjo
	 * true - ima banjo
	 * false - nima banje
	 */	
	private boolean imaBanjo;
	
	/**
	 * temperatura vode v °C
	 */
	private int temperaturaVode;

	/**
	 * Konstruktor za inicializacijo nove kuhinje
	 * Inicializira vse lastnosti za predvideno standardno kuhinjo
	 * @param stOken Število oken v sobi
	 * @param velikostSobe Velikost sobe (m2)
	 * @param velikPralniStroj Ali je pralni stroj velik
	 * @param imaSusilniStroj Ali ima sušilni stroj
	 * @param imaBanjo Ali ima banjo
	 */
	public SobaKopalnica(int stOken, double velikostSobe, boolean velikPralniStroj, boolean imaSusilniStroj, boolean imaBanjo) {
		super(1, stOken, velikostSobe, "kopalnica");
		this.velikPralniStroj = velikPralniStroj;
		this.imaSusilniStroj=imaSusilniStroj;
		this.imaBanjo  = imaBanjo;
		this.temperaturaVode = 20;
	}

	/**
	 * metoda za segrevanje vode na želeno temperaturo
	 * @param zelenaTemperatura Želena temperatura vode
	 * @return temperaturaPoGretju Temperatura vode po gretju
	 + @throws Exception če je prišlo do napake pri gretju temperature
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

	/**
	 * metoda za nalaganje perila v pralni stroj
	 * @param kolicinaOblacil Koliko oblačil želimo oprati
	 * @return greVStroj Ali gre količina oblačil v stroj ali ne
	 * true - količina oblačil je primerna in gre v stroj
	 * false - količina oblačil je prevelika in ne gre v stroj
	 */
	public boolean zasdostnaVelikostStroja(int kolicinaOblacil){
		boolean greVStroj = true;
		
		if(!velikPralniStroj){
			if(kolicinaOblacil>27){
				int odstrani = kolicinaOblacil-27;
				System.out.println("Na žalost je vaš stroj premajhen. Prosim, odstranite " + odstrani + " kosov oblačil in poskusite ponovno.");
				greVStroj = false;
			}
		}
		
		else if(kolicinaOblacil>45){
			int odstrani = kolicinaOblacil-27;
			System.out.println("Na žalost je vaš oralni stroj premajhen. Prosim, odstranite " + odstrani + " kosov oblačila in poskusite ponovno.");
			greVStroj = false;
		}
		
		else{
			System.out.println("Uspešno ste naložili oblačila v pralni stroj.");
			greVStroj = true;
		}
		
		return greVStroj;
	}
	
	/**
	 * metoda za pranje perila
	 * @param operiNaTemperaturi Temperatura, na kateri želimo oblačila oprati
	 * @param stOblacil Koliko oblačil želimo oprati
	 * @throws Exception če je prišlo do napake pri nalaganju v stroj ali pri gretju temperature
	 */	
	public void operiOblacila(int operiNaTemperaturi, int stOblacil) throws Exception{
		int segrejNa = segrejVodo(operiNaTemperaturi);
		boolean greVStroj = zasdostnaVelikostStroja(stOblacil);
		
		if(segrejNa == operiNaTemperaturi && greVStroj){
				System.out.println("Vseh " + stOblacil + " oblačil je bilo uspešno opranih na temperaturi " + segrejNa + "°C");
		}
		
		else{
			throw new Exception("Prišlo je do napake pri pranju perila");
		}
	}
	
	/**
	 *************************************
	 * Setter metode za variabile objekta
	 *************************************
	 */

	/**
	 * metoda za nastavljanje tipa pralnega stroja
	 * @paramvelikiPralniStroj Ali obstaja velik pralni stroj
	 * true - velik pralni stroja
	 * false - mali pralni stroj
	 */
	public void setVelikPralniStroj(boolean velikPralniStroj){
		this.velikPralniStroj=velikPralniStroj;
	}
	
	/**
	 * metoda za nastavljanje ali ima kopalnica sušilni stroj
	 * @param imaSusilniStroj Ali obstaja sušilni stroj
	 * true - ima sušilni storj
	 * false - nima sušilnega stroja
	 */
	public void setImaSusilniStroj(boolean imaSusilniStroj){
		this.imaSusilniStroj=imaSusilniStroj;
	}
	
	/**
	 * metoda za nastavljanje ali ima kopalnica banjo
	 * @param imaBanjo Ali obstaja banja
	 * true - ima banjo
	 * false - nima banje
	 */	
	public void setImaBanjo(boolean imaBanjo){
		this.imaBanjo=imaBanjo;
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
	 * metoda za metoda za pridobivanje informacije o tipu pralnega stroja
	 * @return velikiPralniStroj Ali ima velik pralni stroj
	 * true - velik pralni stroja
	 * false - mali pralni stroj
	 */
	public boolean getVelikPralniStroj( ){
		return this.velikPralniStroj;
	}
	
	/**
	 * metoda za pridobivanje informacije o tem ali ima kopalnica sušilni stroj
	 * @return imaSusilniStroj Ali obstaja sušilni stroj
	 * true - ima sušilni storj
	 * false - nima sušilnega stroja
	 */
	public boolean getImaSusilniStroj(){
		return this.imaSusilniStroj;
	}
	
	/**
	 * metoda za pridobivanje informacije o tem, ali ima kopalnica banjo
	 @return imaBanjo Ali obstaja banja
	 * true - ima banjo
	 * false - nima banje
	 */	
	public boolean getImaBanjo(){
		return this.imaBanjo;
	}
	
	/**
	 * metoda za pridobivanje informacije o temperaturi vode
	 * @return temperaturaVode Temperatura vode v °C
	 */
	public int getTemperaturaVode(){
		return this.temperaturaVode;
	}	
}