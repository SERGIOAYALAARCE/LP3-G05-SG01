package ejercicios2;

import java.sql.*;
import java.util.*;

public class Ejercicio {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorRegistros gestor = new GestorRegistros();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:ejemplo2.db");
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS emp (id INTEGER PRIMARY KEY, name TEXT, age INTEGER, department TEXT);");
            gestor.cargarDesdeDB(con);

            int option;
            do {
                System.out.println("\n--- Menú ---");
                System.out.println("1. Ingresar registro");
                System.out.println("2. Borrar registro");
                System.out.println("3. Actualizar registro");
                System.out.println("4. Mostrar registros de BD");
                System.out.println("5. Consultar registros desde arreglo (tipo SQL)");
                System.out.println("6. Salir");
                System.out.print("Opción: ");
                option = scanner.nextInt();
                scanner.nextLine();

                if (option == 1) insertRecord(scanner, con, gestor);
                if (option == 2) deleteRecord(scanner, con, gestor);
                if (option == 3) updateRecord(scanner, con, gestor);
                if (option == 4) displayRecords(con);
                if (option == 5) consultarDesdeArreglo(scanner, gestor);
            } while (option != 6);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void insertRecord(Scanner sc, Connection con, GestorRegistros gestor) throws Exception {
        System.out.print("ID: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("Nombre: "); String nombre = sc.nextLine();
        System.out.print("Edad: "); int edad = sc.nextInt(); sc.nextLine();
        System.out.print("Departamento: "); String dep = sc.nextLine();

        PreparedStatement ps = con.prepareStatement("INSERT INTO emp VALUES (?, ?, ?, ?)");
        ps.setInt(1, id); ps.setString(2, nombre); ps.setInt(3, edad); ps.setString(4, dep);
        ps.executeUpdate();

        if (confirmar(sc)) { con.commit(); gestor.refrescar(con); }
        else con.rollback();
    }

    private static void deleteRecord(Scanner sc, Connection con, GestorRegistros gestor) throws Exception {
        System.out.print("ID a borrar: "); int id = sc.nextInt(); sc.nextLine();
        PreparedStatement ps = con.prepareStatement("DELETE FROM emp WHERE id=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        if (confirmar(sc)) { con.commit(); gestor.refrescar(con); }
        else con.rollback();
    }

    private static void updateRecord(Scanner sc, Connection con, GestorRegistros gestor) throws Exception {
        System.out.print("ID a actualizar: "); int id = sc.nextInt(); sc.nextLine();
        System.out.print("Nueva edad: "); int edad = sc.nextInt(); sc.nextLine();
        System.out.print("Nuevo departamento: "); String dep = sc.nextLine();
        PreparedStatement ps = con.prepareStatement("UPDATE emp SET age=?, department=? WHERE id=?");
        ps.setInt(1, edad); ps.setString(2, dep); ps.setInt(3, id);
        ps.executeUpdate();
        if (confirmar(sc)) { con.commit(); gestor.refrescar(con); }
        else con.rollback();
    }

    private static void displayRecords(Connection con) throws Exception {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM emp");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getInt("age") + " " + rs.getString("department"));
        }
    }

    private static void consultarDesdeArreglo(Scanner sc, GestorRegistros gestor) {
        System.out.print("Campos a mostrar (id,nombre,edad,departamento separados por coma): ");
        List<String> campos = Arrays.asList(sc.nextLine().toLowerCase().split(","));

        System.out.print("Campo condición (o vacío): ");
        String campoCond = sc.nextLine();
        System.out.print("Valor condición (o vacío): ");
        String valorCond = sc.nextLine();

        System.out.print("Campo orden (o vacío): ");
        String campoOrden = sc.nextLine();
        System.out.print("Orden descendente? (true/false): ");
        boolean desc = Boolean.parseBoolean(sc.nextLine());

        System.out.print("Límite de registros (0 = sin límite): ");
        int limite = sc.nextInt(); sc.nextLine();

        gestor.consultar(campos, campoCond, valorCond, campoOrden, desc, limite);
    }

    private static boolean confirmar(Scanner sc) {
        System.out.print("Ingrese clave para confirmar: ");
        return sc.nextLine().equals("secreto");
    }
}
