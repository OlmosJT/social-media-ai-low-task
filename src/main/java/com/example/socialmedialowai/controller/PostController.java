package com.example.socialmedialowai.controller;

import com.example.socialmedialowai.dto.PostDTO;
import com.example.socialmedialowai.dto.PostRequest;
import com.example.socialmedialowai.model.Post;
import com.example.socialmedialowai.model.UserEntity;
import com.example.socialmedialowai.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public PostDTO createPost(@RequestBody PostRequest postRequest) {
        Post post = postService.createPost(postRequest);
        return new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getBody(),
                post.getAuthor().getUsername(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getLikedBy().stream().map(UserEntity::getUsername).collect(Collectors.toSet())
        );

    }

    @GetMapping("/{id}")
    public PostDTO getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getBody(),
                post.getAuthor().getUsername(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getLikedBy().stream().map(UserEntity::getUsername).collect(Collectors.toSet())
        );
    }

    @GetMapping()
    public List<PostDTO> getAllUserPosts(@RequestParam Long userId) {
        List<Post> posts = postService.getAllPostsByUserId(userId);
        return posts.stream().map(post -> new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getBody(),
                post.getAuthor().getUsername(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getLikedBy().stream().map(UserEntity::getUsername).collect(Collectors.toSet())

        )).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public PostDTO updatePost(@PathVariable Long id, @RequestBody PostRequest postRequest) {
        Post post = postService.updatePost(postRequest);
        return new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getBody(),
                post.getAuthor().getUsername(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getLikedBy().stream().map(UserEntity::getUsername).collect(Collectors.toSet())
        );
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }

    @PostMapping("/{postId}/likes")
    public PostDTO likePost(@PathVariable Long postId, @RequestParam Long userId) {
        Post post = postService.likePost(postId, userId);
        return new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getBody(),
                post.getAuthor().getUsername(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getLikedBy().stream().map(UserEntity::getUsername).collect(Collectors.toSet())
        );
    }

    // Unlike a post by a user
    @DeleteMapping("/{postId}/likes/{userId}")
    public PostDTO unlikePost(@PathVariable Long postId, @PathVariable Long userId) {
        Post post = postService.unlikePost(postId,userId);
        return new PostDTO(
                post.getId(),
                post.getTitle(),
                post.getBody(),
                post.getAuthor().getUsername(),
                post.getCreatedAt(),
                post.getUpdatedAt(),
                post.getLikedBy().stream().map(UserEntity::getUsername).collect(Collectors.toSet())
        );
    }
}
