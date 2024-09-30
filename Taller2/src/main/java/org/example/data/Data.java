package org.example.data;


import org.example.entities.Libro;
import org.example.entities.Persona;
import org.example.entities.Prestamo;
import org.example.entities.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.nio.channels.ScatteringByteChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class Data {
    private SessionFactory sessionFactory;
    private boolean dataInit;

    public Data() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void enterData() throws ParseException {
        if (dataInit) {
            System.out.println("Los datos ya han sido creados");
            return;
        }

        Session session = sessionFactory.openSession();

        try {
            long countPersonas = (long) session.createQuery("SELECT COUNT (p.id) FROM Persona p").uniqueResult();
            if (countPersonas > 0){
                System.out.println("Los datos ya exhisten en la base de datos");
                dataInit = true;
                session.close();
                return;
            }

            session.beginTransaction();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");

            //Persona

            Persona persona1 = new Persona();
            persona1.setNombre("Daniel");
            persona1.setApellido("Amariles");
            persona1.setSexo("Masculino");

            Persona persona2 = new Persona();
            persona2.setNombre("Margarita");
            persona2.setApellido("Murillo");
            persona2.setSexo("Femenino");

            Persona persona3 = new Persona();
            persona3.setNombre("Frank");
            persona3.setApellido("Velasquez");
            persona3.setSexo("Masculino");

            Persona persona4 = new Persona();
            persona4.setNombre("Johanna");
            persona4.setApellido("Patarroyo");
            persona4.setSexo("Femenino");

            Persona persona5 = new Persona();
            persona5.setNombre("Emiliano");
            persona5.setApellido("Tapasco");
            persona5.setSexo("Masculino");

            //Usuario

            Usuario usuario1 = new Usuario();
            usuario1.setIdPersona(persona1);
            usuario1.setRol("Bibliotecario");

            Usuario usuario2 = new Usuario();
            usuario2.setIdPersona(persona2);
            usuario2.setRol("Cliente");

            Usuario usuario3 = new Usuario();
            usuario3.setIdPersona(persona3);
            usuario3.setRol("Cliente");

            Usuario usuario4 = new Usuario();
            usuario4.setIdPersona(persona4);
            usuario4.setRol("Cliente");

            Usuario usuario5 = new Usuario();
            usuario5.setIdPersona(persona5);
            usuario5.setRol("Cliente");

            //Libro

            Libro libro1 = new Libro();
            libro1.setAutor("Gabriel García Márquez");
            libro1.setTitulo("Cien años de soledad");
            libro1.setIsbn("102030");

            Libro libro2 = new Libro();
            libro2.setAutor("Gabriel García Márquez");
            libro2.setTitulo("Crónica de una muerte anunciada");
            libro2.setIsbn("112233");

            Libro libro3 = new Libro();
            libro3.setAutor("Edgar Allan Poe");
            libro3.setTitulo("El Cuervo");
            libro3.setIsbn("122334");

            Libro libro4 = new Libro();
            libro4.setAutor("Stephen King");
            libro4.setTitulo("El Resplandor");
            libro4.setIsbn("132435");

            Libro libro5 = new Libro();
            libro5.setAutor("J.R.R. Tolkien");
            libro5.setTitulo("El Señor de los Anillos");
            libro5.setIsbn("142536");

            //Prestamo

            Prestamo prestamo1 = new Prestamo();
            prestamo1.setIdUsuario(usuario1);
            prestamo1.setIdLibro(libro1);
            prestamo1.setFechaPrestamo(formato.parse("2024-01-01"));
            prestamo1.setFechaDevolucion(formato.parse("2000-01-15"));
            prestamo1.setActivo(false);

            Prestamo prestamo2 = new Prestamo();
            prestamo2.setIdUsuario(usuario2);
            prestamo2.setIdLibro(libro2);
            prestamo2.setFechaPrestamo(formato.parse("2024-01-16"));
            prestamo2.setFechaDevolucion(formato.parse("2024-01-31"));
            prestamo1.setActivo(false);

            Prestamo prestamo3 = new Prestamo();
            prestamo3.setIdUsuario(usuario3);
            prestamo3.setIdLibro(libro3);
            prestamo3.setFechaPrestamo(formato.parse("2024-02-01"));
            prestamo3.setFechaDevolucion(formato.parse("0000-00-00"));
            prestamo1.setActivo(true);

            Prestamo prestamo4 = new Prestamo();
            prestamo4.setIdUsuario(usuario4);
            prestamo4.setIdLibro(libro4);
            prestamo4.setFechaPrestamo(formato.parse("2024-02-16"));
            prestamo4.setFechaDevolucion(formato.parse("2024-02-28"));
            prestamo1.setActivo(false);

            Prestamo prestamo5 = new Prestamo();
            prestamo5.setIdUsuario(usuario5);
            prestamo5.setIdLibro(libro5);
            prestamo5.setFechaPrestamo(formato.parse("2024-03-03"));
            prestamo5.setFechaDevolucion(formato.parse("0000-00-00"));
            prestamo1.setActivo(true);

            List<Object> entities = Arrays.asList(persona1, persona2, persona3, persona4, persona5, usuario1, usuario2, usuario3, usuario4, usuario5, libro1, libro2, libro3, libro4, libro5, prestamo1, prestamo2, prestamo3, prestamo4, prestamo5);

            for (Object entity : entities) {
                session.persist(entity);
            }

            session.getTransaction().commit();
            dataInit = true;
            System.out.println("Los datos has sido agregados correctamente");
        }
        catch (HibernateException error) {
            System.out.println("El error es" + error.getMessage());
        } finally {
            session.close();
        }
    }
}
