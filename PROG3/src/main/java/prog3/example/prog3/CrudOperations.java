package prog3.example.prog3;

import java.util.List;

public interface CrudOperations<T> {

    List<T> findAll();

    List<T> saveAll(List<T> toSave);

    T save(T toSave);

    T delete(T toDelete);
}