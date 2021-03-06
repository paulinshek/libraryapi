package library_project.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    private long id;
    private String isbn;
    private String title;
    private String author;
    private String publishDate;

    public Book(String isbn, String title, String author, String publishDate) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
    }

    public Book(long id, String isbn, String title, String author, String publishDate) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
    }

    public Book() {}

    @Id
    @GeneratedValue(generator="identity")
    @GenericGenerator(name="identity", strategy="identity")
    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
