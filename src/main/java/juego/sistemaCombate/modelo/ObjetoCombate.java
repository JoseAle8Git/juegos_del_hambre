package juego.sistemaCombate.modelo;

public class ObjetoCombate {

    private String nombre;
    private TipoObjeto tipo;
    private int cantidad;

    public ObjetoCombate(String nombre, TipoObjeto tipo, int cantidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoObjeto getTipo() {
        return tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void usar() {
        if (cantidad > 0) {
            cantidad--;
        }
    }

    public void anadir(int cantidadExtra) {
        this.cantidad += cantidadExtra;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(TipoObjeto tipo) {
        this.tipo = tipo;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}