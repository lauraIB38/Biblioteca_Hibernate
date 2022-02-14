package main;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import pojos.Libro;
import pojos.Prestamo;
import pojos.Socio;

public class ProyectoDAO {

	// ******************* AÑADIR, BORRAR, MODIFICAR LIBRO************************
	
	public void anadirLibro(Libro libro) {
		Transaction tx = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = sesion.beginTransaction();
			sesion.save(libro);
			tx.commit();
			System.out.println("Se ha añadido el libro correctamente");
		} catch (Exception e) {
			System.out.println("Ha habido algún problema, vuelva a intentarlo");
			tx.rollback();
		} finally {
			sesion.close();
		}
	}

	public static void borrarLibro(int id) {
		Transaction tx = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = sesion.beginTransaction();
			Query<Libro> qLibros = sesion.createQuery("delete from Libro where idLibro=:id");
			qLibros.setParameter("id", id);
			qLibros.executeUpdate();
			tx.commit();
			System.out.println("Se ha borrado el libro correctamente");
		} catch (Exception e) {
			System.out.println("Este libro esta prestado\n");
			tx.rollback();
		} finally {
			sesion.close();
		}
	}

	public static void modificarLibro(Libro libro) {
		Transaction tx = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = sesion.beginTransaction();
			sesion.update(libro);
			tx.commit();
			System.out.println("Se han modificado los datos correctamente");
		} catch (Exception e) {
			System.out.println("Ha habido algún problema, vuelva a intentarlo");
			tx.rollback();
		} finally {
			sesion.close();
		}
	}

	// ********************AÑADIR, BORRAR, MODIFICAR SOCIO*******************************
	
	public static void anadirSocio(Socio socio) {
		Transaction tx = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = sesion.beginTransaction();
			sesion.save(socio);
			tx.commit();
			System.out.println("Se ha añadido el socio correctamente");
		} catch (Exception e) {
			System.out.println("Ha habido algún problema, vuelva a intentarlo");
			tx.rollback();
		} finally {
			sesion.close();
		}
	}

	public static void borrarSocio(int id) {
		Transaction tx = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = sesion.beginTransaction();
			Query<Socio> qSocios = sesion.createQuery("delete from Socio where idSocio=:id");
			qSocios.setParameter("id", id);
			qSocios.executeUpdate();
			tx.commit();
			System.out.println("Se ha borrado el socio correctamente");
		} catch (Exception e) {
			System.out.println("Antes de borrar el socio debe devolver el libro\n");
			tx.rollback();
		} finally {
			sesion.close();
		}
	}

	public static void modificarSocio(Socio socio) {
		Transaction tx = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = sesion.beginTransaction();
			sesion.update(socio);
			tx.commit();
			System.out.println("Se han modificado los datos correctamente");
		} catch (Exception e) {
			System.out.println("Ha habido algún problema, vuelva a intentarlo");
			tx.rollback();
		} finally {
			sesion.close();
		}
	}

	// **************CONSULTAS APARTADO 3*************************************
	
	public void consultarLibrosPorTituloOrdenados(String titulobuscado) {
		Transaction tx = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = sesion.beginTransaction();
			Criteria criteria = sesion.createCriteria(Libro.class).add(Restrictions.eq("titulo", titulobuscado))
					.addOrder(Order.asc("titulo"));
			List<Libro> listalibros = criteria.list();
			listalibros.forEach((s) -> System.out.println(s));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			sesion.close();
		}
	}

	public void consultarLibrosPorEditorial(String editorialBUscada) {
		Transaction tx = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = sesion.beginTransaction();
			Criteria criteria = sesion.createCriteria(Libro.class).add(Restrictions.eq("editorial", editorialBUscada));
			List<Libro> listalibros = criteria.list();
			listalibros.forEach((s) -> System.out.println(s));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			sesion.close();
		}

	}

	public void consultarSociosPorNombre(String nombre) {
		Transaction tx = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = sesion.beginTransaction();

			Criteria criteria = sesion.createCriteria(Socio.class);
			Criterion criterion1 = Restrictions.eq("nombre", nombre);
			criteria.add(criterion1);
			List <Socio>listaSocios =  criteria.list();
			listaSocios.forEach((s) -> System.out.println(s));

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			sesion.close();
		}

	}

	public void consultarSocioPorApellido(String apellido) {
		Transaction tx = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = sesion.beginTransaction();
			Criteria criteria = sesion.createCriteria(Socio.class).add(Restrictions.eq("apellidos", apellido));
			List<Socio> listaSocios = criteria.list();
			listaSocios.forEach((s) -> System.out.println(s));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			sesion.close();
		}

	}

	// *********************AÑADIR UN PRESTAMO**************************

	public void altaPrestamo(Prestamo p) {
		Transaction tx = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = sesion.beginTransaction();
			sesion.save(p);
			tx.commit();
			System.out.println("Se ha introducido el prestamo correctamente");
		} catch (Exception e) {
			System.out.println("Ha habido algún problema, vuelva a intentarlo");
			tx.rollback();
		} finally {
			sesion.close();
		}
	}

	//***********************CONSULTAS APARTADO 5****************************************

	public static void librosPrestadosActualmente() {
		Transaction tx = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = sesion.beginTransaction();
			Criteria criteria = sesion.createCriteria(Prestamo.class);
			List prestamos = criteria.list();
			prestamos.forEach((s) -> System.out.println(s));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			sesion.close();
		}
	}



	public void librosConFechaFinSuperada() {
		Transaction tx = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = sesion.beginTransaction();
			Date hoy = Calendar.getInstance().getTime();
			Criteria criteria = sesion.createCriteria(Prestamo.class).add(Restrictions.le("fechaFin", hoy));
			List<Prestamo> prestamo = criteria.list();
			prestamo.forEach((s) -> System.out.println(s));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			sesion.close();
		}
	}

	public void sociosConLibrosFechaFinSuperada() {
		Transaction tx = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = sesion.beginTransaction();
			Date hoy = Calendar.getInstance().getTime();
			Criteria criteria = sesion.createCriteria(Prestamo.class).add(Restrictions.le("fechaFin", hoy));
			List<Prestamo> prestamo = criteria.list();
			tx.commit();
			System.out.println("Los socios que han superado la fecha de fin de prestamos son: \n");
			prestamo.forEach((p) -> System.out.println(p.getSocio().getNombre() + " " + p.getSocio().getApellidos()));
		} catch (Exception e) {
			tx.rollback();
		} finally {
			sesion.close();
		}
	}

	// ******************************METODOS ADICIONALES*********************************
	
	public void listaLibros() {
		Transaction tx = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = sesion.beginTransaction();
			Query<Libro> qLibro = sesion.createQuery("from Libro");
			List listaLibros = qLibro.list();
			listaLibros.forEach((s) -> System.out.println(s));
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			sesion.close();
		}
	}

	public void listaSocios() {
		Transaction tx = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = sesion.beginTransaction();
			Query<Socio> qSocio = sesion.createQuery("from Socio");
			List<Socio> listaSocios = qSocio.list();
			listaSocios.forEach((s) -> System.out.println(s));
		} catch (Exception e) {
			tx.rollback();
		} finally {
			sesion.close();
		}
	}

	public Socio devuelveUnSocio(int idSocio) {
		Transaction tx = null;
		Socio socio = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = sesion.beginTransaction();
			Criteria criteria = sesion.createCriteria(Socio.class).add(Restrictions.eq("idSocio", idSocio));
			socio = (Socio) criteria.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			System.out.println("No hay ningun socio con ese código");
			tx.rollback();
		} finally {
			sesion.close();
		}
		return socio;
	}

	public Libro devuelveUnLibro(int idLibro) {
		Transaction tx = null;
		Libro libro = null;
		Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			tx = sesion.beginTransaction();
			Criteria criteria = sesion.createCriteria(Libro.class).add(Restrictions.eq("idLibro", idLibro));
			libro = (Libro) criteria.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			System.out.println("No hay ningun libro con ese código");
			tx.rollback();
		} finally {
			sesion.close();
		}
		return libro;
	}

}
