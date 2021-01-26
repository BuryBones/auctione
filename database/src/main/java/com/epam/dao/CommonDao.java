package main.java.com.epam.dao;

import java.util.List;
import java.util.Optional;

public interface CommonDao<T> {

  Optional<T> findById(int id);
  List<T> findAll();
  void save(T object);
  void update(T object);
  void delete(T object);

}
