package com.docentes.api.infraestructura.config;

import com.docentes.api.aplicacion.DocenteServicio;
import com.docentes.api.aplicacion.UsuarioServicio;
import com.docentes.api.dominio.puertos.entrada.GestionDocentesUseCase;
import com.docentes.api.dominio.puertos.entrada.GestionUsuariosUseCase;
import com.docentes.api.dominio.puertos.salida.DocenteRepositorioPort;
import com.docentes.api.dominio.puertos.salida.UsuarioRepositorioPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CONFIGURACIÓN: aquí se "conectan las piezas".
 * Spring le entrega a cada servicio el adaptador que implementa su puerto de salida.
 * Así el dominio y la aplicación no dependen de Spring.
 */
@Configuration
public class ConfiguracionBeans {

    @Bean
    public GestionUsuariosUseCase gestionUsuarios(UsuarioRepositorioPort repositorio) {
        return new UsuarioServicio(repositorio);
    }

    @Bean
    public GestionDocentesUseCase gestionDocentes(DocenteRepositorioPort repositorio) {
        return new DocenteServicio(repositorio);
    }
}
