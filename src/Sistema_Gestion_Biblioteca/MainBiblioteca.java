package Sistema_Gestion_Biblioteca;

/**
 * Punto de entrada del programa. Instancia libros combinando ambos
 * constructores, demuestra rechazos de datos inválidos, agota copias
 * con prestar()/devolver() y ejercita el setter validado.
 */
public class MainBiblioteca {

    public static void main(String[] args) {

        // ---- Rechazo 1: título inválido queda reemplazado por el valor por defecto ----
        Libro libroInvalido = new Libro("", "Autor de Prueba", "0000000000000", 1, 12000.0);
        if (!libroInvalido.getTitulo().equals("Sin título")) {
            System.out.println("ERROR: no se aplicó el título por defecto esperado.");
        }

        // ---- Rechazo 2: setPrecioReposicion con un valor inválido no modifica el precio ----
        double precioPrevio = libroInvalido.getPrecioReposicion();
        boolean precioAceptado = libroInvalido.setPrecioReposicion(-100.0);
        System.out.println("¿Se aceptó el precio " + (-100.0) + "? " + precioAceptado
                + " (se mantiene el precio anterior)");
        if (precioAceptado || libroInvalido.getPrecioReposicion() != precioPrevio) {
            System.out.println("ERROR: el precio inválido no debería haberse aplicado.");
        }

        // Al declarar los dos constructores propios (canónico y de conveniencia),
        // el constructor sin argumentos que el compilador regalaba automáticamente
        // dejó de existir:
        // new Libro(); // no compila

        // ---- Instanciación combinando ambos constructores ----
        Libro libro1 = new Libro("Clean Code", "Robert C. Martin", "9780132350884"); // constructor de conveniencia
        Libro libro2 = new Libro("Efectivo con Java", "Ana Restrepo", "9781234567897", 3, 22000.0); // canónico
        Libro libro3 = new Libro("Cien Años de Soledad", "Gabriel García Márquez", "9780307474728", 2, 18500.0); // canónico

        libro1.mostrarFicha();
        libro2.mostrarFicha();
        libro3.mostrarFicha();

        // ---- Agotar copias de libro1 (arranca con 1 copia) ----
        boolean prestamo1 = libro1.prestar(); // true: quedaba 1 copia
        boolean prestamo2 = libro1.prestar(); // false: ya no quedan copias, no debe quedar negativo
        if (!prestamo1 || prestamo2 || libro1.getCopiasDisponibles() < 0) {
            System.out.println("ERROR: el control de copias de prestar() no se comportó como se esperaba.");
        }
        libro1.devolver();

        // ---- Setter validado con un valor correcto ----
        double precioAnterior = libro1.getPrecioReposicion();
        if (libro1.setPrecioReposicion(18000.0)) {
            System.out.println("Precio de reposición actualizado de \"" + libro1.getTitulo() + "\": $"
                    + precioAnterior + " -> $" + libro1.getPrecioReposicion());
        }

        // ---- Desafío de extensión: préstamos históricos ----
        System.out.println();
        System.out.println("Préstamos históricos de \"" + libro1.getTitulo() + "\": "
                + libro1.getPrestamosHistoricos() + " (incluye el intento rechazado)");
    }
}
