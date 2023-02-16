import controllers.BookController;
import controllers.BuyBookController;
import data.PostgresDB;
import data.interfaces.IDB;
import repositories.BookRepository;
import repositories.interfaces.IBookRepository;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB();
        IBookRepository bookRepository = new BookRepository(db);
        BookController bookController = new BookController(bookRepository);
        BuyBookController buyBookController = new BuyBookController();
        Application app = new Application(bookController, buyBookController);
        app.start();
    }
}