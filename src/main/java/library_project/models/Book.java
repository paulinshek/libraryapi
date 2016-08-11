package library_project.models;

public class Book {

    private final long id;
    private final String content;

    public Book(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
