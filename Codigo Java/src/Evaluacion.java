package gestiondecursos;


public class Evaluacion {
    private Curso curso;
    private Estudiante estudiante;
    private int nota;

    public Evaluacion(Curso curso, Estudiante estudiante, int nota) {
        this.curso = curso;
        this.estudiante = estudiante;
        this.nota = nota;
    }

    public Curso getCurso() {
        return curso;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public int getNota() {
        return nota;
    }
}
