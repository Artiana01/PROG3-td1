package prog3.example.prog3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorCrudOperations implements CrudOperations<Author> {
    private Connection connection;

    // Le constructeur prend une connexion en paramètre
    public AuthorCrudOperations(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        String query = "SELECT * FROM author";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // Construisez l'objet Author à partir des résultats de la requête
                Author author = new Author(
                        resultSet.getString("name"),
                        resultSet.getString("sex"),
                        "Male");

                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authors;
    }

    @Override
    public List<Author> saveAll(List<Author> toSave) {
        String query = "INSERT INTO author (name, sex) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Author author : toSave) {
                preparedStatement.setString(1, author.getName());
                preparedStatement.setString(2, author.getSex());

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
    public Author save(Author toSave) {
        String query = "INSERT INTO author (name, sex) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, toSave.getName());
            preparedStatement.setString(2, toSave.getSex());

            // Exécutez la requête d'insertion
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérez l'exception selon vos besoins (journalisation, etc.)
        }

        return toSave;
    }

    @Override
    public Author delete(Author toDelete) {
        String query = "DELETE FROM author WHERE name = ? AND sex = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, toDelete.getName());
            preparedStatement.setString(2, toDelete.getSex());

            // Exécutez la requête de suppression
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Gérez l'exception selon vos besoins (journalisation, etc.)
        }

        return toDelete;
    }
}