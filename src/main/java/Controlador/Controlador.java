package Controlador;

//import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import Modelo.Lector;
import Modelo.Libro;
import Modelo.Prestamo;

import Servicio.PrestamoServicio;
import Servicio.ServicioLector;
import Servicio.ServicioLibro;

@Controller 
public class Controlador implements ErrorViewResolver {

	@Autowired 
	private ServicioLector servicioLector; 

	@Autowired 
	private ServicioLibro servicioLibro;

	@Autowired
	private PrestamoServicio prestamoServicio;

	@RequestMapping(path = "/error", produces = MediaType.APPLICATION_JSON_VALUE )
	@Override
    public ModelAndView resolveErrorView(HttpServletRequest request,
            HttpStatus status, Map<String, Object> model) {
			
    	ModelAndView mav = new ModelAndView("errores");
    	mav.addObject("status", status.value());
    	mav.addObject("error",status.getReasonPhrase()) ;
    	mav.addObject("message",status.toString());
        return mav;
    }

	// Redirige a la pagina Index de la aplicacion
	@RequestMapping("/")
	@ResponseBody
	public ModelAndView inicio() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

	//Seccion lectores
	@RequestMapping("/HomeLector")
	@ResponseBody
	public ModelAndView HomeClientes() {
		ModelAndView mav = new ModelAndView("HomeLector");
		return mav;
	}

	@RequestMapping("/ListarLectores")
	@ResponseBody
	public ModelAndView ListarLectores() {
	//	List<Lector> ListaLector = (List<Lector>)servicioLector.findAll();
		ModelAndView mav = new ModelAndView("VistaLector");
		mav.addObject("LstLector", servicioLector.findAll());
		return mav;
	}

	@RequestMapping(value = "/GrabarLector", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView GrabarLector() {
		Lector newLector = new Lector();
		ModelAndView mav = new ModelAndView("CrearLector");
		mav.addObject("Lector", newLector);
		return mav;
	}

	// Guarda los datos de un lector cuando vuelve de la vista CrearLector
	@RequestMapping(value = "/GrabarLector", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView GrabarLector(@Valid @ModelAttribute("Lector") Lector le01, BindingResult result) {
		String strMensaje = "";
		ModelAndView mav = new ModelAndView("Mensajes");
		if (result.hasErrors()) {
			strMensaje = "Lector no insertado - error en los datos del lector";
		} else {
			 servicioLector.Grabar(le01);
			 strMensaje ="Lector Creado Correctamente";
		}
		mav.addObject("Mensaje", strMensaje);
		return mav;
	} 

	//***** SECCION LIBROS*****  
	@RequestMapping("/HomeLibro")
	@ResponseBody
	public ModelAndView HomeLibro() {
		ModelAndView mav = new ModelAndView("HomeLibro");
		return mav;
	}

	@RequestMapping("/ListarLibros")
	@ResponseBody
	public ModelAndView ListarLibros() {
		List<Libro> ListaLibros = (List<Libro>) servicioLibro.findAll();
		ModelAndView mav = new ModelAndView("VistaLibros");
		mav.addObject("LstLibros", ListaLibros);
		return mav;
	}

	// Envia el flujo del programa hacia la vista CrearLibro
	@RequestMapping(value = "/GrabarLibro", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView GrabarLibro() {
		Libro newLibro = new Libro();
		ModelAndView mav = new ModelAndView("CrearLibro");
		mav.addObject("Libro", newLibro);
		return mav;
	}

	// Guarda los datos de un libro cuando vuelve de la vista CrearLibro
	@RequestMapping(value = "/GrabarLibro", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView GrabarLibro(@Valid @ModelAttribute("Libro") Libro lb01, BindingResult result) {
		String strMensaje = "";
		ModelAndView mav = new ModelAndView("Mensajes");
		if (result.hasErrors()) {
			strMensaje = "Libro no insertado - error en los datos del Libro";
		} else {
			strMensaje = servicioLibro.Grabar(lb01);
			strMensaje = "Libro creado correctamente";
		}
		mav.addObject("Mensaje", strMensaje);
		return mav;
	}
	
	/*******FIN SECCION LIBROS ********/	
	
	/* **** SECCION PRESTAMOS **** */
	//De aqui en adelante va lo de Prestamo
	@RequestMapping("/HomePrestamo")
	@ResponseBody
	public ModelAndView HomePrestamo() {
		ModelAndView mav = new ModelAndView("HomePrestamo");
		return mav;
	}

	/* Listar todos los prestamos */
	@RequestMapping("/ListarPrestamos")
	@ResponseBody
	public ModelAndView ListarPrestamos() {
		List<Prestamo> ListaPrestamos = (List<Prestamo>) prestamoServicio.findAll();
		ModelAndView mav = new ModelAndView("VistaPrestamos");
		mav.addObject("LstPrestamos", ListaPrestamos);
		return mav;
	}
	
	@RequestMapping(value = "/GrabarPrestamo", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView GrabarPrestamo() {
		//SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		
		Prestamo newprestamo = new Prestamo();
		newprestamo.setFecha(new Date());
		ModelAndView mav = new ModelAndView("CrearPrestamo");
		List<Lector> lectores = servicioLector.findAll(); 
		List<Libro> libros = servicioLibro.findAll();
		mav.addObject("Prestamo", newprestamo);
		mav.addObject("LstLector", lectores);
		mav.addObject("LstLibros", libros);
		return mav;
	}

	// Guarda los datos de un cliente cuando vuelve de la vista CrearPrestamo
	/*public ModelAndView GrabarPrestamo( Prestamo cl01) {*/
	@RequestMapping(value = "/GrabarPrestamo", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView GrabarPrestamo(@Valid @ModelAttribute("Prestamo") Prestamo cl01, BindingResult result) {
		
		String strMensaje = "";
		ModelAndView mav = new ModelAndView("Mensajes");
		
		//System.out.println("Valor de la fecha en el controlador: " + cl01.getFecha());
		//System.out.println("Controlador. GrabarPrestamo");
		
		strMensaje = prestamoServicio.Grabar(cl01);
		
		System.out.println("Mensaje del prestamoServicio.grabar en el controlador: " + strMensaje);
		
		mav.addObject("Mensaje", strMensaje);
		return mav;
	}
}
