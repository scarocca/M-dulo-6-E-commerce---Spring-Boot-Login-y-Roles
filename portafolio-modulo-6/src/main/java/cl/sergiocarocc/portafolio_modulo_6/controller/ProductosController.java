package cl.sergiocarocc.portafolio_modulo_6.controller;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cl.sergiocarocc.portafolio_modulo_6.entity.Plan;

import cl.sergiocarocc.portafolio_modulo_6.service.PlanService;


@Controller
public class ProductosController {
	
	
	private final PlanService ps;
	
	
	public ProductosController(PlanService ps) {
		super();
		this.ps = ps;
	}


	@GetMapping("/productos")
    public String verPaginaDeProductos(Model model) {
        // 1. Obtenemos los datos del servicio
        List<Plan> listaPlanes = ps.listarTodos();
        
        // 2. Pasamos la lista al HTML usando el "model"
        // El primer parámetro "planes" es el nombre que usaremos en Thymeleaf
        model.addAttribute("listadoDePlanes", listaPlanes);
        
        // 3. Devolvemos el nombre del archivo HTML (sin el .html)
        return "public/productos"; 
    }

}
