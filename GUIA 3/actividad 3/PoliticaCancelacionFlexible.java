package reservas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class PoliticaCancelacionFlexible implements PoliticaCancelacion {
    
    @Override
    public boolean puedeCancelar(Reserva reserva) {
        LocalDate hoy = LocalDate.now();
        LocalDate checkIn = LocalDate.parse(reserva.getFechaInicio(), 
                                          DateTimeFormatter.ISO_LOCAL_DATE);
        
        long horasHastaCheckIn = ChronoUnit.HOURS.between(hoy.atStartOfDay(), 
                                                         checkIn.atStartOfDay());
        
        return horasHastaCheckIn >= 24;
    }
    
    @Override
    public double calcularPenalizacion(Reserva reserva) {
        return 0.0; // Sin penalización si se cancela a tiempo
    }
    
    @Override
    public String getDescripcion() {
        return "Cancelación flexible: Permite cancelar hasta 24 horas antes sin penalización";
    }
}