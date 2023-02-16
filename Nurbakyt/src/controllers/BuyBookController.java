package controllers;

import entities.Book;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class BuyBookController {
    private List<Book> boughtBooks = new ArrayList<>();
    public BuyBookController(){}

    public boolean buyBook(Book book, User user){
        if(book.getPrice()<= user.getBalance()) {
            user.pay(book.getPrice());
            this.boughtBooks.add(book);
            return true;
        }
        return false;
    }
    public boolean refundBook(Book bookToRefund, User user){
        boolean index = false;
        for (Book book : this.boughtBooks) {
            if (book.getId() == bookToRefund.getId()){
                index = true;
                break;
            }
        }
        if(index) {
            user.refund(bookToRefund.getPrice());
            List <Book> refundedBooks = new ArrayList<>();
            for (Book validBook : boughtBooks) {
                if (!(validBook.getId() == bookToRefund.getId())){
                    refundedBooks.add(bookToRefund);
                }
            }
            setBoughtBooks(refundedBooks);
            return true;
        }
        return false;
    }

    public void setBoughtBooks(List<Book> boughtBooks) {
        this.boughtBooks = boughtBooks;
    }

    public List<Book> getBoughtBooks() {
        return boughtBooks;
    }
}
