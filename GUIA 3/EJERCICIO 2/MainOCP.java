public class MainOCP {
    public static void main(String[] args) {
        Forma[] formas = { new Circulo(), new Rectangulo(), new Triangulo() };
        for (Forma f : formas) {
            f.dibujar();
        }
    }
}
