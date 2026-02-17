package cl.sergiocarocc.portafolio_modulo_6.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.sergiocarocc.portafolio_modulo_6.entity.Consulta;

;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
	List<Consulta> findAllByOrderByFechaEnvioDesc();
}
