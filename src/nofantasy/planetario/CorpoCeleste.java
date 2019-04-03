package nofantasy.planetario;

public abstract class CorpoCeleste {
	
	private String nome;
	private double massa;
	private double raggioOrbita;
	private double angolo0;
	private Coordinate coordinate;
	
	//costruttore per luna e pianeta
	public CorpoCeleste(String nome, double massa, double angolo0, double raggioOrbita) {
		this.nome = nome;
		this.massa = massa;
		this.angolo0 = angolo0;
		this.raggioOrbita = raggioOrbita;	
	}
	
	//costruttore per la stella
	public CorpoCeleste(String nome, double massa) {
		
		this.nome = nome;
		this.massa = massa;
		
		coordinate = new Coordinate(0, 0);
		
	}

	public String getNome() {
		return nome;
	}

	public double getMassa() {
		return massa;
	}

	public double getRaggioOrbita() {
		return raggioOrbita;
	}

	public double getAngolo0() {
		return angolo0;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public String calcolaId(String newId) {
				
				//calcolo parte numerica dell'id
				if(!"9999".equals(newId.substring(2, 5))) {
					int num = Integer.valueOf(newId.substring(2, 5));
					num++;
					if(num<10) {
						newId = newId.substring(0, 2) + "000" + Integer.toString(num);
					} else if (num<100) {
						newId = newId.substring(0, 2) + "00" + Integer.toString(num);
					}else if (num<1000) {
						newId = newId.substring(0, 2) + "0" + Integer.toString(num);
					}else {
						newId = newId.substring(0, 2) + Integer.toString(num);				
					}
					
				}else {
					//calcolo parte ""alfabetia??"" dell'id
					char c = newId.charAt(1);
					c++;
					
					if (c > 91) {
						c = 'a';
					}else if(c > 122) {
						c = 128;
					}
					
					newId = newId.charAt(0) + c + "0000";
				}
				
				return newId;
	}
	
}
