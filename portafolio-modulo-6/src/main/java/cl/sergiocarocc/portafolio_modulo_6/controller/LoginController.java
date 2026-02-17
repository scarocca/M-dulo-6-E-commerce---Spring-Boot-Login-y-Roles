package cl.sergiocarocc.portafolio_modulo_6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	 @GetMapping("/login")
	    public String login() {
	        return "public/login"; 
	    }

}
