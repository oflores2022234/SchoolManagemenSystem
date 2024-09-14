package org.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        GestorAcademico gestor = new GestorAcademico();

        /*Creación de los estudiantes*/
        Estudiante estudiante1 = new Estudiante(1, "Alejandro", "Flores", LocalDate.of(2005, 9, 9), "matriculado");
        Estudiante estudiante2 = new Estudiante(2, "Josué", "Ambrocio", LocalDate.of(2005, 12, 17), "matriculado");

        System.out.println("Estudiante 1\n " + estudiante1);
        System.out.println("Estudiante 2\n " + estudiante2);
        // Crear dos cursos
        Curso curso1 = new Curso(1, "Matemáticas", "Curso de matemáticas avanzadas", 4, "v1");
        Curso curso2 = new Curso(2, "Taller", "Estudios de Programación", 3, "v1");

        System.out.println("Curso 1\n " + curso1);
        System.out.println("Curso 2\n " + curso2);
        //Matriculamos a los estudiantes
        try {
            gestor.matricularEstudiante(estudiante1);
            gestor.matricularEstudiante(estudiante2);
            System.out.println("Estudiantes matriculados correctamente.");
        } catch (EstudianteYaInscritoException e) {
            System.out.println(e.getMessage());
        }

        //prueba que intentará matricular al mismo estudiant nuevamente
        try {
            gestor.matricularEstudiante(estudiante1);
        } catch (EstudianteYaInscritoException e) {
            System.out.println("Error al marticular estudiante: " + e.getMessage());
        }

        //Agregamos los cursos
        try {
            gestor.agregarCurso(curso1);
            gestor.agregarCurso(curso2);
            System.out.println("Cursos añadidos correctamente.");
        } catch (Exception e) {
            System.out.println("Error al añadir curso: " + e.getMessage());
        }

        // prueba de intentar agergar el curso de nievo
        try {
            gestor.agregarCurso(curso1);
            System.out.println("Curso añadido nuevamente.");
        } catch (Exception e) {
            System.out.println("Error al añadir curso: " + e.getMessage());
        }

        // Inscripción del estuante al curso
        try {
            gestor.inscribirEstudianteCurso(estudiante1, curso1.getId());
            gestor.inscribirEstudianteCurso(estudiante2, curso2.getId());
            System.out.println("Estudiantes inscritos en cursos.");
        } catch (EstudianteYaInscritoException e) {
            System.out.println("Error al inscribir estudiante: " + e.getMessage());
        }

        //Prueba para instribir al mismo estudiante al curso
        try {
            gestor.inscribirEstudianteCurso(estudiante1, curso1.getId());
        } catch (EstudianteYaInscritoException e) {
            System.out.println("Error al inscribir estudiante en curso: " + e.getMessage());
        }

        //Prueba de inscribir al alumno a curso no exisstente
        try {
            gestor.inscribirEstudianteCurso(estudiante1, 999); // Curso con ID que no existe
        } catch (EstudianteYaInscritoException e) {
            System.out.println("Error al inscribir estuediantes en curso: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Desasignar al estudiante de la clase
        try {
            gestor.desinscribirEstudianteCurso(estudiante1.getId(), curso1.getId());
            System.out.println("Estudiante desasignado del curso correctamente.");
        } catch (EstudianteNoInscritoEnCursoException e) {
            System.out.println("Error al desasignar estudiante de curso: " + e.getMessage());
        }

        //Prueba de desasignar a estudiante ya desasignado
        try {
            gestor.desinscribirEstudianteCurso(estudiante1.getId(), curso1.getId());
        } catch (EstudianteNoInscritoEnCursoException e) {
            System.out.println("Error al desinscribir estudiante de curso: " + e.getMessage());
        }

        //prueba de desasignasr a estudiante a curso no exictente
        try {
            gestor.desinscribirEstudianteCurso(estudiante1.getId(), 999); // Curso con ID que no existe
        } catch (EstudianteNoInscritoEnCursoException e) {
            System.out.println("Error al desinscribir estudiante de curso: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}