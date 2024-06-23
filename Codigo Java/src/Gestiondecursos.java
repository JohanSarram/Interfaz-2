package gestiondecursos;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Gestiondecursos {
    private List<Curso> cursos;
    private List<Profesor> profesores;

    public Gestiondecursos() {
        cursos = new ArrayList<>();
        profesores = new ArrayList<>();
        inicializarDatosPredeterminados();
    }

    // Método para inicializar datos predeterminados (profesores y cursos)
    private void inicializarDatosPredeterminados() {
        // Crear y agregar profesores predeterminados
        Profesor profesor1 = new Profesor("Juan", "Pérez");
        Profesor profesor2 = new Profesor("María", "González");
        Profesor profesor3 = new Profesor("Carlos", "Sánchez");
        Profesor profesor4 = new Profesor("Laura", "Martínez");
        Profesor profesor5 = new Profesor("Pedro", "López");

        profesores.add(profesor1);
        profesores.add(profesor2);
        profesores.add(profesor3);
        profesores.add(profesor4);
        profesores.add(profesor5);

        // Crear y agregar cursos predeterminados
        Curso curso1 = new Curso("Matemáticas", profesor1);
        Curso curso2 = new Curso("Historia", profesor2);
        Curso curso3 = new Curso("Programación", profesor3);
        Curso curso4 = new Curso("Biología", profesor4);
        Curso curso5 = new Curso("Literatura", profesor5);

        cursos.add(curso1);
        cursos.add(curso2);
        cursos.add(curso3);
        cursos.add(curso4);
        cursos.add(curso5);
    }

    // Método para abrir la ventana de agregar curso
    public void abrirVentanaAgregarCurso() {
        JFrame frame = new JFrame("Agregar Curso");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        centrarVentanaEnPantalla(frame);

        JPanel panel = new JPanel();

        JLabel labelNombreCurso = new JLabel("Nombre del Curso:");
        JTextField nombreCursoField = new JTextField(20);
        panel.add(labelNombreCurso);
        panel.add(nombreCursoField);

        JLabel labelProfesor = new JLabel("Profesor:");
        JComboBox<String> profesorComboBox = new JComboBox<>();
        for (Profesor profesor : profesores) {
            profesorComboBox.addItem(profesor.getNombreCompleto());
        }
        panel.add(labelProfesor);
        panel.add(profesorComboBox);

            JButton buttonAgregarCurso = new JButton("Agregar Curso");
            buttonAgregarCurso.setPreferredSize(new Dimension(150, 30));        buttonAgregarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreCurso = nombreCursoField.getText();
                String nombreProfesor = (String) profesorComboBox.getSelectedItem();
                Profesor profesorSeleccionado = buscarProfesor(nombreProfesor);

                if (nombreCurso.isEmpty() || nombreProfesor.isEmpty() || profesorSeleccionado == null) {
                    JOptionPane.showMessageDialog(frame, "Todos los campos deben estar completos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Curso curso = new Curso(nombreCurso, profesorSeleccionado);
                cursos.add(curso);

                JOptionPane.showMessageDialog(frame, "Curso agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // Limpia los campos después de agregar el curso
                nombreCursoField.setText("");
            }
        });
        panel.add(buttonAgregarCurso);

        frame.add(panel);
        frame.setVisible(true);
    }

    // Método para abrir la ventana de agregar profesor
    public void abrirVentanaAgregarProfesor() {
        JFrame frame = new JFrame("Agregar Profesor");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        centrarVentanaEnPantalla(frame);

        JPanel panel = new JPanel();

        JLabel labelNombre = new JLabel("Nombre:");
        JTextField nombreField = new JTextField(20);
        panel.add(labelNombre);
        panel.add(nombreField);

        JLabel labelApellido = new JLabel("Apellido:");
        JTextField apellidoField = new JTextField(20);
        panel.add(labelApellido);
        panel.add(apellidoField);

        JButton buttonAgregarProfesor = new JButton("Agregar Profesor");
        buttonAgregarProfesor.setPreferredSize(new Dimension(150, 30));
        buttonAgregarProfesor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();

                if (nombre.isEmpty() || apellido.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Todos los campos deben estar completos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Profesor profesor = new Profesor(nombre, apellido);
                profesores.add(profesor);

                JOptionPane.showMessageDialog(frame, "Profesor agregado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // Limpia los campos después de agregar el profesor
                nombreField.setText("");
                apellidoField.setText("");
            }
        });
        panel.add(buttonAgregarProfesor);

        frame.add(panel);
        frame.setVisible(true);
    }

    // Método para abrir la ventana de agregar estudiante
    public void abrirVentanaAgregarEstudiante() {
        JFrame frame = new JFrame("Agregar Estudiante");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        centrarVentanaEnPantalla(frame);

        JPanel panel = new JPanel();

        JLabel labelNombre = new JLabel("Nombre:");
        JTextField nombreField = new JTextField(20);
        panel.add(labelNombre);
        panel.add(nombreField);

        JLabel labelApellido = new JLabel("Apellido:");
        JTextField apellidoField = new JTextField(20);
        panel.add(labelApellido);
        panel.add(apellidoField);

        JButton buttonAgregarEstudiante = new JButton("Agregar Estudiante");
        buttonAgregarEstudiante.setPreferredSize(new Dimension(150, 30));        buttonAgregarEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                String apellido = apellidoField.getText();

                if (nombre.isEmpty() || apellido.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Todos los campos deben estar completos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Estudiante estudiante = new Estudiante(nombre, apellido);

                // Mostrar ventana para seleccionar cursos
                JFrame frameSeleccionCurso = new JFrame("Seleccionar Curso");
                frameSeleccionCurso.setSize(400, 300);
                frameSeleccionCurso.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                centrarVentanaEnPantalla(frameSeleccionCurso);

                JPanel panelSeleccion = new JPanel();
                JLabel labelSeleccion = new JLabel("Seleccione el curso:");
                JComboBox<String> cursoComboBox = new JComboBox<>();
                for (Curso curso : cursos) {
                    cursoComboBox.addItem(curso.getNombre());
                }
                panelSeleccion.add(labelSeleccion);
                panelSeleccion.add(cursoComboBox);

                JButton buttonSeleccionarCurso = new JButton("Inscribir en Curso");
                buttonSeleccionarCurso.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nombreCurso = (String) cursoComboBox.getSelectedItem();
                        Curso cursoSeleccionado = buscarCurso(nombreCurso);

                        if (cursoSeleccionado != null) {
                            cursoSeleccionado.agregarEstudiante(estudiante);
                            JOptionPane.showMessageDialog(frameSeleccionCurso, "Estudiante inscrito correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            frameSeleccionCurso.dispose(); // Cierra la ventana de selección de curso
                            frame.dispose(); // Cierra la ventana de agregar estudiante
                        } else {
                            JOptionPane.showMessageDialog(frameSeleccionCurso, "Curso no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                panelSeleccion.add(buttonSeleccionarCurso);

                frameSeleccionCurso.add(panelSeleccion);
                frameSeleccionCurso.setVisible(true);
            }
        });
        panel.add(buttonAgregarEstudiante);

        frame.add(panel);
        frame.setVisible(true);
    }

    // Método para abrir la ventana de horarios
    public void abrirVentanaHorarios() {
        JFrame frame = new JFrame("Horarios de Cursos");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        centrarVentanaEnPantalla(frame);

        JPanel panel = new JPanel();

        JTextArea textAreaHorarios = new JTextArea(20, 40);
        textAreaHorarios.setEditable(false);
        panel.add(textAreaHorarios);

        // Mostrar información de horarios de cada curso
        StringBuilder horarios = new StringBuilder();
        for (Curso curso : cursos) {
            horarios.append("Curso: ").append(curso.getNombre()).append("\n");
            horarios.append("Profesor: ").append(curso.getProfesor().getNombreCompleto()).append("\n");
            horarios.append("Estudiantes:\n");
            for (Estudiante estudiante : curso.getEstudiantes()) {
                horarios.append("- ").append(estudiante.getNombreCompleto()).append("\n");
            }
            horarios.append("\n");
        }
        textAreaHorarios.setText(horarios.toString());

        frame.add(panel);
        frame.setVisible(true);
    }

        // Método para centrar una ventana en la pantalla
    private void centrarVentanaEnPantalla(JFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = frame.getSize().width;
        int h = frame.getSize().height;
        int x = (dim.width - w) / 2;
        int y = (dim.height - h) / 2;
        frame.setLocation(x, y);
    }

    // Método para buscar un profesor por su nombre completo
    private Profesor buscarProfesor(String nombreCompleto) {
        for (Profesor profesor : profesores) {
            if (profesor.getNombreCompleto().equals(nombreCompleto)) {
                return profesor;
            }
        }
        return null;
    }

    // Método para buscar un curso por su nombre
    private Curso buscarCurso(String nombreCurso) {
        for (Curso curso : cursos) {
            if (curso.getNombre().equals(nombreCurso)) {
                return curso;
            }
        }
        return null;
    }

    // Clase principal que contiene el método main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Gestiondecursos gestiondecursos = new Gestiondecursos();
            gestiondecursos.crearInterfazGrafica();
        });
    }

    // Método para crear la interfaz gráfica principal con un menú organizado
    public void crearInterfazGrafica() {
        JFrame frame = new JFrame("Gestión de Cursos");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centrarVentanaEnPantalla(frame);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Crear botones para las acciones principales
        JButton buttonAgregarCurso = new JButton("Agregar Curso");
        JButton buttonAgregarProfesor = new JButton("Agregar Profesor");
        JButton buttonAgregarEstudiante = new JButton("Agregar Estudiante");
        JButton buttonVerHorarios = new JButton("Ver Horarios");
        buttonVerHorarios.setPreferredSize(new Dimension(150, 30));
        // Configurar listeners para cada botón
        buttonAgregarCurso.addActionListener(e -> abrirVentanaAgregarCurso());
        buttonAgregarProfesor.addActionListener(e -> abrirVentanaAgregarProfesor());
        buttonAgregarEstudiante.addActionListener(e -> abrirVentanaAgregarEstudiante());
        buttonVerHorarios.addActionListener(e -> abrirVentanaHorarios());

        // Añadir botones al panel
        panel.add(buttonAgregarCurso, gbc);
        gbc.gridy++;
        panel.add(buttonAgregarProfesor, gbc);
        gbc.gridy++;
        panel.add(buttonAgregarEstudiante, gbc);
        gbc.gridy++;
        panel.add(buttonVerHorarios, gbc);

        // Agregar el panel al centro del frame
        frame.add(panel, BorderLayout.CENTER);

        // Hacer visible el frame
        frame.setVisible(true);
    }
}
