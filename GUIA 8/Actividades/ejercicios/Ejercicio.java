package ejercicios;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class Ejercicio {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Class.forName("org.sqlite.JDBC"); //PASO 1 REGISTRA EL DRIVER
            Connection con = DriverManager.getConnection("jdbc:sqlite:ejemplo1.db");  // PASO 2 SE CREA EL OBJETO "con" para validar la conexion
            con.setAutoCommit(false);
			if (con != null) { 
				System.out.println("Se creo y/o abrio la base de datos: "); 
				} 
            // Creación de la tabla con 4 campos
            Statement stmt = con.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS emp (id INTEGER PRIMARY KEY, name TEXT, age INTEGER, department TEXT);");
            // Menú
            int option;
            do {
                System.out.println("\n--- Menú ---");
                System.out.println("1. Ingresar registro");
                System.out.println("2. Borrar registro");
                System.out.println("3. Actualizar registro");
                System.out.println("4. Mostrar registros");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción: ");
                option = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
                switch (option) {
                    case 1:
                        insertRecord(scanner, con);
                        break;
                    case 2:
                        deleteRecord(scanner, con);
                        break;
                    case 3:
                        updateRecord(scanner, con);
                        break;
                    case 4:
                        displayRecords(con);
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            } while (option != 5);
            con.close(); // Cerrar la conexión
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            scanner.close();
        }
    }
    private static void insertRecord(Scanner scanner, Connection con) throws Exception {
        System.out.print("Ingrese ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese nombre: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese edad: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese departamento: ");
        String department = scanner.nextLine();
        PreparedStatement insertStmt = con.prepareStatement("INSERT INTO emp (id, name, age, department) VALUES (?, ?, ?, ?)");
        insertStmt.setInt(1, id);
        insertStmt.setString(2, name);
        insertStmt.setInt(3, age);
        insertStmt.setString(4, department);
        insertStmt.executeUpdate();        
        System.out.println("Registro insertado.");
        // Confirmación
        if (confirmAction(scanner, "COLOCA LA CLAVE PARA CONFIRMAR TU ACCION")) {
        	con.commit();// GUARDAR REGISTRO
        	  System.out.println("Operación exitosa.");
        } else {
        	con.rollback();// NO GUARDAR REGISTRO
            System.out.println("Operación cancelada.");
        }
    }
    private static void deleteRecord(Scanner scanner, Connection con) throws Exception {
        System.out.print("Ingrese ID del registro a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        // Confirmación
        if (confirmAction(scanner, "COLOCA LA CLAVE PARA CONFIRMAR TU ACCION")) {
            PreparedStatement deleteStmt = con.prepareStatement("DELETE FROM emp WHERE id = ?");
            deleteStmt.setInt(1, id);
            int rowsDeleted = deleteStmt.executeUpdate();
            if (rowsDeleted > 0) {
                con.commit(); // Aplicar la transacción si se eliminó algún registro
                System.out.println(rowsDeleted + " registro(s) eliminado(s).");
            } else {
                System.out.println("No se encontró el registro con el ID especificado.");
            }
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private static void updateRecord(Scanner scanner, Connection con) throws Exception {
        System.out.print("Ingrese ID del registro a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese nueva edad: ");
        int newAge = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese nuevo departamento: ");
        String newDepartment = scanner.nextLine();
        // Confirmación
        if (confirmAction(scanner, "COLOCA LA CLAVE PARA CONFIRMAR TU ACCION")) {
            PreparedStatement updateStmt = con.prepareStatement("UPDATE emp SET age = ?, department = ? WHERE id = ?");
            updateStmt.setInt(1, newAge);
            updateStmt.setString(2, newDepartment);
            updateStmt.setInt(3, id);
            int rowsUpdated = updateStmt.executeUpdate();
            System.out.println(rowsUpdated + " registro(s) actualizado(s).");
            con.commit();
        } else {
            System.out.println("Operación cancelada.");
        }
    }
    private static void displayRecords(Connection con) throws Exception {
        PreparedStatement selectStmt = con.prepareStatement("SELECT * FROM emp");
        ResultSet rs = selectStmt.executeQuery();
        System.out.println("Datos actuales en la tabla:");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getInt("age") + " " + rs.getString("department"));
        }
    }
    private static boolean confirmAction(Scanner scanner, String message) {
        System.out.print(message);
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("secreto");
    }
}