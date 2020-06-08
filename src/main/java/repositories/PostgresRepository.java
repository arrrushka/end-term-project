package repositories;

import repositories.interfaces.IDBRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresRepository implements IDBRepository {

    @Override
    public Connection getConnection() {
        try {
            String connStr = "jdbc:postgresql://localhost:5432/postgres";
            return DriverManager.getConnection(connStr, "postgres", "280701");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
