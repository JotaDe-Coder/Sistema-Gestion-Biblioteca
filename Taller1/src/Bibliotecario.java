public class Bibliotecario extends Persona {
    private int nombreBibliotecario;

    public Bibliotecario(String nombre, String apellido) {
        super(nombre, apellido);
        this.nombreBibliotecario = nombreBibliotecario;
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Nombre de bibliotecario:");
    }

    public int getNombreBibliotecario() {
        return nombreBibliotecario;
    }

    public void setNombreBibliotecario(int nombreBibliotecario) {
        this.nombreBibliotecario = nombreBibliotecario;
    }
    
    public class bibliotecario extends Libro {
        @Override
        public void prestar() {
    }


    }


}
