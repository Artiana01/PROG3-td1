package connexionDb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class connexion {
    @Bean
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                System.getenv("Url"),
                System.getenv("User"),
                System.getenv("Password")
        );


    }
}
