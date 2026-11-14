package com.docentes.api.infraestructura.adaptadores.salida.persistencia;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/** Spring Data genera el SQL a partir del nombre de cada método. */
public interface DocenteJpaRepository extends JpaRepository<DocenteEntity, Long> {
    List<DocenteEntity> findByAreaTrabajoContainingIgnoreCase(String area);
    List<DocenteEntity> findByAniosExperienciaGreaterThanEqual(Integer anios);
}
