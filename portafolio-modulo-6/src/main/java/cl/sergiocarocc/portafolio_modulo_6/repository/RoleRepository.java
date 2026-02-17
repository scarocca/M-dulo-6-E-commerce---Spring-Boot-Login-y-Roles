package cl.sergiocarocc.portafolio_modulo_6.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.sergiocarocc.portafolio_modulo_6.entity.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNombre(String nombre);
}
