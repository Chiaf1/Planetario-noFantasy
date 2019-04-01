package nofantasy.planetario;

import java.util.*;

public class Stella extends CorpoCeleste{
	
	private static final int N_MAX_PIANETI = 26000;
	private String id;
	private static String newId = "SA0000";
	private ArrayList<Pianeta> pianeti = new ArrayList<Pianeta>();
	
	public Stella() {
		super("",0.0,0.0);
	}
	
	public Stella(String nome, double massa, double raggio) {
		
		super(nome, massa, raggio);
		
		//calcolo Id
		id = newId;
		newId = super.calcolaId(newId);
	}
	
	public String getId() {
		return id;
	}
	
	public boolean aggiungiPianeta(Pianeta newPianeta) {
		if (pianeti.size()<=N_MAX_PIANETI) {
			pianeti.add(newPianeta);
			return true;
		}else {
			return false;
		}
	}
	
	public ArrayList<Pianeta> getPianeta(){
		return pianeti;		
	}
	
	public Pianeta getPianeta(int i) {
		return pianeti.get(i);
	}
	
	public Pianeta getPianeta(String nome) {
		for(int i=0; i<pianeti.size(); i++) {
			if(nome == pianeti.get(i).getNome()) {
				return pianeti.get(i);
			}
		}
		Pianeta vuoto = new Pianeta();
		return vuoto;
	}
	
	public int getNumeroPianeti() {
		return pianeti.size();
	}
	
	public void distruggiPianeta(int i) {
		pianeti.remove(i);
	}
	
	public void distruggiPianeta(String nome) {
		for(int i=0; i<pianeti.size(); i++) {
			if(nome == pianeti.get(i).getNome()) {
				pianeti.remove(i);
			}
		}
	}
	
}
