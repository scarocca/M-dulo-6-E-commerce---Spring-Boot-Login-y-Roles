package cl.sergiocarocc.portafolio_modulo_6.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import cl.sergiocarocc.portafolio_modulo_6.entity.Usuario;
import cl.sergiocarocc.portafolio_modulo_6.service.UsuarioService;

@Controller
public class RegistroController {

   
    private final UsuarioService usuarioService;
    
    

    public RegistroController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	// Muestra el formulario de registro
    @GetMapping("/registro")
    public String mostrarFormulario(Model model) {
        // Pasamos un objeto Usuario vacío para que Thymeleaf lo llene
        model.addAttribute("usuario", new Usuario());
        return "public/registro";
    }

    // Procesa los datos enviados desde el HTML
    @PostMapping("/registro")
    public String registrarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        try {
            usuarioService.registrarUsuarioPublico(usuario);
            // Si todo sale bien, lo mandamos al login con un mensaje de éxito
            return "redirect:/login?exito";
        } catch (Exception e) {
            // Si hay error (ej: usuario duplicado), volvemos al formulario
            return "redirect:/registro?error";
        }
    }
}