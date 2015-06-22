package by.rudko.jboss.controller;

import by.rudko.jboss.model.WsBook;

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
    private static final Map<String, WsBook> bookRepository = new HashMap<>();
    static {
        bookRepository.put("1", new WsBook("1", "Book1"));
        bookRepository.put("2", new WsBook("2", "Book2"));
        bookRepository.put("3", new WsBook("3", "Book3"));
        bookRepository.put("4", new WsBook("4", "Book4"));
        bookRepository.put("5", new WsBook("5", "Book5"));
        bookRepository.put("6", new WsBook("6", "Book6"));
    }


    @GET
    @Path("/")
    public Collection<WsBook> getAll() {
        return bookRepository.values();
    }

    @GET
    @Path("/{isbn}")
    public WsBook getBook(@PathParam("isbn") String isbn) {
        return bookRepository.get(isbn);
    }

    @PUT
    @Path("/{isbn}")
    public WsBook addBook(@QueryParam("isbn") String isbn,
                        @QueryParam("name") String name) {
        return bookRepository.put(isbn, new WsBook(isbn, name));
    }

    @DELETE
    @Path("/{isbn}")
    public WsBook removeBook(@QueryParam("isbn") String isbn){
        return bookRepository.remove(isbn);
    }
}
