package com.docentes.api.dominio.puertos.entrada;

import com.docentes.api.dominio.modelo.Usuario;
import java.util.List;

/**
 * PUERTO DE ENTRADA: dice QUÉ puede pedirle el mundo exterior al sistema sobre usuarios.
 */
public interface GestionUsuariosUseCase {
    Usuario crear(Usuario usuario);
    List<Usuario> listar();
    Usuario buscar(String username);
    Usuario actualizar(String username, Usuario datos);
    void eliminar(String username);

    Usuario login(String username, String password);
    Usuario recordarClave(String username);

    // Reportes parametrizados
    List<Usuario> reportePorNombre(String texto);
    List<Usuario> reportePorDominioEmail(String dominio);
}
