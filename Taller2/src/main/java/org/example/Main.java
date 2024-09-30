package org.example;

import org.example.connection.DatabaseConnection;
import org.example.controlers.LibroDAO;
import org.example.controlers.PersonaDAO;
import org.example.controlers.PrestamoDAO;
import org.example.controlers.UsuarioDAO;
import org.example.data.Data;
import org.example.entities.Libro;
import org.example.entities.Persona;
import org.example.entities.Prestamo;
import org.example.entities.Usuario;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        DatabaseConnection dbconnect = new DatabaseConnection();
        dbconnect.connectDb();
        Data addData = new Data();
        addData.enterData();

        Scanner scanner = new Scanner(System.in);

        // Crear una nueva Persona

        System.out.println("Ingrese los datos para crear a la persona:");
        Persona persona = new Persona();

        System.out.println("Ingrese el nombre:");
        String nombre = scanner.nextLine();
        persona.setNombre(nombre);

        System.out.println("Ingrese el apellido:");
        String apellido = scanner.nextLine();
        persona.setApellido(apellido);

        System.out.println("Ingrese el sexo:");
        String sexo = scanner.nextLine();
        persona.setSexo(sexo);

        // Crear instancia del DAO y guardar la persona

        PersonaDAO personaDao = new PersonaDAO();
        personaDao.crearPersona(persona);

        System.out.println("Persona guardada exitosamente.");


        // Crear un nuevo Usuario

        System.out.println("Ingrese los datos para crear el usuario:");
        Usuario usuario = new Usuario();

        System.out.println("Ingrese el rol:");
        String rol = scanner.nextLine();
        usuario.setRol(rol);

        usuario.setIdPersona(persona);

        // Crear instancia del DAO y guardar el usuario

        UsuarioDAO usuarioDao = new UsuarioDAO();
        usuarioDao.crearUsuario(usuario);

        System.out.println("Usuario guardado exitosamente.");


        // Crear un nuevo Libro

        System.out.println("Ingrese los datos para crear el Libro:");
        Libro libro = new Libro();

        System.out.println("Ingrese el titulo del libro:");
        String titulo = scanner.nextLine();
        libro.setTitulo(titulo);

        System.out.println("Ingrese el autor del libro:");
        String autor = scanner.nextLine();
        libro.setAutor(autor);

        System.out.println("Ingrese el isbn del libro:");
        String isbn = scanner.nextLine();
        libro.setIsbn(isbn);


        // Crear instancia del DAO y guardar el libro

        LibroDAO libroDao = new LibroDAO();
        libroDao.crearLibro(libro);

        System.out.println("Libro guardado exitosamente.");

        // Eliminar libro

        System.out.println("Ingrese el Id del libro a eliminar:");
        int libroIdStr2 = scanner.nextInt();

        LibroDAO libroDao2 = new LibroDAO();
        libroDao.eliminarLibro(libroIdStr2);


        // Actualizar el Libro

        System.out.println("Ingrese el Id del libro a actualizar:");
        int libroIdStr = scanner.nextInt();
        Libro libroActualizar = libroDao.obtenerLibroId(libroIdStr);

        if (libroActualizar != null) {
            System.out.println("Ingrese el nuevo título (dejar en blanco si no desea cambiarlo):");
            String nuevoTitulo = scanner.nextLine();
            if (!nuevoTitulo.isEmpty()) {
                libroActualizar.setTitulo(nuevoTitulo);
            }

            System.out.println("Ingrese el nuevo autor (dejar en blanco si no desea cambiarlo):");
            String nuevoAutor = scanner.nextLine();
            if (!nuevoAutor.isEmpty()) {
                libroActualizar.setAutor(nuevoAutor);
            }

            System.out.println("Ingrese el nuevo ISBN (dejar en blanco si no desea cambiarlo):");
            String nuevoIsbn = scanner.nextLine();
            if (!nuevoIsbn.isEmpty()) {
                libroActualizar.setIsbn(nuevoIsbn);
            }

            libroDao.actualizarLibro(libroActualizar);

            System.out.println("Libro actualizado exitosamente.");
        } else {
            System.out.println("No se encontró el libro con el ID especificado.");
        }
    }
}