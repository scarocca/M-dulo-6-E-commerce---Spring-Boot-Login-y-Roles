package cl.sergiocarocc.portafolio_modulo_6.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cl.sergiocarocc.portafolio_modulo_6.entity.Foto;
import cl.sergiocarocc.portafolio_modulo_6.service.FotoService;

@Controller 
public class homeController {
	private final FotoService fs;
	
	
	public homeController(FotoService fs) {
		super();
		this.fs = fs;
	}


	@GetMapping({"/","/index"})
	public String index(Model model) {
	    // Supongamos que tienes un servicio para obtener fotos de la galería
	    List<Foto> fotos = fs.obtenerUltimasTres(); 
	    model.addAttribute("fotosGaleria", fotos);
	    return "public/index";
	}

}
