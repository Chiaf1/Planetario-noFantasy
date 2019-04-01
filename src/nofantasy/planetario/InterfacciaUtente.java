package nofantasy.planetario;

import java.util.*;

public class InterfacciaUtente {
	
	private Scanner lettore = new Scanner(System.in);
	private Sistema sistema; 
	
	public void creaziioneSistema() {
		
	}
	
	public void aggiornaDati() {
		
	}
	
	public void azione() {
		
	}
	
	private void aggiungiLuna() {
		scrittura("Per aggiungere una luna devi specificare un pianeta di appartenenza\n"
				+ "e la stella corrispondente. Dopo di che devi inserire le specifiche\n"
				+ "della luna.\n");
		
	}
	
	private void distruggiLuna() {
		
	}
	
	private void aggiungiPianeta() {
		Pianeta newPianeta;
		scrittura("Per aggiungere un pianeta inserire i dati richiesti: ");
		
		String nome = letturaString("Inserire il nome: ");
		double massa = letturaDouble("Inserire la massa: ");
		double raggio = letturaDouble("Inserire il raggio del pianeta: ");
		double periodo = letturaDouble("Inserire il periodo:");
		double angolo0 = letturaDouble("Inserire l'angolo di partenza: ");
		double raggioOrbita = letturaDouble("Inserire il raggio dell'orbita: ");
		
		newPianeta = new Pianeta(nome, massa, raggio, periodo, angolo0, raggioOrbita);
		if (!(sistema.getStella().aggiungiPianeta(newPianeta))){
			scrittura("Non è stato possibile aggiungere il pianeta, i motivi possono essere due:"
					+ "_è stato raggiunto il limite di spazio per i pianeti"
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
	
	private void visualizzazioneInfo() {
		
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
				String daButtare = lettore.next();
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
				System.out.println("il valroe inserito non è accettabile");
			}
		} while (!isEnded);
		return datoLetto;
	}
	
	private void scrittura(String ms) {
		System.out.println(ms);
	}

}
