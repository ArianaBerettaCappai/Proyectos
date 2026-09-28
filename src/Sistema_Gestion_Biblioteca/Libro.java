package Sistema_Gestion_Biblioteca;

/**
 * Representa un ejemplar del catálogo de la biblioteca.
 *
 * La clase es "final": el constructor canónico invoca un método de instancia
 * (setPrecioReposicion) antes de que el objeto termine de construirse. Si
 * Libro pudiera extenderse, una subclase podría sobrescribir ese método y
 * ejecutar lógica propia sobre un objeto todavía incompleto, dejándolo en un
 * estado inconsistente. Por eso esta clase no está pensada para heredarse.
 */
public final class Libro {

    private final String titulo;
    private final String autor;
    private final String isbn;
    private int copiasDisponibles;
    private double precioReposicion;

    // Desafío de extensión: cuenta todos los intentos de préstamo, se acepten o no.
    private int prestamosHistoricos;

    /**
     * Constructor canónico: único lugar donde vive la validación completa.
     * Ningún Libro termina de construirse con datos inválidos; siempre gana
     * un valor por defecto seguro, informando por consola qué se rechazó.
     */
    public Libro(String titulo, String autor, String isbn, int copiasDisponibles, double precioReposicion) {
        if (titulo == null || titulo.isBlank()) {
            this.titulo = "Sin título";
            System.out.println("Título inválido, se usó \"Sin título\" por defecto.");
        } else {
            this.titulo = titulo;
        }

        if (autor == null || autor.isBlank()) {
            this.autor = "Autor desconocido";
            System.out.println("Autor inválido, se usó \"Autor desconocido\" por defecto.");
        } else {
            this.autor = autor;
        }

        if (isbn == null || isbn.isBlank()) {
            this.isbn = "ISBN pendiente";
            System.out.println("ISBN inválido, se usó \"ISBN pendiente\" por defecto.");
        } else {
            this.isbn = isbn;
        }

        if (copiasDisponibles < 0) {
            this.copiasDisponibles = 0;
            System.out.println("Copias disponibles inválidas (" + copiasDisponibles + "), se usaron 0 por defecto.");
        } else {
            this.copiasDisponibles = copiasDisponibles;
        }

        // La regla "precio > 0" vive una sola vez, en setPrecioReposicion.
        // El constructor delega ahí; si la rechaza, aplica el valor por defecto.
        if (!setPrecioReposicion(precioReposicion)) {
            this.precioReposicion = 15000.0;
            System.out.println("Precio de reposición inválido (" + precioReposicion + "), se usó $15000.0 por defecto.");
        }
    }

    /**
     * Constructor de conveniencia para el caso frecuente de un libro nuevo:
     * 1 copia disponible y precio de reposición por defecto de $15000.0.
     * Delega TODA la validación en el constructor canónico con this(...).
     */
    public Libro(String titulo, String autor, String isbn) {
        this(titulo, autor, isbn, 1, 15000.0);
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getCopiasDisponibles() {
        return copiasDisponibles;
    }

    public double getPrecioReposicion() {
        return precioReposicion;
    }

    public int getPrestamosHistoricos() {
        return prestamosHistoricos;
    }

    /**
     * Único lugar donde vive la regla "el precio debe ser mayor a 0".
     * No toca el precio anterior si el nuevo valor es inválido.
     */
    public boolean setPrecioReposicion(double precio) {
        if (precio > 0) {
            this.precioReposicion = precio;
            return true;
        }
        return false;
    }

    /**
     * Operación de dominio: intenta prestar un ejemplar.
     * No existe un setter genérico de copias; esta es la única puerta
     * para bajar el contador, y siempre respeta su propia regla.
     */
    public boolean prestar() {
        prestamosHistoricos++;
        if (copiasDisponibles > 0) {
            copiasDisponibles--;
            System.out.println("Préstamo registrado: \"" + titulo + "\". Copias disponibles: " + copiasDisponibles);
            return true;
        }
        System.out.println("Error: no hay copias disponibles de \"" + titulo + "\" para prestar.");
        return false;
    }

    /** Operación de dominio: registra la devolución de un ejemplar. */
    public void devolver() {
        copiasDisponibles++;
        System.out.println("Devolución registrada: \"" + titulo + "\". Copias disponibles: " + copiasDisponibles);
    }

    /** Imprime todos los datos del libro de forma prolija. */
    public void mostrarFicha() {
        System.out.println("=== Ficha de libro ===");
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("ISBN: " + isbn);
        System.out.println("Copias disponibles: " + copiasDisponibles);
        System.out.println("Precio de reposición: $" + precioReposicion);
        System.out.println("=======================");
    }
}
