package Sistema_Gestion_Inventario;

/**
 * Representa un producto de la tienda.
 * Modela el "plano" (clase) a partir del cual se crean los objetos
 * (cada producto real del local).
 */
public class Producto {

    // ---- Atributos públicos (estado del objeto) ----
    public String nombre;
    public String codigo;
    public double precio;
    public int stock;

    public Producto(String nombre, String codigo, double precio, int stock) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.stock = stock;
    }

    /**
     * Vende unidades del producto.
     * Si la cantidad es válida (positiva y no supera el stock disponible),
     * descuenta el stock e informa la venta. En caso contrario, informa el error.
     */
    public void venderUnidades(int cantidad) {
        if (cantidad <= 0) {
            System.out.println("Error: la cantidad a vender debe ser mayor que cero.");
        } else if (cantidad > stock) {
            System.out.println("Error: stock insuficiente para vender " + cantidad
                    + " unidades de " + nombre + ".");
        } else {
            stock -= cantidad;
            System.out.println("Venta realizada: " + cantidad + " unidades de "
                    + nombre + ". Stock restante: " + stock);
        }
    }

    /**
     * Repone stock del producto.
     * Si la cantidad es positiva, la suma al stock e informa la reposición.
     * En caso contrario, informa el error.
     */
    public void reponerStock(int cantidad) {
        if (cantidad > 0) {
            stock += cantidad;
            System.out.println("Reposición registrada: +" + cantidad
                    + " unidades. Stock actual: " + stock);
        } else {
            System.out.println("Error: la cantidad a reponer debe ser mayor que cero.");
        }
    }

    /**
     * Actualiza el precio del producto.
     * El parámetro se llama igual que el atributo (sombreamiento intencional);
     * se usa this.precio para referirse al atributo del objeto.
     */
    public void actualizarPrecio(double precio) {
        double precioAnterior = this.precio;
        this.precio = precio;
        System.out.println("Precio actualizado de " + nombre + ": $" + precioAnterior
                + " -> $" + this.precio);
    }

    /**
     * Imprime todos los datos del producto con formato prolijo.
     */
    public void mostrarFicha() {
        System.out.println("=== Ficha de producto ===");
        System.out.println("Código:   " + codigo);
        System.out.println("Nombre:   " + nombre);
        System.out.println("Precio:   $" + precio);
        System.out.println("Stock:    " + stock);
        System.out.println("=========================");
    }

    // ---- Desafío de extensión (opcional) ----
    /**
     * Reduce el precio un porcentaje dado, validando que esté entre 0 y 100.
     */
    public void aplicarDescuento(double porcentaje) {
        if (porcentaje < 0 || porcentaje > 100) {
            System.out.println("Error: el porcentaje de descuento debe estar entre 0 y 100.");
            return;
        }
        double precioAnterior = precio;
        precio = precio - (precio * porcentaje / 100.0);
        System.out.println("Descuento del " + porcentaje + "% aplicado a " + nombre
                + ": $" + precioAnterior + " -> $" + precio);
    }
}
