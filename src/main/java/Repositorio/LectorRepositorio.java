package Repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Modelo.Lector;

@Repository
public interface LectorRepositorio extends CrudRepository <Lector, Long> {
	
public final static String FIND_BY_Nombre = "SELECT p FROM Lector p WHERE p.nombre = :varNombre";
	
	@Query(FIND_BY_Nombre)
	public List<Lector> findByNombre(@Param("varNombre") String strNombre);
		
	public final static String FIND_BY_Apellido = "SELECT p FROM Lector p WHERE p.apellidos = :varApellidos";
	
	@Query(FIND_BY_Apellido)
	public List<Lector> FIND_BY_Apellido(@Param("varApellidos") String strApellidos);

}
