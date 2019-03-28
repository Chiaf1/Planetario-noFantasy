package nofantasy.planetario;

public class Luna extends CorpoCeleste{
	
	private String id;
	private static String newId;
	
	public Luna() {
		super("",0.0,0.0,0.0,0.0,0.0);
	}
	
	public Luna(String nome, double massa, double raggio, double periodo, double angolo0, double raggioOrbita) {
		
		super(nome, massa, raggio, periodo, angolo0, raggioOrbita);
		
		//creazione id
		id = newId;
		newId = super.calcolaId(newId);
	}
	
	public String getId() {
		return id;
	}

}
