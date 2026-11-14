package com.docentes.api.aplicacion;

import com.docentes.api.dominio.excepciones.CredencialesInvalidasException;
import com.docentes.api.dominio.excepciones.DatosInvalidosException;
import com.docentes.api.dominio.excepciones.NoEncontradoException;
import com.docentes.api.dominio.modelo.Usuario;
import com.docentes.api.dominio.puertos.entrada.GestionUsuariosUseCase;
import com.docentes.api.dominio.puertos.salida.UsuarioRepositorioPort;
import java.util.List;

/**
 * APLICACIÓN: implementa los casos de uso de usuarios.
 */
public class UsuarioServicio implements GestionUsuariosUseCase {

    private final UsuarioRepositorioPort repositorio;

    public UsuarioServicio(UsuarioRepositorioPort repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public Usuario crear(Usuario usuario) {
        if (!usuario.esValido()) {
            throw new DatosInvalidosException("Username, password y un email válido son obligatorios");
        }
        if (repositorio.existe(usuario.getUsername())) {
            throw new DatosInvalidosException("Ya existe un usuario con username " + usuario.getUsername());
        }
        return repositorio.guardar(usuario);
    }

    @Override
    public List<Usuario> listar() {
        return repositorio.listar();
    }

    @Override
    public Usuario buscar(String username) {
        return repositorio.buscarPorUsername(username)
                .orElseThrow(() -> new NoEncontradoException("Usuario no encontrado: " + username));
    }

    @Override
    public Usuario actualizar(String username, Usuario datos) {
        Usuario existente = buscar(username);
        if (datos.getPassword() != null && !datos.getPassword().isBlank()) existente.setPassword(datos.getPassword());
        if (datos.getNombre() != null) existente.setNombre(datos.getNombre());
        if (datos.getEmail() != null && !datos.getEmail().isBlank()) existente.setEmail(datos.getEmail());
        if (!existente.esValido()) {
            throw new DatosInvalidosException("Los datos del usuario no son válidos");
        }
        return repositorio.guardar(existente);
    }

    @Override
    public void eliminar(String username) {
        if (!repositorio.existe(username)) {
            throw new NoEncontradoException("Usuario no encontrado: " + username);
        }
        repositorio.eliminar(username);
    }

    @Override
    public Usuario login(String username, String password) {
        return repositorio.buscarPorUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new CredencialesInvalidasException("Usuario o contraseña incorrectos"));
    }

    @Override
    public Usuario recordarClave(String username) {
        return buscar(username);
    }

    @Override
    public List<Usuario> reportePorNombre(String texto) {
        return repositorio.buscarPorNombre(texto == null ? "" : texto);
    }

    @Override
    public List<Usuario> reportePorDominioEmail(String dominio) {
        return repositorio.buscarPorEmail(dominio == null ? "" : dominio);
    }
}
