package by.rudko.jboss.repository;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import by.rudko.jboss.model.Book;

/**
 * Created by rudkodm on 6/29/15.
 */
@Stateless
@Named("real")

@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class BookRepositoryImpl implements BookRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
    @Override
    public Collection<Book> getAll() {
        return entityManager
        		.createQuery("SELECT b FROM Book b",Book.class)
                .getResultList();
    }

    @Override
    public Book getBook(String isbn) {
        return entityManager.find(Book.class, isbn);
    }

    @Override
    public void addBook(Book book) {
    	entityManager.persist(book);
    }

    @Override
    public void removeBook(Book book) {
       entityManager.remove(book);
    }
}
