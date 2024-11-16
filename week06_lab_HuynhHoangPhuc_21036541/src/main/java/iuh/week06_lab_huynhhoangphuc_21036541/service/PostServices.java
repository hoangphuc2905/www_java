package iuh.week06_lab_huynhhoangphuc_21036541.service;


import iuh.week06_lab_huynhhoangphuc_21036541.models.Post;
import iuh.week06_lab_huynhhoangphuc_21036541.models.PostComment;
import iuh.week06_lab_huynhhoangphuc_21036541.repositories.PostCommentRepository;
import iuh.week06_lab_huynhhoangphuc_21036541.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServices {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostCommentRepository postCommentRepository;


    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post updatedPost) {
        if (postRepository.existsById(id)) {
            updatedPost.setId(id);
            return postRepository.save(updatedPost);
        }
        return null;
    }

    public List<Post> getPostsByUser(Long authorId) {
        return postRepository.findByAuthorId(authorId);
    }

     // getCommentsByPost
    public List<PostComment> getCommentsByPost(Post post) {
        return postCommentRepository.findByPostId(post.getId());
    }

    public void saveComment(PostComment postComment) {
        postCommentRepository.save(postComment);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
