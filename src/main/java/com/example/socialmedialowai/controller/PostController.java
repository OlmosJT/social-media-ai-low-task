package com.example.socialmedialowai.controller;

import com.example.socialmedialowai.dto.request.PostCreationDTO;
import com.example.socialmedialowai.dto.response.PostDTO;
import com.example.socialmedialowai.mapper.PostMapper;
import com.example.socialmedialowai.model.Post;
import com.example.socialmedialowai.service.PostService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public PostDTO createPost(@Validated @RequestBody PostCreationDTO requestDTO) {
        Post post = postService.createPost(requestDTO);
        return PostMapper.map(post);
    }

    @GetMapping("/all")
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return PostMapper.map(posts);
    }

    @GetMapping()
    public List<PostDTO> getAllUserPosts(@RequestParam Long userId) {
        List<Post> posts = postService.getAllPostsByUserId(userId);
        return PostMapper.map(posts);
    }

    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return PostMapper.map(post);
    }

    @PutMapping("/{id}")
    public PostDTO updatePost(@PathVariable(name = "id") Long postId, @RequestBody PostDTO requestDTO) {
        Post post = postService.updatePost(postId, requestDTO);
        return PostMapper.map(post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @PostMapping("/{postId}/likes")
    public PostDTO likePost(@PathVariable Long postId, @RequestParam Long userId) {
        Post post = postService.likePost(postId, userId);
        return PostMapper.map(post);
    }

    @DeleteMapping("/{postId}/likes")
    public PostDTO unlikePost(@PathVariable Long postId, @RequestParam Long userId) {
        Post post = postService.unlikePost(postId, userId);
        return PostMapper.map(post);
    }
}
