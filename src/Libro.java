public class Libro {
    private String titulo;
    private String autor;
    private String isbn;
    private int disponible;

    public Libro() {

    }

    public Libro(String titulo, String autor, String isbn, int disponible ) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponible = disponible;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = Integer.parseInt(disponible);
    }

    public void prestar() {
    }

    public void devolver() {
    }
}
