package controllers;

import entities.Book;
import entities.User;
import repositories.interfaces.IBookRepository;

import java.util.ArrayList;
import java.util.List;

public class BookController {
    private final IBookRepository bookRepository;
    public BookController(IBookRepository bookRepository){
        this.bookRepository = bookRepository;
    }
    public String getAllBooks(){
        List<Book> books = bookRepository.getAllBooks();
        if (books.size() == 0)
            return "There is no any books!";
        return books.toString();
    }
    public Book FindBook(int id){
        List<Book> books = bookRepository.getAllBooks();
        Book buyBook = new Book();
        for (Book book : books) {
            if (book.getId() == id){
                buyBook = book;
                break;
            }
        }
        return buyBook;
    }

}
