package albumDelMundial;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public abstract class Album {
	protected Dictionary<String, Figurita[]> selecciones;
	protected ArrayList<Integer> figuritasId;
	protected String premio;
	private static int albumNumero;	
	protected int numeroId;
	protected String tipo;
	protected boolean completoElAlbum;
	protected final int totalFiguritasTradicionales = 384; //32 selecciones * 12 jugadors c/u.
	
	
	protected Album(String premio, String tipo, String[] paises) {
		this.selecciones = new Hashtable<String, Figurita[]>();
		this.premio = premio;
		this.numeroId = Album.albumNumero +=1;
		this.tipo = tipo;
		this.figuritasId = new ArrayList<Integer>();
		this.completoElAlbum = false;
		for (int i = 0; i < paises.length; i++) {
			Figurita[] nuevasFiguritas = new Figurita[12];
			selecciones.put(paises[i], nuevasFiguritas);
		}
	}
	
	public boolean pegarFigurita(String seleccion, Figurita figurita) {
		if(this.completoElAlbum) 
			return false;
		if(this.selecciones.get(seleccion) != null) {
			int numeroFigu = figurita.darNumeroFigurita();
			Figurita[] figusActuales = selecciones.get(seleccion);
			if(figusActuales[numeroFigu] == null) {
				figusActuales[numeroFigu] = figurita;
				this.figuritasId.add(figurita.darNumeroId());
				if(this.figuritasId.size() == this.totalFiguritasTradicionales)
					this.completoElAlbum = true;
				return true;
			}
		}
		return false;
	}
	/*
	protected boolean estaEnAlbum(int numeroId) {
		return this.figuritasId.contains(numeroId);
	};*/
	
	protected String darTipo() {
		return this.tipo;
	};
	
	protected boolean completoElAlbum() {
		return this.completoElAlbum;
	};
	
	protected boolean completoLaSeleccion(String seleccion) {
		Figurita[] figusSeleccion = this.selecciones.get(seleccion);
		for (int i = 0; i < figusSeleccion.length; i++) {
			if(figusSeleccion[i] == null) {
				return false;
			}
		}
		return true;
	}
	
	protected int darNumeroId() {
		return this.numeroId;
	}
	
	protected String darPremio() {
		return this.premio;
	}
	
}
