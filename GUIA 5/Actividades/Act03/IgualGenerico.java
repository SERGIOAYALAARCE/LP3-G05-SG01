package Sesion05.Actividades.Act03;

public class IgualGenerico {
    public static <T> boolean esIgualA(T obj1, T obj2) {
        // Verifica si ambos objetos son null
        if (obj1 == null && obj2 == null) {
            return true; // Ambos son null, son iguales
        }
        // Si solo uno de ellos es null, no son iguales
        if (obj1 == null || obj2 == null) {
            return false;
        }
        // Comparar los objetos
        return obj1.equals(obj2);
    }

    public static void main(String[] args) {
        System.out.println(esIgualA(1, 1)); // true
        System.out.println(esIgualA("Hola", "Hola")); // true
        System.out.println(esIgualA(null, "Hola")); // false
        System.out.println(esIgualA(null, null)); // true
    }
}
