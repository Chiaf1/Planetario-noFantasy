package nofantasy.planetario;

public class Luna extends CorpoCeleste{
	
	private String id;
	private static String newId = "LA0000";
	
	public Luna() {
		super("",0.0,0.0,0.0);
	}
	
	public Luna(String nome, double massa, double angolo0, double raggioOrbita, Pianeta pianeta) {
		
		super(nome, massa, angolo0, raggioOrbita);
		
		//creazione id
		id = newId;
		newId = super.calcolaId(newId);
		
		//calcolo coordinate
		Coordinate coordinate = new Coordinate(raggioOrbita*Math.cos(angolo0) + pianeta.getCoordinate().getX(), raggioOrbita*Math.sin(angolo0) + pianeta.getCoordinate().getY());
		super.setCoordinate(coordinate);
	}
	
	public String getId() {
		return id;
	}

}
