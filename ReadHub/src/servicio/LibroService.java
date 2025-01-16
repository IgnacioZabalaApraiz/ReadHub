package servicio;

import modeloHibernate.Libro;
import java.util.List;

public interface LibroService {
    List<Libro> getAllLibros();
    void saveLibro(Libro libro);
    void updateLibroDisponibilidad(Libro libro);
    void deleteLibro(Libro libro);
    Libro getLibroByTitulo(String titulo);
}

