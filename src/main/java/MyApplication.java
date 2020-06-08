import domain.models.Order;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import java.sql.*;

@ApplicationPath("/")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        Order order = new Order();
        order.getBooks();



        packages("controllers");
        packages("filters");
    }
}
