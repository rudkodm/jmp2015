package by.rudko.jboss.repository;

import by.rudko.jboss.model.Book;

import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rudkodm on 6/29/15.
 */
@Stateless
@Named("dummy")
public class DummyBookRepository implements BookRepository {
	private Map<String, Book> data;

    public DummyBookRepository() {
        data = new HashMap<String, Book>();
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
	public void addBook(Book book) {
		data.put(book.getIsbn(), book);
	}

	@Override
	public void removeBook(Book book) {
		data.remove(book.getIsbn());
	}
}
