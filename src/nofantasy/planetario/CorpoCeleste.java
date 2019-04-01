package nofantasy.planetario;

public abstract class CorpoCeleste {
	
	private String nome;
	private double massa;
	private double raggioOrbita;
	private double periodo;
	private double angolo0;
	private Coordinate coordinate;
	
	public CorpoCeleste(String nome, double massa, double periodo, double angolo0, double raggioOrbita) {
		
		this.nome = nome;
		this.massa = massa;
		this.periodo = periodo;
		this.angolo0 = angolo0;
		this.raggioOrbita = raggioOrbita;
		
		//calcolo coordinate
		coordinate = new Coordinate(raggioOrbita*Math.cos(angolo0), raggioOrbita*Math.sin(angolo0));
				
	}
	
	public CorpoCeleste(String nome, double massa, double raggioOrbita) {
		
		this.nome = nome;
		this.massa = massa;
		this.raggioOrbita = raggioOrbita;
		
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

	public double getPeriodo() {
		return periodo;
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
		//creazione ID
				
				if("9999" != newId.substring(2, 5)) {
					int num = Integer.valueOf(newId.substring(2, 5));
					num++;
					if(num<10) {
						newId = newId.substring(0, 1) + "000" + Integer.toString(num);
					} else if (num<100) {
						newId = newId.substring(0, 1) + "00" + Integer.toString(num);
					}else if (num<1000) {
						newId = newId.substring(0, 1) + "0" + Integer.toString(num);
					}else {
						newId = newId.substring(0, 1) + Integer.toString(num);				
					}
					
				}else {
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
