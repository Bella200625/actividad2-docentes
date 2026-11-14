package com.docentes.api.infraestructura.adaptadores.salida.persistencia;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/** Spring Data genera el SQL a partir del nombre de cada método. */
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, String> {
    List<UsuarioEntity> findByNombreContainingIgnoreCase(String texto);
    List<UsuarioEntity> findByEmailContainingIgnoreCase(String texto);
}
