public class Main {
    public static void main(String[] args) {
        // Profesores
        Profesor p1 = new Profesor("Juan", "Pérez", "12345678", "juan@uni.edu", "P001", "Programación");
        Profesor p2 = new Profesor("María", "López", "87654321", "maria@uni.edu", "P002", "Matemáticas");

        // Registro de profesores
        SistemaGestion.registrarProfesor(p1);
        SistemaGestion.registrarProfesor(p2);

        // Sílabo (COMPOSICIÓN): se crea para los cursos
        Syllabus sylProg1 = new Syllabus();
        sylProg1.agregarTema("Introducción a la Programación");
        sylProg1.agregarTema("Estructuras de Control");
        sylProg1.agregarTema("POO básica");

        Syllabus sylMate1 = new Syllabus();
        sylMate1.agregarTema("Límites");
        sylMate1.agregarTema("Derivadas");
        sylMate1.agregarTema("Integrales");

        // Cursos
        Curso c1 = new Curso("C001", "Programación I", Categoria.PROGRAMACION, p1, sylProg1);
        Curso c2 = new Curso("C002", "Cálculo I", Categoria.MATEMATICAS, p2, sylMate1);

        // Registro de cursos
        SistemaGestion.registrarCurso(c1);
        SistemaGestion.registrarCurso(c2);

        // Estudiantes
        Estudiante e1 = new Estudiante("Ana", "Gómez", "11112222", "ana@uni.edu", "E001");
        Estudiante e2 = new Estudiante("Luis", "Ramírez", "33334444", "luis@uni.edu", "E002");

        // Registro de estudiantes
        SistemaGestion.registrarEstudiante(e1);
        SistemaGestion.registrarEstudiante(e2);

        // Inscripciones (AGREGACIÓN)
        e1.inscribirse(c1);
        e1.inscribirse(c2);
        e2.inscribirse(c1);

        // Mostrar información (POLIMORFISMO en mostrarInfo())
        p1.mostrarInfo();
        p2.mostrarInfo();
        e1.mostrarInfo();
        e2.mostrarInfo();
        c1.mostrarInfo();
        c2.mostrarInfo();

        // Reportes del sistema
        SistemaGestion.mostrarResumenGeneral();
        System.out.println("Conteo por categoría: " + SistemaGestion.conteoPorCategoria());
        System.out.println("Inscritos por curso : " + SistemaGestion.inscritosPorCurso());
    }
}
