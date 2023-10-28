# Album del Mundial

# **Album del Mundial**

20/11/2022

**─**

Tomás Ledesma

Universidad Nacional de General Sarmiento

Programación 2

Comisión 1

# **Tads:**

### **TAD AlbumDelMundial**

Datos:

- Map<Integer, TAD Usuario> usuarios: Contiene a los TAD usuarios registrados en el sistema.
- Fabrica TAD Fábrica (brindado por los profesores).
- int albumesExtendidos: va sumando a medida que los usuarios se van registrando con albumes de este tipo.
- int albumesWebs: va sumando a medida que los usuarios se van registrando con albumes de este tipo.
- int albumesTradicionales: va sumando a medida que los usuarios se van registrando con albumes de este tipo.

Irep:

- Los usuarios deben ser unicos, no se puede repetir su DNI.

Acciónes:

- usuarioRegistrado(int dni): chequea si está registrado un usuario con el dni recibido por parámetro, de ser correcto lo retorna, caso contrario lanza una excepción.
- int registrarParticipante(int dni, string nombre, string tipoAlbum): Registra a un usuario en el sistema, si este no existe se crea y retorna su número de dni, si este ya pertenece se lanza una excepción. Ademas se crea y asocia su álbum.
- void comprarFiguritas(int dni): Genera 4 figuritas tradicionales aleatorias del mundial haciendo uso de fabrica.generarSobre(int cant). Verifica al usuario con usuarioRegistrado(dni).
- void comprarFiguritasTop10(int dni): Genera 4 figuritas top10 aleatorias del mundial haciendo uso de fabrica.generarSobreTop10(int cant). Verifica al usuario con usuarioRegistrado(dni).
- void comprarFiguritasConCodigoPromocional(int dni): Chequea el codigo promocional que tiene el usuario y si este ya no fue usado, en caso negativo se usa el codigo promocional y se generan 4 figuritas tradicionales aleatorias del mundial y se las carga en su lista de figurita, caso contrario se lanza una excepción. Verifica al usuario con usuarioRegistrado(dni).
- List<String> pegarFiguritas(int dni): pega figuritas posibles y devuelve la seleccion y el numero que ocupa la figurita en la misma en una lista de string.
- boolean llenoAlbum(int dni): retorna true si el usuario completo el album caso contrario retorna false. Verifica al usuario con usuarioRegistrado(dni).
- int buscarFiguritaRepetida(int dni): busca si el usuario tiene alguna figurita repetida ya sea tradicional o top10 y caso afirmativo retorna el codigoId de la misma caso contrario -1.
- boolean intercambiar(int dni, int codFigurita): trata de buscar a otro usuario que no tenga repetida la figurita pasada por parametro y caso afirmativo, chequea que el valor de esta figurita encontrada sea menor a la pasa por parametros, en caso afirmativo se ejecuta el intercambio, caso contrario retorna false. Tambien verifica que ambos usuarios tengan el mismo album. Verifica al usuario con usuarioRegistrado(dni).
- boolean intercambiarUnaFiguritaRepetida(int dni): recibe un dni de un usuario y trata de primero encontrarle una figurita repetida y posteriormente de encontrar algun usuario que pueda intercambiarsela, para esto hace uso de intercambiar(dni, codFigurita) y buscarFiguritaRepetida(dni). Verifica al usuario con usuarioRegistrado(dni).
- String darNombre(int dni): retorna el nombre de un usuario. Verifica al usuario con usuarioRegistrado(dni).
- String darPremio(): retorna un prmio aleatorio para un usuario. Verifica al usuario con usuarioRegistrado(dni).
- String listadoDeGanadores: retorna una lista de usuarios que completaron el album y consiguieron un premio.
- List<String> participantesQueCompletaronElPais(String nombrePais): retorna una lista con los usuarios que completaron dicha seleccion.

### **TAD Album Tradicional**

Datos:

- Map<String, Figurita[]> selecciones: Un map con key: nombre de la seleccion, que contiene en su valor un arreglo de 12 lugares a ocupar con TAD Figurita. La longitud del arreglo sería de 12 lugares.
- ArrayList<Integer> figuritasId: contiene un arraylist con el numeroId de las figuritas pegadas.
- String premio
- boolean completoElAlbum: Estado que muestra si el album esta completo o no.
- int numeroID
- String tipo
- totalFiguritasTradicionales = 384: total de figuritas que puede tener el album.
- boolean usoSorteoInstantaneo: indica el estado de si ya se usó el código promocional del álbum para el sorteo instantáneo.
- boolean usoSorteoInstantaneo;

Irep:

- Las selecciones no pueden estar repetidas y cada una debe tener 12 espacios disponibles. Las selecciones deben ser 32 en total.
- El premio debe ser final e = “Pelota”
- El tipo debe ser final e = “Tradicional”
- El numeroID debe ser un valor único e autoincremental entre todos los álbumes indiferentemente de su tipo.

Acciónes:

- boolean pegarFiguritas(Figurita figurita): Primero se verifica si el álbum está completo, de ser así no se intenta pegar la figurita. Sino se pega la figurita recibida en el album, verificando de que la seleccion y el lugar que ocupa la figurita en la misma esté disponible. Devuelve true si la figurita se agrego o false en caso contrario. Además se agrega el codigoId de la figurita en figuritasId, y se verifica si se completó el álbum, caso positivo se modifica el estado completoElAlbum a true.
- String darTipo(): retorna el tipo de album.
- boolean completoElALbum(): retorna si el album esta completo.
- boolean completoLaSeleccionArgentina(): retorna true si se completó la selección pasada por parámetro, sino false.
- int darNumeroId(): retorna el numero id del álbum.
- String darPremio(): retorna el premio;
- boolean usoSorteoInstantaneo(): retorna el estado usoSorteoInstantaneo.
- void usarSorteoInstantaneo(): setea el estado usoSorteoInstantaneo en true.

### **TAD Album Extendido**

Datos:

- Map<String, Figurita[]> selecciones:
    
    Un map con key: nombre de la seleccion, que contiene en su valor un arreglo de 12 lugares a ocupar con TAD Figurita. La longitud del arreglo sería de 12 lugares.
    
    Además las últimas 10 keys son los mundiales que contienen un arreglo de 2 lugares, donde van los jugadores del balón de Oro y Plata de los 10 mundiales anteriores.
    
- ArrayList<Integer> figuritasId: contiene un arraylist con el numeroId de las figuritas pegadas.
- String premio
- boolean completoElAlbum: Estado que muestra si el album esta completo o no.
- int numeroID
- String tipo
- boolean usoSorteoInstantaneo;
- totalFiguritasTradicionales = 384: total de figuritas que puede tener el album.
- final int totalFiguritasTOP10 = 20

Irep:

- Las selecciones no pueden estar repetidas y cada una debe tener 12 espacios disponibles. Deben ser 32 selecciones en total.
- Los mundiales no pueden estar repetidos y deben tener 2 espacios disponibles. Los mundiales deben ser 20.
- El premio debe ser = “Pelota y viaje”
- El tipo debe ser = “Extendido”
- El numeroID debe ser un valor único e autoincremental entre todos los álbumes indiferentemente de su tipo.

Acciónes:

- boolean pegarFiguritas(Figurita figurita): Primero se verifica si el álbum está completo, de ser así no se intenta pegar la figurita. Sino se pega la figurita recibida en el album, verificando de que la seleccion y el lugar que ocupa la figurita en la misma esté disponible. Devuelve true si la figurita se agrego o false en caso contrario. Además se agrega el codigoId de la figurita en figuritasId, y se verifica si se completó el álbum, caso positivo se modifica el estado completoElAlbum a true.
- contrario.
- String darTipo(): retorna el tipo de album.
- boolean completoElALbum(): retorna si el album esta completo.
- boolean completoLaSeleccionArgentina(): retorna si completo la selecciona Argentina.
- boolean usoSorteoInstantaneo(): retorna el estado usoSorteoInstantaneo.
- void usarSorteoInstantaneo(): setea el estado usoSorteoInstantaneo en true.

### **TAD Album Web**

Datos:

- Map<String, Figurita[]> selecciones: Un map con key: nombre de la seleccion, que contiene en su valor un arreglo de 12 lugares a ocupar con TAD Figurita. La longitud del arreglo sería de 12 lugares.
- ArrayList<Integer> figuritasId: contiene un arraylist con el numeroId de las figuritas pegadas.
- String premio
- boolean completoElAlbum: Estado que muestra si el album esta completo o no.
- int numeroID
- String tipo
- totalFiguritasTradicionales = 384: total de figuritas que puede tener el album.
- boolean usoSorteoInstantaneo: indica el estado de si ya se usó el código promocional del álbum para el sorteo instantáneo.
- boolean codigoUsado: estado que determina si el usuario uso o no el código promocional para conseguir un sobre gratis.

Irep:

- Las selecciones no pueden estar repetidas y cada una debe tener 12 espacios disponibles. Las selecciones deben ser 32 en total.
- El numeroID debe ser un valor único e autoincremental entre todos los álbumes indiferentemente de su tipo.
- El premio debe ser final e = “Remera de la seleccion”
- El tipo debe ser final e = “Web”

Acciónes:

- boolean pegarFiguritas(Figurita figurita): Primero se verifica si el álbum está completo, de ser así no se intenta pegar la figurita. Sino se pega la figurita recibida en el album, verificando de que la seleccion y el lugar que ocupa la figurita en la misma esté disponible. Devuelve true si la figurita se agrego o false en caso contrario. Además se agrega el codigoId de la figurita en figuritasId, y se verifica si se completó el álbum, caso positivo se modifica el estado completoElAlbum a true.
- String darTipo(): retorna el tipo de album.
- boolean completoElALbum(): retorna si el album esta completo.
- boolean completoLaSeleccionArgentina(): retorna true si se completó la selección pasada por parámetro, sino false.
- int darNumeroId(): retorna el numero id del álbum.
- String darPremio(): retorna el premio;
- boolean usoCodigoPromocional(): retorna el estado codigoUsado.
- void usarCodigo(): setea el estado codigoUsado en true.

### **TAD Usuario**

Datos:

- int dni
- String nombre
- TAD Album (Tradicional-Extendido-Web)
- ArrayList<Figurita> figuritas: Contiene las figuritas que el usuario tenga repetidas o sin pegar.
- ArrayList<Integer> codigoIdFiguritas: codigoId de todas las figuritas que tenga el usuario.

Irep:

- El tipo de album del usuario puede ser de tipo Tradicional, Extendido o Web pero solo uno de ellos.
- La figurita y su codigoId deben compartir el número de índice, para a través de este poder acceder a ambos arrays por el mismo valor. Ejemplo: Siendo x el valor de índice donde se encuentra figurita en this.figuritas yo puedo acceder a su código de esta manera: this.codigoIdFiguritas.get(x).

Nota: Se usa un arraylist con los codigos de las figuritas para facilitar el validar si un participante ya contiene una figurita con ese código haciendo un this.codigoIdFiguritas.contains(codigoId).

Acciónes:

- void agregarFiguritas(Figurita figurita): agrega la figurita pasada por parametro al array list del usuario, y su codigo id al arraylist de codigoIdFigurita.
- void agregarFiguritas(List<Figurita> sobre): agrega las figuritas compradas por el usuario a la lista de figuritas y sus codigos id al arraylist de codigoIdFigurita.
- List<String> pegarFiguritas(): Pega las figuritas que el usuario tenga si su respectivo lugar se encuentra disponible. Devuelve una lista con string de la seleccion y numero de la figurita que se pudo pegar. Si se pudieron pegar elimina las figuritas y sus codigos id de los arraylists del usuario.
- boolean esRepetida(int codigoFigurita): Devuelve un booleano si la figurita es repetida, para esto el usuario debe contener su id dentro de codigoIdFigurita.
- String devolverTipoDeAlbum(): retorna el tipo de album que tiene el usuario.
- void eliminarRepetida(Figurita figurita): recibe una figurita a eliminar de su lista, si esta existe en sus figuritas, la elimina.
- boolean completoElAlbum(): retorna un booleano que es true si el album del usuario esta completo, caso contrario false.
- boolean completoLaSeleccion(String seleccion): retorna si completo la elección pasada por parámetros.
- boolean usoCodigoPromocional(): retorna true si ya fue usado, sino retorna false. En caso de que el usuario no tenga álbum tradicional se lanza una excepcion.
- void usoCodigoSorteo(): retorna true si el usuario ya uso el codigo de sorteo, sino retorna false;
- boolean usarCodigoSorteo(): el usuario hace uso de su codigo de sorteo, chequea que tenga album tradicional y que ya no haya realizado el mismo. En caso de que su álbum no sea tradicional o extendido se lanza una excepción. En caso de que el usuario ya haya hecho uso de su código anteriormente se lanza una excepción.
- String devolverPremioAlbum(): retorna el tipo de premio que tiene su album.
- String devolverNombre(): retorna el nombre del usuario.
- int devolverdni(): retorna su dni.

### **TAD Figurita Tradicional**

Datos:

- String seleccion
- String nombreJugador
- String numeroID
- int numeroFiguritaSeleccion: numero que ocupa la figurita en la seleccion.
- double valorFinal

Irep:

- El valor de numeroId debe ser único en la combinación de (seleccion - nombreJugador - numeroFiguritaSeleccion), es decir todas las cartas iguales deben tener el mismo codigoId. Pero entre cartas diferentes el valor del codigoId debe ser único y autoincremental.

Acciónes:

- int darValorFinal(): devuelve el valor finald e la figurita.
- int darNumeroId(): retorna el numeroID de la figurita.
- String darSeleccion(): devuelve la seleccion de la figurita.
- int darNumeroFigurita(): devuelve el el numeroFiguritaSeleccion.

### **TAD Figurita TOP10**

Datos:

- String nombreDelPaisSede: Del mundial en que el jugador ganó.
- int añoDelMundial: Del mundial en que el jugador ganó.
- String balon: “oro” o “plata”.
- String seleccion
- String nombreJugador
- Int numeroID
- double valorFinal
- int numeroFiguritaSeleccion;

Irep:

- El valor de numeroId debe ser único en la combinación de (seleccion - nombreJugador - numeroFiguritaSeleccion), es decir todas las cartas iguales deben tener el mismo codigoId. Pero entre cartas diferentes el valor del codigoId debe ser único y autoincremental.

Acciónes:

- int darValorFinal(): devuelve el valor finald e la figurita.
- int darNumeroId(): retorna el numeroID de la figurita.
- String darSeleccion(): devuelve la seleccion de la figurita.
- int darNumeroFigurita(): devuelve el el numeroFiguritaSeleccion.
- darMundial(): retorna el pais sede y el año del mundial.

# **Conceptos de herencia**

Herencia:

1. **AlbumWeb** **AlbumTradicional** **AlbumExtendido**: Las 3 clases heredan atributos y métodos de la clase abstracta **Album**, esto fue necesario ya que estas clases comparten atributos y métodos en común, pero también algunos métodos y atributos que son propios de cada clase. Por lo que la mejor opción fue crear la clase abstracta **Album.**java.
2. **FiguritaTOP10**: **Figurita**.java es la clase que representa a las figuritas tradicionales solicitadas, **FiguritaTOP10** hereda todos los atributos y métodos de está y además implementa los propios, a diferencia del punto anterior **Figurita**.java no es una clase abstracta por lo que también se implementa.

Polimorfismo:

1. Pudimos aprovechar el polimorfismo de las clases **Figurita** y **FiguritaTOP10** en el manejo de estas tanto en álbum como en usuario, ya que tanto un usuario como AlbumExtendido contienen a ambas clases. Uno de los casos es en Usuario.java - pegarFiguritas(), donde la función recorre el ArrayList<Figurita> que contiene objetos de clase **Figurita** y **FiguritaTOP10** y para saber en que sector del album pegarla consulta a figurita (figurita.darSeleccion()). Por medio del polimorfismo java interpreta si debe ejecutar el darSeleccion() de la clase **Figurita**, que devuelve la selección correspondiente de la figurita, o el darSeleccion() de **FiguritaTOP10** que retorna el mundial correspondiente de la figurita.

Abstracción:

- **Album**.java es una clase abstracta que contiene atributos y métodos que comparten los distintos tipos de álbumes solicitados. No existe la necesidad de instanciar esta clase ya que cada álbum en particular posee atributos y/o métodos propios, por lo que lo mejor era crear esta clase como abstracta y que las clases correspondientes de cada álbum hereden de está.

Sobreescritura:

1. **AlbumExtendido**.java - pegarFigurita(Figurita figurita). La clase **AlbumExtendido** debe comparar la longitud de codigoIdFiguritas teniendo en cuenta las 20 más que son TOP10, por lo que implementa su propio método pegarFigurita() haciendo un @Override del método que hereda del padre.
2. **FiguritaTOP10**.java - darSeleccion(): darSeleccion() en **FiguritaTOP10** retorna el mundial al que pertenece por lo que también hace una sobreescritura del metodo darSeleccion() que contiene la clase padre **Figurita**.

Sobrecarga:

1. **Usuario**.java - agregarFiguritas(Figurita figurita): La clase usuario hace una sobrecarga de métodos respecto a agregarFiguritas:
2. agregarFiguritas(Figurita figurita): Le agrega una figurita recibida por parámetros al usuario, esto se usa en el intercambio de figuritas de usuario a usuario.
3. agregarFiguritas(List<Figurita> sobre): Agrega todas las figuritas de la lista al usuario, este método se usa cuando el usuario compra un sobre de figuritas.

# **Diagrama de clases UMLComplejidad de buscarFiguritaRepetida(int dni)**

[https://lh7-us.googleusercontent.com/uuNefEiEwYnca-rY7BKtE677ajZyq4fajeWT04f785CT_s-VpuHEJpi262CYoRkn0dH_AF5n0pjQ2kNjR57sBOCGuwxHCfFKat-KAEuusfbZHctrS442V1IlShTPZyK79cGWoQr79vbzIEyNnniW7q8](https://lh7-us.googleusercontent.com/uuNefEiEwYnca-rY7BKtE677ajZyq4fajeWT04f785CT_s-VpuHEJpi262CYoRkn0dH_AF5n0pjQ2kNjR57sBOCGuwxHCfFKat-KAEuusfbZHctrS442V1IlShTPZyK79cGWoQr79vbzIEyNnniW7q8)

[https://lh7-us.googleusercontent.com/rNKueHrgbZEAu_DysIX3Ee6E_lXksbokSFoj6hs2no69kNpPcJYhxDYGxaQSaAqGKx2gwnjRteC6vY38gNFJ1MZLnjPtvFCC6may0MEc1hbsV6L29tu76cFFffUHwjSZKg-AUL-JQkT92I0xGbSQE8o](https://lh7-us.googleusercontent.com/rNKueHrgbZEAu_DysIX3Ee6E_lXksbokSFoj6hs2no69kNpPcJYhxDYGxaQSaAqGKx2gwnjRteC6vY38gNFJ1MZLnjPtvFCC6may0MEc1hbsV6L29tu76cFFffUHwjSZKg-AUL-JQkT92I0xGbSQE8o)
