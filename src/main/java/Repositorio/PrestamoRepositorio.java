package Repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Modelo.Prestamo;

@Repository
public interface PrestamoRepositorio extends CrudRepository<Prestamo, Long> {

	public final static String Listar_Prestamos = "SELECT p FROM Prestamo p ";

	@Query(Listar_Prestamos)
	public List<Prestamo> ListarPrestamos();

}
