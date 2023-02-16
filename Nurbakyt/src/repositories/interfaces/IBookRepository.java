package repositories.interfaces;

import entities.Book;

import java.util.List;

public interface IBookRepository {
    List<Book> getAllBooks();
}
