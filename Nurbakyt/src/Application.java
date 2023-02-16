import controllers.BookController;
import controllers.BuyBookController;
import entities.Book;
import entities.User;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Application {
    private final BookController bookController;
    private final BuyBookController buyBookController;
    private final Scanner scanner;
    public Application(BookController bookController, BuyBookController buyBookController){
        this.bookController = bookController;
        this.buyBookController = buyBookController;
        scanner = new Scanner(System.in);
    }
    public void start(){
        System.out.println();
        System.out.println("You need register first");
        System.out.println("Please enter your name");
        String name = scanner.next();
        System.out.println("Please enter your surname");
        String surname = scanner.next();
        System.out.println("Please enter your balance");
        Double balance = scanner.nextDouble();

        User user = new User(name, surname, balance);
        System.out.println("User was created!");
        while (true){
            System.out.println();
            System.out.println("Welcome to our BookShop!");
            System.out.println("Select option:");
            System.out.println("1. Show my balance");
            System.out.println("2. List of all books");
            System.out.println("3. Buy a book");
            System.out.println("4. Show my list of books");
            System.out.println("5. Refund the book");
            System.out.println("0. Exit");
            try {
                System.out.print("Enter option (1-5): ");
                int option = scanner.nextInt();
                if (option == 1) {
                    showMyBalance(user);
                } else if(option == 2){
                    getAllBooksMenu();
                } else if(option == 3){
                    buyBookMenu(user);
                } else if(option == 4){
                    getBoughtBooks();
                } else if(option == 5){
                    refundBook(user);
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be integer");
                scanner.nextLine(); // to ignore incorrect input
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("___________________________________________");
        }
    }
    public void showMyBalance(User user){
        System.out.println("Your balance is: ");
        System.out.println(user.getBalance());
    }
    public void getAllBooksMenu(){
        String response = bookController.getAllBooks();
        System.out.println(response);
    }
    public void buyBookMenu(User user){
        getAllBooksMenu();
        System.out.println("Choose book id:");
        int book_id = scanner.nextInt();
        Book bookToBuy = bookController.FindBook(book_id);
        if (buyBookController.buyBook(bookToBuy, user))
            System.out.println("Successfully bought book: " + "<<" + bookToBuy.getName() + ">>" + bookToBuy.getAuthor());
        else System.out.println("You do not have enough money to buy a book " + "<<" + bookToBuy.getName() + ">>" + bookToBuy.getAuthor());
    }
    public void getBoughtBooks(){
        List<Book> boughtBooks = buyBookController.getBoughtBooks();
        if(boughtBooks.isEmpty()){
            System.out.println("Nothing to show, buy a book first.");
        }
        else {
            for (Book book : boughtBooks) {
                System.out.println(book.toString());
            }
        }
    }
    public void refundBook(User user){
        if(buyBookController.getBoughtBooks().isEmpty()){
            System.out.println("There is no book to refund.");
        }
        else {
            getBoughtBooks();
            System.out.println("Choose book id:");
            int book_id = scanner.nextInt();
            Book bookToRefund = bookController.FindBook(book_id);
            System.out.println(buyBookController.refundBook(bookToRefund, user)?
                    "Successfully refunded book: " +"<<" + bookToRefund.getName() + ">>" + bookToRefund.getAuthor():"Couldn't refund the book:"+bookToRefund);
        }
    }
}
