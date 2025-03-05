/**
 * Razred za prikaz modela sobe, naststavljena na standardno temperaturo in njenih lastnosti
 *
 * @author Ana Skrbinšek
 * @version primer 30
 */

public class Soba{
	
	//deklariramo lastnosti
	
	/**
	 * tip sobe
	 */
	private String tipSobe;
	
	/**
	 *Število vrat, ki jih ima soba
	 */
	private int stVrat;
	
	/**
	 *Število oken v sobi
	 */
	private int stOken;
	
	/**
	 *Kvadratura sobe v kvadratnih metrih
	 */
	private double velikostSobe;
	
	/**
	 *Temperatura v sobi v stopinjah celzija
	 */
	private int temperatura;
	
	/**
	 *preverimo, ali so okna zaprta
	 *true - so odprta
	 *false - so zaprta
	 */
	private boolean oknaOdprta;
	
	/**
	 *preverimo, ali je ogrevanje vključeno
	 *true - je vključeno
	 *false - je izključeno
	 */
	private boolean ogrevanjeVkljuceno;
	
	/**
	 *čistoča sobe izražena v %
	 *100 - soba je 100% čista in je ni treba sesati
	 *0 - soba je zelo umazana, potrebno jo je posesati
	 */
	private int cistocaSobe;
	
	/**
	 * Konstruktor za inicializacijo nove sobe
	 * Inicializira vse lastnosti
	 * @param v Število vrat v sobi
	 * @param o Število oken v sobi
	 * @param vs Velikost sobe (m2)
	 * @param t Temperatura (stopinje celzija)
	 * @param ts Tip sobe
	 */
	public Soba(int v, int o, double vs, String ts, int t){
		this.stVrat = v;
		this.stOken = o;
		this.velikostSobe = vs;
		this.temperatura = t;
		this.oknaOdprta = false;
		this.ogrevanjeVkljuceno = false;
		this.cistocaSobe = 100;
		this.tipSobe = ts;
	}
	
	/**
	 *Konstruktor za inicializacijo nove sobe, ki je ogreta na priporočeno temperaturo
	 * @param v Število vrat v sobi
	 * @param o Število oken v sobi
	 * @param vs Velikost sobe (m2)
	 * @param ts Tip sobe
	 */
	public Soba(int v, int o, double vs, String ts){
		//pokličemo prejšnji konstruktor
		this(v, o, vs, ts, 22);
	}
	
	/**
	 *metoda za odpiranje oken
	 @return ali so okna odprta ali zaprta
	 */
	public boolean odpriOkna() throws Exception{
		if(oknaOdprta){
			return false;
		}
		
		else{			
			if(stOken > 0){
				System.out.println("Odpiram toliko oken: " + stOken);
				oknaOdprta = true;
				return true;
			}
			
			else{
				throw new Exception("V sobi ni oken, zato jih ne morem odpreti");
			}
		}
		
	}
	
	/**
	 * metoda za prezračevanje prostora
	 * v povprečju se temperatura v sobi pri odprtih oknih zmanjšuje do 5°C na 30 minut
	 * dodamo malo več, če je več oken
	 * po koncu zračenja zapremo okna
	 * @param m Čas odprtja oken v minutah
	 *@return Trenutna temepratura v sobi po zračenju
	 */
	public int zracenje(int m){
		if(oknaOdprta){
			System.out.println("Trenutna temperatura v sobi: " + temperatura + ". Zračmo " + m + " minut.");
			if(m<30){
				temperatura-=2;
			}
		
			else{
				//Če je število oken 1, potem se temperatura v prostoru zmanjša v povprečju do 5°C na 30 minut
				//Za vsako dodatno okno, bomo dodali še 1°C znižanja na 30 minut
				int stopinje = ((m/30)*5)+(stOken-1);
				
				temperatura-=stopinje;
				//določili bomo, da temperatura ne bo padla pod -5°C, zaradi predvidene znanje temperature
				if(temperatura < -5){
					temperatura = -5;
					System.out.println("Temperatura v sobi je " + temperatura + " in se ne bo več znižala.");
				}
			}
			System.out.println("Prezračil sem " + m + " minut. Sedaj zapiram okna.");
			oknaOdprta=false;
		}
		return temperatura;
	}
	
	/**
	 * metoda za vključevanje gretja
	 * Najvišja še vzdržna temperatura v bivalnem prostoru naj bi bila do 27°C v vročih mesecih, zato bo to tudi najvišja nastavitev
	 * @return Ali je ogrevanje vključeno ali ne
	 */
	public boolean prizgiGretje() throws Exception{
		if(ogrevanjeVkljuceno){
			return false;
		}

		else{
			if(temperatura<27){
				ogrevanjeVkljuceno = true;
				return true;
			}
			
			else{
				throw new Exception("Temperatura v sobi je previsoka.");				
			}			
		}
	}
	
	/**
	 * metoda za ogrevanje sobe
	 * v povprečju, naj bi se sobe, ogrevane s centralnim ogrevanjem ogrele za 1°C na minuto
	 * po koncu ogrevanja ugasnemo gretje
	 * @param m Čas ogrevanja v minutah
	 *@return Trenutna temperatura v sobi po ogrevanju
	 */
	public int ogrevanje(int m){
		if(ogrevanjeVkljuceno){
			if(m<5){
				System.out.println("Ogrevanje končano po " + m + " minutah. Temperatura ostaja " + temperatura + " °C.");
				temperatura+=0;
			}
		
			else{
				int stopinje = m/5;
				temperatura += stopinje;
				System.out.println("Ogreval bom " + m + " minut.");
				
				if(temperatura > 27){
					temperatura = 27;
					System.out.println("Temperatura je dosegla maksimum " + temperatura + " °C. Ogrevanje zaključujem predčasno");
				}
				
				else{
					System.out.println("Ogreval sem " + m + " minut. Ugašam gretje ...");
				}
			}
		ogrevanjeVkljuceno = false;
		}	
		return temperatura;
	}
	
	/**
	 * metoda za čiščenje sobe
	 * če želimo v sobi počistiti prah in posesati
	 * v povprečju naj bi sesali s približno hitrostjo 0.32 minute na m2.
	 * če je soba bolj umazana, bomo čas čiščenja malo podaljšali
	 * po koncu ogrevanja ugasnemo gretje
	 * @return Čas čiščenja sobe zaokrožen na 2 decimalki
	 */
	public double sesanjeSobe(){
		
		System.out.println("Pričenjam s čiščenjem ... ");
		double casCiscenja = 0;
		if(velikostSobe<5){
			casCiscenja = 1;
			cistocaSobe = 100;
		}
		
		else{
			if(cistocaSobe>59){
				casCiscenja = (velikostSobe*0.32);
				cistocaSobe = 100;
			}
			
			else if(cistocaSobe<=0){
				casCiscenja = (velikostSobe*0.32)*2;
				cistocaSobe = 100;
			}
			
			else{
				casCiscenja = (velikostSobe*0.32) + ((velikostSobe*0.32)/(100/cistocaSobe));
				cistocaSobe = 100;
			}
		}
		
		casCiscenja = (Math.round(casCiscenja*100.0)/100.0);
		System.out.println("Čistil sem " + casCiscenja + " minut. Soba je sedaj " + cistocaSobe + "% čista.");		
		return casCiscenja;

	}
	
	/**
	 *************************************
	 * Setter metode za variabile objekta
	 *************************************
	 */
	 
	/**
	 * metoda za nastavljanje števila vrat v sobi
	 * @param stVrat Število vrat
	 */
	public void setStVrat(int stVrat){
		this.stVrat = stVrat;
	}

	/**
	 * metoda za nastavljanje števila oken v sobi
	 * @param stOken Število oken
	 */
	public void setStStOken(int stOken){
		this.stOken = stOken;
	}

	/**
	 * metoda za nastavljanje velikosti sobe
	 * @param velikostSobe Velikost sobe v m2
	 */
	public void setVelikostSobe(double velikostSobe){
		this.velikostSobe = velikostSobe;
	}

	/**
	 * metoda za nastavljanje temperature v sobi v °C
	 * @param temperatura Temperatura v sobi v °C
	 */
	public void setTemperatura(int temperatura){
		this.temperatura = temperatura;
	}
	
	/**
	 * metoda za nastavljanje odprtosti oken
	 * @param oknaOdprta Ali so okna odprta
	 * true - so odprta
	 * false - so zaprta
	 */
	public void setOknaOdprta(boolean oknaOdprta){
		this.oknaOdprta = oknaOdprta;
	}	
	
	/**
	 * metoda za nastavljanje vključenosti gretja
	 * @param ogrevanjeVkljuceno Ali je ogrevanje vključeno ali ne
	 * true - je vključeno
	 * false - je izključeno
	 */
	public void setOgrevanjeVkljuceno(boolean ogrevanjeVkljuceno){
		this.ogrevanjeVkljuceno = ogrevanjeVkljuceno;
	}	
	
	/**
	 * metoda za nastavljanje čistoče sobe
	 * @param cistocaSobe Čistoča sobe v odstotkih
	 */
	public void setCistocaSobe(int cistocaSobe){
		this.cistocaSobe = cistocaSobe;
	}
	
	/**
	 * metoda za nastavljanje tipa sobe
	 * @param tipSobe Tip sobe
	 */	
	public void setTipSobe(String tipSobe){
		this.tipSobe = tipSobe;
	}
	
	/**
	 *************************************
	 * Getter metode za variabile objekta
	 ************************************
	 */

	/**
	 * metoda za pridobivanje informacije o številu vrat v sobi
	 * @return število vrat v sobi
	 */
	public int getStVrat(){
		return this.stVrat;
	}	
	
	/**
	 * metoda za pridobivanje informacije o številu oken v sobi
	 * @return število oken v sobi
	 */
	public int getStOken(){
		return this.stOken;
	}

	/**
	 * metoda za pridobivanje informacije o velikosti sobe v m2
	 * @return velikost sobe v m2
	 */
	public double getVelikostSobe(){
		return this.velikostSobe;
	}

	/**
	 * metoda za pridobivanje informacije o trenutni temperaturi v sobi v °C
	 * @return temperatura v sobi v °C
	 */
	public int getTemperatura(){
		return this.temperatura;
	}	

	/**
	 * metoda za pridobivanje informacije o odprtosti oken
	 * true - okna so odprta
	 * false - okna so zaprta
	 * @return ali so okna odprta ali zaprta (true, false)
	 */
	public boolean getOknaOdprta(){
		return this.oknaOdprta;
	}

	/**
	 * metoda za pridobivanje informacije o vključenosti ogrevanja v sobi
	 * true - ogrevanje je vključeno
	 * false - ogrevanje je izključeno
	 * @return ali je ogrevanje vključeno ali ne (true, false)
	 */
	public boolean getOgrevanjeVkljuceno(){
		return this.ogrevanjeVkljuceno;
	}

	/**
	 * metoda za pridobivanje informacije o čistoči sobe v %
	 * @return procent čistoče sobe v %
	 */
	public int getCistocaSobe(){
		return this.cistocaSobe;
	}

		public String getTipSobe(){
			return this.tipSobe;
		}
}