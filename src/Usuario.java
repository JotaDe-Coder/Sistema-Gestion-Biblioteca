public class Usuario extends Persona {
    private int nombreUsuario;

    public Usuario(String nombre, String apellido) {
        super(nombre, apellido);
    }

    @Override
    public void mostrarDatos() {
        System.out.println("Nombre de usuario:");
    }

    public int getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(int nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

}
