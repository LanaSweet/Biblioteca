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

//import Modelo.Prestamos;

@Entity
@Table(name="libros")
public class Libro implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	//Constructores
		public Libro() {	}
		
		public Libro(String titulo, String autor, String genero) {
			this.titulo = titulo;
			this.autor = autor;
			this.genero = genero;		
		}
	
	@Id()
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="libro")
	private List<Prestamo> Prestamos=new ArrayList<Prestamo>();
	
	@Column(name="Titulo")
	private String titulo;
	
	@Column(name="Autor")
	private String autor; 
	
	@Column(name="Genero")
	private String genero;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
