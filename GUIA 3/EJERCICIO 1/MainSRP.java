public class MainSRP {
    public static void main(String[] args) {
        Empleado emp = new Empleado("Juan", 2500, "Sistemas");
        CalculadoraPago calculadora = new CalculadoraPago();
        System.out.println("Pago mensual: " + calculadora.calcularPagoMensual(emp));
    }
}
