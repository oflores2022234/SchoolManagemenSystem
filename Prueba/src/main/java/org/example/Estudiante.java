package org.example;

import java.time.LocalDate;


public class Estudiante extends Persona {

    private String estado;

    public Estudiante(int id, String nombre, String apellido, LocalDate fechaDeNacimiento, String estado) {
        super(id, nombre, apellido, fechaDeNacimiento);
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", apellido='" + getApellido() + '\'' +
                ", fechaDeNacimiento=" + getFechaDeNacimiento() +
                ", estado='" + estado + '\'' +
                '}';
    }
}