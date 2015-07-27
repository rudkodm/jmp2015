package by.rudko.mvc.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import by.rudko.mvc.model.Book;

@Repository
public class DummyBookDao implements BookDao {
    private static final Map<String, Book> books = new HashMap<>();
    
    public DummyBookDao(){
        books.put("1", new Book("1", "Book1", "Description1"));
        books.put("2", new Book("2", "Book2", "Description2"));
        books.put("3", new Book("3", "Book3", "Description3"));
        books.put("4", new Book("4", "Book4", "Description4"));
        books.put("5", new Book("5", "Book5", "Description5"));
    }
    
    @Override
    public Collection<Book> getAll() {
        return books.values();
    }

    @Override
    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

}
