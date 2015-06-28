package by.rudko.jboss.webservice;

import by.rudko.jboss.model.Book;
import by.rudko.jboss.repository.BookRepository;

import javax.inject.Inject;
import javax.jws.WebService;
import java.util.Collection;

/**
 * Created by rudkodm on 6/22/15.
 */
@WebService(
        serviceName = "BookService",
        name = "BookService",
        endpointInterface = "by.rudko.jboss.webservice.BookService",
        targetNamespace = "http://by.rudko.jboss/ws/books"
)
public class BookServiceImpl implements BookService {

    @Inject
    private BookRepository bookRepository;

    @Override
    public Book getBook(String isbn) {
        return bookRepository.getBook(isbn);
    }

    @Override
    public Collection<Book> getAll() {
        return bookRepository.getAll();
    }

    public void setBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
