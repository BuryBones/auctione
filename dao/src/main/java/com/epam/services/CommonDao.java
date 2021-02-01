package main.java.com.epam.services;

import java.util.List;

public interface CommonDao<T> {

  T findById(int id);
  List<T> findAll();
  void save(T object);
  void update(T object);
  void delete(T object);

}
