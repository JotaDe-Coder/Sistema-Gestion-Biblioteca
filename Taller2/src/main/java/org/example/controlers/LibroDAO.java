package org.example.controlers;

import org.example.connection.DatabaseConnection;
import org.example.entities.Libro;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LibroDAO {
    private DatabaseConnection databaseConnection;

    public LibroDAO(){
        this.databaseConnection = new DatabaseConnection();
    }

    //Metodo para guardar datos nuevos libros

    public void crearLibro(Libro libro){
        Transaction transaction = null;

        try (Session session = databaseConnection.getSession()){
            transaction = session.beginTransaction();
            session.save(libro);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //Metodo para obtener un libro por id

    public Libro obtenerLibroId(int id){
        try(Session session = databaseConnection.getSession()){
            return session.get(Libro.class, id);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Metodo para obtener todos los libros

    public List<Libro> obtenerLibros(){
        try(Session session = databaseConnection.getSession()){
            return session.createQuery("FROM libro", Libro.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Metodo para actualizar libro

    public void actualizarLibro(Libro libro){
        Transaction transaction = null;
        try(Session session = databaseConnection.getSession()){
            transaction = session.beginTransaction();
            session.update(libro);
            transaction.commit();

        }catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //Metodo para eliminar libro

    public void eliminarLibro(int id) {
        Transaction transaction = null;
        try (Session session = databaseConnection.getSession()) {
            transaction = session.beginTransaction();
            Libro libro = session.get(Libro.class, id);
            if (libro != null) {
                session.delete(libro);
                System.out.println("Libro eliminado exitosamente.");
                transaction.commit();
            } else {
                System.out.println("No se encontró el libro con el Id proporcionado.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}