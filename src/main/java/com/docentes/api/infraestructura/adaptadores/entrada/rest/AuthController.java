package com.docentes.api.infraestructura.adaptadores.entrada.rest;

import com.docentes.api.dominio.modelo.Usuario;
import com.docentes.api.dominio.puertos.entrada.GestionUsuariosUseCase;
import org.springframework.web.bind.annotation.*;

/**
 * ADAPTADOR DE ENTRADA: login y recordatorio de clave.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    public record LoginRequest(String username, String password) {}
    public record RecordarRequest(String username) {}
    public record LoginResponse(String mensaje, String username, String nombre) {}
    public record RecordarResponse(String username, String nombre, String email, String password) {}

    private final GestionUsuariosUseCase usuarios;

    public AuthController(GestionUsuariosUseCase usuarios) {
        this.usuarios = usuarios;
    }

    // Responde 200 si el usuario y la clave son correctos, 401 si no.
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        Usuario u = usuarios.login(req.username(), req.password());
        return new LoginResponse("Bienvenido", u.getUsername(), u.getNombre());
    }

    // Devuelve el email y la clave del usuario para que la app arme el correo de recordatorio.
    @PostMapping("/recordar")
    public RecordarResponse recordar(@RequestBody RecordarRequest req) {
        Usuario u = usuarios.recordarClave(req.username());
        return new RecordarResponse(u.getUsername(), u.getNombre(), u.getEmail(), u.getPassword());
    }
}
