package by.rudko.jboss.repository;

import by.rudko.jboss.model.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Collection;

/**
 * Created by rudkodm on 6/29/15.
 */
@ApplicationScoped
@Named("real")
public class BookRepositoryImpl implements BookRepository {
    @Override
    public Collection<Book> getAll() {
        return null;
    }

    @Override
    public Book getBook(String isbn) {
        return null;
    }

    @Override
    public Book addBook(Book book) {
        return null;
    }

    @Override
    public Book removeBook(String isbn) {
        return null;
    }
}
