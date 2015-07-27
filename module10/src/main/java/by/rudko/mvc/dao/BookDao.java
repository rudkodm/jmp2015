package by.rudko.mvc.dao;

import java.util.Collection;

import by.rudko.mvc.model.Book;

public interface BookDao {
    Collection<Book> getAll();
    void addBook(Book book);
}
