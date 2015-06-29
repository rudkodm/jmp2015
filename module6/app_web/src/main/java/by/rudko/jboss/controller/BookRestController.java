package by.rudko.jboss.controller;

import by.rudko.jboss.model.Book;
import by.rudko.jboss.repository.BookRepository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.*;
import java.util.Collection;

/**
 * Created by rudkodm on 6/21/15.
 */

@Path("/books")
@Produces({ "application/json" })
public class BookRestController {

    @Inject
    @Named("real")
    private BookRepository bookRepository;

    @GET
    @Path("/")
    public Collection<Book> getAll() {
        return bookRepository.getAll();
    }

    @GET
    @Path("/{isbn}")
    public Book getBook(@PathParam("isbn") String isbn) {
        return bookRepository.getBook(isbn);
    }
    
    @POST @Consumes("application/json")
    @Path("/")
    public Book addBook( Book book) {
    	
    	bookRepository.addBook(book);
    	return book;
    }
    
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
