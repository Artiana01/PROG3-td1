package prog3.example.prog3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prog3.example.prog3.Author;
import prog3.example.prog3.AuthorCrudOperations;
import prog3.example.prog3.BookCrudOperations;
import prog3.example.prog3.Subscriber;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Connection connection = createConnection("jdbc:postgresql://localhost:5432/library_management", "prog_admin", "123456");

        testSubscribersCrudOperations(connection);

        // Testez les opérations CRUD pour les auteurs (authors)
        testAuthorCrudOperations(connection);

        // Testez les opérations CRUD pour les livres (books)
        testBookCrudOperations(connection);

        // Fermez la connexion à la base de données
        closeConnection(connection);
    }

    private static Connection createConnection(String url, String user, String password) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            logger.info("Connected to the database");
        } catch (SQLException e) {
            logger.error("Failed to connect to the database", e);
        }
        return connection;
    }

    private static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Connection closed");
            } catch (SQLException e) {
                logger.error("Error closing the database connection", e);
            }
        }
    }
    private static void testSubscribersCrudOperations(Connection connection) {
        SubscribersCrudOperations subscribersCrudOperations = new SubscribersCrudOperations(connection);

        // Testez la méthode findAll pour les abonnés
        List<Subscriber> allSubscribers = subscribersCrudOperations.findAll();
        for (Subscriber subscriber : allSubscribers) {
            logger.info("Subscriber: ID={}, Username={}, Roles={}", subscriber.getId(), subscriber.getUsername(), subscriber.getRoles());
        }

        // Testez la méthode save pour les abonnés
        Subscriber newSubscriber = new Subscriber("new_subscriber_id", "New Subscriber", "password", Collections.singletonList("ROLE_USER"));
        subscribersCrudOperations.save(newSubscriber);
        logger.info("New Subscriber added: ID={}, Username={}, Roles={}", newSubscriber.getId(), newSubscriber.getUsername(), newSubscriber.getRoles());

        // Testez la méthode findAll après l'ajout
        allSubscribers = subscribersCrudOperations.findAll();
        for (Subscriber subscriber : allSubscribers) {
            logger.info("Subscriber: ID={}, Username={}, Roles={}", subscriber.getId(), subscriber.getUsername(), subscriber.getRoles());
        }

        // Testez la méthode delete pour les abonnés
        subscribersCrudOperations.delete(newSubscriber);
        logger.info("Subscriber deleted: ID={}, Username={}, Roles={}", newSubscriber.getId(), newSubscriber.getUsername(), newSubscriber.getRoles());

        // Testez la méthode findAll après la suppression
        allSubscribers = subscribersCrudOperations.findAll();
        for (Subscriber subscriber : allSubscribers) {
            logger.info("Subscriber: ID={}, Username={}, Roles={}", subscriber.getId(), subscriber.getUsername(), subscriber.getRoles());
        }
    }



    private static void testAuthorCrudOperations(Connection connection) {
        AuthorCrudOperations authorCrudOperations = new AuthorCrudOperations(connection);

        // Testez la méthode findAll pour les auteurs
        List<Author> allAuthors = authorCrudOperations.findAll();
        for (Author author : allAuthors) {
            logger.info("Author: ID={}, Name={}, Gender={}", author.getId(), author.getName(), author.getSex());
        }

        // Testez la méthode save pour les auteurs
        Author newAuthor = new Author("new_author_id", "New Author", "Male");
        authorCrudOperations.save(newAuthor);
        logger.info("New Author added: ID={}, Name={}, Gender={}", newAuthor.getId(), newAuthor.getName(), newAuthor.getSex());

        // Testez la méthode findAll après l'ajout
        allAuthors = authorCrudOperations.findAll();
        for (Author author : allAuthors) {
            logger.info("Author: ID={}, Name={}, Gender={}", author.getId(), author.getName(), author.getSex());
        }

        // Testez la méthode delete pour les auteurs
        authorCrudOperations.delete(newAuthor);
        logger.info("Author deleted: ID={}, Name={}, Gender={}", newAuthor.getId(), newAuthor.getName(), newAuthor.getSex());

        // Testez la méthode findAll après la suppression
        allAuthors = authorCrudOperations.findAll();
        for (Author author : allAuthors) {
            logger.info("Author: ID={}, Name={}, Gender={}", author.getId(), author.getName(), author.getSex());
        }
    }



    private static void testBookCrudOperations(Connection connection) {
        BookCrudOperations bookCrudOperations = new BookCrudOperations(connection);

        // Testez la méthode findAll pour les livres
        List<prog3.example.prog3.Book> allBooks = bookCrudOperations.findAll();
        for (prog3.example.prog3.Book book : allBooks) {
            logger.info("Book: ID={}, Title={}, Author={}, Publication Year={}",
                    book.getId(), book.getTitle(), book.getAuthor(), book.getPublicationYear());
        }

        // Testez la méthode save pour les livres
        prog3.example.prog3.Book newBook = new prog3.example.prog3.Book();
        bookCrudOperations.save(newBook);
        logger.info("New Book added: ID={}, Title={}, Author={}, Publication Year={}",
                newBook.getId(), newBook.getTitle(), newBook.getAuthor(), newBook.getPublicationYear());

        // Testez la méthode findAll après l'ajout
        allBooks = bookCrudOperations.findAll();
        for (prog3.example.prog3.Book book : allBooks) {
            logger.info("Book: ID={}, Title={}, Author={}, Publication Year={}",
                    book.getId(), book.getTitle(), book.getAuthor(), book.getPublicationYear());
        }

        // Testez la méthode delete pour les livres
        bookCrudOperations.delete(newBook);
        logger.info("Book deleted: ID={}, Title={}, Author={}, Publication Year={}",
                newBook.getId(), newBook.getTitle(), newBook.getAuthor(), newBook.getPublicationYear());

        // Testez la méthode findAll après la suppression
        allBooks = bookCrudOperations.findAll();
        for (prog3.example.prog3.Book book : allBooks) {
            logger.info("Book: ID={}, Title={}, Author={}, Publication Year={}",
                    book.getId(), book.getTitle(), book.getAuthor(), book.getPublicationYear());
        }
    }

}
