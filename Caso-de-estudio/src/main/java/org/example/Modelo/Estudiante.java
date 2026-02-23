package org.example.Modelo;

import java.time.LocalDate;

public class Estudiante extends Persona {
    private LocalDate fechaNacimiento;

    public Estudiante(int id, String nombre, String identificacion, String email, LocalDate fechaNacimiento, String estado) {
        super(id, nombre, identificacion, email, estado);
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
}
