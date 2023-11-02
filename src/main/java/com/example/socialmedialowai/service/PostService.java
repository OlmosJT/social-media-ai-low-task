package com.example.socialmedialowai.service;

import com.example.socialmedialowai.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    List<Post> getAllPostsByUserId(Long userId);
    Post getPostById(Long id);
    Post createPost(Post post);
    void updatePost(Post post);
    void deletePost(Long id);
}
