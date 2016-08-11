package library_project.Repos;

import java.util.Iterator;

/**
 * Created by pshek on 11/08/2016.
 */
public interface Repository<E> {
    E get(int id) throws NoEntityException;
    Iterator<E> getAll();
    void add(E entity);
    void remove(int id) throws NoEntityException;
}
