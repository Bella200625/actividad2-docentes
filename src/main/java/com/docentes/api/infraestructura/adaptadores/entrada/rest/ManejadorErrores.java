package com.docentes.api.infraestructura.adaptadores.entrada.rest;

import com.docentes.api.dominio.excepciones.CredencialesInvalidasException;
import com.docentes.api.dominio.excepciones.DatosInvalidosException;
import com.docentes.api.dominio.excepciones.NoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Convierte los errores del dominio en respuestas HTTP con un mensaje claro.
 */
@RestControllerAdvice
public class ManejadorErrores {

    @ExceptionHandler(NoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mensaje noEncontrado(NoEncontradoException e) {
        return new Mensaje(e.getMessage());
    }

    @ExceptionHandler(DatosInvalidosException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mensaje datosInvalidos(DatosInvalidosException e) {
        return new Mensaje(e.getMessage());
    }

    @ExceptionHandler(CredencialesInvalidasException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Mensaje credenciales(CredencialesInvalidasException e) {
        return new Mensaje(e.getMessage());
    }
}
