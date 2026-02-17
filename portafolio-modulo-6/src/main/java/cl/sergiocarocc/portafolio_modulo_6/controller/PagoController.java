package cl.sergiocarocc.portafolio_modulo_6.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.sergiocarocc.portafolio_modulo_6.entity.ItemCarrito;
import cl.sergiocarocc.portafolio_modulo_6.entity.Reserva;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/pago")
public class PagoController {

    // Paso 1: Recibe los datos del formulario de reserva y muestra la tarjeta
    @PostMapping("/procesar")
    public String mostrarPasarela(@ModelAttribute Reserva reserva, 
                                 @RequestParam("planId") Long planId,
                                 @RequestParam("fechaTexto") String fechaTexto,
                                 Model model) {
        model.addAttribute("reserva", reserva); // Cambié tempReserva a reserva para consistencia
        model.addAttribute("planId", planId);
        model.addAttribute("fechaTexto", fechaTexto);
        return "public/pasarela-simulada";
    }

    // Paso 2: Recibe los datos de la tarjeta y muestra el spinner de carga
    @PostMapping("/confirmar-final")
    public String procesarEspera(@ModelAttribute Reserva reserva, 
                                @RequestParam("planId") Long planId,
                                @RequestParam("fechaTexto") String fechaTexto,
                                Model model) {
        // Pasamos los datos que el formulario invisible en 'pago-procesando.html' necesitará
        model.addAttribute("reserva", reserva);
        model.addAttribute("planId", planId);
        model.addAttribute("fechaTexto", fechaTexto);
        
        return "public/pago-procesando"; 
    }
    @PostMapping("/procesar-carrito")
    public String mostrarPasarelaCarrito(@ModelAttribute Reserva reserva, 
                                        @RequestParam("fechaTexto") String fechaTexto,
                                        HttpSession session,
                                        Model model) {
        
        // Recuperamos el carrito para mostrar el total en la pasarela si quisiéramos
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        
        model.addAttribute("reserva", reserva);
        model.addAttribute("fechaTexto", fechaTexto);
        model.addAttribute("esCarrito", true); // Bandera para saber que son varios items
        
        return "public/pasarela-simulada";
    }
    @PostMapping("/confirmar-final-carrito")
    public String procesarEsperaCarrito(@ModelAttribute Reserva reserva, 
                                       @RequestParam("fechaTexto") String fechaTexto,
                                       Model model) {
        
        // Pasamos los datos al spinner
        model.addAttribute("reserva", reserva);
        model.addAttribute("fechaTexto", fechaTexto);
        model.addAttribute("esCarrito", true); // Indicamos que es una reserva múltiple
        
        return "public/pago-procesando"; 
    }
}