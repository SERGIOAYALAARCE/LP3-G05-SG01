package ejercicios2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GestorRegistros {
    private List<Persona> registros;

    public GestorRegistros() {
        this.registros = new ArrayList<>();
    }

    public void cargarDesdeDB(Connection con) throws Exception {
        registros.clear();
        String sql = "SELECT id, name, age, department FROM emp ORDER BY id;";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            registros.add(new Persona(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("department")
            ));
        }
    }

    public void refrescar(Connection con) throws Exception {
        cargarDesdeDB(con);
    }

    public List<Persona> consultar(List<String> camposMostrar, String campoCondicion,
                                   String valorCondicion, String campoOrden,
                                   boolean descendente, int limite) {

        Stream<Persona> stream = registros.stream().filter(p -> {
            if (campoCondicion == null || valorCondicion == null || valorCondicion.isEmpty()) return true;
            switch (campoCondicion.toLowerCase()) {
                case "id": return p.getId() == Integer.parseInt(valorCondicion);
                case "nombre": return p.getNombre().equalsIgnoreCase(valorCondicion);
                case "edad": return p.getEdad() == Integer.parseInt(valorCondicion);
                case "departamento": return p.getDepartamento().equalsIgnoreCase(valorCondicion);
            }
            return true;
        });

        if (campoOrden != null) {
            Comparator<Persona> cmp = null;
            switch (campoOrden.toLowerCase()) {
                case "id": cmp = Comparator.comparing(Persona::getId); break;
                case "nombre": cmp = Comparator.comparing(Persona::getNombre); break;
                case "edad": cmp = Comparator.comparing(Persona::getEdad); break;
                case "departamento": cmp = Comparator.comparing(Persona::getDepartamento); break;
            }
            if (cmp != null) stream = descendente ? stream.sorted(cmp.reversed()) : stream.sorted(cmp);
        }

        if (limite > 0) stream = stream.limit(limite);
        List<Persona> resultado = stream.collect(Collectors.toList());

        for (Persona p : resultado) {
            if (camposMostrar.contains("id")) System.out.print("ID: " + p.getId() + " ");
            if (camposMostrar.contains("nombre")) System.out.print("Nombre: " + p.getNombre() + " ");
            if (camposMostrar.contains("edad")) System.out.print("Edad: " + p.getEdad() + " ");
            if (camposMostrar.contains("departamento")) System.out.print("Departamento: " + p.getDepartamento() + " ");
            System.out.println();
        }
        return resultado;
    }
}
