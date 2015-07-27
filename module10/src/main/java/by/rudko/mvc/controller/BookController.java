package by.rudko.mvc.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.rudko.mvc.dao.BookDao;
import by.rudko.mvc.model.Book;

@Controller
public class BookController {

    private static final String ATR_BOOK_FORM = "book";
    private static final String ATR_ALL_BOOKS = "allBooks";
    private static final String PAGE_BOOKS = "books";
    
    
    @Autowired
    private BookDao bookDao;
    
    @RequestMapping(value = PAGE_BOOKS, method = RequestMethod.GET)
    public ModelAndView getAllBooks(){
        
        Collection<Book> books = bookDao.getAll();

        ModelAndView model = new ModelAndView(PAGE_BOOKS);
        model.addObject(ATR_ALL_BOOKS, books);
        model.addObject(ATR_BOOK_FORM, new Book());
       
        return model;
    }
    
    @RequestMapping(value = PAGE_BOOKS, method = RequestMethod.POST)
    public String addBook(@ModelAttribute Book book){
        bookDao.addBook(book);
        return "redirect:"+PAGE_BOOKS;
    }
    
    
}
