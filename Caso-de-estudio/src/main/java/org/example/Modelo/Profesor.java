package org.example.Modelo;


public class Profesor extends Persona {
    private String departamento;

    public Profesor(int id, String nombre, String identificacion, String email, String departamento, String estado) {
        super(id, nombre, identificacion, email, estado);
        this.departamento = departamento;
    }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
}
