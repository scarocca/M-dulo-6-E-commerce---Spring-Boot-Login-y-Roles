package cl.sergiocarocc.portafolio_modulo_6.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.sergiocarocc.portafolio_modulo_6.entity.Usuario;



@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByUsername(String username);
}
