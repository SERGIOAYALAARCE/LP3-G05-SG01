public class MainISP {
    public static void main(String[] args) {
        Imprimible impresora = new Impresora();
        impresora.imprimir();

        ImpresoraMultifuncional multifuncional = new ImpresoraMultifuncional();
        multifuncional.imprimir();
        multifuncional.escanear();
    }
}
