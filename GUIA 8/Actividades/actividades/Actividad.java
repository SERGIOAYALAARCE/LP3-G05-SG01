package actividades;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class Actividad {
    // Helper para pausar hasta que el usuario presione Enter
    private static void esperarEnter(Scanner sc, String mensaje) {
        System.out.println(mensaje + " (presiona Enter para continuar)");
        sc.nextLine();
    }
    // Muestra todos los registros de la tabla emp
    private static void mostrarTodo(Connection con) throws Exception {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM emp ORDER BY id;");
        System.out.println("ID | NAME    | AGE");
        System.out.println("-------------------");
        boolean vacio = true;
        while (rs.next()) {
            vacio = false;
            System.out.printf("%2d | %-7s | %d%n",
                    rs.getInt("id"), rs.getString("name"), rs.getInt("age"));
        }
        if (vacio) System.out.println("[La tabla está vacía]");
        rs.close();
        stmt.close();
    }
    // Inserta usando PreparedStatement (sin especificar id — AUTOINCREMENT)
    private static void insertarPrepared(Connection con, String nombre, int edad) throws Exception {
        PreparedStatement ps = con.prepareStatement("INSERT INTO emp (name, age) VALUES (?, ?);");
        ps.setString(1, nombre);
        ps.setInt(2, edad);
        int filas = ps.executeUpdate();
        System.out.println("INSERT -> Filas afectadas: " + filas + " (Inserted: " + nombre + ", " + edad + ")");
        ps.close();
    }
    // Actualiza edad por id usando PreparedStatement
    private static void actualizarEdad(Connection con, int id, int nuevaEdad) throws Exception {
        PreparedStatement ps = con.prepareStatement("UPDATE emp SET age = ? WHERE id = ?;");
        ps.setInt(1, nuevaEdad);
        ps.setInt(2, id);
        int filas = ps.executeUpdate();
        System.out.println("UPDATE -> Filas afectadas: " + filas + " (ID: " + id + ", Nueva edad: " + nuevaEdad + ")");
        ps.close();
    }

    // Elimina por id usando PreparedStatement
    private static void eliminarPorId(Connection con, int id) throws Exception {
        PreparedStatement ps = con.prepareStatement("DELETE FROM emp WHERE id = ?;");
        ps.setInt(1, id);
        int filas = ps.executeUpdate();
        System.out.println("DELETE -> Filas afectadas: " + filas + " (ID: " + id + ")");
        ps.close();
    }

    // Ejemplo de transacción que muestra rollback y commit
    private static void ejemploTransaccion(Connection con, Scanner sc) throws Exception {
        System.out.println("\n--- EJEMPLO DE TRANSACCIÓN ---");
        con.setAutoCommit(false);
        Statement stmt = con.createStatement();
        try {
            System.out.println("Inserto dos registros dentro de la transacción (serán ROLLBACK).");
            stmt.executeUpdate("INSERT INTO emp (name, age) VALUES ('TRANS_A', 40);");
            stmt.executeUpdate("INSERT INTO emp (name, age) VALUES ('TRANS_B', 41);");
            System.out.println("Ahora hago ROLLBACK (no se deben guardar estos dos registros).");
            con.rollback();

            // Mostrar estado después del rollback
            mostrarTodo(con);
            esperarEnter(sc, "Verificado rollback");

            System.out.println("Ahora inserto dos registros distintos y hago COMMIT (estos sí se guardarán).");
            stmt.executeUpdate("INSERT INTO emp (name, age) VALUES ('TRANS_C', 42);");
            stmt.executeUpdate("INSERT INTO emp (name, age) VALUES ('TRANS_D', 43);");
            con.commit();
            System.out.println("Commit ejecutado: los registros TRANS_C y TRANS_D fueron guardados.");

        } catch (Exception e) {
            System.out.println("Error en la transacción. Ejecutando rollback...");
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true); // regresar al modo por defecto
            stmt.close();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Ejecución paso a paso de operaciones CRUD, PreparedStatement y transacciones ===");

        try {
            // Cargar driver y conectar
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:ejemplo.db");
            System.out.println("Conexión establecida a ejemplo.db");

            // Crear tabla con AUTOINCREMENT para evitar conflicto de PK al ejecutar varias veces
            Statement stmt = con.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS emp (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, age INTEGER);");
            stmt.close();

            esperarEnter(sc, "\n[Inicio] Tabla creada / verificada");

            // 1) INSERT usando PreparedStatement
            System.out.println("\n-- OPERACIÓN: INSERT (PreparedStatement) --");
            insertarPrepared(con, "Carlos", 30);
            mostrarTodo(con);
            esperarEnter(sc, "Insert realizado y verificado");

            // Insertar otro registro para tener datos
            insertarPrepared(con, "María", 22);
            mostrarTodo(con);
            esperarEnter(sc, "Segundo insert realizado y verificado");

            // 2) SELECT / RECUPERAR
            System.out.println("\n-- OPERACIÓN: SELECT / RECUPERAR --");
            System.out.println("Mostrando registros actuales:");
            mostrarTodo(con);
            esperarEnter(sc, "Select verificado");

            // 3) UPDATE usando PreparedStatement
            System.out.println("\n-- OPERACIÓN: UPDATE (PreparedStatement) --");
            // Para demostrar, actualizamos la edad del primer registro que exista (id mínimo)
            Statement stFind = con.createStatement();
            ResultSet rsMin = stFind.executeQuery("SELECT id FROM emp ORDER BY id LIMIT 1;");
            int idParaActualizar = -1;
            if (rsMin.next()) idParaActualizar = rsMin.getInt("id");
            rsMin.close();
            stFind.close();

            if (idParaActualizar != -1) {
                System.out.println("Actualizando edad del registro con id = " + idParaActualizar);
                actualizarEdad(con, idParaActualizar, 35);
            } else {
                System.out.println("No hay registros para actualizar.");
            }
            mostrarTodo(con);
            esperarEnter(sc, "Update verificado");

            // 4) DELETE usando PreparedStatement
            System.out.println("\n-- OPERACIÓN: DELETE (PreparedStatement) --");
            // Eliminamos, por ejemplo, el registro con id más alto (demostración)
            Statement stFind2 = con.createStatement();
            ResultSet rsMax = stFind2.executeQuery("SELECT id FROM emp ORDER BY id DESC LIMIT 1;");
            int idParaEliminar = -1;
            if (rsMax.next()) idParaEliminar = rsMax.getInt("id");
            rsMax.close();
            stFind2.close();

            if (idParaEliminar != -1) {
                System.out.println("Eliminando el registro con id = " + idParaEliminar);
                eliminarPorId(con, idParaEliminar);
            } else {
                System.out.println("No hay registros para eliminar.");
            }
            mostrarTodo(con);
            esperarEnter(sc, "Delete verificado");

            // 5) Manejo de transacciones (commit / rollback) con demostración paso a paso
            ejemploTransaccion(con, sc);
            mostrarTodo(con);
            esperarEnter(sc, "Transacción verificada");

            // Cierre
            con.close();
            System.out.println("\nConexión cerrada.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
