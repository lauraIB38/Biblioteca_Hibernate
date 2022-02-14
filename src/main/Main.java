package main;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import pojos.Libro;
import pojos.Prestamo;
import pojos.Socio;

public class Main {

	static ProyectoDAO proyectoDAO = new ProyectoDAO();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		boolean funcionando = true;
		String menu = "MENU:\n1.Libros\n2.Socios\n3.Consultas\n4.Alta Prestamo\n5.Otras Consultas\n6.Salir";
		String menulibros = "Seleccione:\n1.Añadir libro\n2.Borrar libro\n3.Modificar libro\n4.Volver";
		String menusocios = "Seleccione:\n1.Añadir socio\n2.Borrar socio\n3.Modificar socio\n4.Volver";
		String menuconsultas = "Seleccione consultar:\n1.Socios por nombre\n2.Socios por apellido\n3.Libros por titulo\n4.Libros por editorial\n5.Volver";
		String menuconsultas2 = "Seleccione consultar:\n1.Libros prestados actualmente\n2.Libros prestados por socio\n3.Libros con fecha fin prestamos superada\n4.Socios con libros con fecha fin prestamo superada\n5.Volver";
		while (funcionando) {
			System.out.println(menu);
			switch (safeScanner()) {
			case 1: {
				System.out.println(menulibros);
				switch (safeScanner()) {
				case 1: {
					anadirunlibro();
					break;
				}
				case 2: {
					borrarUnLibro();
					break;
				}
				case 3: {
					modificarLibro();
					break;
				}
				case 4: {
					break;
				}
				}
				break;
			}
			case 2: {
				System.out.println(menusocios);
				switch (safeScanner()) {
				case 1: {
					anadirUnSocio();
					break;
				}
				case 2: {
					borrarUnSocio();
					break;
				}
				case 3: {
					modificarSocio();
					break;
				}
				case 4: {
					break;
				}
				}
				break;
			}
			case 3: {
				System.out.println(menuconsultas);
				switch (safeScanner()) {
				case 1: {
					consultaNombre();
					break;
				}
				case 2: {
					consultaApellido();
					break;
				}
				case 3: {
					consultaTitulo();
					break;
				}
				case 4: {
					consultaEditorial();
					break;
				}
				case 5: {
					break;
				}
				}
				break;
			}
			case 4: {
				prestamo();
				break;
			}
			case 5: {
				System.out.println(menuconsultas2);
				switch (safeScanner()) {
				case 1: {
					proyectoDAO.librosPrestadosActualmente();
					continuar();
					break;
				}
				case 2: {
					librosPorSocio();
					break;
				}
				case 3: {
					proyectoDAO.librosConFechaFinSuperada();
					continuar();
					break;
				}
				case 4: {
					proyectoDAO.sociosConLibrosFechaFinSuperada();
					continuar();
					break;
				}
				case 5: {
					break;
				}
				}
				break;
			}
			case 6: {
				funcionando = false;
				break;
			}
			}

		}
	}

	// ***********************AÑADIR, BORRAR, MODIFICAR LIBROS******************************************

	public static void anadirunlibro() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca titulo:");
		String titulo = sc.nextLine();
		System.out.println("Numero de ejemplares:");
		int numEjemplares = Integer.parseInt(sc.nextLine());
		System.out.println("Editorial:");
		String editorial = sc.nextLine();
		System.out.println("Numero de paginas:");
		int numPaginas = Integer.parseInt(sc.nextLine());
		System.out.println("Anio de edicion:");
		int anioEdicion = Integer.parseInt(sc.nextLine());
		Libro libro = new Libro(titulo, numEjemplares, editorial, numPaginas, anioEdicion);
		proyectoDAO.anadirLibro(libro);
		continuar();
	}

	public static void borrarUnLibro() {
		proyectoDAO.listaLibros();
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca codigo:");
		int id = Integer.parseInt(sc.nextLine());
		Libro libro = proyectoDAO.devuelveUnLibro(id);
		if (libro == null) {
			System.out.println("El libro no existe");
			return;
		}
		proyectoDAO.borrarLibro(id);
		continuar();
	}

	private static void modificarLibro() {
		Scanner sc = new Scanner(System.in);
		proyectoDAO.listaLibros();
		try {
			System.out.println("Introduzca el codigo del libro que desea modificar");
			int id = Integer.parseInt(sc.nextLine());
			Libro libroModificar = proyectoDAO.devuelveUnLibro(id);
			System.out.println("Introduzca titulo:");
			String titulo = sc.nextLine();
			if (titulo != "") {
				libroModificar.setTitulo(titulo);
			}
			System.out.println("Numero de ejemplares:");
			String numEjemplares = sc.nextLine();
			if (numEjemplares != "") {
				libroModificar.setNumEjemplares(Integer.parseInt(numEjemplares));
			}
			System.out.println("Editorial:");
			String editorial = sc.nextLine();
			if (editorial != "") {
				libroModificar.setEditorial(editorial);
			}
			System.out.println("Numero de paginas:");
			String numPaginas = sc.nextLine();
			if (numPaginas != "") {
				libroModificar.setNumPaginas(Integer.parseInt(numPaginas));
			}
			System.out.println("Anio de edicion:");
			String anioEdicion = sc.nextLine();
			if (anioEdicion != "") {
				libroModificar.setAnioEdicion(Integer.parseInt(anioEdicion));
			}
			proyectoDAO.modificarLibro(libroModificar);
		} catch (Exception e) {
			System.out.println("Algun dato no es correcto");
		}
		continuar();
	}

	// ***********************AÑADIR, BORRAR, MODIFICAR SOCIOS******************************************

	private static void anadirUnSocio() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca nombre:");
		String nombre = sc.nextLine();
		System.out.println("Apellidos:");
		String apellidos = sc.nextLine();
		System.out.println("Edad:");
		int edad = Integer.parseInt(sc.nextLine());
		System.out.println("Direccion:");
		String direccion = sc.nextLine();
		System.out.println("Telefono:");
		int telefono = Integer.parseInt(sc.nextLine());
		Socio socio = new Socio(nombre, apellidos, edad, direccion, telefono);
		proyectoDAO.anadirSocio(socio);
		continuar();
	}

	private static void borrarUnSocio() {
		Scanner sc = new Scanner(System.in);
		proyectoDAO.listaSocios();
		System.out.println("Introduzca codigo:");
		int id = Integer.parseInt(sc.nextLine());
		Socio socio = proyectoDAO.devuelveUnSocio(id);
		if (socio == null) {
			System.out.println("El socio no existe");
			return;
		}
		proyectoDAO.borrarSocio(id);
		continuar();
	}

	private static void modificarSocio() {
		Scanner sc = new Scanner(System.in);
		proyectoDAO.listaSocios();
		try {
			System.out.println("Introduzca el codigo del socio que desea modificar");
			int id = Integer.parseInt(sc.nextLine());
			Socio socioModificar = proyectoDAO.devuelveUnSocio(id);
			System.out.println("Introduzca nombre:");
			String nombre = sc.nextLine();
			if (nombre != "") {
				socioModificar.setNombre(nombre);
			}
			System.out.println("Apellidos:");
			String apellidos = sc.nextLine();

			if (apellidos != "") {
				socioModificar.setApellidos(apellidos);
			}
			System.out.println("Edad:");
			String edad = sc.nextLine();
			if (edad != "") {
				socioModificar.setEdad(Integer.parseInt(edad));
			}
			System.out.println("Direccion:");
			String direccion = sc.nextLine();
			if (direccion != "") {
				socioModificar.setDireccion(direccion);
			}
			System.out.println("Teléfono:");
			String telefono = sc.nextLine();
			if (telefono != "") {
				socioModificar.setTelefono(Integer.parseInt(telefono));
			}
			proyectoDAO.modificarSocio(socioModificar);
		} catch (Exception e) {
			System.out.println("Algun dato no es correcto");
		}
		continuar();
	}

	// ************************CONSULTAS****************************

	public static void consultaNombre() {
		proyectoDAO.listaSocios();
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca nombre:");
		proyectoDAO.consultarSociosPorNombre(sc.nextLine());
		continuar();
	}

	public static void consultaApellido() {
		proyectoDAO.listaSocios();
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca apellido:");
		proyectoDAO.consultarSocioPorApellido(sc.nextLine());
		continuar();
	}

	public static void consultaTitulo() {
		proyectoDAO.listaLibros();
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca titulo:");
		proyectoDAO.consultarLibrosPorTituloOrdenados(sc.nextLine());
		continuar();
	}

	public static void consultaEditorial() {
		proyectoDAO.listaLibros();
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca editorial:");
		proyectoDAO.consultarLibrosPorEditorial(sc.nextLine());
		continuar();
	}

	// ***************DAR DE ALTA PRESTAMO******************************

	public static void prestamo() {
		Scanner sc = new Scanner(System.in);

		proyectoDAO.listaSocios();
		System.out.println("Introduzca codigo del socio:");
		int idSocio = Integer.parseInt(sc.nextLine());
		Socio socio = proyectoDAO.devuelveUnSocio(idSocio);
		if (socio == null) {
			System.out.println("Ese socio no existe");
			return;
		}

		proyectoDAO.listaLibros();
		System.out.println("Introduzca codigo del libro:");
		int idLibro = Integer.parseInt(sc.nextLine());
		Libro libro = proyectoDAO.devuelveUnLibro(idLibro);
		if (libro == null) {
			System.out.println("Ese libro no existe");
			return;
		}

		Date fechainicio = Calendar.getInstance().getTime();
		Date fechafin = Calendar.getInstance().getTime();
		fechafin.setDate(fechainicio.getDate() + 10);

		Prestamo p = new Prestamo(libro, socio, fechainicio, fechafin);
		proyectoDAO.altaPrestamo(p);
		continuar();
	}

	// ***********************OTRAS CONSULTAS****************************

	private static void librosPorSocio() {
		proyectoDAO.listaSocios();// imprime lista socios sin criteria
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca el codigo de socio");
		Socio socio = proyectoDAO.devuelveUnSocio(sc.nextInt()); // te devuelve un socio con criteria
		if (socio != null) {
			System.out.println("Numero de libros prestado a " + socio.getNombre() + "= " + socio.getPrestamos().size());
		} else {
			System.out.println("El socio no existe");
		}
		continuar();
	}

	// **************METODOS ADICIONALES************
	private static void continuar() {
		System.out.println("[Pulse enter para continuar]");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}

	private static int safeScanner() {
		Scanner sc = new Scanner(System.in);
		int i = -1;
		while (i == -1) {
			try {
				i = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.println("Debe introducir un numero");
			}
		}
		return i;
	}

}
