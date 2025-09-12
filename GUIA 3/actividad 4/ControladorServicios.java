package reservas;

public class ControladorServicios {

    public void solicitarServicioLimpieza(HabitacionBase habitacion) {
        if (habitacion instanceof ServicioLimpieza) {
            ServicioLimpieza servicio = (ServicioLimpieza) habitacion;
            servicio.solicitarLimpieza();
            System.out.println("Costo: $" + servicio.calcularCostoLimpieza());
        } else {
            System.out.println("Servicio de limpieza no disponible para esta habitación");
        }
    }

    public void solicitarServicioComida(HabitacionBase habitacion, String tipoComida) {
        if (habitacion instanceof ServicioComida) {
            ServicioComida servicio = (ServicioComida) habitacion;
            
            switch (tipoComida.toLowerCase()) {
                case "desayuno":
                    servicio.solicitarDesayuno();
                    break;
                case "almuerzo":
                    servicio.solicitarAlmuerzo();
                    break;
                case "cena":
                    servicio.solicitarCena();
                    break;
                default:
                    System.out.println("Tipo de comida no válido");
                    return;
            }
            System.out.println("Precio promedio del menú: $" + servicio.obtenerMenuPrecios());
        } else {
            System.out.println("Servicio de comida no disponible para esta habitación");
        }
    }

    public void solicitarServicioLavanderia(HabitacionBase habitacion, int prendas) {
        if (habitacion instanceof ServicioLavanderia) {
            ServicioLavanderia servicio = (ServicioLavanderia) habitacion;
            servicio.solicitarLavadoRopa();
            System.out.println("Costo para " + prendas + " prendas: $" + servicio.calcularCostoLavanderia(prendas));
        } else {
            System.out.println("Servicio de lavandería no disponible para esta habitación");
        }
    }

    public void solicitarServicioSpa(HabitacionBase habitacion, String tipoServicio, String detalles) {
        if (habitacion instanceof ServicioSpa) {
            ServicioSpa servicio = (ServicioSpa) habitacion;
            
            if (tipoServicio.equalsIgnoreCase("masaje")) {
                servicio.reservarMasaje(detalles, "14:00");
            } else if (tipoServicio.equalsIgnoreCase("facial")) {
                servicio.reservarTratamientoFacial(detalles);
            }
            System.out.println("Precio base spa: $" + servicio.obtenerPrecioSpa());
        } else {
            System.out.println("Servicio de spa no disponible para esta habitación");
        }
    }

    public void mostrarServiciosDisponibles(HabitacionBase habitacion) {
        System.out.println("\nServicios disponibles para habitación " + habitacion.getId() + ":");
        
        if (habitacion instanceof ServicioLimpieza) {
            System.out.println("✓ Servicio de limpieza");
        }
        if (habitacion instanceof ServicioComida) {
            System.out.println("✓ Servicio de comida");
        }
        if (habitacion instanceof ServicioLavanderia) {
            System.out.println("✓ Servicio de lavandería");
        }
        if (habitacion instanceof ServicioSpa) {
            System.out.println("✓ Servicio de spa");
        }
    }
}