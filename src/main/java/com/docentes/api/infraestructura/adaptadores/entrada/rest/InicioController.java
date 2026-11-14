package com.docentes.api.infraestructura.adaptadores.entrada.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** Página de inicio: sirve para comprobar que la API está viva. */
@RestController
public class InicioController {

    @GetMapping("/")
    public Mensaje inicio() {
        return new Mensaje("API Gestión Docentes funcionando correctamente");
    }
}
