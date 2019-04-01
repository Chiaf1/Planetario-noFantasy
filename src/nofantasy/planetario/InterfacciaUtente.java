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
		String nomeLuna, nomePianeta;
		double massa, raggioOrbita, periodo, angolo0, raggio;
		scrittura("Per aggiungere una luna devi specificare un pianeta di appartenenza.\n"
				+ "Dopo di che devi inserire le specifichedella luna.\n");
		nomeLuna = letturaString("Luna: ");
		nomePianeta = letturaString("\nPianeta: ");
		massa = letturaDouble("\nMassa: ");
		raggioOrbita = letturaDouble("\nRaggio orbitale: ");
		periodo = letturaDouble("\nPeriodo orbitale: ");
		angolo0 = letturaDouble("\nPeriodo orbitale: ");
		raggio = letturaDouble("\nRaggio pianeta: ");
		Luna newLuna = new Luna(nomeLuna, massa, raggio, periodo, angolo0, raggioOrbita);
		if (!(sistema.getStella().getPianeta(nomePianeta).aggiungiLuna(newLuna))) {
			scrittura("Hai inserito troppe lune o il pianeta ne possiede già un'altra con questo raggio orbitale");
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
		
	}
	
	private void distruggiPianeta() {
		
	}
	
	private void calcoloRotta() {
		
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
