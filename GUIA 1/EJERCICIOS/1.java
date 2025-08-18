public class Main {
    public static int sumaArreglo(int[] arr) {
        int suma = 0;
        for (int num : arr) {
            suma += num;
        }
        return suma;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        System.out.println(sumaArreglo(arr));
    }
}
