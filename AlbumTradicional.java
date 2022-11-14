package albumDelMundial;

public class AlbumTradicional extends Album{
	private boolean solicitoSorteo;
	
	public AlbumTradicional(String premio, String tipo, String[] paises) {
		super(premio, tipo, paises);
		this.solicitoSorteo = false;
	}
	
	public boolean realizoSorteo() {
		return this.solicitoSorteo;
	}
	
	public void realizarSorteo() {
		this.solicitoSorteo = true;
	}

}
