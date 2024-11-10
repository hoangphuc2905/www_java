package iuh.week06_lab_huynhhoangphuc_21036541.repositories;

import iuh.week06_lab_huynhhoangphuc_21036541.models.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}