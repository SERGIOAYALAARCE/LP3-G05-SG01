package eje1;

public class Coche {
    private String marca;
    private String modelo;
    private int añoFabricacion;
    private double precio;
    private boolean encendido;
    
    public Coche() {
        this.marca = "Desconocida";
        this.modelo = "Genérico";
        this.añoFabricacion = 2000;
        this.precio = 10000;
        this.encendido = false;
    }

    public Coche(String marca, String modelo, int añoFabricacion, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.añoFabricacion = añoFabricacion;
        this.precio = precio;
        this.encendido = false;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAñoFabricacion() {
        return añoFabricacion;
    }

    public void setAñoFabricacion(int añoFabricacion) {
        this.añoFabricacion = añoFabricacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean aplicarDescuento(double descuento) {
        if (this.añoFabricacion < 2010) {
            this.precio -= descuento;
            System.out.println("Descuento aplicado. Nuevo precio: " + this.precio);
            return true;
        } else {
            System.out.println("No se aplicó descuento, el coche no es antiguo.");
            return false;
        }
    }


    public void encender() {
        if (!encendido) {
            encendido = true;
            System.out.println(marca + " " + modelo + " ha sido encendido.");
        } else {
            System.out.println(marca + " " + modelo + " ya estaba encendido.");
        }
    }

    public void apagar() {
        if (encendido) {
            encendido = false;
            System.out.println(marca + " " + modelo + " ha sido apagado.");
        } else {
            System.out.println(marca + " " + modelo + " ya estaba apagado.");
        }
    }

    public void acelerar() {
        if (encendido) {
            System.out.println(marca + " " + modelo + " está acelerando.");
        } else {
            System.out.println("No puedes acelerar, el coche está apagado.");
        }
    }

    public void frenar() {
        if (encendido) {
            System.out.println(marca + " " + modelo + " está frenando.");
        } else {
            System.out.println("No puedes frenar, el coche está apagado.");
        }
    }
}
