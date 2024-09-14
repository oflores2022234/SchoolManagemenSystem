package org.example;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorAcademico implements ServiciosAcademicosI {

    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Curso> cursos;
    private HashMap<Integer, ArrayList<Estudiante>> inscripciones;

    public GestorAcademico() {
        estudiantes = new ArrayList<>();
        cursos = new ArrayList<>();
        inscripciones = new HashMap<>();
    }

    @Override
    public void matricularEstudiante(Estudiante estudiante) throws EstudianteYaInscritoException {
        if (!estudiantes.contains(estudiante)) {
            estudiantes.add(estudiante);
        } else {
            throw new EstudianteYaInscritoException("El estudiante ya está matriculado.");
        }
    }

    @Override
    public void agregarCurso(Curso curso) {
        if (!cursos.contains(curso)) {
            cursos.add(curso);
        }
    }

    @Override
    public void inscribirEstudianteCurso(Estudiante estudiante, int idCurso) throws EstudianteYaInscritoException {
        if (!estudiantes.contains(estudiante)) {
            throw new IllegalArgumentException("El estudiante no está registrado.");
        }

        Curso curso = cursos.stream().filter(c -> c.getId() == idCurso).findFirst().orElse(null);
        if (curso == null) {
            throw new IllegalArgumentException("Curso no encontrado.");
        }

        ArrayList<Estudiante> inscritos = inscripciones.getOrDefault(idCurso, new ArrayList<>());
        if (inscritos.contains(estudiante)) {
            throw new EstudianteYaInscritoException("El estudiante ya está inscrito en este curso.");
        }

        inscritos.add(estudiante);
        inscripciones.put(idCurso, inscritos);
    }

    @Override
    public void desinscribirEstudianteCurso(int idEstudiante, int idCurso) throws EstudianteNoInscritoEnCursoException {
        Curso curso = cursos.stream().filter(c -> c.getId() == idCurso).findFirst().orElse(null);
        if (curso == null) {
            throw new IllegalArgumentException("Curso no encontrado.");
        }

        Estudiante estudiante = estudiantes.stream().filter(e -> e.getId() == idEstudiante).findFirst().orElse(null);
        if (estudiante == null) {
            throw new IllegalArgumentException("Estudiante no registrado.");
        }

        ArrayList<Estudiante> inscritos = inscripciones.get(idCurso);
        if (inscritos == null || !inscritos.contains(estudiante)) {
            throw new EstudianteNoInscritoEnCursoException("El estudiante no está inscrito en este curso.");
        }

        inscritos.remove(estudiante);
        if (inscritos.isEmpty()) {
            inscripciones.remove(idCurso);
        } else {
            inscripciones.put(idCurso, inscritos);
        }
    }
}