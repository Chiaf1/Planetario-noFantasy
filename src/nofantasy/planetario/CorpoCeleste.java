package nofantasy.planetario;

public class CorpoCeleste {
	
	private String nome;
	private double massa;
	private double raggioOrbita;
	private double periodo;
	private double angolo0;
	private double raggio;
	private Coordinate coordinate;
	
	public CorpoCeleste(String nome, double massa, double raggio, double periodo, double angolo0, double raggioOrbita) {
		
		this.nome = nome;
		this.massa = massa;
		this.raggio = raggio;
		this.periodo = periodo;
		this.angolo0 = angolo0;
		this.raggioOrbita = raggioOrbita;
		
		//calcolo coordinate
		coordinate = new Coordinate(raggio*Math.cos(angolo0), raggio*Math.sin(angolo0));
				
	}
	
	public CorpoCeleste(String nome, double massa, double raggio) {
		
		this.nome = nome;
		this.massa = massa;
		this.raggio = raggio;
		
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

	public double getRaggio() {
		return raggio;
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
