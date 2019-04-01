package nofantasy.planetario;

import java.util.*;

public class Sistema {
	
	
	private Stella stella = new Stella();
	private Coordinate centroDiMassa = new Coordinate(0, 0);
	private ArrayList<String> listaCollisioni = new ArrayList<String>();
	
	public Sistema(Stella stella) {
		this.stella = stella;
	}
	
	public void aggiorna(long tempo) {
		centroDiMassa(tempo);
		
		collisioni();
	}
	
	public Coordinate getCentroDiMassa() {
		return centroDiMassa;
	}
	
	public ArrayList<String> getListaCollisioni() {
		return listaCollisioni;
	}
	
	private void centroDiMassa(long tempo) {
		double massaM = stella.getMassa();
		
		double xM = stella.getMassa() * stella.getCoordinate().getX();
		double yM = stella.getMassa() * stella.getCoordinate().getY();
		
		for (int i = 0; i<stella.getNumeroPianeti(); i++) {
			
			massaM += stella.getPianeta(i).getMassa();
			xM += stella.getPianeta(i).getMassa() * stella.getPianeta(i).getCoordinate().getX();
			yM += stella.getPianeta(i).getMassa() * stella.getPianeta(i).getCoordinate().getY();
			
			for(int c = 0; c<stella.getPianeta(i).getNumeroLune(); c++) {
				massaM += stella.getPianeta(i).getLuna(c).getMassa();
				xM += stella.getPianeta(i).getLuna(c).getMassa() * stella.getPianeta(i).getLuna(c).getCoordinate().getX();
				yM += stella.getPianeta(i).getLuna(c).getMassa() * stella.getPianeta(i).getLuna(c).getCoordinate().getY();
			}
		}
		
		centroDiMassa.setX(xM/massaM);
		centroDiMassa.setY(yM/massaM);
	}
	
	private void collisioni() {
		int i,c,d;
		for ( i = 0; i<stella.getNumeroPianeti(); i++) {
			
			for( c = 0; c<stella.getNumeroPianeti(); c++) {
				
				for ( d = 0; d<stella.getPianeta(c).getNumeroLune(); d++) {
					
					if(i!=c) {
						
						if (stella.getPianeta(i).getRaggioOrbita() <= (stella.getPianeta(c).getLuna(d).getRaggioOrbita() + stella.getPianeta(c).getRaggioOrbita()) && stella.getPianeta(i).getRaggioOrbita() >= (stella.getPianeta(c).getLuna(d).getRaggioOrbita() - stella.getPianeta(c).getRaggioOrbita())) {
							
							listaCollisioni.add(stella.getPianeta(i).getNome() + ", " + stella.getPianeta(c).getLuna(d).getNome());
							
						}
					}
					
				}
				
			}
			
		}
		
		for ( i = 0; i<stella.getNumeroPianeti(); i++) {
			
			for( c = 0; c<stella.getPianeta(i).getNumeroLune(); c++) {
				
				for ( d = 0; d<stella.getNumeroPianeti(); d++) {
					if(i!=d) {
					for (int e=0; e<stella.getPianeta(c).getNumeroLune(); e++) {
						
							if ((stella.getPianeta(i).getLuna(c).getRaggioOrbita() + stella.getPianeta(i).getRaggioOrbita()) <= (stella.getPianeta(c).getLuna(d).getRaggioOrbita() + stella.getPianeta(c).getRaggioOrbita()) && (stella.getPianeta(i).getRaggioOrbita() + stella.getPianeta(i).getRaggioOrbita()) >= (stella.getPianeta(c).getLuna(d).getRaggioOrbita() - stella.getPianeta(c).getRaggioOrbita())) {
								
								if (((stella.getPianeta(i).getRaggioOrbita() - stella.getPianeta(i).getLuna(c).getRaggioOrbita()) <= (stella.getPianeta(c).getRaggioOrbita() - stella.getPianeta(i).getLuna(c).getRaggioOrbita()) && (stella.getPianeta(i).getRaggioOrbita() + stella.getPianeta(i).getRaggioOrbita()) >= (stella.getPianeta(c).getLuna(d).getRaggioOrbita() - stella.getPianeta(c).getRaggioOrbita()))) {
									
									listaCollisioni.add(stella.getPianeta(i).getLuna(c).getNome() + ", " + stella.getPianeta(d).getLuna(e).getNome());
									
								}
								
								
							}
					
						}
					}
					
				}
				
			}
			
		}
	}
	
		
	public StringBuffer calcoloRotta(String nomeCorpo1, String nomeCorpo2) {
		//inizializzo il buffer che invio come risposta e la distanza che invierò come risposta
		StringBuffer risultato = new StringBuffer();
		double distanza = 0;
		
		//inizializzo i due id con un codice che non significa nulla in modo da poter capire dopo se ho trovato il corpo o no
		String idCorpo1 = "0A0000";
		String idCorpo2 = "0A0000";
		
		//inizializzo gli indici per i pianeti e per le lune
		//mi salvo solo gli indici della catena di corpi in modo da poter aver accesso ai dati che mi interessano senza dover creare istanze nuove
		//cioè dopo quando calcolo la rotta i dati li prendo seguendo i vari indici es:
		//stella.getPianeta(incidePianeta1) se  il corpo1 è un pianeta
		//stella,getPianeta(indicePianeta1).getLuna(indiceLuna1) se il corpo1 è una luna
		//facendo così so anche a che pianeta appartiene la luna e nel caso della rotta luna>luna posso risalire al volo al pianeta sul quale fare "scalo"
		int indicePianeta1 = 0;
		int indicePianeta2 = 0;
		int indiceLuna1 = 0;
		int indiceLuna2 = 0;
		
		int i,c;
		
		//ricerca del corpo 1, mi salvo solo l'id per poter risalire più velocemente al tipo di corpo celeste dopo qunado faccio il calcolo della rotta
		if(nomeCorpo1 == stella.getNome()) {
			idCorpo1 = stella.getId();
		}else {
			for( i = 0; i == stella.getNumeroPianeti(); i++) {
				//mi salvo l'indice del pianeta
				indicePianeta1 = i;
				if(nomeCorpo1 == stella.getPianeta(i).getNome()) {
					idCorpo1 = stella.getPianeta(i).getId();
					break;
				}else {
					for( c = 0; c == stella.getPianeta(i).getNumeroLune(); c++) {
						//come prima, mi salvo l'indice della luna
						indiceLuna1 = c;
						if(nomeCorpo1 == stella.getPianeta(i).getLuna(c).getNome()) {
							idCorpo1 = stella.getPianeta(i).getLuna(c).getId();
							break;
						}						
					}
				}			
			}			
		}

		//ricerca del corpo 2, mi salvo solo l'id per poter risalire più velocemente al tipo di corpo celeste dopo qunado faccio il calcolo della rotta
		if(nomeCorpo2 == stella.getNome()) {
			idCorpo2 = stella.getId();
		}else {
			for( i = 0; i == stella.getNumeroPianeti(); i++) {
				//mi salvo l'indice del pianeta
				indicePianeta2 = i;
				if(nomeCorpo2 == stella.getPianeta(i).getNome()) {
					idCorpo2 = stella.getPianeta(i).getId();
					break;
				}else {
					for( c = 0; c == stella.getPianeta(i).getNumeroLune(); c++) {
						//come prima, mi salvo l'indice della luna
						indiceLuna2 = c;
						if(nomeCorpo2 == stella.getPianeta(i).getLuna(c).getNome()) {
							idCorpo2 = stella.getPianeta(i).getLuna(c).getId();
							break;
						}						
					}
				}			
			}			
		}
			
		//calcolo della rotta + la distanza percorsa
		if (idCorpo1 == "0A0000" || idCorpo2 == "0A0000") {
			//se non ho trovato entrambe i corpi ritorno il messaggio seguente
			risultato.append("mi spiace ma non è stato possibile calcolare la rotta");
			return risultato;
		}else {
			
			if (idCorpo1 != idCorpo2) {
				//nel caso in cui i due corpi esistono e sono diversi stampo la rotta e la distanza a seconda della loro combinazione
				risultato.append("La rotta da seguire per andare da " + nomeCorpo1 + " a " + nomeCorpo2 + " è:");
				switch (idCorpo1.substring(0, 1)) {
				case "S":
					switch(idCorpo2.substring(0, 1)) {
					case "P"://caso stella>pianeta
						risultato.append(stella.getNome() + " > " + stella.getPianeta(indicePianeta2).getNome());
						distanza = stella.getCoordinate().distanza(stella.getPianeta(indicePianeta2).getCoordinate());
						risultato.append("la distanza da percorrere è: " + distanza);
						break;
					case "L"://caso stella>luna
						risultato.append(stella.getNome() + " > " + stella.getPianeta(indicePianeta2).getLuna(indiceLuna2).getNome());
						distanza = stella.getCoordinate().distanza(stella.getPianeta(indicePianeta2).getLuna(indiceLuna2).getCoordinate());
						risultato.append("la distanza da percorrere è: " + distanza);
						break;				
					}				
					break;
				case "P":
					switch(idCorpo2.substring(0, 1)) {
					case "S"://caso pianeta>stella
						risultato.append(stella.getPianeta(indicePianeta1).getNome() + " > " + stella.getNome());
						distanza = stella.getPianeta(indicePianeta1).getCoordinate().distanza(stella.getCoordinate());
						risultato.append("la distanza da percorrere è: " + distanza);
						break;
					case "P"://caso pianeta>stella>pianeta
						risultato.append(stella.getPianeta(indicePianeta1).getNome() + " > " + stella.getNome() + " > " + stella.getPianeta(indicePianeta2).getNome());
						distanza = stella.getPianeta(indicePianeta1).getCoordinate().distanza(stella.getCoordinate()) + stella.getCoordinate().distanza(stella.getPianeta(indicePianeta2).getCoordinate());
						risultato.append("la distanza da percorrere è: " + distanza);
						break;
					case "L"://caso pianeta>luna
						risultato.append(stella.getPianeta(indicePianeta1).getNome() + " > " + stella.getPianeta(indicePianeta2).getLuna(indiceLuna2).getNome());
						distanza = stella.getPianeta(indicePianeta1).getCoordinate().distanza(stella.getPianeta(indicePianeta2).getLuna(indiceLuna2).getCoordinate());
						risultato.append("la distanza da percorrere è: " + distanza);
						break;				
					}				
					break;
				case "L":
					switch(idCorpo2.substring(0, 1)) {
					case "S"://caso luna>stella
						risultato.append(stella.getPianeta(indicePianeta1).getLuna(indiceLuna1).getNome() + " > " + stella.getNome());
						distanza = stella.getPianeta(indicePianeta1).getLuna(indiceLuna1).getCoordinate().distanza(stella.getCoordinate());
						risultato.append("la distanza da percorrere è: " + distanza);
						break;
					case "P"://caso luna>pianeta
						risultato.append(stella.getPianeta(indicePianeta1).getLuna(indiceLuna1).getNome() + " > " + stella.getPianeta(indicePianeta2).getNome());
						distanza = stella.getPianeta(indicePianeta1).getLuna(indiceLuna1).getCoordinate().distanza(stella.getPianeta(indicePianeta2).getCoordinate());
						risultato.append("la distanza da percorrere è: " + distanza);
						break;
					case "L"://caso luna>pianeta>luna
						risultato.append(stella.getPianeta(indicePianeta1).getLuna(indiceLuna1).getNome() + " > " + stella.getPianeta(indicePianeta1).getNome() + " > " + stella.getPianeta(indicePianeta2).getLuna(indiceLuna2).getNome());
						distanza = stella.getPianeta(indicePianeta1).getLuna(indiceLuna1).getCoordinate().distanza(stella.getPianeta(indicePianeta1).getCoordinate()) + stella.getPianeta(indiceLuna1).getCoordinate().distanza(stella.getPianeta(indicePianeta2).getLuna(indiceLuna2).getCoordinate());
						risultato.append("la distanza da percorrere è: " + distanza);
						break;				
					}				
					break;			
				}
			}else {
				//nel caso in cui i due corpi siano uguali ritrno il seguente messaggio
				risultato.append("non posso tracciare la rotta tra due corpi celesti uguali");
				return risultato;
			}
			
		}
		//return finale nel caso sia andato tutto nel modo giusto
		return risultato;
		
	}
	
}
