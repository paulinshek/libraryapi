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
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            StandardServiceRegistryBuilder.destroy(registry);
        }
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

    public Book getBook(long id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Book result = (Book) session.createQuery("from Book b where b.id=" + id).list().get(0);

        session.getTransaction().commit();
        session.close();
        return result;
    }

    public Reservation getReservation(long id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Reservation result = (Reservation) session.createQuery("from Reservation r where r.id=" + id).list().get(0);

        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void removeBook(long id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Book result = (Book) session.createQuery("from Book b where b.id=" + id).list().get(0);
        session.remove(result);

        session.getTransaction().commit();
        session.close();
    }

    public void removeReservation(long id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Reservation result = (Reservation) session.createQuery("from Reservation r where r.id=" + id).list().get(0);
        session.remove(result);

        session.getTransaction().commit();
        session.close();
    }

}
