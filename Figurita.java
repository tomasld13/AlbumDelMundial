package albumDelMundial;

public class Figurita {
	protected String seleccion;
	protected String nombreJugador;
	protected static int numerosFiguritas;
	protected int numeroFiguritaSeleccion;
	protected int numeroId;
	protected int valorFinal;
	
	public Figurita(String seleccion, String nombreJugador, int valorBase, int numero, int numeroId) {
		this.seleccion = seleccion;
		this.nombreJugador = nombreJugador;
		this.numeroId = numeroId;
		this.valorFinal = valorBase;
		this.numeroFiguritaSeleccion = numero;
	}
	
	public int darValorFinal() {
		return this.valorFinal;
	}
	
	public String darSeleccion() {
		return seleccion;
	}
	
	public int darNumeroFigurita() {
		return numeroFiguritaSeleccion;
	}
	
	public int darNumeroId() {
		return this.numeroId;
	}	
	
	
	
}
