package com.docentes.api.dominio.puertos.salida;

import com.docentes.api.dominio.modelo.Usuario;
import java.util.List;
import java.util.Optional;

/**
 * PUERTO DE SALIDA: lo que el dominio NECESITA para guardar usuarios.
 */
public interface UsuarioRepositorioPort {
    Usuario guardar(Usuario usuario);
    List<Usuario> listar();
    Optional<Usuario> buscarPorUsername(String username);
    boolean existe(String username);
    void eliminar(String username);
    List<Usuario> buscarPorNombre(String texto);
    List<Usuario> buscarPorEmail(String texto);
}
