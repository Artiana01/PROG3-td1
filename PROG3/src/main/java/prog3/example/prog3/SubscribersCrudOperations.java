package prog3.example.prog3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubscribersCrudOperations implements CrudOperations<Subscriber> {
    private Connection connection;

    // Le constructeur prend une connexion en paramètre
    public SubscribersCrudOperations(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Subscriber> findAll() {
        List<Subscriber> subscribersList = new ArrayList<>();
        String query = "SELECT * FROM subscribers";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Subscriber subscribers = new Subscriber(
                        resultSet.getString("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),  // Utilisez la valeur réelle du mot de passe
                        Collections.singletonList(resultSet.getString("role"))
                );


                subscribersList.add(subscribers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subscribersList;
    }

    @Override
    public List<Subscriber> saveAll(List<Subscriber> toSave) {
        String query = "INSERT INTO subscribers (username, password, role) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Subscriber subscriber : toSave) {
                preparedStatement.setString(1, subscriber.getUsername());
                preparedStatement.setString(2, subscriber.getPassword());

                // Vérifiez si la liste des rôles n'est pas vide avant de l'utiliser
                if (!subscriber.getRoles().isEmpty()) {
                    preparedStatement.setString(3, subscriber.getRoles().get(0));
                } else {
                    // Gérez le cas où la liste des rôles est vide
                    preparedStatement.setString(3, null);  // Ou toute autre valeur par défaut
                }

                preparedStatement.addBatch();
            }



            preparedStatement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return toSave;
    }

    @Override
    public Subscriber save(Subscriber toSave) {
        String query = "INSERT INTO subscribers (username, password, role) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, toSave.getUsername());
            preparedStatement.setString(2, toSave.getPassword());
            preparedStatement.setString(3, toSave.getRoles().get(0));


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return toSave;
    }

    @Override
    public Subscriber delete(Subscriber toDelete) {
        String query = "DELETE FROM subscribers WHERE username = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, toDelete.getUsername());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return toDelete;
    }
}
