package library_project.repos;

import java.util.Iterator;

/**
 * Created by pshek on 11/08/2016.
 */
public interface Repository<E> {
    E get(long id);
    Iterator<E> getAll();
    void add(E entity);
    void remove(long id);
}
