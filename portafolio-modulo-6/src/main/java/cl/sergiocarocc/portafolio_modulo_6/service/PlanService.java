package cl.sergiocarocc.portafolio_modulo_6.service;



import java.util.List;


import org.springframework.stereotype.Service;

import cl.sergiocarocc.portafolio_modulo_6.entity.Plan;
import cl.sergiocarocc.portafolio_modulo_6.repository.PlanRepository;


@Service
public class PlanService {
	private final PlanRepository planRepository;
    private final FileService fileService;
    
    public PlanService(PlanRepository planRepository, FileService fileService) {
        this.planRepository = planRepository;
        this.fileService = fileService;
    }
    
    public List<Plan> listarTodos(){
    	return planRepository.findAll();
    	
    }

    public Plan buscarPorId(Long id) {
        return planRepository.findById(id).orElse(null);
    }
    
    public void guardar(Plan plan) {
        planRepository.save(plan);
    }

    public void eliminar(Long id) {
        Plan plan = buscarPorId(id);
        
        if (plan != null) {
            // Delegamos la responsabilidad de borrar la imagen al experto (FileService)
            fileService.eliminarArchivo(plan.getImagenUrl());
            
            // Borramos de la base de datos
            planRepository.deleteById(id);
        }
    }
    
    public void ocultarPlan(Long id) {
        Plan plan = buscarPorId(id);
        if (plan != null) {
            plan.setActivo(false); // Lo "apagamos"
            planRepository.save(plan);
        }
    }
    public void activarPlan(Long id) {
        Plan plan = buscarPorId(id);
        if (plan != null) {
            plan.setActivo(true); // Cambiamos a true
            planRepository.save(plan);
        }
    }
    public List<Plan> listarPlanesActivos() {
        return planRepository.findAll().stream()
                             .filter(p -> p.isActivo())
                             .toList();
    }
    
    
}
