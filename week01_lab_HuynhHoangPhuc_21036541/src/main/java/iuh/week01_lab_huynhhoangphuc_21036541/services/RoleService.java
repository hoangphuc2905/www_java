package iuh.week01_lab_huynhhoangphuc_21036541.services;



import iuh.week01_lab_huynhhoangphuc_21036541.entities.Role;
import iuh.week01_lab_huynhhoangphuc_21036541.repositories.RoleRepository;

import java.util.Optional;
import java.util.Set;


public class RoleService {
   private RoleRepository roleRepository;

   public RoleService() {
      roleRepository = new RoleRepository();
   }

   public Set<Role> findAll() {
      return roleRepository.findAll();
   }

   public Optional<Role> findById(String id) {
      return roleRepository.find(id);
   }

   public boolean addRole(Role role) {
      return roleRepository.add(role);
   }

   public boolean updateRole(Role role) {
      return roleRepository.update(role);
   }

   public boolean deleteRole(String roleId) {
      return roleRepository.delete(roleId);
   }
}
