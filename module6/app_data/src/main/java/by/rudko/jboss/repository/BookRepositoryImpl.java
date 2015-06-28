package by.rudko.jboss.repository;

import by.rudko.jboss.model.Book;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by rudkodm on 6/29/15.
 */
@ApplicationScoped
public class BookRepositoryImpl implements BookRepository{
    private HashMap<String, Book> data = new HashMap();
    {
        data.put("1", new Book("1", "Book1"));
        data.put("2", new Book("2", "Book2"));
        data.put("3", new Book("3", "Book3"));
        data.put("4", new Book("4", "Book4"));
        data.put("5", new Book("5", "Book5"));
        data.put("6", new Book("6", "Book6"));
        data.put("7", new Book("7", "Book7"));
    }
    @Override
    public Collection<Book> getAll() {
        return data.values();
    }

    @Override
    public Book getBook(String isbn) {
        return data.get(isbn);
    }

    @Override
    public Book addBook(Book book) {
        return data.put(book.getIsbn(), book);
    }

    @Override
    public Book removeBook(String isbn) {
        return data.remove(isbn);
    }
}
