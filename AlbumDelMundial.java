package albumDelMundial;

import java.util.*;

public class AlbumDelMundial implements IAlbumDelMundial {
	private Dictionary<Integer, Usuario> usuarios; // DNI - Usuario
	private Map<Integer, Figurita> figuritas; // numeroID - Figurita
	private Map<Integer, FiguritaTOP10> figuritasTOP10; // numeroID - Figurita
	private Fabrica fabrica;
	private int albumesExtendidos;
	private int albumesWebs;
	private int albumesTradicionales;

	public AlbumDelMundial() {
		this.usuarios = new Hashtable<>();
		this.fabrica = new Fabrica();
		this.figuritas = this.fabrica.darFiguritasTradicionalesTotales();
		this.figuritasTOP10 = this.fabrica.darFiguritasTOP10Totales();
		this.albumesExtendidos = 0;
		this.albumesWebs = 0;
		this.albumesTradicionales = 0;
	}

	public Usuario usuarioRegistrado(int dni) {
		Usuario usuario = usuarios.get(dni);
		if (usuario == null) {
			throw new RuntimeException("No existe ningun usuario registrado con ese dni");
		}
		return usuario;
	}

	@Override
	public int registrarParticipante(int dni, String nombre, String tipoAlbum) {
		Usuario usuario = usuarios.get(dni);
		if (usuario != null) {
			throw new RuntimeException("Ya existe un usuario registrado con ese DNI");
		}
		Album album;
		switch (tipoAlbum) {
		case "Extendido":
			album = this.fabrica.crearAlbumExtendido();
			this.albumesExtendidos++;
			break;
		case "Web":
			album = this.fabrica.crearAlbumWeb();
			this.albumesWebs++;
			break;
		case "Tradicional":
			album = this.fabrica.crearAlbumTradicional();
			this.albumesTradicionales++;
			break;
		default:
			throw new RuntimeException("Los unicos tipos de albumes posibles son: 'Extendido', 'Web' y 'Tradicional'");
		}
		Usuario nuevo = new Usuario(dni, nombre, album);
		usuarios.put(dni, nuevo);
		return album.darNumeroId();
	}

	@Override
	public void comprarFiguritas(int dni) {
		Usuario usuario = usuarioRegistrado(dni);
		List<Figurita> sobre = this.fabrica.generarSobre(4);
		usuario.agregarFiguritas(sobre);
	}

	@Override
	public void comprarFiguritasTop10(int dni) {
		Usuario usuario = usuarioRegistrado(dni);
		if (usuario.devolverTipoDeAlbum() != "Extendido")
			throw new RuntimeException("Solo los usuarios con albumes 'Extendidos' pueden comprar sobres TOP10");
		List<Figurita> sobre = this.fabrica.generarSobreTop10(4);
		usuario.agregarFiguritas(sobre);
	}

	@Override
	public void comprarFiguritasConCodigoPromocional(int dni) {
		Usuario usuario = usuarioRegistrado(dni);
		if (usuario.devolverTipoDeAlbum() != "Web")
			throw new RuntimeException(
					"Solo los usuarios con albumes 'Web' pueden aplicar al sobre gratis con codigoPromocional");
		usuario.usarCodigoPromocional();
		List<Figurita> sobre = this.fabrica.generarSobre(4);
		usuario.agregarFiguritas(sobre);
	}

	@Override
	public List<String> pegarFiguritas(int dni) {
		Usuario usuario = usuarioRegistrado(dni);
		return usuario.pegarFiguritas();
	}

	@Override
	public boolean llenoAlbum(int dni) {
		Usuario usuario = usuarioRegistrado(dni);
		return usuario.completoElAlbum();
	}

	@Override
	public String aplicarSorteoInstantaneo(int dni) {
		Usuario usuario = usuarioRegistrado(dni);
		if (usuario.devolverTipoDeAlbum() == "Web")
			throw new RuntimeException(
					"Solo los usuarios con albumes 'Tradicional' o 'Extendido' pueden aplicar al sorteo");
		String[] premios = this.fabrica.darPremios();
		int random = (int) (Math.random() * 3);
		return premios[random];
	}

	@Override
	public int buscarFiguritaRepetida(int dni) {
		Usuario usuario = usuarioRegistrado(dni);
		Collection<Figurita> figuritasC = figuritas.values();
		Object[] figuritasT = figuritasC.toArray();
		for (int i = 0; i < figuritasT.length; i++) {
			if (usuario.esRepetida((Figurita) figuritasT[i])) {
				return ((Figurita) figuritasT[i]).darNumeroId();
			}

		}
		if (usuario.devolverTipoDeAlbum() == "Extendido") {
			Collection<FiguritaTOP10> figuritasCTOP = figuritasTOP10.values();
			Object[] figuritasTOP = figuritasCTOP.toArray();
			for (int i = 0; i < figuritasTOP.length; i++) {
				if (usuario.esRepetida((FiguritaTOP10) figuritasTOP[i])) {
					return ((FiguritaTOP10) figuritasTOP[i]).darNumeroId();
				}
			}
		}
		return -1;
	}

	@Override
	public boolean intercambiar(int dni, int codFigurita) {
		if (codFigurita == -1) {
			return false;
		}
		Usuario usuario = usuarioRegistrado(dni);
		Figurita figuritaUsr1 = this.figuritas.get(codFigurita);
		if (usuario == null)
			throw new RuntimeException("El usuario no existe");
		else if (!usuario.esRepetida(figuritaUsr1))
			throw new RuntimeException("El usuario no tiene esta figurita repetida");
		String album = usuario.devolverTipoDeAlbum();
		if (figuritaUsr1 == null)
			figuritaUsr1 = this.figuritasTOP10.get(codFigurita);
		if (figuritaUsr1 instanceof FiguritaTOP10) {
			Enumeration<Usuario> usuariosEnum = usuarios.elements();
			while (usuariosEnum.hasMoreElements()) {
				Usuario usuario2 = usuariosEnum.nextElement();
				if (usuario2.devolverTipoDeAlbum() == album) {
					if (usuario2.devolverDni() != dni && !usuario2.esRepetida(figuritaUsr1)) {
						int codigoFiguritaRepetidaUsuario2 = buscarFiguritaRepetida(usuario2.devolverDni());
						FiguritaTOP10 figuritaRepetidaUsuario2 = figuritasTOP10.get(codigoFiguritaRepetidaUsuario2);
						if (!usuario.esRepetida(figuritaRepetidaUsuario2)
								&& figuritaUsr1.darValorFinal() >= (figuritaRepetidaUsuario2.darValorFinal())) {
							if (usuario2.esRepetida(figuritaRepetidaUsuario2)
									&& !usuario.esRepetida(figuritaRepetidaUsuario2)
									&& figuritaUsr1.darValorFinal() >= (figuritaRepetidaUsuario2).darValorFinal()) {
								usuario2.eliminarRepetida(figuritaRepetidaUsuario2);
								List<Figurita> figuritaTemp = new ArrayList<Figurita>();
								figuritaTemp.add(figuritaUsr1);
								usuario2.agregarFiguritas(figuritaTemp);
								usuario.eliminarRepetida(figuritaUsr1);
								figuritaTemp.remove(0);
								figuritaTemp.add(figuritaRepetidaUsuario2);
								usuario.agregarFiguritas(figuritaTemp);
								return true;
							}
						}
					}

				}

			}
		}
		Enumeration<Usuario> usuariosEnum = usuarios.elements();
		while (usuariosEnum.hasMoreElements()) {
			Usuario usuario2 = usuariosEnum.nextElement();
			if (usuario2.devolverTipoDeAlbum() == album) {
				if (usuario2.devolverDni() != dni && !usuario2.esRepetida(figuritaUsr1)) {
					int codigoFiguritaRepetidaUsuario2 = buscarFiguritaRepetida(usuario2.devolverDni());
					Figurita figuritaRepetidaUsuario2 = figuritas.get(codigoFiguritaRepetidaUsuario2);
					if (!usuario.esRepetida(figuritaRepetidaUsuario2)
							&& figuritaUsr1.darValorFinal() >= (figuritaRepetidaUsuario2.darValorFinal())) {
						usuario2.eliminarRepetida(figuritaRepetidaUsuario2);
						List<Figurita> figuritaTemp = new ArrayList<Figurita>();
						figuritaTemp.add(figuritaUsr1);
						usuario2.agregarFiguritas(figuritaTemp);
						usuario.eliminarRepetida(figuritaUsr1);
						figuritaTemp.remove(0);
						figuritaTemp.add(figuritaRepetidaUsuario2);
						usuario.agregarFiguritas(figuritaTemp);
						return true;
					}
				}
			}

		}
		return false;
	}

	@Override
	public boolean intercambiarUnaFiguritaRepetida(int dni) {
		usuarioRegistrado(dni);
		return this.intercambiar(dni, buscarFiguritaRepetida(dni));
	}

	@Override
	public String darNombre(int dni) {
		Usuario usuario = usuarioRegistrado(dni);
		return usuario.devolverNombre();
	}

	@Override
	public String darPremio(int dni) {
		Usuario usuario = usuarioRegistrado(dni);
		return usuario.devolverPremioAlbum();
	}

	@Override
	public String listadoDeGanadores() {
		String lista = "";
		Enumeration<Usuario> usuariosEnum = usuarios.elements();
		while (usuariosEnum.hasMoreElements()) {
			Usuario usrActual = usuariosEnum.nextElement();
			if (usrActual.completoElAlbum()) {
				int dni = usrActual.devolverDni();
				String nombre = usrActual.devolverNombre();
				String tipoPremioAlbum = usrActual.devolverPremioAlbum();
				String resultado = "(" + dni + ") " + nombre + ": " + tipoPremioAlbum;
				lista = lista + resultado + ", ";
			}
		}
		if (lista.isEmpty()) {
			return lista;
		}
		return lista.substring(0, lista.length() - 2);
	}

	@Override
	public List<String> participantesQueCompletaronElPais(String nombrePais) {
		List<String> completaron = new ArrayList<String>();
		Enumeration<Usuario> usuariosEnum = usuarios.elements();
		while (usuariosEnum.hasMoreElements()) {
			Usuario usrActual = usuariosEnum.nextElement();
			if (usrActual.completoLaSeleccion(nombrePais)) {
				int dni = usrActual.devolverDni();
				String nombre = usrActual.devolverNombre();
				String tipoAlbum = usrActual.devolverTipoDeAlbum();
				String resultado = "(" + dni + ") " + nombre + ": " + tipoAlbum;
				completaron.add(resultado);
			}
		}
		return completaron;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AlbumDelMundial [UsuariosRegistrados=");
		builder.append(usuarios.size());
		builder.append(", UsuariosConAlbumCompleto=");
		int completaron = 0;
		Enumeration<Usuario> usuariosEnum = usuarios.elements();
		while (usuariosEnum.hasMoreElements()) {
			Usuario usrActual = usuariosEnum.nextElement();
			if (usrActual.completoElAlbum()) {
				completaron++;
			}
		}
		builder.append(completaron);
		builder.append(", AlbumesExtendidos=");
		builder.append(this.albumesExtendidos);
		builder.append(", AlbumesWebs=");
		builder.append(this.albumesWebs);
		builder.append(", AlbumesTradicionales=");
		builder.append(this.albumesTradicionales);
		builder.append(", FiguritasTradicionalesDisponibles=");
		builder.append(figuritas.size());
		builder.append(", FiguritasTOP10Disponibles=");
		builder.append(figuritasTOP10.size());
		builder.append("]");
		return builder.toString();
	}

}
