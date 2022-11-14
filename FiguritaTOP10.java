package albumDelMundial;

public class FiguritaTOP10 extends Figurita{
	private String nombreDelPaisSede;
	private String añoDelMundial;
	private String balon;
	
	public FiguritaTOP10(String seleccion, String nombreJugador, int valorBase, int numero, int numeroId,
						 String nombreDelPaisSede, String añoDelMundial, String balon) {
		super(seleccion, nombreJugador, valorBase, numero, numeroId);
		this.nombreDelPaisSede = nombreDelPaisSede;
		this.añoDelMundial = añoDelMundial;
		this.balon = balon;
	}
	
	public String darMundial() {
		return this.nombreDelPaisSede + "'" + this.añoDelMundial;
	}
	
}
