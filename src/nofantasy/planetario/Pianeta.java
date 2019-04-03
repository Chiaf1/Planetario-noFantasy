package nofantasy.planetario;

import java.util.*;

public class Pianeta extends CorpoCeleste{
	
	private static final int N_MAX_LUNE = 5000;
	private String id;
	private static String newId = "PA0000";
	private ArrayList<Luna> lune = new ArrayList<Luna>();
	
	public Pianeta() {
		super("",0.0,0.0,0.0);
	}
	
	public Pianeta(String nome, double massa, double angolo0, double raggioOrbita) {
		
		super(nome, massa, angolo0, raggioOrbita);
		
		//creazione id
		id = newId;
		newId = super.calcolaId(newId);
		
		//calcolo coordinate
		Coordinate coordinate = new Coordinate(raggioOrbita*Math.cos(angolo0), raggioOrbita*Math.sin(angolo0));
		super.setCoordinate(coordinate);
	}
	
	public String getId() {
		return id;
	}
	
	public boolean aggiungiLuna(Luna newLuna) {
		for(int i = 0; i<lune.size(); i++) {
			if (newLuna.getRaggioOrbita() == lune.get(i).getRaggioOrbita()) {
				return false;
			}				
		}
		
		if (lune.size() <= N_MAX_LUNE) {
			lune.add(newLuna);
			return true;
		}else {
			return false;
		}
		
	}
	
	public ArrayList<Luna> getLuna(){
		return lune;		
	}
	
	public Luna getLuna(int i) {
		return lune.get(i);		
	}
	
	public Luna getLuna(String nome) {
		for(int i=0; i<lune.size(); i++) {
			if(nome.equals(lune.get(i).getNome())) {
				return lune.get(i);
			}
		}
		Luna vuoto = new Luna();
		return vuoto;
	}
	
	public int getNumeroLune() {
		return lune.size();		
	}
	
	public void distruggiLuna(int i) {
		lune.remove(i);
	}
	
	public boolean distruggiLuna(String nome) {
		for(int i=0; i<lune.size(); i++) {
			if(nome.equals(lune.get(i).getNome())) {
				lune.remove(i);
				return true;
			}
		}
		return false;
	}

}
