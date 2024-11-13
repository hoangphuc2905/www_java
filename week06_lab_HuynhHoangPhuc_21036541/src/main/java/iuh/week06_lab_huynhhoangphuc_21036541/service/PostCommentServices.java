package iuh.week06_lab_huynhhoangphuc_21036541.service;

import iuh.week06_lab_huynhhoangphuc_21036541.models.Post;
import iuh.week06_lab_huynhhoangphuc_21036541.models.PostComment;
import iuh.week06_lab_huynhhoangphuc_21036541.repositories.PostCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCommentServices {
    @Autowired
    private PostCommentRepository postCommentRepository;

    public List<PostComment> getCommentsByPost(Post post) {
        return postCommentRepository.findByPostId(post.getId());
    }
}
