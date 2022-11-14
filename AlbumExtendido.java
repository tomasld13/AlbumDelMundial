package albumDelMundial;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class AlbumExtendido extends Album{
	private Dictionary<String, FiguritaTOP10[]> figuritasTOP10;//Mundial - TAD FiguritaTOP10
	private ArrayList<Integer> figuritasTOP10Id;//numeroId figuritaTOP10
	private final int totalFiguritasTOP10 = 20;//mundiales * (jugador Oro + jugador Plata)
	
	public AlbumExtendido(String premio, String tipo, String[] paises, String[] mundiales) {
		super(premio, tipo, paises); 
		this.figuritasTOP10 = new Hashtable<String, FiguritaTOP10[]>();;
		this.figuritasTOP10Id = new ArrayList<Integer>();
		for (int i = 0; i < mundiales.length; i++) {
			FiguritaTOP10[] figuritasTOP10PorMundial = new FiguritaTOP10[2];
			figuritasTOP10.put(mundiales[i], figuritasTOP10PorMundial);
		}
	}
	
	@Override
	public boolean pegarFigurita(String seleccion, Figurita figurita) {
		if(this.completoElAlbum)
			return false;
		if(figurita instanceof FiguritaTOP10) {
			String mundial = seleccion;
			if(figuritasTOP10.get(mundial) != null) {
				int numeroFigu = figurita.darNumeroFigurita();
				FiguritaTOP10[] figusActuales = figuritasTOP10.get(mundial);
				if(figusActuales[numeroFigu] == null) {
					figusActuales[numeroFigu] = (FiguritaTOP10) figurita;
					this.figuritasTOP10Id.add(figurita.darNumeroId());
					if(this.figuritasTOP10Id.size() == totalFiguritasTOP10 && this.figuritasId.size() == this.totalFiguritasTradicionales)
						this.completoElAlbum = true;
					return true;
				}
			}
		}else {
			if(this.selecciones.get(seleccion) != null) {
				int numeroFigu = figurita.darNumeroFigurita();
				Figurita[] figusActuales = selecciones.get(seleccion);
				if(figusActuales[numeroFigu] == null) {
					figusActuales[numeroFigu] = figurita;
					this.figuritasId.add(figurita.darNumeroId());
					if(this.figuritasTOP10Id.size() == totalFiguritasTOP10 && this.figuritasId.size() == this.totalFiguritasTradicionales)
						this.completoElAlbum = true;
					return true;
				}
			}
		}
		return false;
	}
	
	/*@Override
	protected boolean estaEnAlbum(int numeroId) {
		return this.figuritasId.contains(numeroId) || this.figuritasTOP10Id.contains(numeroId);
	};*/
}
