package cl.sergiocarocc.portafolio_modulo_6.service;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    // Centralizamos la ruta aquí o la podrías traer de application.properties
    private final String carpetaPlanes = "src/main/resources/static/assets/img/planes/";

    public void eliminarArchivo(String nombreImagen) {
        if (nombreImagen == null || nombreImagen.isEmpty()) return;

        try {
            Path ruta = Paths.get(carpetaPlanes).toAbsolutePath().resolve(nombreImagen);
            Files.deleteIfExists(ruta);
        } catch (IOException e) {
            // Logeamos el error pero no detenemos la aplicación
            System.err.println("Error al borrar archivo: " + e.getMessage());
        }
    }
}
