package iuh.week06_lab_huynhhoangphuc_21036541.repositories;

import iuh.week06_lab_huynhhoangphuc_21036541.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}