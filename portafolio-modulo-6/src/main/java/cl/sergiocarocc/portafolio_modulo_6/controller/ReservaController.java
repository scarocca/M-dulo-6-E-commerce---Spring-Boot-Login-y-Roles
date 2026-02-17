package cl.sergiocarocc.portafolio_modulo_6.controller;


import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.sergiocarocc.portafolio_modulo_6.entity.ItemCarrito;
import cl.sergiocarocc.portafolio_modulo_6.entity.Plan;
import cl.sergiocarocc.portafolio_modulo_6.entity.Reserva;
import cl.sergiocarocc.portafolio_modulo_6.service.PlanService;
import cl.sergiocarocc.portafolio_modulo_6.service.ReservaService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

 
    private final ReservaService reservaService;
    private final PlanService planService;
    
    
    public ReservaController(ReservaService reservaService, PlanService planService) {
		super();
		this.reservaService = reservaService;
		this.planService = planService;
	}

	// 1. Mostrar el formulario de reserva para un plan específico
    @GetMapping("/nuevo/{planId}")
    public String mostrarFormularioReserva(@PathVariable Long planId, Model model, RedirectAttributes flash) {
        Plan planEncontrado = planService.buscarPorId(planId);
        
        // Si el ID no existe en la BD, evitamos el crash
        if (planEncontrado == null) {
            flash.addFlashAttribute("mensajeError", "El plan seleccionado no existe.");
            return "redirect:/productos";
        }

        Reserva reserva = new Reserva();
        reserva.setPlan(planEncontrado); // Vinculamos el plan a la reserva
        
        // PASO VITAL: Enviamos ambos objetos con nombres claros
        model.addAttribute("plan", planEncontrado); 
        model.addAttribute("reserva", reserva);
        
        return "public/reserva-form";
    }

    @PostMapping("/guardar")
    public String guardarReserva(@ModelAttribute Reserva reserva, 
                                @RequestParam(value = "planId", required = false) Long planId, // Cambiado a required = false
                                @RequestParam("fechaTexto") String fechaTexto, 
                                Model model,
                                RedirectAttributes flash) {
        try {
            // Si el planId es null, significa que venimos del flujo de carrito
            if (planId == null) {
                // Podrías redirigir al método de confirmar-todo si algo salió mal
                return "redirect:/reservas/confirmar-todo"; 
            }

            Plan plan = planService.buscarPorId(planId);
            reserva.setPlan(plan);
            reserva.setFechaCita(LocalDateTime.parse(fechaTexto));
            
            reservaService.crearReserva(reserva);
            
            model.addAttribute("reserva", reserva);
            model.addAttribute("nombreCliente", reserva.getNombreCliente());
            
            return "public/reserva-exito";
            
        } catch (Exception e) {
            flash.addFlashAttribute("mensajeError", e.getMessage());
            return "redirect:/productos";
        }
    }
    @PostMapping("/confirmar-todo")
    public String confirmarTodo(HttpSession session, 
                                @ModelAttribute Reserva datosCliente,
                                @RequestParam("fechaTexto") String fechaTexto, 
                                Model model,
                                RedirectAttributes flash) {
        List<ItemCarrito> carrito = (List<ItemCarrito>) session.getAttribute("carrito");
        
        try {
            LocalDateTime fecha = LocalDateTime.parse(fechaTexto);
            
            // El servicio ahora nos devolverá la lista de reservas creadas
            List<Reserva> reservasRealizadas = reservaService.guardarReservaMultiple(carrito, fecha, datosCliente);
            
            session.removeAttribute("carrito");
            
            // Pasamos las reservas al modelo para la página de éxito
            model.addAttribute("reservas", reservasRealizadas);
            model.addAttribute("nombreCliente", datosCliente.getNombreCliente());
            
            return "public/reserva-exito"; // Nueva página
            
        } catch (Exception e) {
            flash.addFlashAttribute("mensajeError", e.getMessage());
            return "redirect:/carrito/checkout";
        }
    }
}