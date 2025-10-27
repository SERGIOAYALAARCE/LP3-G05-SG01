import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcSqliteExample {

    private static final String DB_URL = "jdbc:sqlite:sample.db";

    public static void main(String[] args) {
        try {
            // 1. Crear tabla si no existe
            createTable();

            // 2. Operaciones CRUD
            // Insertar registros
            insertRegistro(1, "Roy", 19);
            insertRegistro(2, "Mauricio", 21);
            insertRegistro(3, "Renato", 25);

            // Recuperar registros
            System.out.println("Registros después de insertar:");
            printAllRegistros();

            // Actualizar registro
            updateNombre(2, "Mauricio Modificado");
            System.out.println("\nRegistros después de actualizar id=2:");
            printAllRegistros();

            // Borrar registro
            deleteRegistro(3);
            System.out.println("\nRegistros después de borrar id=3:");
            printAllRegistros();

            // 3. Ejemplo de transacción: insertar 2 registros como una operación atómica
            System.out.println("\nEjemplo de transacción (insert y rollback demo):");
            transaccionEjemplo();

            System.out.println("\nRegistros finales:");
            printAllRegistros();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Crear la tabla con 3 campos: id (PK), nombre, edad
    private static void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS Estudiante (" +
                     "id INTEGER PRIMARY KEY, " +
                     "nombre TEXT NOT NULL, " +
                     "edad INTEGER" +
                     ");";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    // Insertar usando PreparedStatement
    private static void insertRegistro(int id, String nombre, int edad) throws SQLException {
        String sql = "INSERT OR REPLACE INTO Estudiante(id, nombre, edad) VALUES (?, ?, ?);";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setInt(3, edad);
            ps.executeUpdate();
        }
    }

    // Recuperar todos los registros
    private static List<String> getAllRegistros() throws SQLException {
        String sql = "SELECT id, nombre, edad FROM Estudiante ORDER BY id;";
        List<String> results = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");
                results.add(String.format("id=%d, nombre=%s, edad=%d", id, nombre, edad));
            }
        }
        return results;
    }

    private static void printAllRegistros() throws SQLException {
        List<String> registros = getAllRegistros();
        if (registros.isEmpty()) {
            System.out.println("(no hay registros)");
        } else {
            registros.forEach(System.out::println);
        }
    }

    // Actualizar nombre por id
    private static void updateNombre(int id, String nuevoNombre) throws SQLException {
        String sql = "UPDATE Estudiante SET nombre = ? WHERE id = ?;";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevoNombre);
            ps.setInt(2, id);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                System.out.println("No se encontró registro con id=" + id + " para actualizar.");
            }
        }
    }

    // Borrar registro por id
    private static void deleteRegistro(int id) throws SQLException {
        String sql = "DELETE FROM Estudiante WHERE id = ?;";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                System.out.println("No se encontró registro con id=" + id + " para borrar.");
            }
        }
    }

    // Ejemplo de manejo de transacciones con commit/rollback
    private static void transaccionEjemplo() {
        Connection conn = null;
        PreparedStatement ps = null;
        String insertSql = "INSERT INTO Estudiante(id, nombre, edad) VALUES (?, ?, ?);";

        try {
            conn = DriverManager.getConnection(DB_URL);
            // Desactivar autocommit para controlar manualmente la transacción
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(insertSql);

            // Insert 1
            ps.setInt(1, 10);
            ps.setString(2, "AlumnoTrans1");
            ps.setInt(3, 30);
            ps.executeUpdate();

            // Insert 2 (simulamos un error si id ya existe para demostrar rollback)
            ps.setInt(1, 10); // mismo id -> violación de PK si no usamos OR REPLACE
            ps.setString(2, "AlumnoTrans2");
            ps.setInt(3, 31);
            ps.executeUpdate(); // esto debería lanzar SQLException por PK duplicada

            // Si todo va bien, hacemos commit
            conn.commit();
            System.out.println("Transacción completada: commit.");

        } catch (SQLException e) {
            System.out.println("Error en la transacción: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Transacción revertida (rollback).");
                } catch (SQLException ex) {
                    System.out.println("Error al hacer rollback: " + ex.getMessage());
                }
            }
        } finally {
            // Restaurar autocommit y cerrar recursos
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException ignored) {}
        }
    }
}
