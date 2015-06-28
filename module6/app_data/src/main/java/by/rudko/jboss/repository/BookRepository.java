package by.rudko.jboss.repository;

import by.rudko.jboss.model.Book;

import java.util.Collection;

/**
 * Created by rudkodm on 6/29/15.
 */
public interface BookRepository {
    public Collection<Book> getAll();

    public Book getBook(String isbn);

    public Book addBook(Book book);

    public Book removeBook(String isbn);
}
