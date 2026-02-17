package cl.sergiocarocc.portafolio_modulo_6.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.sergiocarocc.portafolio_modulo_6.entity.ItemCarrito;
import cl.sergiocarocc.portafolio_modulo_6.entity.Reserva;
import cl.sergiocarocc.portafolio_modulo_6.repository.ReservaRepository;




@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva crearReserva(Reserva reserva) throws Exception {
        // 1. Validar disponibilidad
        boolean ocupado = reservaRepository.existeReservaEnEsaFecha(
                            reserva.getPlan().getId(), 
                            reserva.getFechaCita());
        
        if (ocupado) {
            throw new Exception("Lo sentimos, este horario ya no está disponible.");
        }
        
        // 2. Si está libre, guardamos
        return reservaRepository.save(reserva);
    }
    
    public List<Reserva> listarTodas() {
    	return reservaRepository.findAllByOrderByFechaCitaDesc();
    }
    public List<Reserva> guardarReservaMultiple(List<ItemCarrito> carrito, LocalDateTime fecha, Reserva datosCliente) throws Exception {
        List<Reserva> listaConfirmadas = new ArrayList<>();        
        // 1. Validar disponibilidad de TODOS los planes antes de guardar nada
        for (ItemCarrito item : carrito) {
            boolean ocupado = reservaRepository.existeReservaEnEsaFecha(
                                item.getPlan().getId(), 
                                fecha);
            
            if (ocupado) {
                // Si uno falla, lanzamos error y cancelamos todo el proceso
                throw new Exception("Lo sentimos, el servicio '" + item.getPlan().getNombre() + 
                                    "' ya no está disponible para esa fecha y hora.");
            }
        }

        // 2. Si llegamos aquí, todos están libres. Procedemos a guardar.
        for (ItemCarrito item : carrito) {
            Reserva nueva = new Reserva();
            nueva.setNombreCliente(datosCliente.getNombreCliente());
            nueva.setEmailCliente(datosCliente.getEmailCliente());
            nueva.setTelefonoCliente(datosCliente.getTelefonoCliente());
            nueva.setFechaCita(fecha);
            nueva.setPlan(item.getPlan());
            nueva.setEstado("CONFIRMADA");
            nueva.setCodigoSeguimiento(java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            
            listaConfirmadas.add(reservaRepository.save(nueva));
        }
        return listaConfirmadas;
        }
}