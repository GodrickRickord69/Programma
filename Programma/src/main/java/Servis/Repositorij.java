package Servis;

import java.util.List;

public interface Repositorij <T> {

    List<T> getAll();
    T getById(int id);
    int create(T item);
    int update(T item);
    void delete (int item);
}
