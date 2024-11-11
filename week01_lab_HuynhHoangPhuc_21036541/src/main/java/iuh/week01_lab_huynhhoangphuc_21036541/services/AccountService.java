package iuh.week01_lab_huynhhoangphuc_21036541.services;

import iuh.week01_lab_huynhhoangphuc_21036541.entities.Account;
import iuh.week01_lab_huynhhoangphuc_21036541.entities.GrantAccess;
import iuh.week01_lab_huynhhoangphuc_21036541.entities.GrantAccessId;
import iuh.week01_lab_huynhhoangphuc_21036541.repositories.AccountRepository;
import jakarta.inject.Named;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Named
public class AccountService {
   private final AccountRepository accountRepository;
   private final RoleService roleService;
   private final GrantAccessService grantAccessService;

   public AccountService() {
      accountRepository = new AccountRepository();
      roleService = new RoleService();
      grantAccessService = new GrantAccessService();
   }

   public Account checkLogin(String phone, String password) {
      if (phone == null || password == null || phone.trim().isEmpty() || password.trim().isEmpty()) {
         return null;
      }
      return accountRepository.checkLogin(phone, password);
   }

   public boolean add(Account account, String[] roles) {
      Set<GrantAccess> collect =
            Arrays.stream(roles)
                  .map(roleService::findById)
                  .filter(Optional::isPresent)
                  .map(Optional::get)
                  .map(role -> new GrantAccess(
                        new GrantAccessId(role.getRoleId(), account.getAccountId()),
                        role,
                        account,
                        true,
                        null)
                  )
                  .collect(Collectors.toSet());
      account.setGrantAccesses(collect);
      return accountRepository.add(account) && collect.stream().allMatch(grantAccessService::add);
   }

   public boolean update(Account account, String[] roles) {
      // Find all grant accesses of the account, then set to the account
      // Because the account not yet have grant accesses
      Set<GrantAccess> grantAccesses = grantAccessService.findByAccount(account.getAccountId());
      account.setGrantAccesses(grantAccesses);
//       Remove all grant accesses that are not in the new roles
      account.setGrantAccesses(
            account.getGrantAccesses().stream()
                  .filter(g -> Arrays.asList(roles).contains(g.getId().getRoleId()))
                  .collect(Collectors.toSet())
      );
      grantAccesses
            .stream()
            .filter(grantAccess -> !Arrays.asList(roles).contains(grantAccess.getId().getRoleId()))
            .forEach(grantAccess -> grantAccessService.delete(grantAccess));
      // Add new grant accesses
      Set<GrantAccess> collect =
            Arrays.stream(roles)
                  .filter(roleId -> grantAccesses.stream().noneMatch(grantAccess -> grantAccess.getId().getRoleId().equals(roleId)))
                  .map(roleService::findById)
                  .filter(Optional::isPresent)
                  .map(Optional::get)
                  .map(role -> new GrantAccess(
                        new GrantAccessId(role.getRoleId(), account.getAccountId()),
                        role,
                        account,
                        true,
                        null)
                  )
                  .collect(Collectors.toSet());

      account.getGrantAccesses().addAll(collect);
      return collect.stream().allMatch(grantAccessService::add) && accountRepository.update(account);
   }

   public boolean delete(String accountId) {
      return accountRepository.delete(accountId);
   }

   public Set<Account> findAll() {
      return accountRepository.findAll();
   }

   public Optional<Account> findById(String accountId) {
      return accountRepository.find(accountId);
   }

   public Set<Account> findByRole(String roleId) {
      return accountRepository.findByRole(roleId);
   }

}
