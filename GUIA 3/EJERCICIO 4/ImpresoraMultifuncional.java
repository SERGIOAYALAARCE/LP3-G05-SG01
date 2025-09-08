public class ImpresoraMultifuncional implements Imprimible, Escaneable {
    @Override
    public void imprimir() {
        System.out.println("Imprimiendo desde multifuncional...");
    }

    @Override
    public void escanear() {
        System.out.println("Escaneando documento...");
    }
}
