package com.docentes.api.infraestructura.adaptadores.salida.persistencia;

import com.docentes.api.dominio.modelo.Usuario;
import com.docentes.api.dominio.puertos.salida.UsuarioRepositorioPort;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

/**
 * ADAPTADOR DE SALIDA: implementa el puerto usando JPA + base de datos.
 * Convierte entre el modelo del dominio (Usuario) y la tabla (UsuarioEntity).
 */
@Component
public class UsuarioRepositorioAdapter implements UsuarioRepositorioPort {

    private final UsuarioJpaRepository jpa;

    public UsuarioRepositorioAdapter(UsuarioJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return aDominio(jpa.save(aEntidad(usuario)));
    }

    @Override
    public List<Usuario> listar() {
        return jpa.findAll().stream().map(this::aDominio).toList();
    }

    @Override
    public Optional<Usuario> buscarPorUsername(String username) {
        return jpa.findById(username).map(this::aDominio);
    }

    @Override
    public boolean existe(String username) {
        return jpa.existsById(username);
    }

    @Override
    public void eliminar(String username) {
        jpa.deleteById(username);
    }

    @Override
    public List<Usuario> buscarPorNombre(String texto) {
        return jpa.findByNombreContainingIgnoreCase(texto).stream().map(this::aDominio).toList();
    }

    @Override
    public List<Usuario> buscarPorEmail(String texto) {
        return jpa.findByEmailContainingIgnoreCase(texto).stream().map(this::aDominio).toList();
    }

    private UsuarioEntity aEntidad(Usuario u) {
        UsuarioEntity e = new UsuarioEntity();
        e.setUsername(u.getUsername());
        e.setPassword(u.getPassword());
        e.setNombre(u.getNombre());
        e.setEmail(u.getEmail());
        return e;
    }

    private Usuario aDominio(UsuarioEntity e) {
        return new Usuario(e.getUsername(), e.getPassword(), e.getNombre(), e.getEmail());
    }
}
