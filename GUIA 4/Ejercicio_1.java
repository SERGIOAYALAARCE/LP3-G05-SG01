import java.io.IOException;

class ExcepcionVocal extends Exception {
    public ExcepcionVocal(char c) {
        super("Se ingresó una vocal: " + c);
    }
}

class ExcepcionNumero extends Exception {
    public ExcepcionNumero(char c) {
        super("Se ingresó un número: " + c);
    }
}

class ExcepcionBlanco extends Exception {
    public ExcepcionBlanco() {
        super("Se ingresó un espacio en blanco");
    }
}

class ExcepcionSalida extends Exception {
    public ExcepcionSalida() {
        super("Se ingresó el carácter de salida. Fin del programa.");
    }
}

class LeerEntrada {
    public char getChar() throws IOException {
        return (char) System.in.read(); // Lee un caracter desde teclado
    }
}

public class Ejercicio1 {
    private LeerEntrada lector = new LeerEntrada();

    public void procesar() throws Exception {
        char c = lector.getChar();

        if ("aeiouAEIOU".indexOf(c) != -1) {
            throw new ExcepcionVocal(c);
        } else if (Character.isDigit(c)) {
            throw new ExcepcionNumero(c);
        } else if (Character.isWhitespace(c)) {
            throw new ExcepcionBlanco();
        } else if (c == 'q' || c == 'Q') { // carácter de salida
            throw new ExcepcionSalida();
        } else {
            System.out.println("Carácter ingresado: " + c);
        }
    }

    public static void main(String[] args) {
        Ejercicio1 app = new Ejercicio1();
        while (true) {
            try {
                app.procesar();
            } catch (ExcepcionVocal | ExcepcionNumero | ExcepcionBlanco e) {
                System.out.println(e.getMessage());
            } catch (ExcepcionSalida e) {
                System.out.println(e.getMessage());
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
