package iuh.week01_lab_huynhhoangphuc_21036541.services;

import iuh.week01_lab_huynhhoangphuc_21036541.entities.GrantAccess;
import iuh.week01_lab_huynhhoangphuc_21036541.repositories.GrantAccessRepository;


import java.util.Optional;
import java.util.Set;


public class GrantAccessService {
   private GrantAccessRepository grantAccessRepository;

   public GrantAccessService() {
      grantAccessRepository = new GrantAccessRepository();
   }

   public boolean add(GrantAccess grantAccess) {
      return grantAccessRepository.add(grantAccess);
   }

   public boolean update(GrantAccess grantAccess) {
      return grantAccessRepository.update(grantAccess);
   }

   public boolean delete(GrantAccess grantAccess) {
      return grantAccessRepository.delete(grantAccess);
   }

   public Optional<GrantAccess> findByAccountAndRole(String accountId, String roleId) {
      return grantAccessRepository.find(accountId, roleId);
   }

   public Set<GrantAccess> findByAccount(String accountId) {
      return grantAccessRepository.findByAccount(accountId);
   }
}
