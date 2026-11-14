package com.docentes.api.infraestructura.adaptadores.salida.persistencia;

import com.docentes.api.dominio.modelo.Docente;
import com.docentes.api.dominio.puertos.salida.DocenteRepositorioPort;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

/**
 * ADAPTADOR DE SALIDA: implementa el puerto de docentes usando JPA.
 */
@Component
public class DocenteRepositorioAdapter implements DocenteRepositorioPort {

    private final DocenteJpaRepository jpa;

    public DocenteRepositorioAdapter(DocenteJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Docente guardar(Docente docente) {
        return aDominio(jpa.save(aEntidad(docente)));
    }

    @Override
    public List<Docente> listar() {
        return jpa.findAll().stream().map(this::aDominio).toList();
    }

    @Override
    public Optional<Docente> buscarPorId(Long id) {
        return jpa.findById(id).map(this::aDominio);
    }

    @Override
    public boolean existe(Long id) {
        return jpa.existsById(id);
    }

    @Override
    public void eliminar(Long id) {
        jpa.deleteById(id);
    }

    @Override
    public List<Docente> buscarPorArea(String area) {
        return jpa.findByAreaTrabajoContainingIgnoreCase(area).stream().map(this::aDominio).toList();
    }

    @Override
    public List<Docente> buscarPorExperienciaMinima(int anios) {
        return jpa.findByAniosExperienciaGreaterThanEqual(anios).stream().map(this::aDominio).toList();
    }

    private DocenteEntity aEntidad(Docente d) {
        DocenteEntity e = new DocenteEntity();
        e.setId(d.getId());
        e.setNombre(d.getNombre());
        e.setApellidos(d.getApellidos());
        e.setEmail(d.getEmail());
        e.setTelefono(d.getTelefono());
        e.setBlog(d.getBlog());
        e.setProfesional(d.getProfesional());
        e.setEscalaron(d.getEscalaron());
        e.setIdioma(d.getIdioma());
        e.setAniosExperiencia(d.getAniosExperiencia());
        e.setAreaTrabajo(d.getAreaTrabajo());
        return e;
    }

    private Docente aDominio(DocenteEntity e) {
        Docente d = new Docente();
        d.setId(e.getId());
        d.setNombre(e.getNombre());
        d.setApellidos(e.getApellidos());
        d.setEmail(e.getEmail());
        d.setTelefono(e.getTelefono());
        d.setBlog(e.getBlog());
        d.setProfesional(e.getProfesional());
        d.setEscalaron(e.getEscalaron());
        d.setIdioma(e.getIdioma());
        d.setAniosExperiencia(e.getAniosExperiencia());
        d.setAreaTrabajo(e.getAreaTrabajo());
        return d;
    }
}
