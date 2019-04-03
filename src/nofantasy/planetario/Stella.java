package nofantasy.planetario;

import java.util.*;

public class Stella extends CorpoCeleste{
	
	private static final int N_MAX_PIANETI = 26000;
	private String id;
	private static String newId = "SA0000";
	private ArrayList<Pianeta> pianeti = new ArrayList<Pianeta>();
	
	public Stella() {
		super("",0.0);
	}
	
	public Stella(String nome, double massa) {
		
		super(nome, massa);
		
		//calcolo Id
		id = newId;
		newId = super.calcolaId(newId);
	}
	
	public String getId() {
		return id;
	}
	
	public boolean aggiungiPianeta(Pianeta newPianeta) {
		if (super.getNome().equals(newPianeta.getNome())) {
			return false;
		}else {
			for(int i = 0; i<pianeti.size(); i++) {
				if (newPianeta.getRaggioOrbita() == pianeti.get(i).getRaggioOrbita()) {
					return false;
				}
				if (newPianeta.getNome().equals(pianeti.get(i).getNome())) {
					return false;
				}
				for(int c = 0; c<pianeti.get(i).getNumeroLune(); c++) {
					if (newPianeta.getNome().equals(pianeti.get(i).getLuna(c).getNome())) {
						return false;
					}
				}
			}
		}
		
		
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
			if(nome.equals(pianeti.get(i).getNome())) {
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
	
	public boolean distruggiPianeta(String nome) {
		for(int i=0; i<pianeti.size(); i++) {
			if(nome.equals(pianeti.get(i).getNome())) {
				pianeti.remove(i);
				return true;
			}
		}
		return false;
	}
	
}
