package nofantasy.planetario;

import java.util.*;

public class InterfacciaUtente {
	
	private Scanner lettore = new Scanner(System.in);
	private Sistema sistema; 
	
	public void creaziioneSistema() {
		scrittura("Benvenuti Nel Sistema Di Gestione Del Vostro Sistema.\n"
				+ "Per iniziare vi chiediamo di inserire i dati della stella.");
		
		String nome = letturaString("Inserire il nome della stella: ");
		double massa = letturaDouble("Inserire la massa della stella: ");
		
		Stella stella = new Stella(nome, massa);
		sistema = new Sistema(stella);
	}
	
	//gestione delle azioni consentite del programma
	public boolean azione() {
		switch(letturaChar("\nMenu\n"
				+ "_s: ricerca corpo\n"
				+ "_i: visualizza informazioni corpo\n"
				+ "_a: aggiungi corpo\n"
				+ "_d: distruggi corpo\n"
				+ "_c: calcolo della rotta\n"
				+ "_m: per ricevere i dati sul centro di massa\n"
				+ "_n: per avere i dati sulle collisioni\n"
				+ "_e: chiudi il programma")) {
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
					+ "_l: per distruggere una luna\n")) {
			case 'p':
				distruggiPianeta();
				break;
			case 'l':
				distruggiLuna();
				break;
			}
			break;
		case 'c':
			calcoloRotta();
			break;
		case 'm':
			calcoloCentroDiMassa();
			break;
		case 'n':
			calcoloCollisioni();
			break;
		case 'e':
			return true;
		default: 
			scrittura("hai sbagliato ad inserire");
			break;
		}
		return false;
	}
	
	private void calcoloCentroDiMassa() {
		sistema.centroDiMassa();
		scrittura("Il centro di massa si trova:\n" + "(" + sistema.getCentroDiMassa().getX() + "," + sistema.getCentroDiMassa().getY() + ")");
	}
	
	private void calcoloCollisioni() {
		sistema.collisioni();
		if (sistema.getListaCollisioni().size()>=1) {
			for (int i = 0; i<sistema.getListaCollisioni().size(); i++) {
				scrittura(sistema.getListaCollisioni().get(i));
			}
		}else {
			scrittura("Non vi sono collisioni all'interno del sistema.");
		}
	}

	private void ricercaCorpo(String nomeCorpo) {	
		if(nomeCorpo.equals(sistema.getStella().getNome())) {
			scrittura(nomeCorpo + " esiste ed � la stella del sistema");
			return;
		}else {
			for(int i = 0; i < sistema.getStella().getNumeroPianeti(); i++) {
				if(nomeCorpo.equals(sistema.getStella().getPianeta(i).getNome())) {
					scrittura(nomeCorpo + " esiste ed � un pianeta che orbita attorno a " + sistema.getStella().getNome());
					return;
				}else {
					for(int c = 0; c < sistema.getStella().getPianeta(i).getNumeroLune(); c++) {
						if(nomeCorpo.equals(sistema.getStella().getPianeta(i).getLuna(c).getNome())) {
							scrittura(nomeCorpo + " esiste ed � una luna di " + sistema.getStella().getPianeta(i).getNome());
							return;
						}						
					}
				}			
			}			
		}
		scrittura("il corpo non � stato all'interno del sistema");
	}
	
	private void visualizzazioneInfo(String nomeCorpo) {
		if(nomeCorpo.equals(sistema.getStella().getNome())) {
			scrittura(nomeCorpo + " � una stella, ecco i suoi dati: ");
			stampaDati(sistema.getStella(), true);
			if (sistema.getStella().getNumeroPianeti() >= 1) {
				scrittura("I pianeti della stella sono:");
				for (Pianeta elemento : sistema.getStella().getPianeta()) {
					scrittura("*" + elemento.getNome());
				}
			}else {
				scrittura("La stella non ha pianeti");
			}
			return;
		}else {
			for(int i = 0; i < sistema.getStella().getNumeroPianeti(); i++) {
				if(nomeCorpo.equals(sistema.getStella().getPianeta(i).getNome())) {
					scrittura(nomeCorpo + " � un pianeta, ecco i suoi dati: ");
					stampaDati(sistema.getStella().getPianeta(i), false);
					if (sistema.getStella().getPianeta(i).getNumeroLune() >= 1) {
						scrittura("Le lune del pianeta sono:");
						for (Luna elemento : sistema.getStella().getPianeta(i).getLuna()) {
							scrittura("*" + elemento.getNome());
						}
					}else {
						scrittura("Il pianeta non ha lune");
					}
					return;
				}else {
					for(int c = 0; c < sistema.getStella().getPianeta(i).getNumeroLune(); c++) {
						if(nomeCorpo.equals(sistema.getStella().getPianeta(i).getLuna(c).getNome())) {
							scrittura(nomeCorpo + " � una luna, ecco i suoi dati: ");
							stampaDati(sistema.getStella().getPianeta(i).getLuna(c), false);
							scrittura("il percorso per trovare la luna �: ");
							scrittura(sistema.getStella().getNome() + ">" + sistema.getStella().getPianeta(i).getNome() + ">" + sistema.getStella().getPianeta(i).getLuna(c).getNome());
							return;
						}						
					}
				}			
			}			
		}		
		scrittura("il corpo non � stato trovato all'interno del sistema");
	}
	
	private void aggiungiLuna() {
		String nomeLuna, nomePianeta;
		boolean isTrovato = false;
		double massa, raggioOrbita, angolo0;
		scrittura("Per aggiungere una luna devi specificare un pianeta di appartenenza.\n"
				+ "Dopo di che devi inserire le specifichedella luna.\n");
		nomeLuna = letturaString("Luna: ");
		nomePianeta = letturaString("Pianeta: ");
		massa = letturaDouble("Massa: ");
		raggioOrbita = letturaDouble("Raggio orbitale: ");
		angolo0 = letturaDouble("Angolo di partenza: ");
		
		//controllo eventuali conflitti
		if (sistema.getStella().getNome().equals(nomeLuna)) {
			scrittura("Il nome inserito esiste gi�. Cambiarlo tu devi.");
			return;
		}else {
			for(int i = 0; i<sistema.getStella().getNumeroPianeti(); i++) {
				if (nomeLuna.equals(sistema.getStella().getPianeta(i).getNome())) {
					scrittura("Il nome inserito esiste gi�. Cambiarlo tu devi.");
					return;
				}
				for(int c = 0; c<sistema.getStella().getPianeta(i).getNumeroLune(); c++) {
					if (nomeLuna.equals(sistema.getStella().getPianeta(i).getLuna(c).getNome())) {
						scrittura("Il nome inserito esiste gi�. Cambiarlo tu devi.");
						return;
					}
					if (raggioOrbita == sistema.getStella().getPianeta(i).getLuna(c).getRaggioOrbita()) {
						scrittura("Il nome inserito esiste gi�. Cambiarlo tu devi.");
						return;
					}
				}
			}
		}
		
		//ricerca del pianeta + aggiunta della luna e controllo sulla riuscita dell'operazione
		for (int i = 0; i<sistema.getStella().getNumeroPianeti(); i++) {
			if (nomePianeta.equals(sistema.getStella().getPianeta(i).getNome())) {
				Luna newLuna = new Luna(nomeLuna, massa, angolo0, raggioOrbita, sistema.getStella().getPianeta(i));
				if (!(sistema.getStella().getPianeta(nomePianeta).aggiungiLuna(newLuna))) {
					scrittura("Hai inserito troppe lune o il pianeta ne possiede gi� un'altra con questo raggio orbitale/nome");
				}
				isTrovato = true;
				break;
			}
		}
		if (!isTrovato) {
			scrittura("Non � stato possibile aggiungere la luna perch� il pianeta selezionato non esiste");
		}		
	}
	
	private void distruggiLuna() {
		String nomeLuna;
		boolean b = false;
		scrittura("Per distruggere una luna bisogna sapere il nome.");
		nomeLuna = letturaString("\nLuna: ");
		
		//controllo esistenza luna + distruzione luna e controllo riuscita operazione
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
		double angolo0 = letturaDouble("Inserire l'angolo di partenza: ");
		double raggioOrbita = letturaDouble("Inserire il raggio dell'orbita: ");
		
		//aggiunta pianeta + controllo operazione riuscita
		newPianeta = new Pianeta(nome, massa, angolo0, raggioOrbita);
		if (!(sistema.getStella().aggiungiPianeta(newPianeta))){
			scrittura("Non � stato possibile aggiungere il pianeta, i motivi possono essere due: \n"
					+ "_� stato raggiunto il limite di spazio per i pianeti\n"
					+ "_� stato inserito un pianeta con il raggio dell'orbita o il nome uguale ad un pianeta gi� esistente");
		}
	}
	
	private void distruggiPianeta() {
		String pianetaDaDistruggere = letturaString("Per distruggere un pianeta digitare il nome: ");
		
		//distruzione pianeta + controllo operazione riuscita
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
			System.out.println(msg);
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
	
	//stampo tutti i dati del corpo e distinguo il caso stella da pianeta/luna
	private void stampaDati(CorpoCeleste corpo, boolean isStella) {
		scrittura("_nome: " + corpo.getNome());
		scrittura("_massa: " + corpo.getMassa());
		if(!isStella) {
			scrittura("_raggio dell' orbita: " + corpo.getRaggioOrbita());
			scrittura("_angolo di partenza: " + corpo.getAngolo0());
		}
		scrittura("_coordinate: (" + corpo.getCoordinate().getX() + "," + corpo.getCoordinate().getY() + ")");		
	}

}
