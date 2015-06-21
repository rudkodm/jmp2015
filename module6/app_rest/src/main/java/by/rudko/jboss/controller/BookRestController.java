package by.rudko.jboss.controller;

import by.rudko.jboss.model.Book;

import javax.ws.rs.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rudkodm on 6/21/15.
 */

@Path("/books")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public class BookRestController {
    private static final Map<String, Book> bookRepository = new HashMap<>();
    static {
        bookRepository.put("1", new Book("1", "Book1"));
        bookRepository.put("2", new Book("2", "Book2"));
        bookRepository.put("3", new Book("3", "Book3"));
        bookRepository.put("4", new Book("4", "Book4"));
        bookRepository.put("5", new Book("5", "Book5"));
        bookRepository.put("6", new Book("6", "Book6"));
    }


    @GET
    @Path("/")
    public Collection<Book> getAll() {
        return bookRepository.values();
    }

    @GET
    @Path("/{isbn}")
    public Book getBook(@PathParam("isbn") String isbn) {
        return bookRepository.get(isbn);
    }

    @PUT
    @Path("/{isbn}")
    public Book addBook(@QueryParam("isbn") String isbn,
                        @QueryParam("name") String name) {
        return bookRepository.put(isbn, new Book(isbn, name));
    }

    @DELETE
    @Path("/{isbn}")
    public Book removeBook(@QueryParam("isbn") String isbn){
        return bookRepository.remove(isbn);
    }
}
