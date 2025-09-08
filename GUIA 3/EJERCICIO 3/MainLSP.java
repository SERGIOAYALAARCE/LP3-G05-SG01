public class MainLSP {
    public static void main(String[] args) {
        Vehiculo v1 = new Coche();
        Vehiculo v2 = new Bicicleta();

        v1.acelerar();
        v2.acelerar();
    }
}
