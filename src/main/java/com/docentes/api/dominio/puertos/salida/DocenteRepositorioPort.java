package com.docentes.api.dominio.puertos.salida;

import com.docentes.api.dominio.modelo.Docente;
import java.util.List;
import java.util.Optional;

/**
 * PUERTO DE SALIDA: lo que el dominio NECESITA para guardar docentes.
 */
public interface DocenteRepositorioPort {
    Docente guardar(Docente docente);
    List<Docente> listar();
    Optional<Docente> buscarPorId(Long id);
    boolean existe(Long id);
    void eliminar(Long id);
    List<Docente> buscarPorArea(String area);
    List<Docente> buscarPorExperienciaMinima(int anios);
}
