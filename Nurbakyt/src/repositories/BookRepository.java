package repositories;

import data.interfaces.IDB;
import entities.Book;
import repositories.interfaces.IBookRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class BookRepository implements IBookRepository {
    private final IDB db;
    public BookRepository(IDB db){
        this.db = db;
    }
    public List<Book> getAllBooks(){
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT id,name,author,year,description,price FROM books";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Book> books = new LinkedList<>();
            while (rs.next()) {
                Book book = new Book(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getInt("year"),
                        rs.getString("description"),
                        rs.getDouble("price")
                );

                books.add(book);
            }

            return books;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}
