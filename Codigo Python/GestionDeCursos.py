import sys
from PyQt5.QtWidgets import QApplication, QWidget, QMainWindow, QLabel, QLineEdit, QPushButton, QVBoxLayout, QHBoxLayout, QMessageBox, QComboBox, QTextEdit, QStackedWidget, QDialog


class Profesor:
    def __init__(self, nombre, apellido):
        self.nombre = nombre
        self.apellido = apellido

    def getNombreCompleto(self):
        return f"{self.nombre} {self.apellido}"


class Curso:
    def __init__(self, nombre, profesor):
        self.nombre = nombre
        self.profesor = profesor
        self.estudiantes = []

    def agregarEstudiante(self, estudiante):
        self.estudiantes.append(estudiante)


class Estudiante:
    def __init__(self, nombre, apellido):
        self.nombre = nombre
        self.apellido = apellido

    def getNombreCompleto(self):
        return f"{self.nombre} {self.apellido}"


class GestionDeCursos(QMainWindow):
    def __init__(self):
        super().__init__()
        self.cursos = []
        self.profesores = []
        self.initUI()

    def initUI(self):
        self.setWindowTitle("Gestión de Cursos")
        self.setGeometry(100, 100, 600, 400)

        self.central_widget = QWidget()
        self.setCentralWidget(self.central_widget)

        self.stacked_widget = QStackedWidget()
        layout = QVBoxLayout(self.central_widget)
        layout.addWidget(self.stacked_widget)

        # Página principal
        self.pagina_principal = QWidget()
        self.stacked_widget.addWidget(self.pagina_principal)

        self.layout_principal = QVBoxLayout(self.pagina_principal)

        self.label_info = QLabel("Bienvenido a la Gestión de Cursos")
        self.layout_principal.addWidget(self.label_info)

        button_agregar_curso = QPushButton("Agregar Curso")
        button_agregar_curso.clicked.connect(self.mostrarVentanaAgregarCurso)
        self.layout_principal.addWidget(button_agregar_curso)

        button_agregar_profesor = QPushButton("Agregar Profesor")
        button_agregar_profesor.clicked.connect(self.mostrarVentanaAgregarProfesor)
        self.layout_principal.addWidget(button_agregar_profesor)

        button_agregar_estudiante = QPushButton("Agregar Estudiante")
        button_agregar_estudiante.clicked.connect(self.mostrarVentanaAgregarEstudiante)
        self.layout_principal.addWidget(button_agregar_estudiante)

        button_ver_horarios = QPushButton("Ver Horarios")
        button_ver_horarios.clicked.connect(self.mostrarVentanaHorarios)
        self.layout_principal.addWidget(button_ver_horarios)

        # Inicializamos las páginas de agregar curso, profesor, estudiante y horarios
        self.ventana_agregar_curso = VentanaAgregarCurso(self)
        self.stacked_widget.addWidget(self.ventana_agregar_curso)

        self.ventana_agregar_profesor = VentanaAgregarProfesor(self)
        self.stacked_widget.addWidget(self.ventana_agregar_profesor)

        self.ventana_agregar_estudiante = VentanaAgregarEstudiante(self)
        self.stacked_widget.addWidget(self.ventana_agregar_estudiante)

        self.ventana_horarios = VentanaHorarios(self)
        self.stacked_widget.addWidget(self.ventana_horarios)

    def mostrarVentanaAgregarCurso(self):
        self.stacked_widget.setCurrentWidget(self.ventana_agregar_curso)

    def mostrarVentanaAgregarProfesor(self):
        self.stacked_widget.setCurrentWidget(self.ventana_agregar_profesor)

    def mostrarVentanaAgregarEstudiante(self):
        self.stacked_widget.setCurrentWidget(self.ventana_agregar_estudiante)

    def mostrarVentanaHorarios(self):
        self.stacked_widget.setCurrentWidget(self.ventana_horarios)


class VentanaAgregarCurso(QWidget):
    def __init__(self, parent=None):
        super().__init__(parent)
        self.parent = parent
        self.initUI()

    def initUI(self):
        self.layout = QVBoxLayout()

        self.label_nombre_curso = QLabel("Nombre del Curso:")
        self.nombre_curso_field = QLineEdit()
        self.layout.addWidget(self.label_nombre_curso)
        self.layout.addWidget(self.nombre_curso_field)

        self.label_profesor = QLabel("Profesor:")
        self.profesor_combo = QComboBox()
        for profesor in self.parent.profesores:
            self.profesor_combo.addItem(profesor.getNombreCompleto())
        self.layout.addWidget(self.label_profesor)
        self.layout.addWidget(self.profesor_combo)

        self.button_agregar_curso = QPushButton("Agregar Curso")
        self.button_agregar_curso.clicked.connect(self.agregarCurso)
        self.layout.addWidget(self.button_agregar_curso)

        self.setLayout(self.layout)

    def agregarCurso(self):
        nombre_curso = self.nombre_curso_field.text()
        nombre_profesor = self.profesor_combo.currentText()

        if not nombre_curso or not nombre_profesor:
            QMessageBox.warning(self, "Error", "Todos los campos deben estar completos")
            return

        profesor_seleccionado = next((p for p in self.parent.profesores if p.getNombreCompleto() == nombre_profesor), None)
        if profesor_seleccionado:
            curso = Curso(nombre_curso, profesor_seleccionado)
            self.parent.cursos.append(curso)
            QMessageBox.information(self, "Éxito", "Curso agregado correctamente")
            self.nombre_curso_field.clear()
        else:
            QMessageBox.warning(self, "Error", "Profesor no encontrado")


class VentanaAgregarProfesor(QWidget):
    def __init__(self, parent=None):
        super().__init__(parent)
        self.parent = parent
        self.initUI()

    def initUI(self):
        self.layout = QVBoxLayout()

        self.label_nombre = QLabel("Nombre:")
        self.nombre_field = QLineEdit()
        self.layout.addWidget(self.label_nombre)
        self.layout.addWidget(self.nombre_field)

        self.label_apellido = QLabel("Apellido:")
        self.apellido_field = QLineEdit()
        self.layout.addWidget(self.label_apellido)
        self.layout.addWidget(self.apellido_field)

        self.button_agregar_profesor = QPushButton("Agregar Profesor")
        self.button_agregar_profesor.clicked.connect(self.agregarProfesor)
        self.layout.addWidget(self.button_agregar_profesor)

        self.setLayout(self.layout)

    def agregarProfesor(self):
        nombre = self.nombre_field.text()
        apellido = self.apellido_field.text()

        if not nombre or not apellido:
            QMessageBox.warning(self, "Error", "Todos los campos deben estar completos")
            return

        profesor = Profesor(nombre, apellido)
        self.parent.profesores.append(profesor)
        QMessageBox.information(self, "Éxito", "Profesor agregado correctamente")
        self.nombre_field.clear()
        self.apellido_field.clear()


class VentanaAgregarEstudiante(QWidget):
    def __init__(self, parent=None):
        super().__init__(parent)
        self.parent = parent
        self.initUI()

    def initUI(self):
        self.layout = QVBoxLayout()

        self.label_nombre = QLabel("Nombre:")
        self.nombre_field = QLineEdit()
        self.layout.addWidget(self.label_nombre)
        self.layout.addWidget(self.nombre_field)

        self.label_apellido = QLabel("Apellido:")
        self.apellido_field = QLineEdit()
        self.layout.addWidget(self.label_apellido)
        self.layout.addWidget(self.apellido_field)

        self.button_agregar_estudiante = QPushButton("Agregar Estudiante")
        self.button_agregar_estudiante.clicked.connect(self.agregarEstudiante)
        self.layout.addWidget(self.button_agregar_estudiante)

        self.setLayout(self.layout)

    def agregarEstudiante(self):
        nombre = self.nombre_field.text()
        apellido = self.apellido_field.text()

        if not nombre or not apellido:
            QMessageBox.warning(self, "Error", "Todos los campos deben estar completos")
            return

        estudiante = Estudiante(nombre, apellido)

        # Mostrar ventana para seleccionar cursos
        ventana_seleccion_curso = VentanaSeleccionCurso(estudiante, self.parent.cursos, self)
        ventana_seleccion_curso.exec_()


class VentanaSeleccionCurso(QDialog):

    def __init__(self, estudiante, cursos, parent=None):
        super().__init__(parent)
        self.estudiante = estudiante
        self.cursos = cursos
        self.initUI()

    def initUI(self):
        self.layout = QVBoxLayout()

        self.label_seleccion = QLabel("Seleccione el curso:")
        self.curso_combo = QComboBox()
        for curso in self.cursos:
            self.curso_combo.addItem(curso.nombre)
        self.layout.addWidget(self.label_seleccion)
        self.layout.addWidget(self.curso_combo)

        self.button_seleccionar_curso = QPushButton("Inscribir en Curso")
        self.button_seleccionar_curso.clicked.connect(self.inscribirEstudiante)
        self.layout.addWidget(self.button_seleccionar_curso)

        self.setLayout(self.layout)

    def inscribirEstudiante(self):
        nombre_curso = self.curso_combo.currentText()
        curso_seleccionado = next((c for c in self.cursos if c.nombre == nombre_curso), None)
        if curso_seleccionado:
            curso_seleccionado.agregarEstudiante(self.estudiante)
            QMessageBox.information(self, "Éxito", "Estudiante inscrito correctamente")
            self.accept()
        else:
            QMessageBox.warning(self, "Error", "Curso no encontrado")


class VentanaHorarios(QWidget):
    def __init__(self, parent=None):
        super().__init__(parent)
        self.parent = parent
        self.initUI()

    def initUI(self):
        self.layout = QVBoxLayout()

        self.text_area_horarios = QTextEdit()
        self.text_area_horarios.setReadOnly(True)
        self.layout.addWidget(self.text_area_horarios)

        self.setLayout(self.layout)

    def mostrarHorarios(self, horarios):
        self.text_area_horarios.clear()
        self.text_area_horarios.append(horarios)


if __name__ == "__main__":
    app = QApplication(sys.argv)
    ventana_principal = GestionDeCursos()
    ventana_principal.show()
    sys.exit(app.exec_())
