package Modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="prestamos")
public class Prestamo implements Serializable {
	private static final long serialVersionUID = 1L;

	//Atributos
	@Id()
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private int id_Libro;
	private int id_Lector;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name="Fecha")
	Date Fecha;
	
	//Constructores
	public Prestamo(){}
	public Prestamo( int idLector, int idLibro, Date fecha) {
	   	this.id_Lector = idLector;
		this.id_Libro = idLibro;
		Fecha = fecha;
	}
	
	//@ManyToOne(fetch=FetchType.LAZY)
	@ManyToOne(optional = false)
	@JoinColumn(name="id_Lector",insertable=false, updatable=false)
	private Lector lector;
	
	//@ManyToOne(fetch=FetchType.LAZY)
	@ManyToOne(optional = false)
	@JoinColumn(name="id_Libro",insertable=false, updatable=false)
	private Libro libro;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Lector getLector() {
		return lector;
	}

	public Libro getLibro() {
		return libro;
	}
	
	public void setLector(Lector lector) {
		this.lector = lector;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	
	
	
	

	public int getId_Libro() {
		return id_Libro;
	}
	public void setId_Libro(int id_Libro) {
		this.id_Libro = id_Libro;
	}
	public int getId_Lector() {
		return id_Lector;
	}
	public void setId_Lector(int id_Lector) {
		this.id_Lector = id_Lector;
	}
	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
	
}
