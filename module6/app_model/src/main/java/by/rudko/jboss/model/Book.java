package by.rudko.jboss.model;

/**
 * Created by rudkodm on 6/21/15.
 */
public class Book {

    public Book(){}

    public Book(String isbn, String name) {
        this.isbn = isbn;
        this.name = name;
    }

    private String isbn;
    private String name;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
