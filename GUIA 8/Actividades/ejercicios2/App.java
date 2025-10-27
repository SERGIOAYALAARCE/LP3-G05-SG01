package ejercicios2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorRegistros gestor = new GestorRegistros();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:ejemplo2.db");
            con.setAutoCommit(false);

            // ✅ Crear tabla si no existe
            con.createStatement().execute(
                "CREATE TABLE IF NOT EXISTS emp (" +
                "id INTEGER PRIMARY KEY, " +
                "name TEXT, " +
                "age INTEGER, " +
                "department TEXT);"
            );
            con.commit(); 
            gestor.cargarDesdeDB(con);

            int opcion;
            do {
                System.out.println("\n--- MENÚ ---");
                System.out.println("1. Insertar registro");
                System.out.println("2. Eliminar registro");
                System.out.println("3. Actualizar registro");
                System.out.println("4. Mostrar registros desde BD");
                System.out.println("5. Consultar desde arreglo (tipo SQL)");
                System.out.println("6. Salir");
                System.out.print("Opción: ");
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.print("ID: ");
                        int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Edad: ");
                        int edad = sc.nextInt(); sc.nextLine();
                        System.out.print("Departamento: ");
                        String dep = sc.nextLine();
                        con.prepareStatement("INSERT INTO emp VALUES(" + id + ", '" + nombre + "', " + edad + ", '" + dep + "')").executeUpdate();

                        if (confirmar(sc)) { con.commit(); gestor.refrescar(con); }
                        else con.rollback();
                        break;

                    case 2:
                        System.out.print("ID a eliminar: ");
                        int idDel = sc.nextInt(); sc.nextLine();
                        con.prepareStatement("DELETE FROM emp WHERE id=" + idDel).executeUpdate();

                        if (confirmar(sc)) { con.commit(); gestor.refrescar(con); }
                        else con.rollback();
                        break;

                    case 3:
                        System.out.print("ID a actualizar: ");
                        int idUp = sc.nextInt(); sc.nextLine();
                        System.out.print("Nueva edad: ");
                        int newEdad = sc.nextInt(); sc.nextLine();
                        System.out.print("Nuevo departamento: ");
                        String newDep = sc.nextLine();
                        con.prepareStatement("UPDATE emp SET age=" + newEdad + ", department='" + newDep + "' WHERE id=" + idUp).executeUpdate();

                        if (confirmar(sc)) { con.commit(); gestor.refrescar(con); }
                        else con.rollback();
                        break;

                    case 4:
                        var rs = con.prepareStatement("SELECT * FROM emp").executeQuery();
                        while (rs.next()) {
                            System.out.println(rs.getInt("id") + " " + rs.getString("name") + " " + rs.getInt("age") + " " + rs.getString("department"));
                        }
                        break;

                    case 5:
                        System.out.print("Campos a mostrar (ej. id,nombre,edad,departamento): ");
                        List<String> campos = Arrays.asList(sc.nextLine().toLowerCase().split(","));

                        System.out.print("Campo condición (o vacío): ");
                        String campoCond = sc.nextLine();
                        if (campoCond.isEmpty()) campoCond = null;

                        System.out.print("Valor condición (o vacío): ");
                        String valorCond = sc.nextLine();
                        if (valorCond.isEmpty()) valorCond = null;

                        System.out.print("Campo orden (o vacío): ");
                        String campoOrd = sc.nextLine();
                        if (campoOrd.isEmpty()) campoOrd = null;

                        System.out.print("¿Descendente? (true/false): ");
                        boolean desc = Boolean.parseBoolean(sc.nextLine());

                        System.out.print("Límite (0 = sin límite): ");
                        int lim = sc.nextInt(); sc.nextLine();

                        gestor.consultar(campos, campoCond, valorCond, campoOrd, desc, lim);
                        break;
                }
            } while (opcion != 6);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }

    private static boolean confirmar(Scanner sc) {
        System.out.print("Ingrese clave para confirmar: ");
        return sc.nextLine().equals("secreto");
    }
}
