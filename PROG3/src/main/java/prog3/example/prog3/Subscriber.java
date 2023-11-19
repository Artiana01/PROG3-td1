package prog3.example.prog3;

import java.util.List;

public class Subscriber extends User {

    public Subscriber(String username, String password, String s, List<String> roles) {
        super(username, password, roles);
    }

    // Ajoutez une méthode getId() pour récupérer l'ID
    public String getId() {
        return super.getId();
    }
}
