package com.docentes.api.dominio.modelo;

/**
 * DOMINIO: representa un docente.
 */
public class Docente {

    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String telefono;
    private String blog;
    private String profesional;
    private String escalaron;
    private String idioma;
    private Integer aniosExperiencia;
    private String areaTrabajo;

    public Docente() {
    }

    /** Regla de negocio: un docente necesita nombre y apellidos, y la experiencia no puede ser negativa. */
    public boolean esValido() {
        return nombre != null && !nombre.isBlank()
                && apellidos != null && !apellidos.isBlank()
                && (aniosExperiencia == null || aniosExperiencia >= 0);
    }

    public Long getId() { return id; }
    
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getApellidos() { return apellidos; }
    
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    
    public String getEmail() { return email; }
    
    public void setEmail(String email) { this.email = email; }
    
    public String getTelefono() { return telefono; }
    
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public String getBlog() { return blog; }
    
    public void setBlog(String blog) { this.blog = blog; }
    
    public String getProfesional() { return profesional; }
    
    public void setProfesional(String profesional) { this.profesional = profesional; }
    
    public String getEscalaron() { return escalaron; }
    
    public void setEscalaron(String escalaron) { this.escalaron = escalaron; }
    
    public String getIdioma() { return idioma; }
    
    public void setIdioma(String idioma) { this.idioma = idioma; }
    
    public Integer getAniosExperiencia() { return aniosExperiencia; }
    
    public void setAniosExperiencia(Integer aniosExperiencia) { this.aniosExperiencia = aniosExperiencia; }
    
    public String getAreaTrabajo() { return areaTrabajo; }
    
    public void setAreaTrabajo(String areaTrabajo) { this.areaTrabajo = areaTrabajo; }
}
