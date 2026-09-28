package Sistema_Gestion_Biblioteca;

/**
 * Desafío de extensión: versión mínima e inmutable de un portador de datos
 * equivalente a Libro, pero solo con título, autor e isbn, y sin operaciones
 * de préstamo. Al ser un record, el compilador genera automáticamente el
 * constructor, los accesores (titulo(), autor(), isbn()), equals, hashCode
 * y toString, y todos los campos son implícitamente private y final.
 */
public record LibroInmutable(String titulo, String autor, String isbn) {
}
