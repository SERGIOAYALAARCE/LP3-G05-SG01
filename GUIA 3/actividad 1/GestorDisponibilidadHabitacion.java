package reservas;

import java.util.List;

public class GestorDisponibilidadHabitacion {

    public boolean verificarDisponibilidad(Habitacion habitacion, String fechaInicio, String fechaFin) {
        for (Reserva reserva : habitacion.getReservas()) {
            if (reserva.chocaConRango(fechaInicio, fechaFin)) {
                return false;
            }
        }
        return true;
    }

    public void marcarComoReservada(Habitacion habitacion) {
        habitacion.setDisponible(false);
    }

    public void marcarComoDisponible(Habitacion habitacion) {
        habitacion.setDisponible(true);
    }
}
