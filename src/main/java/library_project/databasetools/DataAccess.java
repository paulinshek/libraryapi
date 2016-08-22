package library_project.databasetools;

import library_project.models.Book;
import library_project.models.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Iterator;

public class DataAccess {
    SessionFactory sessionFactory;

    public DataAccess() {
        // SessionFactory is set up once for an application

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        //try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        //}
        //catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            //StandardServiceRegistryBuilder.destroy( registry );
        //}
    }

    public void addBook(Book newBook) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(newBook);
        session.getTransaction().commit();
        session.close();
    }

    public void addReservation(Reservation newReservation) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(newReservation);
        session.getTransaction().commit();
        session.close();
    }

    public Iterator<Book> getAllBooks() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Iterator<Book> result = session.createQuery( "from Book" ).list().iterator();

        session.getTransaction().commit();
        session.close();

        return result;
    }

    public Iterator<Reservation> getAllReservations() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Iterator<Reservation> result = session.createQuery( "from Reservation" ).list().iterator();

        session.getTransaction().commit();
        session.close();

        return result;
    }

    public Book getBook(int id){
        return null;
    }

    public Reservation getReservation(int id){
        return null;
    }

    public void removeBook(int id){

    }

    public void removeReservation(int id){

    }

}
