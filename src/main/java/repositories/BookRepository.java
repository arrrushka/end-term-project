package repositories;

import domain.models.Book;
import jakarta.ws.rs.BadRequestException;
import repositories.interfaces.IDBRepository;
import repositories.interfaces.IEntityRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BookRepository implements IEntityRepository {
        private IDBRepository dbrepo = new PostgresRepository();


    @Override
    public void add(Book entity) {

        try {
            String sql = "INSERT INTO book(ISBN, book_name, page, price) " +
                    "VALUES(?, ?, ?, ?, ?, ?)";"VALUES('" +entity.getISBN() +"','"+ entity.getTitle() + "','"+ entity.getPage() +
                    "','"+ entity.getPrice()+"')";
            PreparedStatement stmt = dbrepo.getConnection().prepareStatement(sql);
            stmt.execute(sql);
        } catch (SQLException ex) {
            throw new BadRequestException("Cannot run SQL statement: " + ex.getMessage());
        }
    }
}
