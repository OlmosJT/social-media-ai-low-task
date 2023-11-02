package com.example.socialmedialowai.service;

import com.example.socialmedialowai.dto.request.PostCreationDTO;
import com.example.socialmedialowai.dto.response.PostDTO;
import com.example.socialmedialowai.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    List<Post> getAllPostsByUserId(Long userId);
    Post getPostById(Long id);
    Post createPost(PostCreationDTO dto);
    Post updatePost(Long postId, PostDTO dto);
    void deletePost(Long id);
    Post likePost(Long postId, Long userId);
    Post unlikePost(Long postId, Long userId);
}
