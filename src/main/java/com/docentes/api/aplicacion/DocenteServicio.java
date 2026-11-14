package com.docentes.api.aplicacion;

import com.docentes.api.dominio.excepciones.DatosInvalidosException;
import com.docentes.api.dominio.excepciones.NoEncontradoException;
import com.docentes.api.dominio.modelo.Docente;
import com.docentes.api.dominio.puertos.entrada.GestionDocentesUseCase;
import com.docentes.api.dominio.puertos.salida.DocenteRepositorioPort;
import java.util.List;

/**
 * APLICACIÓN: implementa los casos de uso de docentes.
 */
public class DocenteServicio implements GestionDocentesUseCase {

    private final DocenteRepositorioPort repositorio;

    public DocenteServicio(DocenteRepositorioPort repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Docente crear(Docente docente) {
        if (!docente.esValido()) {
            throw new DatosInvalidosException("Nombre y apellidos son obligatorios y la experiencia no puede ser negativa");
        }
        docente.setId(null); // el id lo genera la base de datos
        return repositorio.guardar(docente);
    }

    @Override
    public List<Docente> listar() {
        return repositorio.listar();
    }

    @Override
    public Docente buscar(Long id) {
        return repositorio.buscarPorId(id)
                .orElseThrow(() -> new NoEncontradoException("Docente no encontrado: " + id));
    }

    @Override
    public Docente actualizar(Long id, Docente datos) {
        buscar(id); // si no existe, lanza NoEncontradoException
        datos.setId(id);
        if (!datos.esValido()) {
            throw new DatosInvalidosException("Nombre y apellidos son obligatorios y la experiencia no puede ser negativa");
        }
        return repositorio.guardar(datos);
    }

    @Override
    public void eliminar(Long id) {
        if (!repositorio.existe(id)) {
            throw new NoEncontradoException("Docente no encontrado: " + id);
        }
        repositorio.eliminar(id);
    }

    @Override
    public List<Docente> reportePorArea(String area) {
        return repositorio.buscarPorArea(area == null ? "" : area);
    }

    @Override
    public List<Docente> reportePorExperienciaMinima(int anios) {
        return repositorio.buscarPorExperienciaMinima(anios);
    }
}
