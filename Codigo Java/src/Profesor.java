package gestiondecursos;
public class Profesor {
    private String nombre;
    private String apellido;

    public Profesor(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}

