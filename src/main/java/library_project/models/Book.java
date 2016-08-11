package library_project.models;

public class Book {

    private static int COUNT = 0;
    private int id;
    private String isbn;
    private String title;
    private String author;
    private String publishDate;

    public Book(String isbn, String title, String author, String publishDate) {
        this.id = getNextId();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
    }

    public Book() {
        this.id = 0;
        this.isbn = "";
        this.title = "";
        this.author = "";
        this.publishDate = "";
    }

    private int getNextId(){
        COUNT+= 1;
        return COUNT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
