package prog3.example.prog3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookCrudOperations implements CrudOperations<Book> {
    private Connection connection;

    // Le constructeur prend une connexion en paramètre
    public BookCrudOperations(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM book";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // Construisez l'objet Book à partir des résultats de la requête
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("book_name"),
                        resultSet.getInt("page_numbers"),

                        resultSet.getString("release_date")
                );

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    @Override
    public List<Book> saveAll(List<Book> toSave) {
        String query = "INSERT INTO book (id, book_name, page_numbers, release_date) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Book book : toSave) {
                preparedStatement.setInt(1, book.getId());
                preparedStatement.setString(2, book.getBookName());
                preparedStatement.setInt(3, book.getPageNumbers());
                preparedStatement.setString(4, book.getReleaseDate());

                preparedStatement.addBatch();
            }

            // Exécutez le batch (insertion en bloc)
            preparedStatement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérez l'exception selon vos besoins (journalisation, etc.)
        }

        return toSave;
    }

    @Override
    public Book save(Book toSave) {
        String query = "INSERT INTO book (id, book_name, page_numbers, release_date) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, toSave.getId());
            preparedStatement.setString(2, toSave.getBookName());
            preparedStatement.setInt(3, toSave.getPageNumbers());
            preparedStatement.setString(4, toSave.getReleaseDate());

            // Exécutez la requête d'insertion
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérez l'exception selon vos besoins (journalisation, etc.)
        }

        return toSave;
    }


    @Override
    public Book delete(Book toDelete) {
        String query = "DELETE FROM book WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, toDelete.getId());

            // Exécutez la requête de suppression
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérez l'exception selon vos besoins (journalisation, etc.)
        }

        return toDelete;
    }

}

