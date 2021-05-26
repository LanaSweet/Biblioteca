package Repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import Modelo.Libro;

@Repository
public interface LibroRepositorio extends CrudRepository <Libro, Long>{
	 
	public final static String FIND_BY_Nombre = "SELECT p FROM Libro p WHERE p.titulo = :varNombre";
	   
	@Query(FIND_BY_Nombre)
	public List<Libro> findByNombre(@Param("varNombre") String strNombre);
	 
	
}
