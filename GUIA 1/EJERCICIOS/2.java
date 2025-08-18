import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            int num;
            do {
                System.out.print("Ingrese número " + (i+1) + ": ");
                num = sc.nextInt();
            } while (i > 0 && num <= arr[i-1]);
            arr[i] = num;
        }
    }
}
