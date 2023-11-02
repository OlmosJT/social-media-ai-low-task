package com.example.socialmedialowai.repository;

import com.example.socialmedialowai.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findPostByAuthor_Id(Long authorId);
    List<Post> findAllByAuthor_Id(Long authorId);

}
