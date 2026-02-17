package cl.sergiocarocc.portafolio_modulo_6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cl.sergiocarocc.portafolio_modulo_6.entity.Role;
import cl.sergiocarocc.portafolio_modulo_6.entity.Usuario;
import cl.sergiocarocc.portafolio_modulo_6.repository.RoleRepository;
import cl.sergiocarocc.portafolio_modulo_6.repository.UsuarioRepository;



@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void registrarUsuarioPublico(Usuario usuario) {
        // 1. Cifrar password
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // 2. Buscar el rol "ROLE_USER" en la BD
        Role userRole = roleRepo.findByNombre("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("El rol ROLE_USER no existe en la BD"));

        // 3. Asignar el rol y guardar
        usuario.añadirRol(userRole);
        usuarioRepo.save(usuario);
    }
}
