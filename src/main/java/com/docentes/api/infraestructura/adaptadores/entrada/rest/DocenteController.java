package com.docentes.api.infraestructura.adaptadores.entrada.rest;

import com.docentes.api.dominio.modelo.Docente;
import com.docentes.api.dominio.puertos.entrada.GestionDocentesUseCase;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * ADAPTADOR DE ENTRADA: endpoints REST de docentes.
 */
@RestController
@RequestMapping("/api/docentes")
public class DocenteController {

    private final GestionDocentesUseCase docentes;

    public DocenteController(GestionDocentesUseCase docentes) {
        this.docentes = docentes;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Docente crear(@RequestBody Docente docente) {
        return docentes.crear(docente);
    }

    @GetMapping
    public List<Docente> listar() {
        return docentes.listar();
    }

    @GetMapping("/{id}")
    public Docente buscar(@PathVariable Long id) {
        return docentes.buscar(id);
    }

    @PutMapping("/{id}")
    public Docente actualizar(@PathVariable Long id, @RequestBody Docente datos) {
        return docentes.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    public Mensaje eliminar(@PathVariable Long id) {
        docentes.eliminar(id);
        return new Mensaje("Docente eliminado");
    }

    // Reporte 1: docentes de un área de trabajo. Ej: /api/docentes/reportes/por-area?area=sistemas
    @GetMapping("/reportes/por-area")
    public List<Docente> reportePorArea(@RequestParam(defaultValue = "") String area) {
        return docentes.reportePorArea(area);
    }

    // Reporte 2: docentes con al menos N años de experiencia. Ej: /api/docentes/reportes/por-experiencia?min=5
    @GetMapping("/reportes/por-experiencia")
    public List<Docente> reportePorExperiencia(@RequestParam(defaultValue = "0") int min) {
        return docentes.reportePorExperienciaMinima(min);
    }
}
