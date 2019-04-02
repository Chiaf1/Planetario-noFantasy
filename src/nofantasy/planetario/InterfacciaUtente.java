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
	
	public boolean azione() {
		switch(letturaChar("Menu\n"
				+ "_s: ricerca corpo\n"
				+ "_i: visualizza informazioni corpo\n"
				+ "_a: aggiungi corpo\n"
				+ "_d: distruggi corpo\n"
				+ "_c: calcolo della rotta\n"
				+ "_e: chiudi il programma\n"
				+ "_m: per ricevere i dati sul centro di massa\n"
				+ "_n: per avere i dati sulle collisioni\n")) {
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
		
		for (int i = 0; i<sistema.getListaCollisioni().size(); i++) {
			scrittura(sistema.getListaCollisioni().get(i));
		}
	}

	private void ricercaCorpo(String nomeCorpo) {	
		if(nomeCorpo.equals(sistema.getStella().getNome())) {
			scrittura(nomeCorpo + " esiste ed è la stella del sistema");
		}else {
			for(int i = 0; i == sistema.getStella().getNumeroPianeti(); i++) {
				if(nomeCorpo.equals(sistema.getStella().getPianeta(i).getNome())) {
					scrittura(nomeCorpo + " esiste ed è un pianeta che orbita attorno a " + sistema.getStella().getNome());
					break;
				}else {
					for(int c = 0; c == sistema.getStella().getPianeta(i).getNumeroLune(); c++) {
						if(nomeCorpo.equals(sistema.getStella().getPianeta(i).getLuna(c).getNome())) {
							scrittura(nomeCorpo + " esiste ed è una luna di " + sistema.getStella().getPianeta(i).getNome());
							break;
						}						
					}
				}			
			}			
		}
		scrittura("il corpo non è stato all'interno del sistema");
	}
	
	private void visualizzazioneInfo(String nomeCorpo) {
		if(nomeCorpo.equals(sistema.getStella().getNome())) {
			scrittura(nomeCorpo + " è una stella, ecco i suoi dati: ");
			stampaDati(sistema.getStella(), true);
		}else {
			for(int i = 0; i == sistema.getStella().getNumeroPianeti(); i++) {
				if(nomeCorpo.equals(sistema.getStella().getPianeta(i).getNome())) {
					scrittura(nomeCorpo + " è un pianeta, ecco i suoi dati: ");
					stampaDati(sistema.getStella().getPianeta(i), false);
					for (Luna elemento : sistema.getStella().getPianeta(i).getLuna()) {
						scrittura(elemento.getNome());
					}
					break;
				}else {
					for(int c = 0; c == sistema.getStella().getPianeta(i).getNumeroLune(); c++) {
						if(nomeCorpo.equals(sistema.getStella().getPianeta(i).getLuna(c).getNome())) {
							scrittura(nomeCorpo + " è una luna, ecco i suoi dati: ");
							stampaDati(sistema.getStella().getPianeta(i).getLuna(c), false);
							scrittura("il percorso per trovare la luna è: ");
							scrittura(sistema.getStella().getNome() + ">" + sistema.getStella().getPianeta(i) + ">" + sistema.getStella().getPianeta(i).getLuna(c).getNome());
							break;
						}						
					}
				}			
			}			
		}		
		scrittura("il corpo non è stato trovato all'interno del sistema");
	}
	
	private void aggiungiLuna() {
		String nomeLuna, nomePianeta;
		boolean isTrovato = false;
		double massa, raggioOrbita, periodo, angolo0;
		scrittura("Per aggiungere una luna devi specificare un pianeta di appartenenza.\n"
				+ "Dopo di che devi inserire le specifichedella luna.\n");
		nomeLuna = letturaString("Luna: ");
		nomePianeta = letturaString("Pianeta: ");
		massa = letturaDouble("Massa: ");
		raggioOrbita = letturaDouble("Raggio orbitale: ");
		periodo = letturaDouble("Periodo orbitale: ");
		angolo0 = letturaDouble("Angolo di partenza: ");

		for (int i = 0; i<sistema.getStella().getNumeroPianeti(); i++) {
			if (nomePianeta.equals(sistema.getStella().getPianeta(i).getNome())) {
				Luna newLuna = new Luna(nomeLuna, massa, periodo, angolo0, raggioOrbita, sistema.getStella().getPianeta(i));
				if (!(sistema.getStella().getPianeta(nomePianeta).aggiungiLuna(newLuna))) {
					scrittura("Hai inserito troppe lune o il pianeta ne possiede già un'altra con questo raggio orbitale");
				}
				isTrovato = true;
				break;
			}
		}
		if (!isTrovato) {
			scrittura("Non è stato possibile aggiungere la luna perché il pianeta selezionato non esiste");
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
			scrittura("Non è stato possibile aggiungere il pianeta, i motivi possono essere due: \n"
					+ "_è stato raggiunto il limite di spazio per i pianeti\n"
					+ "_è stato inserito un pianeta con il raggio dell'orbita uguale ad un pianeta già esistente");
		}
	}
	
	private void distruggiPianeta() {
		String pianetaDaDistruggere = letturaString("Per distruggere un pianeta digitare il nome: ");
		
		if(!(sistema.getStella().distruggiPianeta(pianetaDaDistruggere))) {
			scrittura("Il pianeta da distruggere è già stato distrutto o non è mai esistito");
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
				System.out.println("il valore inserito non è nel formato corretto");
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
				System.out.println("il valroe inserito non è accettabile");
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
