package entities;

public class Book {
    private int id;
    private String name;

    private String author;
    private int year;
    private String description;
    private double price;
    public Book(){};
    public Book(int id, String name, String author, int year, String description, double price){
        setId(id);
        setName(name);
        setAuthor(author);
        setYear(year);
        setDescription(description);
        setPrice(price);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getYear() {
        return year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString(){
        return "\n" + id + " | " + name + " | " + author + " | " + year + "\n" +
                "Description of " + name + ": " + description + "\n" +
                "Price: " + price + "\n";
    }
}
