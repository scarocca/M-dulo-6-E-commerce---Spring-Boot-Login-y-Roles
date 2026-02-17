package cl.sergiocarocc.portafolio_modulo_6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.sergiocarocc.portafolio_modulo_6.entity.Plan;



@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    // Aquí podrías crear métodos personalizados, por ejemplo:
    // List<Plan> findByNombreContaining(String nombre);
}
