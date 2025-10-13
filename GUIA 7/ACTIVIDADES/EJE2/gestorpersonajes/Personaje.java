package gestorpersonajes;

public class Personaje {
    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private int alcance;
    private int nivel; // Nuevo atributo

    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        if (vida <= 0 || ataque <= 0 || defensa <= 0 || alcance <= 0)
            throw new IllegalArgumentException("Todos los valores deben ser mayores que cero.");

        this.nombre = nombre;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.alcance = alcance;
        this.nivel = 1; // Nivel inicial
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getAlcance() { return alcance; }
    public int getNivel() { return nivel; }

    public void setVida(int vida) { this.vida = vida; }
    public void setAtaque(int ataque) { this.ataque = ataque; }
    public void setDefensa(int defensa) { this.defensa = defensa; }
    public void setAlcance(int alcance) { this.alcance = alcance; }

    // Subir nivel: aumenta los atributos un 10% por nivel
    public void subirNivel() {
        nivel++;
        vida += Math.max(1, vida * 0.1);
        ataque += Math.max(1, ataque * 0.1);
        defensa += Math.max(1, defensa * 0.1);
        alcance += Math.max(1, alcance * 0.05);
    }

    @Override
    public String toString() {
        return nombre + "," + vida + "," + ataque + "," + defensa + "," + alcance + "," + nivel;
    }

    public static Personaje fromString(String linea) {
        String[] datos = linea.split(",");
        if (datos.length >= 5) {
            String nombre = datos[0];
            int vida = Integer.parseInt(datos[1]);
            int ataque = Integer.parseInt(datos[2]);
            int defensa = Integer.parseInt(datos[3]);
            int alcance = Integer.parseInt(datos[4]);
            Personaje p = new Personaje(nombre, vida, ataque, defensa, alcance);
            if (datos.length == 6) p.nivel = Integer.parseInt(datos[5]);
            return p;
        }
        return null;
    }
}
