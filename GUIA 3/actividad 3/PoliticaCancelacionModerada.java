package reservas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class PoliticaCancelacionModerada implements PoliticaCancelacion {
    
    @Override
    public boolean puedeCancelar(Reserva reserva) {
        LocalDate hoy = LocalDate.now();
        LocalDate checkIn = LocalDate.parse(reserva.getFechaInicio(), 
                                          DateTimeFormatter.ISO_LOCAL_DATE);
        
        long horasHastaCheckIn = ChronoUnit.HOURS.between(hoy.atStartOfDay(), 
                                                         checkIn.atStartOfDay());
        
        return horasHastaCheckIn >= 72;
    }
    
    @Override
    public double calcularPenalizacion(Reserva reserva) {
        if (puedeCancelar(reserva)) {
            return 0.0;
        }
        return reserva.getHabitacion().getPrecioBase() * 0.5; // 50% de penalización
    }
    
    @Override
    public String getDescripcion() {
        return "Cancelación moderada: Permite cancelar hasta 72 horas antes con 50% de penalización";
    }
}