public class Pedido {
    private String nombrePlato;
    private String tipo;
    private String estado; // pendiente, completo, eliminado

    public Pedido(String nombrePlato, String tipo) {
        this.nombrePlato = nombrePlato;
        this.tipo = tipo;
        this.estado = "pendiente";
    }

    public void marcarCompleto() {
        this.estado = "completo";
    }

    public void eliminar() {
        this.estado = "eliminado";
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return nombrePlato + " (" + tipo + ") - Estado: " + estado;
    }
}
