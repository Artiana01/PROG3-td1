package prog3.example.prog3;

import java.util.UUID;

public class Author {
    private String id;
    private String name;
    private String sex;

    public Author(String name, String sex, String male) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


}
