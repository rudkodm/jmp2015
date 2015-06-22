package by.rudko.jboss.webservice;

import by.rudko.jboss.model.WsBook;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Collection;

/**
 * Created by rudkodm on 6/22/15.
 */
@WebService(targetNamespace = "http://by.rudko.jboss/ws/books")
public interface BookService {

    @WebMethod
    public WsBook getBook(String isbn);

    @WebMethod
    public Collection<WsBook> getAll();
}
