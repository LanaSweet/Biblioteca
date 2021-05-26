package Servicio;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import Modelo.Libro;
import Repositorio.LibroRepositorio;

@Service
@EnableJpaRepositories(basePackages = { "Repositorio" })
public class ServicioLibro {

	@Autowired
	private LibroRepositorio libroRepositorio;
	
	//	listar todos los Libros
	public List<Libro> findAll(){
		List<Libro> ListaLibros = new ArrayList<Libro>();
		for (Libro iniprest : libroRepositorio.findAll()) {
			ListaLibros.add(iniprest);
		}
	return ListaLibros;
	}

	// Grabar un Libro
	@SuppressWarnings("finally")
	public String Grabar(Libro libro) {
		String Mensaje = "";
		try {
			libroRepositorio.save(libro);
			Mensaje = "Prestamo creada correctamente";
		} catch (Exception e) {
			Mensaje = "Ha sucedido el siguiente error:  " + e.getMessage();
		} finally {
			return Mensaje;
		}
	}
}
