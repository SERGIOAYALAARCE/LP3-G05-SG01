public class Main {
    public static float menor(float a, float b, float c) {
        return Math.min(a, Math.min(b, c));
    }

    public static void main(String[] args) {
        System.out.println(menor(4.2f, 1.7f, 9.8f));
    }
}
