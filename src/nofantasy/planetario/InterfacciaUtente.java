package nofantasy.planetario;

import java.util.*;

public class InterfacciaUtente {
	
	private Scanner lettore = new Scanner(System.in);
	private Sistema sistema; 
	
	public void creaziioneSistema() {
		
	}
	
	public void azione() {
		switch(letturaChar("Menu\n"
				+ "_s: ricerca corpo\n"
				+ "_i: visualizza informazioni corpo\n"
				+ "_a: aggiungi corpo\n"
				+ "_d: distruggi corpo\n")) {
		case 's':
			ricercaCorpo(letturaString("Inserire il nome del corpo da cercare: "));
			break;
		case 'i':
			visualizzazioneInfo(letturaString("Inserire il nome del corpo di cui si vogliono sapere le informazioni: "));
			break;
		case 'a':
			switch(letturaChar("Specfica la tipologia del corpo da aggiungere\n"
					+ "_p: per aggiungere un pianeta\n"
					+ "_l: per aggiungere una luna")) {
			case 'p':
				aggiungiPianeta();
				break;
			case 'l':
				aggiungiLuna();
				break;
			}
			break;
		case 'd':
			switch(letturaChar("Specfica la tipologia del corpo da distruggere\n"
					+ "_p: per distruggere un pianeta\n"
					+ "_l: per distruggere una luna")) {
			case 'p':
				distruggiPianeta();
				break;
			case 'l':
				distruggiLuna();
				break;
			}
			break;		
		}
	}

	private void ricercaCorpo(String nomeCorpo) {	
		if(nomeCorpo == sistema.getStella().getNome()) {
			scrittura(nomeCorpo + " esiste ed � la stella del sistema");
		}else {
			for(int i = 0; i == sistema.getStella().getNumeroPianeti(); i++) {
				if(nomeCorpo == sistema.getStella().getPianeta(i).getNome()) {
					scrittura(nomeCorpo + " esiste ed � un pianeta che orbita attorno a " + sistema.getStella().getNome());
					break;
				}else {
					for(int c = 0; c == sistema.getStella().getPianeta(i).getNumeroLune(); c++) {
						if(nomeCorpo == sistema.getStella().getPianeta(i).getLuna(c).getNome()) {
							scrittura(nomeCorpo + " esiste ed � una luna di " + sistema.getStella().getPianeta(i).getNome());
							break;
						}						
					}
				}			
			}			
		}
		scrittura("il corpo non � stato all'interno del sistema");
	}
	
	private void visualizzazioneInfo(String nomeCorpo) {
		if(nomeCorpo == sistema.getStella().getNome()) {
			scrittura(nomeCorpo + " � una stella, ecco i suoi dati: ");
			stampaDati(sistema.getStella(), true);
		}else {
			for(int i = 0; i == sistema.getStella().getNumeroPianeti(); i++) {
				if(nomeCorpo == sistema.getStella().getPianeta(i).getNome()) {
					scrittura(nomeCorpo + " � un pianeta, ecco i suoi dati: ");
					stampaDati(sistema.getStella().getPianeta(i), false);
					for (Luna elemento : sistema.getStella().getPianeta(i).getLuna()) {
						scrittura(elemento.getNome());
					}
					break;
				}else {
					for(int c = 0; c == sistema.getStella().getPianeta(i).getNumeroLune(); c++) {
						if(nomeCorpo == sistema.getStella().getPianeta(i).getLuna(c).getNome()) {
							scrittura(nomeCorpo + " � una luna, ecco i suoi dati: ");
							stampaDati(sistema.getStella().getPianeta(i).getLuna(c), false);
							scrittura("il percorso per trovare la luna �: ");
							scrittura(sistema.getStella().getNome() + ">" + sistema.getStella().getPianeta(i) + ">" + sistema.getStella().getPianeta(i).getLuna(c).getNome());
							break;
						}						
					}
				}			
			}			
		}		
		scrittura("il corpo non � stato trovato all'interno del sistema");
	}
	
	private void aggiungiLuna() {
		String nomeLuna, nomePianeta;
		double massa, raggioOrbita, periodo, angolo0;
		scrittura("Per aggiungere una luna devi specificare un pianeta di appartenenza.\n"
				+ "Dopo di che devi inserire le specifichedella luna.\n");
		nomeLuna = letturaString("Luna: ");
		nomePianeta = letturaString("Pianeta: ");
		massa = letturaDouble("Massa: ");
		raggioOrbita = letturaDouble("Raggio orbitale: ");
		periodo = letturaDouble("Periodo orbitale: ");
		angolo0 = letturaDouble("Periodo orbitale: ");
		Luna newLuna = new Luna(nomeLuna, massa, periodo, angolo0, raggioOrbita);
		if (!(sistema.getStella().getPianeta(nomePianeta).aggiungiLuna(newLuna))) {
			scrittura("Hai inserito troppe lune o il pianeta ne possiede gi� un'altra con questo raggio orbitale");
		}
	}
	
	private void distruggiLuna() {
		String nomeLuna;
		boolean b = false;
		scrittura("Per distruggere una luna bisogna sapere il nome.");
		nomeLuna = letturaString("\nLuna: ");
		for(int i = 0; i < sistema.getStella().getNumeroPianeti(); i++) {
			if(sistema.getStella().getPianeta(i).distruggiLuna(nomeLuna)) {
				b = true;
				break;
			}
		}
		if (!b) {
			scrittura("La luna da te cercata non esiste.");
		}
	}
	
	private void aggiungiPianeta() {
		Pianeta newPianeta;
		scrittura("Per aggiungere un pianeta inserire i dati richiesti: ");
		
		String nome = letturaString("Inserire il nome: ");
		double massa = letturaDouble("Inserire la massa: ");
		double periodo = letturaDouble("Inserire il periodo:");
		double angolo0 = letturaDouble("Inserire l'angolo di partenza: ");
		double raggioOrbita = letturaDouble("Inserire il raggio dell'orbita: ");
		
		newPianeta = new Pianeta(nome, massa, periodo, angolo0, raggioOrbita);
		if (!(sistema.getStella().aggiungiPianeta(newPianeta))){
			scrittura("Non � stato possibile aggiungere il pianeta, i motivi possono essere due: \n"
					+ "_� stato raggiunto il limite di spazio per i pianeti\n"
					+ "_� stato inserito un pianeta con il raggio dell'orbita uguale ad un pianeta gi� esistente");
		}
	}
	
	private void distruggiPianeta() {
		String pianetaDaDistruggere = letturaString("Per distruggere un pianeta digitare il nome: ");
		
		if(!(sistema.getStella().distruggiPianeta(pianetaDaDistruggere))) {
			scrittura("Il pianeta da distruggere � gi� stato distrutto o non � mai esistito");
		}
	}
	
	private void calcoloRotta() {
		scrittura("Inserire il nome del corpo celeste di partenza e quello del corpo di arrivo: ");
		
		String corpoDiPartenza = letturaString("Partenza: ");
		String corpoDiArrivo = letturaString("Arrivo: ");
		
		System.out.println(sistema.calcoloRotta(corpoDiPartenza, corpoDiArrivo));
	}

	private double letturaDouble(String msg) {
		boolean isEnded = false;
		double datoLetto = 0;
		do {
			System.out.print(msg);
			try {
				datoLetto = lettore.nextDouble();
				isEnded = true;
			} catch (InputMismatchException e) {
				System.out.println("il valore inserito non � nel formato corretto");
				lettore.next();
			}
		} while (!isEnded);
		return datoLetto;
	}
	
	private String letturaString(String msg) {
		System.out.print(msg);
		return lettore.next();
	}
	
	private char letturaChar(String msg) {
		boolean isEnded = false;
		char datoLetto = '\0';
		do {
			System.out.print(msg);
			String lettura = lettore.next();
			if (lettura.length() > 0) {
				datoLetto = lettura.charAt(0);
				isEnded = true;
			} else {
				System.out.println("il valroe inserito non � accettabile");
			}
		} while (!isEnded);
		return datoLetto;
	}
	
	private void scrittura(String ms) {
		System.out.println(ms);
	}
	
	private void stampaDati(CorpoCeleste corpo, boolean isStella) {
		scrittura("_nome: " + corpo.getNome());
		scrittura("_massa: " + corpo.getMassa());
		if(!isStella) {
			scrittura("_raggio dell' orbita: " + corpo.getRaggioOrbita());
			scrittura("_periodo: " + corpo.getPeriodo());
			scrittura("_angolo di partenza: " + corpo.getAngolo0());
		}
		scrittura("_coordinate: (" + corpo.getCoordinate().getX() + "," + corpo.getCoordinate().getY() + ")");		
	}

}
