import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] freq = new int[6];
        Random r = new Random();
        for (int i = 0; i < 20000; i++) {
            int cara = r.nextInt(6); // 0 a 5
            freq[cara]++;
        }
        for (int i = 0; i < 6; i++)
            System.out.println("Cara " + (i+1) + ": " + freq[i]);
    }
}
