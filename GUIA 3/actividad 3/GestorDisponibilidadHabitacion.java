package reservas;

import java.util.List;


public class GestorDisponibilidadHabitacion {

    public boolean verificarDisponibilidad(HabitacionBase habitacion, String fechaInicio, String fechaFin) {
        for (Reserva reserva : habitacion.getReservas()) {
            if (reserva.chocaConRango(fechaInicio, fechaFin)) {
                return false;
            }
        }
        return true;
    }

    public void marcarComoReservada(HabitacionBase habitacion) {
        habitacion.setDisponible(false);
    }

    public void marcarComoDisponible(HabitacionBase habitacion) {
        habitacion.setDisponible(true);
    }
}