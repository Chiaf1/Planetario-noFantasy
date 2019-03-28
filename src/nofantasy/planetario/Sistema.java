package nofantasy.planetario;

import java.util.*;

public class Sistema {
	
	private Stella stella = new Stella();
	private Coordinate centroDiMassa = new Coordinate(0, 0);
	private ArrayList<String[]> listaCollisioni;
	
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
	
	public ArrayList<String[]> getListaCollisioni() {
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
		
	}

}
