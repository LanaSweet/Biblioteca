package Servicio;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Modelo.Prestamo;

import Repositorio.PrestamoRepositorio;

@Service
@EnableJpaRepositories(basePackages = { "Repositorio" })
public class PrestamoServicio {
	
	@Autowired
	private PrestamoRepositorio prestamoRepositorio;
	
	@PersistenceContext
	private EntityManager em;
	
//	listar todos los prestamos
	public List<Prestamo> findAll(){
		List<Prestamo> ListaPrestamos = new ArrayList<Prestamo>();
		for (Prestamo iniprest : prestamoRepositorio.ListarPrestamos()) {
			ListaPrestamos.add(iniprest);
		}
		return ListaPrestamos;
	}
	
	// Grabar un prestamo
	@SuppressWarnings("finally")
	@Transactional
	public String Grabar(Prestamo prest) {
		String Mensaje = "";
		try {
			
//			System.out.println("dentro de prestamo.grabar en el servicio ");
//			System.out.println("Fecha: " + prest.getFecha());
//			String pattern = "dd/MM/yyyy";
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			Calendar cal =  Calendar.getInstance();
			cal.setTime( prest.getFecha());
			prest.setFecha(cal.getTime());
			
	//		System.out.println("Prestamo Servicio despues de la transformación:" + prest.getFecha());

			em.persist(prest);

			Mensaje = "Prestamo creada correctamente";
		} catch (Exception e) {
			Mensaje = "Ha sucedido el siguiente error:  " + e.getMessage();
		} finally {
			return Mensaje;
		}
	}
}
