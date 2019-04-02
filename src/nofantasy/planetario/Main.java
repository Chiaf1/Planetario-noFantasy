package nofantasy.planetario;

public class Main {
	
	private static InterfacciaUtente interfaccia = new InterfacciaUtente();

	public static void main(String[] args) {
		interfaccia.creaziioneSistema();
		
		while(true) {
			if(interfaccia.azione()) {
				break;
			}
		}
	}

}
