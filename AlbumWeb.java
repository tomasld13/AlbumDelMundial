package albumDelMundial;

public class AlbumWeb extends Album{
	private boolean codigoUsado;

	public AlbumWeb(String premio, String tipo, String[] paises) {
		super(premio, tipo, paises);
		this.codigoUsado = false;
	}
	
	public void usarCodigo() {
		this.codigoUsado = true;
	}
	
	public boolean usoCodigoPromocional() {
		return this.codigoUsado;
	}

}
