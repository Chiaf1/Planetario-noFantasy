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
