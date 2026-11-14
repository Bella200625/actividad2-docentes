package com.docentes.api.infraestructura.adaptadores.entrada.rest;

import com.docentes.api.dominio.modelo.Usuario;
import com.docentes.api.dominio.puertos.entrada.GestionUsuariosUseCase;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * ADAPTADOR DE ENTRADA: recibe las peticiones HTTP de la app (Kodular)
 * y llama al caso de uso. No sabe nada de la base de datos.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final GestionUsuariosUseCase usuarios;

    public UsuarioController(GestionUsuariosUseCase usuarios) {
        this.usuarios = usuarios;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarios.crear(usuario);
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarios.listar();
    }

    @GetMapping("/{username}")
    public Usuario buscar(@PathVariable String username) {
        return usuarios.buscar(username);
    }

    @PutMapping("/{username}")
    public Usuario actualizar(@PathVariable String username, @RequestBody Usuario datos) {
        return usuarios.actualizar(username, datos);
    }

    @DeleteMapping("/{username}")
    public Mensaje eliminar(@PathVariable String username) {
        usuarios.eliminar(username);
        return new Mensaje("Usuario eliminado");
    }

    // Reporte 1: usuarios cuyo nombre contiene un texto. 
    @GetMapping("/reportes/por-nombre")
    public List<Usuario> reportePorNombre(@RequestParam(defaultValue = "") String texto) {
        return usuarios.reportePorNombre(texto);
    }

    // Reporte 2: usuarios cuyo email contiene un texto
    @GetMapping("/reportes/por-email")
    public List<Usuario> reportePorEmail(@RequestParam(defaultValue = "") String dominio) {
        return usuarios.reportePorDominioEmail(dominio);
    }
}
