import java.util.Scanner;

public class ConvertirSegundos {

    // Método para convertir horas, minutos y segundos en segundos
    public static int convertirASegundos(int horas, int minutos, int segundos) {
        return horas * 3600 + minutos * 60 + segundos;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese horas: ");
        int h = sc.nextInt();
        System.out.print("Ingrese minutos: ");
        int m = sc.nextInt();
        System.out.print("Ingrese segundos: ");
        int s = sc.nextInt();

        int totalSegundos = convertirASegundos(h, m, s);
        System.out.println("Equivalente en segundos: " + totalSegundos);

        sc.close();
    }
}
