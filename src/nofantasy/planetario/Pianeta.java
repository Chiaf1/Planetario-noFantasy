package nofantasy.planetario;

import java.util.*;

public class Pianeta extends CorpoCeleste{
	
	private String id;
	private static String newId;
	ArrayList<Luna> lune = new ArrayList<Luna>();
	
	public Pianeta() {
		super("",0.0,0.0,0.0,0.0,0.0);
	}
	
	public Pianeta(String nome, double massa, double raggio, double periodo, double angolo0, double raggioOrbita) {
		
		super(nome, massa, raggio, periodo, angolo0, raggioOrbita);
		
		//creazione id
		id = newId;
		newId = super.calcolaId(newId);
	}
	
	public String getId() {
		return id;
	}
	
	public void aggiungiLuna(Luna newLuna) {
		lune.add(newLuna);
	}
	
	public ArrayList<Luna> getLuna(){
		return lune;		
	}
	
	public Luna getLuna(int i) {
		return lune.get(i);		
	}
	
	public Luna getLuna(String nome) {
		for(int i=0; i<lune.size(); i++) {
			if(nome == lune.get(i).getNome()) {
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
	
	public void distruggiLuna(String nome) {
		for(int i=0; i<lune.size(); i++) {
			if(nome == lune.get(i).getNome()) {
				lune.remove(i);
			}
		}
	}

}