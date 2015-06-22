package by.rudko.jboss.convertor;

import by.rudko.jboss.model.Book;
import by.rudko.jboss.model.WsBook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by rudkodm on 6/23/15.
 */
public class DomainModelConvertor {

    public static Collection<WsBook> convertBooks(Collection<Book> domainBooks) {
        if(domainBooks == null || domainBooks.isEmpty()){
            return Collections.emptyList();
        }

        Collection<WsBook> wsBooks = new ArrayList<>();
        for (Book domainBook : domainBooks) {
            WsBook wsBook = convertBook(domainBook);
            if(wsBook != null ){
                wsBooks.add(wsBook);
            }
        }
        return wsBooks;
    }

    public static WsBook convertBook(Book domainBook) {
        if(domainBook == null) {
            return null;
        }

        WsBook wsBook = new WsBook();
        wsBook.setIsbn(domainBook.getIsbn());
        wsBook.setName(domainBook.getName());
        return wsBook;
    }
}
