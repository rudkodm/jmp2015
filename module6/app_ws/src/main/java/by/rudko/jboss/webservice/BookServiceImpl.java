package by.rudko.jboss.webservice;

import by.rudko.jboss.model.WsBook;

import javax.jws.WebService;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rudkodm on 6/22/15.
 */
@WebService(
        serviceName = "BookService",
        name = "BookService",
        endpointInterface = "by.rudko.jboss.webservice.BookService",
        targetNamespace = "http://by.rudko.jboss/ws/books"
)
public class BookServiceImpl implements BookService{

    private static final Map<String, WsBook> bookRepository = new HashMap<>();
    static {
        bookRepository.put("1", new WsBook("1", "Book1"));
        bookRepository.put("2", new WsBook("2", "Book2"));
        bookRepository.put("3", new WsBook("3", "Book3"));
        bookRepository.put("4", new WsBook("4", "Book4"));
        bookRepository.put("5", new WsBook("5", "Book5"));
        bookRepository.put("6", new WsBook("6", "Book6"));
    }

    @Override
    public WsBook getBook(String isbn) {
        return new WsBook(isbn, "Book " + isbn);
    }

    @Override
    public Collection<WsBook> getAll() {
        return bookRepository.values();
    }
}
