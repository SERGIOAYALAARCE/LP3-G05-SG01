package ejercicio1;

import java.io.*;
class VocalException extends Exception {
    public VocalException(char c) {
        super("Se ingresó una vocal: " + c);
    }
}
class NumeroException extends Exception {
    public NumeroException(char c) {
        super("Se ingresó un número: " + c);
    }
}
class BlancoException extends Exception {
    public BlancoException() {
        super("Se ingresó un espacio en blanco");
    }
}

class SalidaException extends Exception {
    public SalidaException() {
        super("Carácter de salida detectado. Fin del programa.");
    }
}

class LeerEntrada {
    private Reader stream;

    public LeerEntrada(InputStream fuente) {
        stream = new InputStreamReader(fuente);
    }

    public char getChar() throws IOException {
        return (char) this.stream.read();
    }
}

public class Procesador {
    private LeerEntrada lector;   // atributo de la clase LeerEntrada
    private char ultimoChar;      // almacena el carácter leído

    public Procesador() {
        lector = new LeerEntrada(System.in);
    }

    public void procesar() throws Exception {
        ultimoChar = lector.getChar();

        // Ignorar saltos de línea
        if (ultimoChar == '\n' || ultimoChar == '\r') {
            return;
        }

        if ("aeiouAEIOU".indexOf(ultimoChar) != -1) {
            throw new VocalException(ultimoChar);
        } else if (Character.isDigit(ultimoChar)) {
            throw new NumeroException(ultimoChar);
        } else if (Character.isWhitespace(ultimoChar)) {
            throw new BlancoException();
        } else if (ultimoChar == 'q' || ultimoChar == 'Q') { // salida
            throw new SalidaException();
        } else {
            System.out.println("Carácter aceptado: " + ultimoChar);
        }
    }
    public static void main(String[] args) {
        Procesador proc = new Procesador();
        System.out.println("Ingrese caracteres (Q para salir):");

        while (true) {
            try {
                proc.procesar();
            } catch (VocalException | NumeroException | BlancoException e) {
                System.out.println(e.getMessage());
            } catch (SalidaException e) {
                System.out.println(e.getMessage());
                break;
            } catch (IOException e) {
                System.out.println("Error de entrada/salida: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Excepción inesperada: " + e.getMessage());
            }
        }
    }
}
