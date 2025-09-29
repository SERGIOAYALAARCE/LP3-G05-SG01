public class PruebaPar {
    public static void main(String[] args) {
        Par<String, Integer> p1 = new Par<>("A", 1);
        Par<String, Integer> p2 = new Par<>("A", 1);
        Par<String, Integer> p3 = new Par<>("A", 2);
        Par<String, Integer> p4 = new Par<>("B", 1);

        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);
        System.out.println("p3: " + p3);
        System.out.println("p4: " + p4);

        System.out.println("\np1.esIgual(p2) -> " + p1.esIgual(p2)); // true
        System.out.println("p1.esIgual(p3) -> " + p1.esIgual(p3));   // false
        System.out.println("p1.esIgual(p4) -> " + p1.esIgual(p4));   // false
        System.out.println("p1.esIgual(null) -> " + p1.esIgual(null)); // false
    }
}
