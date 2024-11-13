package iuh.week06_lab_huynhhoangphuc_21036541.repositories;

import iuh.week06_lab_huynhhoangphuc_21036541.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMobile(String mobile);
    boolean existsByMobile(String mobile);
    Optional<User> findByLastName(String lastname);

}