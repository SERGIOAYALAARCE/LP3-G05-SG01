package mvc3;

import java.util.List;
import java.util.Scanner;

public class InventarioView {
    private Scanner scanner;

    public InventarioView() {
        this.scanner = new Scanner(System.in);
    }

    // Métodos originales del inventario
    public void mostrarInventario(List<InventarioModel.Item> items) {
        System.out.println("\n=== INVENTARIO ===");
        if (items.isEmpty()) {
            System.out.println("El inventario está vacío");
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i));
            }
        }
        System.out.println("==================");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarDetallesItem(InventarioModel.Item item) {
        if (item != null) {
            System.out.println("\n=== DETALLES DEL ÍTEM ===");
            System.out.println("Nombre: " + item.getNombre());
            System.out.println("Cantidad: " + item.getCantidad());
            System.out.println("Tipo: " + item.getTipo());
            System.out.println("Descripción: " + item.getDescripcion());
            System.out.println("==========================");
        } else {
            System.out.println("Ítem no encontrado");
        }
    }

    public int mostrarMenu() {
        System.out.println("\n=== SISTEMA DE GESTIÓN DE INVENTARIO ===");
        System.out.println("1. Agregar ítem");
        System.out.println("2. Eliminar ítem");
        System.out.println("3. Ver inventario completo");
        System.out.println("4. Buscar ítem por nombre");
        System.out.println("5. Mostrar detalles de ítem");
        System.out.println("6. Usar ítem");
        System.out.println("7. Sistema de Combate");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opción: ");
        
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public InventarioModel.Item solicitarDatosItem() {
        System.out.print("Nombre del ítem: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Tipo (Arma/Poción): ");
        String tipo = scanner.nextLine();
        
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();
        
        return new InventarioModel.Item(nombre, cantidad, tipo, descripcion);
    }

    public String solicitarNombreItem() {
        System.out.print("Ingrese el nombre del ítem: ");
        return scanner.nextLine();
    }

    public int solicitarIndiceItem() {
        System.out.print("Ingrese el número del ítem: ");
        try {
            return Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Métodos para el sistema de combate
    public void mostrarEstadoCombate(InventarioModel.Jugador jugador, List<InventarioModel.Enemigo> enemigos) {
        System.out.println("\n=== ESTADO DEL COMBATE ===");
        System.out.println("JUGADOR:");
        System.out.println("  " + jugador.getNombre() + " | Salud: " + jugador.getSalud() + "/" + jugador.getSaludMaxima() + 
                          " | Nivel: " + jugador.getNivel());
        System.out.println("  Arma equipada: " + 
            (jugador.getItemEquipado() != null ? jugador.getItemEquipado().getNombre() : "Ninguna"));

        System.out.println("\nENEMIGOS:");
        for (int i = 0; i < enemigos.size(); i++) {
            InventarioModel.Enemigo enemigo = enemigos.get(i);
            System.out.println("  " + (i + 1) + ". " + enemigo.getNombre() + " | Salud: " + enemigo.getSalud() + 
                              " | Tipo: " + enemigo.getTipo() + " | Nivel: " + enemigo.getNivel());
        }
        System.out.println("===========================");
    }

    public int mostrarMenuCombate() {
        System.out.println("\n=== TURNO DEL JUGADOR ===");
        System.out.println("1. Atacar");
        System.out.println("2. Usar objeto");
        System.out.println("3. Ver inventario");
        System.out.println("4. Equipar arma");
        System.out.println("5. Huir");
        System.out.print("Seleccione una acción: ");
        
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public int seleccionarEnemigo(int cantidadEnemigos) {
        System.out.print("Seleccione el número del enemigo a atacar (1-" + cantidadEnemigos + "): ");
        try {
            int seleccion = Integer.parseInt(scanner.nextLine()) - 1;
            return (seleccion >= 0 && seleccion < cantidadEnemigos) ? seleccion : -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public int mostrarMenuObjetos(List<InventarioModel.Item> inventario) {
        System.out.println("\n=== INVENTARIO ===");
        if (inventario.isEmpty()) {
            System.out.println("El inventario está vacío");
            return -1;
        }
        
        for (int i = 0; i < inventario.size(); i++) {
            System.out.println((i + 1) + ". " + inventario.get(i));
        }
        System.out.print("Seleccione el objeto a usar (0 para cancelar): ");
        
        try {
            return Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public int mostrarMenuArmas(List<InventarioModel.Item> inventario) {
        System.out.println("\n=== ARMAS DISPONIBLES ===");
        int contador = 1;
        for (int i = 0; i < inventario.size(); i++) {
            if (inventario.get(i).getTipo().equals("Arma")) {
                System.out.println(contador + ". " + inventario.get(i));
                contador++;
            }
        }
        
        if (contador == 1) {
            System.out.println("No tienes armas en el inventario");
            return -1;
        }
        
        System.out.print("Seleccione el arma a equipar (0 para cancelar): ");
        try {
            return Integer.parseInt(scanner.nextLine()) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarResultadoAtaque(String atacante, String objetivo, int daño, boolean critico) {
        String mensaje = atacante + " ataca a " + objetivo + " causando " + daño + " de daño";
        if (critico) {
            mensaje += " ¡GOLPE CRÍTICO!";
        }
        System.out.println(mensaje);
    }

    public void mostrarCuracion(String objetivo, int curacion) {
        System.out.println(objetivo + " se cura " + curacion + " puntos de salud");
    }

    public void mostrarFinCombate(boolean victoria, int experienciaGanada) {
        if (victoria) {
            System.out.println("\n¡VICTORIA! Has ganado el combate.");
            System.out.println("Experiencia ganada: " + experienciaGanada);
        } else {
            System.out.println("\nDERROTA... Has sido vencido en combate.");
        }
    }

    public void cerrarScanner() {
        scanner.close();
    }
}