package iuh.week01_lab_huynhhoangphuc_21036541.repositories;

import iuh.week01_lab_huynhhoangphuc_21036541.entities.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleRepository implements IRepository<Role> {
   private EntityManager em;

   EntityTransaction transaction = null;

   public RoleRepository() {
      em = Persistence.createEntityManagerFactory("default").createEntityManager();
      transaction = em.getTransaction();
   }

   @Override
   public boolean add(Role role) {
      try {
         transaction.begin();
         em.persist(role);
         transaction.commit();
         return true;
      } catch (Exception e) {
         e.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean update(Role role) {
      try {
         transaction.begin();
         em.merge(role);
         transaction.commit();
         return true;
      } catch (Exception e) {
         e.printStackTrace();
         return false;
      }
   }

   @Override
   public boolean delete(String id) {
      try {
         transaction.begin();
         em.remove(em.find(Role.class, id));
         transaction.commit();
         return true;
      } catch (Exception e) {
         e.printStackTrace();
         return false;
      }
   }

   @Override
   public Optional<Role> find(String id) {
      return Optional.ofNullable(em.find(Role.class, id));
   }

   @Override
   public Set<Role> findAll() {
      return em.createNamedQuery("Role.findAll", Role.class)
                   .getResultStream()
                   .collect(Collectors.toSet());
   }
}
