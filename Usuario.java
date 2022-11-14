package albumDelMundial;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class Usuario {
	
	private int dni;
	private String nombre;
	private Album album;
	private Dictionary<Integer, ArrayList<Figurita>> figuritasTradicionales;
	private Dictionary<Integer, ArrayList<FiguritaTOP10>> figuritasTOP10;
	
	public Usuario(int dni, String nombre, Album album) {
		this.dni = dni;
		this.nombre = nombre;
		this.album = album;
		if(album.darTipo() == "Extendido") {
			this.figuritasTOP10 = new Hashtable<Integer, ArrayList<FiguritaTOP10>>();
		}
		this.figuritasTradicionales = new Hashtable<Integer, ArrayList<Figurita>>();
	}
	
	public void agregarFiguritas(List<Figurita> sobre) {
		for (int i = 0; i < sobre.size(); i++) {
			if(sobre.get(i) instanceof FiguritaTOP10) {
				if(this.figuritasTOP10.get(sobre.get(i).darNumeroId()) == null) {
					ArrayList<FiguritaTOP10> figuTOP10 = new ArrayList<FiguritaTOP10>();
					figuTOP10.add((FiguritaTOP10) sobre.get(i));
					this.figuritasTOP10.put(sobre.get(i).darNumeroId(), figuTOP10);
				}else {
					this.figuritasTOP10.get(sobre.get(i).darNumeroId()).add((FiguritaTOP10) sobre.get(i));
				}
			}else {
				if(this.figuritasTradicionales.get(sobre.get(i).darNumeroId()) == null) {
					ArrayList<Figurita> figuTradicional = new ArrayList<Figurita>();
					figuTradicional.add(sobre.get(i));
					this.figuritasTradicionales.put(sobre.get(i).darNumeroId(), figuTradicional);
				}else {
					this.figuritasTradicionales.get(sobre.get(i).darNumeroId()).add(sobre.get(i));
				}
			}
		}
	}
	
	public List<String> pegarFiguritas() {
		List<String> figuritasPegadas = new ArrayList<String>();
		Enumeration<ArrayList<Figurita>> figuritasEnum = figuritasTradicionales.elements();
		while (figuritasEnum.hasMoreElements()) {
			Figurita figurita = figuritasEnum.nextElement().get(0);
			boolean sePudoPegar = this.album.pegarFigurita(figurita.darSeleccion(), figurita);
			if(sePudoPegar) {
				String figuritaAgregar = figurita.darSeleccion() + figurita.darNumeroFigurita();
				figuritasPegadas.add(figuritaAgregar);
			}
		}
		if(album.darTipo() == "Extendido") {
			Enumeration<ArrayList<FiguritaTOP10>> figuritasTOP10Enum = figuritasTOP10.elements();
			while (figuritasTOP10Enum.hasMoreElements()) {
				FiguritaTOP10 figuritaTOP10 = figuritasTOP10Enum.nextElement().get(0);
				boolean sePudoPegar = this.album.pegarFigurita(figuritaTOP10.darMundial(), figuritaTOP10);
				if(sePudoPegar) {
					String figuritaAgregar = figuritaTOP10.darSeleccion() + figuritaTOP10.darNumeroFigurita();
					figuritasPegadas.add(figuritaAgregar);
				}
			}
		}
		return figuritasPegadas;
	}
	
	public boolean esRepetida(Figurita figurita) {
		if(figurita instanceof FiguritaTOP10) {
			if(this.figuritasTOP10.get(figurita.darNumeroId()) != null 
					&& this.figuritasTOP10.get(figurita.darNumeroId()).get(0) != null)
				return true;
			else 
				return false;
		}else {
			if(this.figuritasTradicionales.get(figurita.darNumeroId()) != null 
					&& this.figuritasTradicionales.get(figurita.darNumeroId()).get(0) != null)
				return true;
			else 
				return false;
		}
	}
	
	public int devolverDni() {
		return this.dni;
	}
	
	public String devolverNombre() {
		return this.nombre;
	}
	
	public String devolverPremioAlbum() {
		if(!this.completoElAlbum()) {
			throw new RuntimeException("El usuario no completo el album");
		}
		return this.album.darPremio();
		
	}
	
	public String devolverTipoDeAlbum() {
		return this.album.darTipo();
	}

	public void eliminarRepetida(Figurita figurita) {
		if(figurita instanceof FiguritaTOP10) {
			 this.figuritasTOP10.get(figurita.darNumeroId()).remove(0);
		}else {
			this.figuritasTradicionales.get(figurita.darNumeroId()).remove(0);
		}
	}
	
	public boolean completoElAlbum() {
		return this.album.completoElAlbum();
	}

	public boolean completoLaSeleccion(String seleccion) {
		return this.album.completoLaSeleccion(seleccion);
	}
	
	public boolean usoCodigoPromocional() {
		if(this.album.darTipo() == "Web") {
			AlbumWeb albumTra = (AlbumWeb) album;
			return albumTra.usoCodigoPromocional();
		}
		throw new RuntimeException("Solos los usuarios con albumes tradicionales pueden participar en el sorteo");
	}
	
	public void usarCodigoPromocional() {
		if(this.album.darTipo() == "Web") {
			AlbumWeb albumTra = (AlbumWeb) album;
			if(albumTra.usoCodigoPromocional()) {
				throw new RuntimeException("El usuario ya uso el codigo promocional");
			}else {
				albumTra.usarCodigo();
			}
		}else {
			throw new RuntimeException("Solos los usuarios con albumes web pueden usar codigo promocional");
		}
	}
	
	/*public boolean usoCodigoSorteo() {
		if(this.album.darTipo() == "Tradicional") {
			AlbumTradicional albumTra = (AlbumTradicional) album;
			return albumTra.realizoSorteo();
		}
		throw new RuntimeException("Solos los usuarios con albumes tradicionales pueden participar en el sorteo");
	}*/
	
	public boolean usarCodigoSorteo() {
		if(this.album.darTipo() == "Tradicional") {
			AlbumTradicional albumTra = (AlbumTradicional) album;
			if(albumTra.realizoSorteo()) {
				return false;
			}else {
				albumTra.realizarSorteo();
				return true;
			}
		}
		throw new RuntimeException("Solos los usuarios con albumes tradicionales pueden participar en el sorteo");
	}
		
}
