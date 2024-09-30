package org.example.controlers;

import org.example.connection.DatabaseConnection;
import org.example.entities.Prestamo;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PrestamoDAO {
    private DatabaseConnection databaseConnection;

    public PrestamoDAO(){
        this.databaseConnection = new DatabaseConnection();
    }

    //Metodo para guardar datos nuevos prestamos

    public void crearPrestamo(Prestamo prestamo){
        Transaction transaction = null;

        try (Session session = databaseConnection.getSession()){
            transaction = session.beginTransaction();
            session.save(prestamo);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //Metodo para obtener un prestamo por id

    public Prestamo obtenerPrestamoId(int id){
        try(Session session = databaseConnection.getSession()){
            return session.get(Prestamo.class, id);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Metodo para obtener todos los prestamos

    public List<Prestamo> obtenerPrestamo(){
        try(Session session = databaseConnection.getSession()){
            return session.createQuery("FROM prestamo", Prestamo.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Metodo para actualizar prestamos

    public void actualizarPrestamo(Prestamo prestamo){
        Transaction transaction = null;
        try(Session session = databaseConnection.getSession()){
            transaction = session.beginTransaction();
            session.update(prestamo);
            transaction.commit();

        }catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //Metodo para eliminar prestamos

    public void eliminarPrestamo(int id){
        Transaction transaction = null;
        try(Session session = databaseConnection.getSession()){
            transaction = session.beginTransaction();
            Prestamo prestamo = session.get(Prestamo.class, id);
            if(prestamo != null){
                session.delete(prestamo);
                transaction.commit();
            }
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}