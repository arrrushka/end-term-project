package domain.models;

import java.sql.*;

public class Book  {
    private int ISBN;
    private String title;
    private double price;
    private int page;

    public Book() {

    }

    public Book(int ISBN, String title, double price, int page) {
        setISBN(ISBN);
        setPage(page);
        setPrice(price);
        setTitle(title);
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void printBooks() {
        try {
            String connStr = "jdbc:postgresql://localhost:5432/postgres";
            Connection conn = DriverManager.getConnection(connStr, "postgres", "280701");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM book");
            while(rs.next()) {
                System.out.println(rs.getString("book_name") + " " +
                        rs.getString("price"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Book {" +
                "ISBN: " + ISBN +
                ", title: '" + title + '\'' +
                ", price: '" + price + '\'' +
                '}';
    }
}
