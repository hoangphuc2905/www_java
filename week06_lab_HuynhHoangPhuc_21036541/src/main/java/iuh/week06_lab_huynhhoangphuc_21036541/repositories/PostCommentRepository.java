package iuh.week06_lab_huynhhoangphuc_21036541.repositories;

import iuh.week06_lab_huynhhoangphuc_21036541.models.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    @Query("select p from PostComment p where p.post.id = ?1")
    List<PostComment> findByPostId(Long postId);
}