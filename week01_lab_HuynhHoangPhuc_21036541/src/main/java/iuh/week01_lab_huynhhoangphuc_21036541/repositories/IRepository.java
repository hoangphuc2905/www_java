package iuh.week01_lab_huynhhoangphuc_21036541.repositories;

import java.util.Optional;
import java.util.Set;

public interface IRepository<T> {
   boolean add(T t);

   boolean update(T t);

   boolean delete(String id);

   Optional<T> find(String id);

   Set<T> findAll();

}
