package library_project.controllers;

import library_project.models.Book;

/**
 * Created by pshek on 12/08/2016.
 */
public class ReservationsController {

    public Iterable<Book> getAllAvailableBooks(){
        return null;
    }

    public Iterable<Book> getAllUnavailableBooks() {
        return null;
    }

    public Iterable<Book> getAllLateBooks() {
        return null;
    }

    // try to get a book out for 2 week period
    public void requestBook(int id) {

    }

    public boolean isOut(int id) {
        return true;
    }

   public void returnBook(int id) {

   }

}
