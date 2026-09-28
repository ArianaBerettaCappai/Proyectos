package Sistema_Gestion_Inventario;

/**
 * Punto de entrada del programa. Instancia varios objetos Producto
 * y ejercita sus métodos para demostrar:
 *  - Atributos (estado) y métodos (comportamiento).
 *  - Independencia entre objetos creados con "new".
 *  - Uso de "this" para resolver el sombreamiento de actualizarPrecio.
 *  - Diferencia entre copiar una referencia y crear un objeto nuevo (aliasing).
 */
public class MainInventario {

    public static void main(String[] args) {

        // ---- 1) Instanciación de al menos tres objetos Producto distintos ----
        Producto productoUno = new Producto("Teclado mecánico", "P-001", 45000.0, 12);
        Producto productoDos = new Producto("Mouse inalámbrico", "P-002", 15000.0, 30);
        Producto productoTres = new Producto("Monitor 24 pulgadas", "P-003", 180000.0, 8);

        System.out.println("---- Fichas iniciales ----");
        productoUno.mostrarFicha();
        productoDos.mostrarFicha();
        productoTres.mostrarFicha();
        System.out.println();

        // ---- 2) Operaciones sobre productoUno (venta, error, reposición, precio) ----
        System.out.println("---- Operaciones sobre productoUno ----");
        productoUno.venderUnidades(3);
        productoUno.venderUnidades(50);   // provoca error: stock insuficiente
        productoUno.reponerStock(20);
        productoUno.actualizarPrecio(39900.0);
        System.out.println();

        // ---- 3) Verificación de independencia entre objetos ----
        System.out.println("---- Verificación de independencia entre objetos ----");
        System.out.println("Stock de productoDos (no debería cambiar): " + productoDos.stock);
        System.out.println("Stock de productoTres (no debería cambiar): " + productoTres.stock);
        System.out.println();

        System.out.println("---- Operaciones sobre productoDos y productoTres ----");
        productoDos.venderUnidades(10);
        productoDos.reponerStock(5);
        productoTres.venderUnidades(2);
        productoTres.actualizarPrecio(165000.0);
        System.out.println();

        // ---- 4) Aliasing: copiar una referencia vs. crear un objeto nuevo ----
        System.out.println("---- Aliasing: referencia vs. objeto nuevo ----");
        Producto copia = productoUno;
        copia.stock = 29;
        // productoUno.stock también es 29: copia y productoUno son la MISMA
        // referencia, apuntando al mismo objeto en el Heap.
        System.out.println("Stock de productoUno tras modificar copia: "
                + productoUno.stock + " (mismo objeto en el Heap)");
        System.out.println("¿copia y productoUno son el mismo objeto? " + (copia == productoUno));
        System.out.println();

        // ---- 5) Desafío de extensión: descuento + arreglo de productos ----
        System.out.println("---- Desafío de extensión ----");
        productoTres.aplicarDescuento(10);
        productoTres.aplicarDescuento(150); // caso inválido: fuera de 0-100

        Producto[] productos = { productoUno, productoDos, productoTres };
        System.out.println();
        System.out.println("---- Fichas finales ----");
        for (Producto p : productos) {
            p.mostrarFicha();
        }
    }
}
