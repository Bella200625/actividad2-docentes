package com.docentes.api.dominio.puertos.entrada;

import com.docentes.api.dominio.modelo.Docente;
import java.util.List;

/**
 * PUERTO DE ENTRADA: dice QUÉ se puede hacer con los docentes.
 */
public interface GestionDocentesUseCase {
    Docente crear(Docente docente);
    List<Docente> listar();
    Docente buscar(Long id);
    Docente actualizar(Long id, Docente datos);
    void eliminar(Long id);

    // Reportes parametrizados
    List<Docente> reportePorArea(String area);
    List<Docente> reportePorExperienciaMinima(int anios);
}
