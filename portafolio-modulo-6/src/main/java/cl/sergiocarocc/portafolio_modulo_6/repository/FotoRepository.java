package cl.sergiocarocc.portafolio_modulo_6.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import cl.sergiocarocc.portafolio_modulo_6.entity.Foto;

import java.util.List;

public interface FotoRepository extends JpaRepository<Foto, Long> {
    // Ordenar para que las últimas fotos subidas aparezcan primero
    List<Foto> findAllByOrderByFechaCargaDesc();
    List<Foto> findTop3ByOrderByIdDesc();
    
}
