package Comunes;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import Modelo.Prestamo;

public abstract class PrestamoValidator implements Validator {

	public boolean supports(Prestamo arg0) {
		  return Prestamo.class.equals(arg0);
	}

	@SuppressWarnings("unlikely-arg-type")
	public void validate(Object obj, Errors e) {
		// TODO Auto-generated method stub
	    Prestamo p = (Prestamo) obj;
	    if(p.getFecha().equals("")) {
	    	e.rejectValue("Fecha", "Fecha en blanco");
	    }
	}
}
