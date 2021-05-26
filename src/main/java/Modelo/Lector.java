package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "lectores")
public class Lector implements Serializable {

	private static final long serialVersionUID = 1L;

	// Constructores
	public Lector() {
	}

	public Lector(String nombre, String apellidos, String dni, String telefono,
			String direccion) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	
	@Id()
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "lector")
	private List<Prestamo> Prestamos = new ArrayList<Prestamo>();

	@Column(name = "Nombre")
	private String nombre;

	@Column(name = "Apellidos")
	private String apellidos;

	@Column(name = "DNI")
	private String dni;

	@Column(name = "Telefono")
	private String telefono;

	@Column(name = "Direccion")
	private String direccion;



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Prestamo> getPrestamos() {
		return Prestamos;
	}

	public void setPrestamos(List<Prestamo> prestamos) {
		Prestamos = prestamos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Lector [nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", telefono=" + telefono
				+ ", direccion=" + direccion + "]";
	}

		
	
	
}
