package org.example.Modelo;


public abstract class Persona {
    private int id;
    private String nombre;
    private String identificacion;
    private String email;
    private String estado;

    public Persona(int id, String nombre, String identificacion, String email, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.email = email;
        this.estado = estado;
    }

    // Getters y Setters comunes
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}