package Servicio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import Modelo.Lector;
import Repositorio.LectorRepositorio;

@Service
@EnableJpaRepositories(basePackages = { "Repositorio" })
public class ServicioLector {
	
	@Autowired
	private LectorRepositorio lectorRepositorio;
	
	@PersistenceContext
	private EntityManager em;
	
	//	listar todos los lectores
	public List<Lector> findAll(){
		List<Lector> ListaLectores = new ArrayList<Lector>();
		for (Lector iniprest : lectorRepositorio.findAll()) {
			ListaLectores.add(iniprest);
		}
	return ListaLectores;
	}

	// Grabar un cliente
	@SuppressWarnings("finally")
	public String Grabar(Lector lector) {
		String Mensaje = "";
		try {
			lectorRepositorio.save(lector);
			Mensaje = "Prestamo creada correctamente";
		} catch (Exception e) {
			Mensaje = "Ha sucedido el siguiente error:  " + e.getMessage();
		} finally {
			return Mensaje;
		}
	}
}
