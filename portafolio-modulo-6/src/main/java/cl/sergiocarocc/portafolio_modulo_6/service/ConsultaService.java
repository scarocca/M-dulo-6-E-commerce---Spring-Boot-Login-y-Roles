package cl.sergiocarocc.portafolio_modulo_6.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.sergiocarocc.portafolio_modulo_6.entity.Consulta;
import cl.sergiocarocc.portafolio_modulo_6.repository.ConsultaRepository;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    // Usamos constructor para la inyección (más recomendado que @Autowired solo)
    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    public void guardar(Consulta consulta) {
    	consulta.setFechaEnvio(LocalDateTime.now());
        consultaRepository.save(consulta);
    }

    public void eliminar(Long id) {
        consultaRepository.deleteById(id);
    }
    
    public long contarTodas() {
        return consultaRepository.count();
    }
}
